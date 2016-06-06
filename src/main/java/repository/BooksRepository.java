package repository;

import com.mkyong.Model.Book;
import com.mkyong.customer.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 2016-05-19.
 */
public class BooksRepository extends JdbcDaoSupport {



    //query mutiple rows with manual mapping
    public List<Book> getAllBook(){

        String sql = "SELECT * FROM \"BX-Books\"";

        List<Book>  allBooks = new ArrayList<Book>();

        List<Book> books = getJdbcTemplate().query(sql, new BookRowMapper());


        return books;
    }

    //query mutiple rows with BeanPropertyRowMapper (Customer.class)
    public List<Customer> findAll2(){

        String sql = "SELECT * FROM CUSTOMER";

        List<Customer> customers  = getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper(Customer.class));

        return customers;
    }

    public String findCustomerNameById(int custId){

        String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";

        String name = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { custId }, String.class);

        return name;

    }

    public int findTotalCustomer(){

        String sql = "SELECT COUNT(*) FROM CUSTOMER";

        int total = getJdbcTemplate().queryForInt(sql);

        return total;
    }
}
