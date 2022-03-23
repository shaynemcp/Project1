package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public UserDao() {
    }

    public User getUserbyUserCredentials(String username, String userpass) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select users.id, users.username, users.userpass, user_roles.user_role\n" +
                    "from users " +
                    "inner join user_roles " +
                    "on users.user_role_id = user_roles.ers_user_role_id  " +
                    "where users.username = ? and users.userpass = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, userpass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { //If such a user exists
                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                String userPass = rs.getString("userpass");
                String userRole = rs.getString("user_role");


                return new User(userId, userName, userPass, userRole);
            }
            return  null;
        }
    }

}
