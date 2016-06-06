package com.mkyong.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class EnrichedBookRating {
    private User user;
    private Book book;
    private int rating;
    private String ISBN;
    private int userId;
    private List <Book> associatedBook;
    private List <User> associatedUser;

    public EnrichedBookRating() {
        associatedBook= new ArrayList<Book>();
        associatedUser= new ArrayList<User>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
