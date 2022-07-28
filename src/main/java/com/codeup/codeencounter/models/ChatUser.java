package com.codeup.codeencounter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;


import javax.persistence.*;

@Entity
@Table(name = "chat_users")
public class ChatUser {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "name")
   private String name;

   @Column(name = "dp")
   private String dp;

   @Column(name = "email")
   private String email;

   @Column(name = "role")
   private String role;

   @OneToOne
   @JoinColumn(name = "user_id")
   private User user;

   public ChatUser() {
      super();
   }

   public ChatUser(Long id, String name, String dp, String email, String role, User user) {
      super();
      this.id = id;
      this.name = name;
      this.dp = dp;
      this.email = email;
      this.role = role;
      this.user = user;
   }

   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public void setEmail(String email) {
      this.email = email;
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