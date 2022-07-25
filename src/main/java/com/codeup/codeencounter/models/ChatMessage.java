package com.codeup.codeencounter.models;


import javax.persistence.*;

@Entity
@Table(name = "userchat")
public class ChatMessage {
   @Id
   @Column(name = "id", nullable = false)
   private Long id;

   private MessageType type;
   private String content;
   private String sender;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public enum MessageType {
      CHAT,
      JOIN,
      LEAVE
   }

   public MessageType getType() {
      return type;
   }

   public void setType(MessageType type) {
      this.type = type;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getSender() {
      return sender;
   }

   public void setSender(String sender) {
      this.sender = sender;
   }
}
//   @Id
//   @GeneratedValue(strategy = GenerationType.AUTO)
//   private Long id;
//   @Column(name = "name")
//   private String name;
//   @Column(name = "dp")
//   private String dp;
//   @Column(name = "email")
//   private String email;
//   @Column(name = "role")
//   private String role;
//}
