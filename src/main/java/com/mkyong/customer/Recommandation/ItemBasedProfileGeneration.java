package com.mkyong.customer.Recommandation;

import com.mkyong.Model.Book;
import com.mkyong.Model.BookRating;
import com.mkyong.Model.BookSimilarity;
import com.mkyong.Model.EnrichedBook;
import service.BookService;
import service.UserService;

import java.util.*;

/**
 * Created by Guillaume on 2016-06-09.
 */
public class ItemBasedProfileGeneration {

    public void  generateBookProfile(){

       Collection<EnrichedBook> books=BookService.getInstance().generateEnrichedBook().values();
        Iterator<EnrichedBook> bookIterator =books.iterator();
        while (bookIterator.hasNext()){
            EnrichedBook book = bookIterator.next();
            List<BookSimilarity>similarities= new ArrayList<BookSimilarity>();
            if(book.getAssociatedRtings().values().size()>=20){
                Iterator<EnrichedBook> comparator =books.iterator();
                while (comparator.hasNext()){
                    EnrichedBook bookCompared = comparator.next();
                    if(bookCompared.getAssociatedRtings().values().size()>=20){
                        double similarityValue=similarities(book,bookCompared);
                        if(similarityValue!=0.0){
                            similarities.add(new BookSimilarity(book,bookCompared,similarityValue));
                        }
                    }


                }
            }



        }
    }


    private double similarities(EnrichedBook book,EnrichedBook comparator){
        Map<String,BookRating> bookRatingMap=book.getAssociatedRtings();
        Map<String,BookRating> comparatorRating=comparator.getAssociatedRtings();
        Double avgRating1=calculateAverageRating(book.getAssociatedRtings().values());
        Double avgRatingComparator=calculateAverageRating(comparator.getAssociatedRtings().values());
        Collection<BookRating> bookRatings=bookRatingMap.values();
        Iterator<BookRating> iter=bookRatings.iterator();

        double cov=0.0;
        double tetaBook1=0.0;
        double tetaBook2=0.0;
       while (iter.hasNext()){
            BookRating ratingFromBook= iter.next();
            BookRating ratingForBook2ByUser=comparatorRating.get(comparator.getISBN() + ratingFromBook.getUserId());
            if(ratingForBook2ByUser!=null){
                double rating=ratingFromBook.getRating();
                double ratingComparator=ratingForBook2ByUser.getRating();
                if(ratingFromBook.getRating()==0){

              rating= UserService.getInstance().getEnrichedUser(ratingFromBook.getUserId()).getAverageRating();

                }
                if(ratingForBook2ByUser.getRating()==0){
                    ratingComparator=UserService.getInstance().getEnrichedUser(ratingForBook2ByUser.getUserId()).getAverageRating();

                }
                 else {

                    tetaBook1+=Math.pow((rating - avgRating1), 2);
                    tetaBook2+= Math.pow((ratingComparator-avgRatingComparator), 2);
                    cov+= (rating- avgRating1)*(ratingComparator-avgRatingComparator);

                }

            }
           return cov/(Math.sqrt(tetaBook1))*(Math.sqrt(tetaBook2));

        }




        return 0.0;
    }

    private double calculateAverageRating(Collection<BookRating>ratings){
        Iterator<BookRating> iter=ratings.iterator();
        double avg=0.0;
        int nbr=0;
        while (iter.hasNext()){
            BookRating rating=iter.next();
            if(rating.getRating()>0){
                avg+=rating.getRating();
                nbr++;
            }
        }


        return avg/nbr;
    }



}
