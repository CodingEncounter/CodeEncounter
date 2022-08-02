package com.codeup.codeencounter.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @NotBlank(message = "Please include your first name.")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Please include your last name.")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Please include your email address.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    @Column(columnDefinition = "TEXT")
    private String codeLanguages;

    @NotBlank(message = "Please include a username.")
    @Column(nullable = false, unique = true)
    private String username;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column
    private String job;

    @Column(name = "dp")
    private String dp;

    @Column(name = "role")
    private String role;

    @Column
    private String profile_picture_url = "/img/blank-profile-picture.png";

    @NotBlank(message = "Please create a password.")
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friend")
    private List<UserFriend> userFriends;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Picture> pictures;



    @ManyToMany
    @JoinTable(name="user_interests",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="interest_id")})
    @OrderColumn(name="display_order")
    private Set<Interest> interests;



    public User(){}

    public User(String aboutMe, String codeLanguages, Date birthday, String email, String firstName, String password,
                String lastName, String job, String dp, String role, String profile_picture_url, String username,
                List<UserFriend> userFriends,  List<Picture> pictures, Date createdDate){
        this.birthday = birthday;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile_picture_url = profile_picture_url;
        this.userFriends = userFriends;
        this.job = job;
        this.dp = dp;
        this.role = role;
        this.username = username;
        this.pictures = pictures;
        this.password = password;
        this.aboutMe = aboutMe;
        this.codeLanguages = codeLanguages;
        this.createdDate = createdDate;
    }


    public User(long id, String aboutMe, String codeLanguages, Date birthday, String email, String firstName, String password,
                String lastName, String job, String dp, String role, String profile_picture_url, String username,
                List<UserFriend> userFriends, List <Picture> pictures, Date createdDate){
        this.aboutMe = aboutMe;
        this.codeLanguages = codeLanguages;
        this.birthday = birthday;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile_picture_url = profile_picture_url;
        this.userFriends = userFriends;
        this.job = job;
        this.dp = dp;
        this.role = role;
        this.username = username;
        this.pictures = pictures;
        this.id = id;
        this.password = password;
        this.createdDate = createdDate;
    }

    public User(User copy) {
        aboutMe = copy.aboutMe;
        codeLanguages = copy.codeLanguages;
        birthday = copy.birthday;
        email = copy.email;
        firstName = copy.firstName;
        lastName = copy.lastName;
        profile_picture_url = copy.profile_picture_url;
        userFriends = copy.userFriends;
        job = copy.job;
        dp = copy.dp;
        role = copy.role;
        username = copy.username;
        pictures = copy.pictures;
        id = copy.id;
        password = copy.password;
        createdDate = copy.createdDate;
    }

//    public User(Long id, String firstName, String dp, String email, String role) {
//        super();
//        this.id = id;
//        this.firstName = firstName;
//        this.dp = dp;
//        this.email = email;
//        this.role = role;

    public long getId(){return id;}
    public String getAboutMe(){return aboutMe;}
    public String getCodeLanguages(){return codeLanguages;}
    public Date getBirthday(){return birthday;}
    public String getEmail(){return email;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getUsername(){ return username;}
    public String getJob(){return job;}
    public String getProfile_picture_url(){return profile_picture_url;}
    public List<UserFriend> getUserFriends(){return userFriends;}
    public List<Picture> getPictures(){return pictures;}
    public String getPassword(){return password;}
    public Date getCreatedDate(){return createdDate;}

    public void setId(long id){this.id = id;}
    public void setAboutMe(String aboutMe){this.aboutMe = aboutMe;}
    public void setCodeLanguages(String codeLanguages){this.codeLanguages = codeLanguages;}
    public void setBirthday(Date birthday){this.birthday = birthday;}
    public void setEmail(String email){this.email = email;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setUsername(String username){ this.username = username;}
    public void setJob(String job){this.job = job;}
    public void setProfile_picture_url(String profile_picture_url){this.profile_picture_url = profile_picture_url;}
    public void setUserFriends(List<UserFriend> userFriends){this.userFriends = userFriends;}
    public void setPictures(List<Picture> pictures){this.pictures = pictures;}
    public void setPassword(String password){this.password = password;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}


