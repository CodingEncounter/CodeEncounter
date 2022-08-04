package com.codeup.codeencounter.controllers;

import com.codeup.codeencounter.models.Meetup;
import com.codeup.codeencounter.models.User;
import com.codeup.codeencounter.models.UserWithRoles;
import com.codeup.codeencounter.repositories.MeetupRepo;
import com.codeup.codeencounter.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class MeetupController {

    private UserRepo userRepo;
    private MeetupRepo meetupRepo;

    public MeetupController(UserRepo userRepo, MeetupRepo meetupRepo) {
        this.userRepo = userRepo;
        this.meetupRepo = meetupRepo;
    }

    @GetMapping("/meetup")
    public String showMeetups(Model model) {
        return "meetup/meetup";
    }

    @GetMapping("/meetup/create")
    public String createMeetup(Model model) {
        model.addAttribute("meetup", new Meetup());
        return "meetup/create-meetup";
    }

    @PostMapping("/meetup/create")
    public String submitMeetup(@ModelAttribute Meetup meetup, Model model) throws ParseException {
        String username = ((UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepo.findByUsername(username);
        String organizer = String.format("%s %s", user.getFirstName(), user.getLastName()).trim();
        meetup.setOrganizer(organizer);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        meetup.setDateTime(format.parse(meetup.getDateTimeString()));
        meetupRepo.save(meetup);
        return "meetup/meetup";
    }

}