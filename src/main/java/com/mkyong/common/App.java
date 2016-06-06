package com.mkyong.common;

import com.mkyong.Model.Book;
import com.mkyong.Model.BookRating;
import com.mkyong.Model.Recommandation;
import com.mkyong.Model.User;
import com.mkyong.ModelGenerator.GenerateUserTasteProfile;
import com.mkyong.customer.Recommandation.TagBasedRecommandation;
import service.BookService;
import service.RatingService;
import service.UserService;
import service.UserTasteService;

import java.util.Collection;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
       Collection<User> tets= UserService.getInstance().getUsers();
        tets.size();

        Collection<Book> book  = BookService.getInstance().getBookrs();
        List<BookRating> rating  = RatingService.getInstance().getRatings();
        tets.size();
        generateUserProfiles();
        generateTagBasedRecommandation();




    }
    private static void generateUserProfiles(){
        GenerateUserTasteProfile profileGenerator=new GenerateUserTasteProfile();
        UserTasteService.getInstance().insertTasteProfiles(profileGenerator.calculateUserProfile());
    }
    private static void generateTagBasedRecommandation(){
        List<Recommandation> test= TagBasedRecommandation.generateRecommandation();
        test.size();
    }


}
