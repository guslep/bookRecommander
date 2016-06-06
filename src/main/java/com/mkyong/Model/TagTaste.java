package com.mkyong.Model;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class TagTaste {
    Long tadId;
    double tagTaste;

    public TagTaste(Long tadId, double tagTaste) {
        this.tadId = tadId;
        this.tagTaste = tagTaste;
    }

    public Long getTadId() {
        return tadId;
    }

    public void setTadId(Long tadId) {
        this.tadId = tadId;
    }

    public double getTagTaste() {
        return tagTaste;
    }

    public void setTagTaste(double tagTaste) {
        this.tagTaste = tagTaste;
    }
}
