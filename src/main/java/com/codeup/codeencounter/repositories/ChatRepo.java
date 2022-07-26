package com.codeup.codeencounter.repositories;

import com.codeup.codeencounter.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeup.codeencounter.models.ChatUser;

@Repository
public interface ChatRepo extends JpaRepository<ChatUser, Long>  {
}