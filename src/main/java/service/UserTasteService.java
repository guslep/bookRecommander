package service;

import com.mkyong.Model.Book;
import com.mkyong.Model.UserTasteProfile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.UserTasteRepository;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class UserTasteService {
    private static UserTasteService ourInstance = new UserTasteService();
        UserTasteRepository userTasteRepository;
    Map<Integer,UserTasteProfile> userTastes;



    public static UserTasteService getInstance() {
        return ourInstance;
    }

    private UserTasteService() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        userTasteRepository = (UserTasteRepository) context.getBean("userTasteRepository");    }


    public Collection<UserTasteProfile> getTaste(){
        if(userTastes ==null){
            generateMap(userTasteRepository.getAllTaste());
        }
        return userTastes.values() ;
    }
    public    Map<Integer,UserTasteProfile> getTasteMap(){
        if(userTastes ==null){
            generateMap(userTasteRepository.getAllTaste());
        }
        return userTastes;
    }

    public UserTasteProfile getTaste(int userId){
        if(userTastes ==null){
            generateMap(userTasteRepository.getAllTaste());
        }
        return userTastes.get(userId);
    }

    private  void generateMap(List<UserTasteProfile> userTasteProfile){
        userTastes = new HashMap<Integer, UserTasteProfile>();
        for (UserTasteProfile taste : userTasteProfile){
            userTastes.put(taste.getUserID(), taste);
        }


    }

    public void insertTasteProfiles(List<UserTasteProfile> userTasteProfiles){
        userTasteRepository.insertBatch(userTasteProfiles);
    }



}
