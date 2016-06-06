package com.mkyong.Model;

import java.awt.geom.Arc2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class UserTasteProfile {
    int userID;
    double averageRating;
    int ratongOverZro;
    Map<Long,TagTaste> userTaste;

    public UserTasteProfile(int userID, double averageRating, int ratongOverZro, Map<Long, TagTaste> userTaste) {
        this.userID = userID;
        this.averageRating = averageRating;
        this.ratongOverZro = ratongOverZro;
        this.userTaste = userTaste;
    }

    public String encode(){
        String encoding="";
     Collection<TagTaste> taste= userTaste.values();
        Iterator<TagTaste> iter= taste.iterator();
        while(iter.hasNext()){
            TagTaste currentTaste= iter.next();
            encoding+=currentTaste.getTadId().toString()+":"+currentTaste.getTagTaste();
            if(iter.hasNext()){
                encoding+=';';
            }
        }



        return encoding;
    }
    public static UserTasteProfile decodeProfile(int userID,String encoding,double averageRating, int ratingOver0){
        if(encoding.equals("")){
            return null;
        }
        String[] tasteByTags= encoding.split(";");
        Map<Long,TagTaste> userTaste= new HashMap<Long, TagTaste>();
        for (int i=0;i<tasteByTags.length;i++){
            String taste=tasteByTags[i];
            String[] decodedTagTaste=taste.split(":");
            Long tagId=Long.parseLong(decodedTagTaste[0]);
            double value= Double.valueOf(decodedTagTaste[1]);
            userTaste.put(tagId,new TagTaste(tagId,value));

        }

        return new UserTasteProfile(userID,averageRating,ratingOver0,userTaste);
    }


    public int getUserID() {
        return userID;
    }

    public Map<Long, TagTaste> getUserTaste() {
        return userTaste;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatongOverZro() {
        return ratongOverZro;
    }
}
