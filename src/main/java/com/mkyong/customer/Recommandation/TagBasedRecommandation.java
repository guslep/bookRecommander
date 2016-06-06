package com.mkyong.customer.Recommandation;

import com.mkyong.Model.Book;
import com.mkyong.Model.Recommandation;
import com.mkyong.Model.TagTaste;
import com.mkyong.Model.UserTasteProfile;
import service.BookService;
import service.RecommandationService;
import service.UserTasteService;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-26.
 */
public class TagBasedRecommandation {


    public  static  List<Recommandation> generateRecommandation(){
return generateRecommandation(UserTasteService.getInstance().getTaste());
    }
    public  static  List<Recommandation> generateRecommandationWithList( Collection<UserTasteProfile>tastes){
        return generateRecommandation(tastes);
    }

    private  static  List<Recommandation> generateRecommandation( Collection<UserTasteProfile>tastes){
        RecommandationService.getInstance().deleteRecommandation();
        int index =0;
        System.out.println(new Date());
        List<Recommandation> userRecommandation= new ArrayList<Recommandation>();

        Collection<Book> books= BookService.getInstance().getBookrs();
         Iterator<UserTasteProfile>iter= tastes.iterator();
        while (iter.hasNext()){
            index++;
            if(index%100==0){

                RecommandationService.getInstance().insertTasteProfiles(userRecommandation.subList(index-100,index));
                System.out.println("parsing user "+index+"out of "+tastes.size());
            }

            UserTasteProfile userTaste=iter.next();
        Iterator<Book> bookIter= books.iterator();
            List<Recommandation> recommandation= new ArrayList<Recommandation>();
            while(bookIter.hasNext()){
                Book book=bookIter.next();
                Recommandation recomm=new Recommandation(calculateSimilaritiesValue(userTaste, book),book.getISBN(),userTaste.getUserID());
                if(recomm.getRecommandationRating()!=0.0){
                    recommandation.add(recomm);

                }

            }
            recommandation.sort(new Comparator<Recommandation>() {
                public int compare(Recommandation o1, Recommandation o2) {
                   if(o1.getRecommandationRating()<o2.getRecommandationRating()){

                       return 1;
                   }else if(o1.getRecommandationRating()==o2.getRecommandationRating()){
                       return 0;
                   }
                   else{
                       return -1;
                   }

                }
            });
            for (int i=0; i<recommandation.size();i++){
                recommandation.get(i).setPosition(i);
            }

            if(recommandation.size()>100){
                userRecommandation.addAll(recommandation.subList(0,100));
            }else{
                userRecommandation.addAll(recommandation)  ;
            }
        }
        System.out.println(new Date());


        return userRecommandation;

    }

    private static double calculateSimilaritiesValue(UserTasteProfile userTaste,Book book){
        double similarities=0.0;

        List<Long> bookTags=book.getTags();

        Iterator< Long> iter= bookTags.iterator();
        while (iter.hasNext()){
           TagTaste taste= userTaste.getUserTaste().get(iter.next());
            if(taste !=null){
                similarities +=taste.getTagTaste();
            }
        }
        return similarities;

    }


}
