package repository;

import com.mkyong.Model.Recommandation;
import com.mkyong.Model.UserTasteProfile;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class RecommandationRepository extends JdbcDaoSupport {



    //query mutiple rows with manual mapping
    public List<Recommandation> getAllRecommandation(){

        String sql = "SELECT * FROM \"Recommandation\"";

        List<Recommandation>  tastes = new ArrayList<Recommandation>();

        List<Recommandation> userTasteProfile = getJdbcTemplate().query(sql, new RecommandationRowMapper());


        return userTasteProfile;
    }
public void clearTable(){

    String sql="DELETE FROM  \"Recommandation\"";
    getJdbcTemplate().execute(sql);
}
    //insert batch example
    public void insertBatch(final List<Recommandation> recommandations){
        //clearTable();
        String sql = "INSERT INTO \"Recommandation\" " +
                "(\"User-ID\", \"ISBN\",\"Rating\",\"Position\")) VALUES (?,?,?,?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {


            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Recommandation recommandation = recommandations.get(i);
                ps.setLong(1, recommandation.getUserId());
                ps.setString(2, recommandation.getISBN());
                ps.setDouble(3, recommandation.getRecommandationRating());
                ps.setInt(4,recommandation.getPosition());
            }


            public int getBatchSize() {
                return recommandations.size();
            }
        });
    }



}
