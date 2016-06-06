package service;

import com.mkyong.Model.BookRating;
import com.mkyong.Model.Recommandation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.RecommandationRepository;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class RecommandationService {
    private static RecommandationService ourInstance = new RecommandationService();
        RecommandationRepository recommandationRepository;
    Map<String,Recommandation> recommandations;
    Map<Integer,List<Recommandation>> ratingByUser= new HashMap<Integer, List<Recommandation>>();




    public static RecommandationService getInstance() {
        return ourInstance;
    }

    private RecommandationService() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        recommandationRepository = (RecommandationRepository) context.getBean("recommandationRepository");    }


    public Collection<Recommandation> getTaste(){
        if(recommandations ==null){
            generateMap(recommandationRepository.getAllRecommandation());
        }
        return recommandations.values() ;
    }
    public    Map<String,Recommandation> getTasteMap(){
        if(recommandations ==null){
            generateMap(recommandationRepository.getAllRecommandation());
        }
        return recommandations;
    }

    public void deleteRecommandation(){
        recommandationRepository.clearTable();
    }

    public Recommandation getTaste(int userId){
        if(recommandations ==null){
            generateMap(recommandationRepository.getAllRecommandation());
        }
        return recommandations.get(userId);
    }

    private  void generateMap(List<Recommandation> recommandationsList){
        recommandations = new HashMap<String, Recommandation>();
        for (Recommandation recommandation : recommandationsList){
            recommandations.put(recommandation.getUserId()+recommandation.getISBN(), recommandation);
        }


    }

    public void insertTasteProfiles(List<Recommandation> userTasteProfiles){
        recommandationRepository.insertBatch(userTasteProfiles);
    }

    public    Map<Integer,List<Recommandation>> getRecommandationByUser(){
        if(ratingByUser.keySet().size()==0){
            List<Recommandation> ratings=  recommandationRepository.getAllRecommandation();
            Iterator<Recommandation> recommandationIter=ratings.iterator();
            while (recommandationIter.hasNext()){
                Recommandation recommandationByUser= recommandationIter.next();
                List<Recommandation> userList=ratingByUser.get(recommandationByUser.getUserId());
                if(userList== null ){
                    List<Recommandation> newUserList=new LinkedList<Recommandation>();
                    newUserList.add(recommandationByUser);
                    ratingByUser.put(recommandationByUser.getUserId(),newUserList);
                }else{
                    userList.add(recommandationByUser);
                }
            }

        }


        return ratingByUser;
    }



}
