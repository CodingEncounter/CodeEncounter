package com.codeup.codeencounter.repositories;

import com.codeup.codeencounter.models.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetupRepo extends JpaRepository<Meetup, Long> {

}
