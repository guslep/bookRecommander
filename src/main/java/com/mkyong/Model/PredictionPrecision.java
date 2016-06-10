package com.mkyong.Model;

/**
 * Created by Guillaume on 2016-06-07.
 */
public class PredictionPrecision {
    int userId;
    double precenseFor10;
    double precenseFor25;
    double presenceFor100;
    double percentPresent;
    int numberRecommended;
    int totalRated;
    double precision;

    public int getUserId() {
        return userId;
    }

    public void addNumberRecommended(){
        numberRecommended++;
    }
    public void addNumberRated(){
        totalRated++;
    }
    public void calculatePrecision(){
       precision= (double)numberRecommended /(double)totalRated;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public double getPrecision() {
        return precision;
    }

    public double getPrecenseFor10() {
        return precenseFor10;
    }

    public void setPrecenseFor10(double precenseFor10) {
        this.precenseFor10 = precenseFor10;
    }

    public double getPrecenseFor25() {
        return precenseFor25;
    }

    public void setPrecenseFor25(double precenseFor25) {
        this.precenseFor25 = precenseFor25;
    }

    public double getPresenceFor100() {
        return presenceFor100;
    }

    public void setPresenceFor100(double presenceFor100) {
        this.presenceFor100 = presenceFor100;
    }

    public double getPercentPresent() {
        return percentPresent;
    }

    public void setPercentPresent(double percentPresent) {
        this.percentPresent = percentPresent;
    }

    public int getNumberRecommended() {
        return numberRecommended;
    }

    public void setNumberRecommended(int numberRecommended) {
        this.numberRecommended = numberRecommended;
    }
}
