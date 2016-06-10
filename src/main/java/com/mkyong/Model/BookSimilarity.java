package com.mkyong.Model;

/**
 * Created by Guillaume on 2016-06-09.
 */
public class BookSimilarity {
    EnrichedBook book1;
    EnrichedBook book2;
    Double similarity;

    public BookSimilarity(EnrichedBook book1, EnrichedBook book2, Double similarity) {
        this.book1 = book1;
        this.book2 = book2;
        this.similarity = similarity;
    }




    public EnrichedBook getBook1() {
        return book1;
    }

    public void setBook1(EnrichedBook book1) {
        this.book1 = book1;
    }

    public EnrichedBook getBook2() {
        return book2;
    }

    public void setBook2(EnrichedBook book2) {
        this.book2 = book2;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}
