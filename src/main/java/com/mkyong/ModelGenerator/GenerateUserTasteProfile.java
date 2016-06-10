package com.mkyong.ModelGenerator;

import com.mkyong.Model.*;
import service.BookService;
import service.UserService;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-22.
 */
public class GenerateUserTasteProfile {
    public  List<UserTasteProfile>  calculateUserProfile() {
        List<UserTasteProfile> profile= new ArrayList<UserTasteProfile>();
        Iterator<EnrichedUser> iter = UserService.getInstance().getEnrichedUsers().iterator();
        while (iter.hasNext()) {
            EnrichedUser user = iter.next();
            profile.add(computeUserProrfile(user));

        }
        return profile;
    }

    public  List<UserTasteProfile>  calculateUserProfile(List<EnrichedUser> userList) {
        List<UserTasteProfile> profile= new ArrayList<UserTasteProfile>();
        Iterator<EnrichedUser> iter = userList.iterator();
        while (iter.hasNext()) {
            EnrichedUser user = iter.next();
            profile.add(computeUserProrfile(user));

        }
        return profile;
    }


    private UserTasteProfile computeUserProrfile(EnrichedUser user) {

        Map<Long, TagTaste> taste = new HashMap<Long, TagTaste>();
        double avg=0.0;
        int nbOver0=0;

        Iterator<BookRating> iter = user.getAssociatedRating().values().iterator();
        Iterator<BookRating> iterAverage = user.getAssociatedRating().values().iterator();
        double average=0.0;
        int nb=0;
        while (iterAverage.hasNext()) {
            BookRating rating = iterAverage.next();
            if (rating.getRating() > 0){
                average +=rating.getRating();
                nb++;
            }

        }
        average=nbOver0==0?0:avg/nbOver0;


        while (iter.hasNext()) {
            BookRating rating = iter.next();
           Book book = BookService.getInstance().getBook(rating.getISBN());
            if(book !=null){
                List<Long> tags = BookService.getInstance().getBook(rating.getISBN()).getTags();
                if (rating.getRating() > 0 && tags!=null) {
                    avg+=rating.getRating();
                    nbOver0++;
                    for (Long tagId : tags) {
                        double enjoyment = (double) rating.getRating() / 10;

                        if (taste.get(tagId) == null) {
                            taste.put(tagId,new TagTaste(tagId,enjoyment) );
                        } else {
                            taste.put(tagId, new TagTaste(tagId,(taste.get(tagId).getTagTaste() + enjoyment) / 2));
                        }
                    }
                } else if (rating.getRating() == 0 && tags!=null) {

                    for (Long tagId : tags) {
                        double enjoyment = (double) average / 10;
                        if(nbOver0<=5){
                            enjoyment+=nbOver0*0.03;
                        }else{
                            enjoyment+=0.5;
                        }

                        if (taste.get(tagId) == null) {
                            taste.put(tagId,new TagTaste(tagId,enjoyment) );
                        } else {
                            taste.put(tagId, new TagTaste(tagId,(taste.get(tagId).getTagTaste() + enjoyment) / 2));
                        }
                    }
                }
            }



        }
        avg=nbOver0==0?0:avg/nbOver0;

        return new UserTasteProfile(user.getUserId(),avg,nbOver0,taste);
    }
}
