package repository;

import com.mkyong.Model.BookRating;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements ParameterizedRowMapper<BookRating> {
    public BookRating mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookRating bookRating = new BookRating();
       bookRating.setISBN(rs.getString("ISBN"));
       bookRating.setUserId(rs.getInt("User-ID"));
       bookRating.setRating(rs.getInt("Book-Rating"));

        return bookRating;
    }

}
