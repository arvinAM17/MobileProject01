package com.example.mobileproject01;

import java.io.Serializable;

public class Comment implements Serializable {
    int postId;
    int id;
    String name;
    String email;
    String body;

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getPostId() {
        return postId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
