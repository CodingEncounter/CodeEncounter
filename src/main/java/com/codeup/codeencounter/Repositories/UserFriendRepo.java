package com.codeup.CodeEncounter.Repositories;

import com.codeup.CodeEncounter.Models.Status;
import com.codeup.CodeEncounter.Models.User;
import com.codeup.CodeEncounter.Models.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFriendRepo extends JpaRepository <UserFriend, Long> {
    List<UserFriend> findAllByUser(User user);
    List<UserFriend> findAllByFriend(User friend);
    List<UserFriend> findAllByFriendAndStatus(User friend, Status status);
    List<UserFriend> findAllByUserAndStatus(User user, Status status);
    UserFriend findByUserAndFriend(User user, User friend);

    UserFriend findByUserAndFriendAndStatus(User user, User user1, Status status);
    UserFriend findByFriendAndUserAndStatus(User user, User user1, Status status);
}
