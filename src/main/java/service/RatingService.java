package service;

import com.mkyong.Model.BookRating;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.RatingRepository;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class RatingService {

    private static RatingService ourInstance = new RatingService();
        RatingRepository ratingRepository;

    List<BookRating> ratings;
    Map<Integer,List<BookRating>> ratingByUser= new HashMap<Integer, List<BookRating>>();
    public static RatingService getInstance() {
        return ourInstance;
    }

    private RatingService() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        ratingRepository = (RatingRepository) context.getBean("ratingRepository");    }


    public List<BookRating> getRatings(){
        if(ratings==null){
            ratings =ratingRepository.getAllBookRating();
        }
        return ratings;
    }

    public List<BookRating> getKFoldRating(){

        return ratingRepository.getKfoldRating();
    }


    public    Map<Integer,List<BookRating>> getRatingByuser(){
        if(ratingByUser.keySet().size()==0){
            List<BookRating> ratings=  getRatings();
            Iterator<BookRating> ratingIter=ratings.iterator();
            while (ratingIter.hasNext()){
                BookRating rating= ratingIter.next();
                List<BookRating> userList=ratingByUser.get(rating.getUserId());
                        if(userList== null ){
                            List<BookRating> newUserList=new LinkedList<BookRating>();
                            newUserList.add(rating);
                            ratingByUser.put(rating.getUserId(),newUserList);
                        }else{
                            userList.add(rating);
                        }
            }

        }


        return ratingByUser;
    }
}
