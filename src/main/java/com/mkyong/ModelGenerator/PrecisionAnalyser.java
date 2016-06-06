package com.mkyong.ModelGenerator;

import com.mkyong.Model.BookRating;
import com.mkyong.Model.EnrichedUser;
import com.mkyong.Model.Recommandation;
import com.mkyong.Model.User;
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

    Map<Integer,List<Recommandation>> recommandationMasterList= RecommandationService.getInstance().getRecommandationByUser();

    ArrayList<ArrayList<BookRating>> splittedRatingByUser= KFoldListGenerator.getKFoldRating();

    generateENrichedUserwithHoldout(splittedRatingByUser,0);
    GenerateUserTasteProfile generator=new GenerateUserTasteProfile();
    List<Recommandation>prcision0= TagBasedRecommandation.generateRecommandationWithList(generator.calculateUserProfile(new ArrayList(generateENrichedUserwithHoldout(splittedRatingByUser, 0))));

    return resultes;
}

    private static Collection<EnrichedUser> generateENrichedUserwithHoldout( ArrayList<ArrayList<BookRating>> splittedRatingByUser,int indexToHold){

        Map<Integer,EnrichedUser> userEnrichedMap = new HashMap<Integer, EnrichedUser>();

        for(int index=0;index<5;index++){
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
                    }else{
                        user.getAssociatedRating().put(rating.getUserId() + rating.getISBN(),rating);
                    }
                }
            }



        }
        return userEnrichedMap.values();

    }

}
