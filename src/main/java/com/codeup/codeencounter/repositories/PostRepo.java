package com.codeup.codeencounter.repositories;

import com.codeup.CodeEncounter.Models.Post;
import com.codeup.CodeEncounter.Models.User;
import com.codeup.CodeEncounter.Models.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository <Post, Long> {
    Post findById(long id);
    List<Post> findAllByUserUsername(String username);
    List<Post> findAllByUser(User user);
    Post findByBody(String body);
}
