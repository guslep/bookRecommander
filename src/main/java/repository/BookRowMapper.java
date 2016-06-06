package repository;

import com.mkyong.Model.Book;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class BookRowMapper implements ParameterizedRowMapper<Book>
{
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setISBN(rs.getString("ISBN"));
		book.setBookTitle(rs.getString("Book-Title"));
		book.setAuthor(rs.getString("Book-Author"));
		book.setPublisher(rs.getString("Publisher"));
		book.setPublicationYear(rs.getString("Year-Of-Publication"));
		rs.getObject("tags");

		if(! rs.wasNull()){
			Array tags=rs.getArray("tags");

			book.setTags(Arrays.asList((Long[]) tags.getArray()) );

		} else{
			book.setTags(Arrays.asList( -1L ));
		}




		return book;
	}
	
}
