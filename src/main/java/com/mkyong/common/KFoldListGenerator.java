package com.mkyong.common;

import com.mkyong.Model.BookRating;
import service.RatingService;

import java.util.*;

/**
 * Created by Guillaume on 2016-06-06.
 */
public class KFoldListGenerator {


    public static ArrayList<ArrayList<BookRating>> getKFoldRating(){

        List<BookRating>rating= RatingService.getInstance().getKFoldRating();
        ArrayList<ArrayList<BookRating>>  splittedRating = new ArrayList<ArrayList<BookRating>>(4);
        splittedRating.add(0,new ArrayList<BookRating>());
        splittedRating.add(1,new ArrayList<BookRating>());
        splittedRating.add(2,new ArrayList<BookRating>());
        splittedRating.add(3,new ArrayList<BookRating>());
        splittedRating.add(4,new ArrayList<BookRating>());
        Iterator <BookRating> allRating= rating.iterator();
        List<BookRating>userRating=new ArrayList<BookRating>();
        int currentid=-1;
        while(allRating.hasNext()){
            BookRating currentRating=allRating.next();

            if(currentRating.getUserId()==currentid){
                userRating.add(currentRating);
            }
            else if(currentRating.getUserId()!= currentid && currentid != -1){
                ArrayList<ArrayList<BookRating>>  splittedUserRating= randomOrderRating(userRating);
                splittedRating.get(0).addAll(splittedUserRating.get(0));
                splittedRating.get(1).addAll(splittedUserRating.get(1));
                splittedRating.get(2).addAll(splittedUserRating.get(2));
                splittedRating.get(3).addAll(splittedUserRating.get(3));
           //     splittedRating.get(4).addAll(splittedUserRating.get(4));
                userRating=new ArrayList<BookRating>();
                userRating.add(currentRating);
                currentid=currentRating.getUserId();
            } else {
                userRating.add(currentRating);
                currentid=currentRating.getUserId();
            }

        }
        return splittedRating;

    }

    private  static ArrayList<ArrayList<BookRating>> randomOrderRating( List<BookRating> userRating ){
        ArrayList<ArrayList<BookRating>>  splittedRating = new ArrayList<ArrayList<BookRating>>(4);
        Collections.shuffle(userRating,new Random(System.nanoTime()));
        Iterator <BookRating> allRating= userRating.iterator();
        int index=0;
        splittedRating.add(0,new ArrayList<BookRating>());
        splittedRating.add(1,new ArrayList<BookRating>());
        splittedRating.add(2,new ArrayList<BookRating>());
        splittedRating.add(3,new ArrayList<BookRating>());
        splittedRating.add(4,new ArrayList<BookRating>());
        while (allRating.hasNext()){
            splittedRating.get(index%5).add(allRating.next());
            index++;
        }



        return splittedRating;
    }

}
