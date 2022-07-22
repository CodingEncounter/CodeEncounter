package com.codeup.codeencounter.models;


import javax.persistence.*;

@Entity
@Table(name = "userchat")
public class UserChat {
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
}