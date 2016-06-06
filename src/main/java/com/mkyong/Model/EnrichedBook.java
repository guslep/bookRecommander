package com.mkyong.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class EnrichedBook {

    private String ISBN;
    private String bookTitle;
    private String publicationYear;
    private String publisher;
    private List<Long> tags;
    private String author;
    private Map<String,BookRating> associatedRtings;

    public EnrichedBook() {
        associatedRtings=new HashMap<String, BookRating>();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public Map<String, BookRating> getAssociatedRtings() {
        return associatedRtings;
    }

    public void setAssociatedRtings(Map<String, BookRating> associatedRtings) {
        this.associatedRtings = associatedRtings;
    }
}
