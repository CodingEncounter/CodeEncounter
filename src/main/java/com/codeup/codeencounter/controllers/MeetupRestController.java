package com.codeup.codeencounter.controllers;

import com.codeup.codeencounter.models.Meetup;
import com.codeup.codeencounter.repositories.MeetupRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetupRestController {

    private MeetupRepo meetupRepo;

    public MeetupRestController(MeetupRepo meetupRepo) {
        this.meetupRepo = meetupRepo;
    }

    @GetMapping("/meetup/all")
    public List<Meetup> fetchMeetups() {
        return meetupRepo.findAll();
    }
}
