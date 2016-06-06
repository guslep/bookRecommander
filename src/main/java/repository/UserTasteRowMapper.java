package repository;

import com.mkyong.Model.Book;
import com.mkyong.Model.UserTasteProfile;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserTasteRowMapper implements ParameterizedRowMapper<UserTasteProfile>
{
	public UserTasteProfile mapRow(ResultSet rs, int rowNum) throws SQLException {

		return UserTasteProfile.decodeProfile(rs.getInt("User-ID"),rs.getString("EncodedTaste"),rs.getDouble("AverageRating"),rs.getInt("BookOverZero"));




		}
	
}
