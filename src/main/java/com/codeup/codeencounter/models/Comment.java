package com.codeup.codeencounter.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(nullable = false)
    private String dateString;

    @Column(nullable = true)
    private String photo_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parentPost_id")
    private Post parentPost;

    @Column(nullable = true)
    private Date editDate;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Comment(){}

    public Comment(String body, Date createdDate, String photo_url, User user, Post parentPost, Date editDate, String dateString){
        this.body = body;
        this.dateString = dateString;
        this.editDate = editDate;
        this.createdDate = createdDate;
        this.photo_url = photo_url;
        this.user = user;
        this.parentPost = parentPost;
    }

    public Comment(long id, String body, Date createdDate, String photo_url, User user, Post parentPost, Date editDate, String dateString){
        this.body = body;
        this.dateString = dateString;
        this.editDate = editDate;
        this.createdDate = createdDate;
        this.photo_url = photo_url;
        this.user = user;
        this.id = id;
        this.parentPost = parentPost;
    }

    public String getBody(){ return body;}
    public Date getCreatedDate(){return createdDate;}
    public String getPhoto_url(){return photo_url;}
    public User getUser(){return user;}
    public long getId(){return id;}
    public Post getParentPost(){return parentPost;}
    public Date getEditDate(){return editDate;}
    public String getDateString(){return dateString;}

    public void setDateString(String dateString){this.dateString = dateString;}
    public void setBody(String body){ this.body = body;}
    public void setEditDate(Date editDate){this.editDate = editDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
    public void setPhoto_url(String photo_url){this.photo_url = photo_url;}
    public void setUser(User user){this.user = user;}
    public void setId(long id){this.id = id;}
    public void setParentPost(Post parentPost) {this.parentPost = parentPost;}
}