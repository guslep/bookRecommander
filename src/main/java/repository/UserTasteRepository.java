package repository;

import com.mkyong.Model.Book;
import com.mkyong.Model.UserTasteProfile;
import com.mkyong.customer.model.Customer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class UserTasteRepository extends JdbcDaoSupport {



    //query mutiple rows with manual mapping
    public List<UserTasteProfile> getAllTaste(){

        String sql = "SELECT * FROM \"UserTaste\"";

        List<UserTasteProfile>  tastes = new ArrayList<UserTasteProfile>();

        List<UserTasteProfile> userTasteProfile = getJdbcTemplate().query(sql, new UserTasteRowMapper());


        return purgeNullRecommandation(userTasteProfile);
    }
private void clearTable(){

    String sql="DELETE FROM  \"UserTaste\"";
    getJdbcTemplate().execute(sql);
}
    //insert batch example
    public void insertBatch(final List<UserTasteProfile> tastes){
        clearTable();
        String sql = "INSERT INTO \"UserTaste\" " +
                "(\"User-ID\", \"EncodedTaste\",\"AverageRating\",\"BookOverZero\") VALUES (?,?,?,?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {


            public void setValues(PreparedStatement ps, int i) throws SQLException {
                UserTasteProfile userTasteProfile = tastes.get(i);
                ps.setLong(1, userTasteProfile.getUserID());
                ps.setString(2, userTasteProfile.encode());
                ps.setDouble(3, userTasteProfile.getAverageRating());
                ps.setInt(4, userTasteProfile.getRatongOverZro());

            }


            public int getBatchSize() {
                return tastes.size();
            }
        });
    }

    private static List<UserTasteProfile> purgeNullRecommandation(List<UserTasteProfile>profilesFromDB){
        List<UserTasteProfile> cleanProfiles=new ArrayList<UserTasteProfile>();
        for(int i=0;i<profilesFromDB.size();i++){
            if(profilesFromDB.get(i)!=null){
                cleanProfiles.add(profilesFromDB.get(i));
            }
        }
        return cleanProfiles;
    }

}
