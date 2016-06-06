package repository;

import com.mkyong.Model.Recommandation;
import com.mkyong.Model.UserTasteProfile;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommandationRowMapper implements ParameterizedRowMapper<Recommandation>
{
	public Recommandation mapRow(ResultSet rs, int rowNum) throws SQLException {

		Recommandation rec=new Recommandation();
		rec.setISBN(rs.getString("ISBN"));
		rec.setUserId(rs.getInt("User-ID"));
		rec.setRecommandationRating(rs.getInt("Rating"));
			return rec;
	}
	
}
