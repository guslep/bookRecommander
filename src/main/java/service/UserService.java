package service;

import com.mkyong.Model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.UserRepository;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class UserService {
    Map<Integer,User> userMap;
    Map<Integer,EnrichedUser> userEnrichedMap;

    private static UserService ourInstance = new UserService();
        UserRepository userRepository;
    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        userRepository = (UserRepository) context.getBean("userRepository");

    }


    public Collection<User> getUsers(){

        if(userMap == null){
            generateMap(userRepository.getAllBook());
        }
        return userMap.values();
    }
    public Map<Integer, User> getUsersMap(){

        if(userMap == null){
            generateMap(userRepository.getAllBook());
        }
        return userMap;
    }

    public User getUser(int userID){
        if(userMap == null){
            generateMap(userRepository.getAllBook());
        }
        return userMap.get(userID);
    }

    public Collection<EnrichedUser> getEnrichedUsers(){

        if(userEnrichedMap == null){
            generateMap(userRepository.getAllBook());
        }
        if(userEnrichedMap==null){
            generateEnrichedBook();
        }

        return userEnrichedMap.values();
    }
    public Map<Integer, EnrichedUser> getEnrichedUsersMap(){

        if(userMap == null){
            generateMap(userRepository.getAllBook());
        }
        if(userEnrichedMap==null){
            generateEnrichedBook();
        }


        return userEnrichedMap;
    }

    public EnrichedUser getEnrichedUser(String userID){
        if(userMap == null){
            generateMap(userRepository.getAllBook());
        }

        if(userEnrichedMap==null){
            generateEnrichedBook();
        }

        return userEnrichedMap.get(userID);
    }

    private  void generateMap(List<User> userList){
        userMap  = new HashMap<Integer, User>();
        for (User user : userList){
            userMap.put(user.getUserId(),user);
        }


    }

    public  void generateEnrichedBook(){
        userEnrichedMap  = new HashMap<Integer, EnrichedUser>();
        Iterator<User> iter=userMap.values().iterator();
        while (iter.hasNext()){

            User user=iter.next();
            EnrichedUser enriched=new EnrichedUser();
            enriched.setAge(user.getAge());
       enriched.setLocation(user.getLocation());
            enriched.setUserId(user.getUserId());

            userEnrichedMap.put(user.getUserId(), enriched);
        }

        List<BookRating> ratings=RatingService.getInstance().getRatings();
        for(BookRating bookRating:ratings){
            userEnrichedMap.get(bookRating.getUserId()).getAssociatedRating().put(bookRating.getISBN() + bookRating.getUserId(), bookRating);
        }


    }

}
