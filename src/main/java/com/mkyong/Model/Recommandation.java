package com.mkyong.Model;

/**
 * Created by Guillaume on 2016-05-26.
 */
public class Recommandation {
    private double recommandationRating;
    private String ISBN;
    private int userId;
    private int position;

    public Recommandation(double recommandationRating, String ISBN, int userId) {
        this.recommandationRating = recommandationRating;
        this.ISBN = ISBN;
        this.userId = userId;
    }

    public Recommandation() {
    }

    public double getRecommandationRating() {
        return recommandationRating;
    }

    public void setRecommandationRating(double recommandationRating) {
        this.recommandationRating = recommandationRating;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
