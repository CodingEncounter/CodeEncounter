package com.codeup.codeencounter.repositories;


import com.codeup.codeencounter.models.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRepo extends JpaRepository<UserChat, Long> {

}