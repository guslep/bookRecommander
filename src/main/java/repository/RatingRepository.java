package repository;

import com.mkyong.Model.BookRating;
import com.mkyong.customer.model.Customer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class RatingRepository extends JdbcDaoSupport {



    //query mutiple rows with manual mapping
    public List<BookRating> getAllBookRating(){

        String sql = "SELECT * FROM \"BX-Book-Ratings\"";



        List<BookRating> rating = getJdbcTemplate().query(sql, new RatingRowMapper());


        return rating;
    }

    //insert batch example
    public void insertBatchKNN(final List<Customer> customers){

        String sql = "INSERT INTO BX-Book-Ratings_1 " +
                "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {


            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Customer customer = customers.get(i);
                ps.setLong(1, customer.getCustId());
                ps.setString(2, customer.getName());
                ps.setInt(3, customer.getAge());
            }


            public int getBatchSize() {
                return customers.size();
            }
        });
    }

    public List<BookRating> getKfoldRating(){
        String sql="select  rating.* from( SELECT \"User-ID\", count(\"Book-Rating\") as nbr FROM public.\"BX-Book-Ratings\" where \"Book-Rating\"!=0 group by \"User-ID\" order by nbr desc )as foo , public.\"BX-Book-Ratings\" as rating where  foo.\"User-ID\" =rating.\"User-ID\" and foo.nbr>19\n" ;
                List<BookRating> rating = getJdbcTemplate().query(sql, new RatingRowMapper());
    return rating;
    }


}
