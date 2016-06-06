package repository;

import com.mkyong.Model.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements ParameterizedRowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        int age;
        String ageStr=rs.getString("Age");
        if(!ageStr.equals("NULL")){
            user.setAge(Integer.parseInt(ageStr));
        }

        user.setUserId(rs.getInt("User-ID"));
        user.setLocation(rs.getString("Location"));
        return user;
    }

}
