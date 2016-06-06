package com.mkyong.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class EnrichedUser {
    private int userId;
    private String location;
    private int age;
    private Map<String,BookRating> associatedRating;

    public EnrichedUser() {
        associatedRating=new HashMap<String, BookRating>();

    }

    public EnrichedUser(int userId, String location, int age) {
        this.userId = userId;
        this.location = location;
        this.age = age;
        new HashMap<String, BookRating>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, BookRating> getAssociatedRating() {
        return associatedRating;
    }

    public void setAssociatedRating(Map<String, BookRating> associatedRating) {
        this.associatedRating = associatedRating;
    }
}
