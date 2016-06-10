package com.mkyong.ModelGenerator;

import com.mkyong.Model.*;
import com.mkyong.common.KFoldListGenerator;
import com.mkyong.customer.Recommandation.TagBasedRecommandation;
import repository.RatingRepository;
import service.RatingService;
import service.RecommandationService;
import service.UserService;

import java.util.*;

/**
 * Created by Guillaume on 2016-06-06.
 */
public class PrecisionAnalyser {


public static double[] calculatePrecision(){
    double[] resultes = new double[10];

    Map<String,Recommandation> recommandationMasterList= RecommandationService.getInstance().getRecommandationeMap();

    ArrayList<ArrayList<BookRating>> splittedRatingByUser= KFoldListGenerator.getKFoldRating();

    //generateENrichedUserwithHoldout(splittedRatingByUser,0);
    GenerateUserTasteProfile generator=new GenerateUserTasteProfile();
    List<Recommandation>prcision= TagBasedRecommandation.generateRecommandationWithList(generator.calculateUserProfile(new ArrayList(generateENrichedUserwithHoldout(splittedRatingByUser, 0))));
    Collection<PredictionPrecision> precisionForuser=precisionForuser(prcision,splittedRatingByUser.get(0));
    Iterator<PredictionPrecision> iter=precisionForuser.iterator();
    Double precison=0.0;
    while (iter.hasNext()){
        PredictionPrecision currentPrecision=iter.next();
        precison+=currentPrecision.getPrecision();
    }
    precison=precison/precisionForuser.size();


    return resultes;
}

    private static Collection<EnrichedUser> generateENrichedUserwithHoldout( ArrayList<ArrayList<BookRating>> splittedRatingByUser,int indexToHold){
        Map<Integer,EnrichedUser> userEnrichedMap = new HashMap<Integer, EnrichedUser>();

        for(int index=0;index<4;index++){
            if(index!=indexToHold){
                List<BookRating> ratingFold=splittedRatingByUser.get(index);

                Iterator<BookRating> iter=ratingFold.iterator();
                while (iter.hasNext()){
                    BookRating rating=iter.next();
                    EnrichedUser user=userEnrichedMap.get(rating.getUserId());
                    if(user==null) {
                        User usr = UserService.getInstance().getUser(rating.getUserId());
                        EnrichedUser enrichedUser = new EnrichedUser(usr.getUserId(), usr.getLocation(), usr.getAge());
                        enrichedUser.getAssociatedRating().put(rating.getUserId() + rating.getISBN(), rating);
                        userEnrichedMap.put(rating.getUserId(), enrichedUser);
                    }else{
                        user.getAssociatedRating().put(rating.getUserId() + rating.getISBN(),rating);
                    }
                }
            }



        }
        return userEnrichedMap.values();

    }
    private static Map<String,BookRating> generateMap(List<BookRating>rating){
        Map<String,BookRating> map = new HashMap<String, BookRating>();
        Iterator<BookRating> iter= rating.iterator();
        while (iter.hasNext()){
            BookRating currentRating = iter.next();
            map.put(currentRating.getUserId()+currentRating.getISBN(),currentRating);
        }
        return map;
    }

    private static Collection<PredictionPrecision> precisionForuser(List<Recommandation>prcision,List<BookRating> ratingMasterList){

        Map<String,BookRating> ratingMap=generateMap(ratingMasterList);
        Map<Integer,PredictionPrecision> ratingPrecision=new HashMap<Integer, PredictionPrecision>();
        Iterator<BookRating> iterRating=ratingMasterList.iterator();
        while (iterRating.hasNext()){
            BookRating rating=iterRating.next();
            PredictionPrecision precis=ratingPrecision.get(rating.getUserId());
            if(precis==null){
                precis=new PredictionPrecision();
                precis.addNumberRated();
                precis.setUserId(rating.getUserId());
                ratingPrecision.put(rating.getUserId(),precis);
            } else {
                precis.addNumberRated();
            }

        }




        Iterator<Recommandation> iter= prcision.iterator();
        while (iter.hasNext()){
            Recommandation rec=iter.next();
           BookRating rating=ratingMap.get(rec.getUserId()+rec.getISBN());

            if(rating!=null){

                PredictionPrecision precis=ratingPrecision.get(rating.getUserId());
              precis.addNumberRecommended();
                precis.calculatePrecision();
            }


        }

        return ratingPrecision.values();



    }



}
