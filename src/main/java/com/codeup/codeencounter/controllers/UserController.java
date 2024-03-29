package com.codeup.codeencounter.controllers;

import com.codeup.codeencounter.repositories.*;
import com.codeup.codeencounter.models.*;
import com.codeup.codeencounter.services.*;
import com.codeup.codeencounter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import static javax.swing.JOptionPane.showMessageDialog;


import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {

    public UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    public UserFriendRepo userFriendRepo;
    public PostRepo postRepo;
    public CommentRepo commentRepo;
    public PictureRepo pictureRepo;
    public GalleryRepo galleryRepo;
    public InterestRepo interestRepo;
    private final EmailService emailService;

    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder,
                          UserFriendRepo userFriendRepo, PostRepo postRepo, CommentRepo commentRepo,
                          PictureRepo pictureRepo, GalleryRepo galleryRepo, InterestRepo interestRepo, EmailService emailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userFriendRepo = userFriendRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.galleryRepo = galleryRepo;
        this.pictureRepo = pictureRepo;
        this.emailService = emailService;
        this.interestRepo = interestRepo;

    }

    //Create User Account
    @GetMapping("/sign-up")
    public String showSignUp(Model model) {
        model.addAttribute("user", new User());

        return "user/register";
    }

    @PostMapping("/sign-up")
    public String registerUser(Model model,
                               @Valid User user,
                               Errors validation) {
        if (userRepo.findByEmail(user.getEmail()) != null){
            validation.rejectValue(
                    "email",
                    "user.email",
                    "This email is already in use."
            );
        }
        if (userRepo.findByUsername(user.getUsername()) != null){
            validation.rejectValue(
                    "username",
                    "user.username",
                    "This username is taken."
            );
        }
        if (user.getUsername().length() > 17 || user.getUsername().length() < 6) {
            validation.rejectValue(
                    "username",
                    "user.username",
                    "Please enter a username between 6 and 17 characters long."
            );
        }
        if (user.getFirstName().length() > 15 || user.getLastName().length() > 15) {
            validation.rejectValue(
                    "firstName",
                    "user.firstName",
                    "Please shorten the given name to less than 16 characters."
            );
        }
        if (validation.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("errors", validation);

            return "user/register";
        }
        //if registration details are valid:
        //creates default gallery
        String hash = passwordEncoder.encode(user.getPassword());
        Gallery newGallery = new Gallery();
        newGallery.setName("Photos");
        newGallery.setUser(user);
        newGallery.setCreatedDate(new Date());

        //creates default picture and profile picture
        Picture defaultPic = new Picture();
        defaultPic.setUser(user);
        defaultPic.setComment("Profile");
        defaultPic.setPictureUrl("/img/blank-profile-picture.png");
        defaultPic.setGallery(newGallery);

        //sets user details
        user.setPassword(hash);
        user.setCreatedDate(new Date());
        user.setProfile_picture_url("/img/blank-profile-picture.png");
        User dbUser = userRepo.save(user);
        galleryRepo.save(newGallery);
        pictureRepo.save(defaultPic);

        model.addAttribute("user", dbUser);

        //makes user friends with default users
//        UserFriend userFriend1 = new UserFriend();
//        userFriend1.setUser(user);
//        userFriend1.setFriend(userRepo.findByUsername("GinaCali92"));
//        userFriend1.setStatus(Status.ACCEPTED);
//        userFriendRepo.save(userFriend1);
//        UserFriend userFriend2 = new UserFriend();
//        userFriend2.setUser(user);
//        userFriend2.setFriend(userRepo.findByUsername("IronMaidenFangirl"));
//        userFriend2.setStatus(Status.ACCEPTED);
//        userFriendRepo.save(userFriend2);
//        UserFriend userFriend3 = new UserFriend();
//        userFriend3.setUser(user);
//        userFriend3.setFriend(userRepo.findByUsername("DanWasHere"));
//        userFriend3.setStatus(Status.ACCEPTED);
//        userFriendRepo.save(userFriend3);
//        UserFriend userFriend4 = new UserFriend();
//        userFriend4.setUser(user);
//        userFriend4.setFriend(userRepo.findByUsername("DragonSlayer95"));
//        userFriend4.setStatus(Status.ACCEPTED);
//        userFriendRepo.save(userFriend4);

//        emailService.prepareAndSend(user,"Welcome to CodeEncounter " + user.getUsername() + "!",
//                "We're glad to have you! You might notice that your newsfeed is a little quiet. We want to avoid the" +
//                        "drama that social media sites typically have of suggesting who you should be friends with or advertising" +
//                        "to you. You probably want to make that decision for yourself! Instead, why not invite invite your friends" +
//                        " to join, or send them a friend request if they " +
//                        "already have!");
        return "redirect:/login";
    }

    //Update User information
    @GetMapping("/edit/{username}")
    public String showEditProfile(Model model, @PathVariable String username) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "user/edit";
    }
    @PostMapping("/edit/{username}")
    public String editProfile(@PathVariable String username, @ModelAttribute User userToBeUpdated) {
        User user = userRepo.findByUsername(username);
        userToBeUpdated.setId(user.getId());
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setCreatedDate(user.getCreatedDate());
        userRepo.save(userToBeUpdated);

        return "redirect:/user/" + user.getUsername();
    }

    //Show User Profile page
    @GetMapping("/user/{username}")
    public String showUser(Model model,
                           @PathVariable String username) {
        User user = userRepo.findByUsername(username);
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserFriend> userFriends1 = userFriendRepo.findAllByUserAndStatus(user, Status.ACCEPTED);// lists friends who accepted you
        List<UserFriend> userFriends2 = userFriendRepo.findAllByFriendAndStatus(user, Status.ACCEPTED);// lists friends who you accepted

        //collects all accepted friendships involving the user
        ArrayList<User> displayUsers = new ArrayList<>();
        ArrayList<User> myFriends = new ArrayList<>();
        for (UserFriend userFriend : userFriends1) {
            displayUsers.add(userFriend.getFriend());
            myFriends.add(userFriend.getFriend());
        }
        for (UserFriend userFriend : userFriends2) {
            displayUsers.add(userFriend.getUser());
            myFriends.add(userFriend.getUser());
        }
        displayUsers.add(user);// includes your own posts in stories view

        ArrayList<Post> displayPosts = new ArrayList<>();// lists all posts by all friends and the user
        ArrayList<Comment> displayComments = new ArrayList<>();// lists all comments to all posts by all friends and user
        for (User displayUser : displayUsers) {
            for (Post post : postRepo.findAllByUser(displayUser)) {
                displayPosts.add(post);
                displayComments.addAll(commentRepo.findAllByParentPost(post));
            }
        }

        List<Comment> friendlyComments = new ArrayList<>(); //only show comments made by mutual friends
        for (Comment comment : displayComments) {
            if (userFriendRepo.findByUserAndFriendAndStatus(user, comment.getUser(), Status.ACCEPTED) != null){
                friendlyComments.add(comment);
            }
            if (userFriendRepo.findByFriendAndUserAndStatus(user, comment.getUser(), Status.ACCEPTED) != null){
                friendlyComments.add(comment);
            }
            if (comment.getUser() == user) {
                friendlyComments.add(comment);
            }
        }

        Collections.sort(displayPosts, (p1, p2) -> {//sort posts by date
            if (p1.getCreatedDate().after(p2.getCreatedDate())) return -1;
            else return 1;
        });

        List<UserFriend> userFriendRequests = userFriendRepo.findAllByFriendAndStatus(user, Status.PENDING);
        model.addAttribute("friendRequests", userFriendRequests);
        model.addAttribute("galleries", galleryRepo.findAllByUser(user));
        model.addAttribute("all-galleries", galleryRepo.findAll());
        model.addAttribute("user", user);

        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("friends",myFriends);
        model.addAttribute("comments", friendlyComments);
        model.addAttribute("posts", displayPosts);
        model.addAttribute("newPost", new Post());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("newGallery", new Gallery());
        model.addAttribute("interests", interestRepo);
        return "user/profile-page";
    }

    //Delete User account
    @PostMapping("/{username}/delete")
    public String deleteAccount(@PathVariable String username){
        User user = userRepo.findByUsername(username);
        List<Post> posts = postRepo.findAllByUserUsername(username);
        for (Post post : posts){
            postRepo.delete(post);
        }
        List<Comment> comments = commentRepo.findAllByUser(user);
        for (Comment comment : comments) {
            commentRepo.delete(comment);
        }
        List<Picture> pictures = pictureRepo.findAllByUser(user);
        for (Picture picture : pictures) {
            pictureRepo.delete(picture);
        }
        List<Gallery> galleries = galleryRepo.findAllByUser(user);
        for (Gallery gallery : galleries) {
            galleryRepo.delete(gallery);
        }
        List<UserFriend> userFriends1 = userFriendRepo.findAllByUser(user);
        List<UserFriend> userFriends2 = userFriendRepo.findAllByFriend(user);
        for (UserFriend userFriend : userFriends1){
            userFriendRepo.delete(userFriend);
        }
        for (UserFriend userFriend : userFriends2) {
            userFriendRepo.delete(userFriend);
        }

        userRepo.delete(user);

        return "redirect:/login?logout";
    }

    @RequestMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
            return "redirect:/user/" + auth.getName();

        return "index";
    }

    @GetMapping("/randomUsers/ajax")
    public String viewAllAdsWithAjax() {
        return "user/random";
    }

//    @PostMapping(value = "/createUser")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/getUser")
//    public ResponseEntity<User> getUser(@RequestParam(required = true) Long userId) {
//        return new ResponseEntity<>(userRepo.findById(userId).get(), HttpStatus.OK);
//    }
}

