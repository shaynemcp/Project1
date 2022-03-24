package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public UserDao() {
    }

    // (C) Method to be able to add a User
    public User addUser(User user) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "insert into users (username, userpass, first_name, last_name, email, user_role_id) " +
                    "values " +
                    "(?,?,?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getUserpass());
            pstmt.setString(3, user.getFirst_name());
            pstmt.setString(4, user.getLast_name());
            pstmt.setString(5, user.getEmail());
            pstmt.setInt(6, user.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt(1);

            return new User(generatedId, user.getUsername(), user.getUserpass(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getId());
        }
    }

    // (R) Method for credentials to be able to login
    public User getUserByUserCredentials(String username, String userpass) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select users.id, users.username, users.userpass, user_roles.user_role " +
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
            return null;
        }
    }

    public User getUserById(int id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select * from users where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { //If such a user exists
                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                String userPass = rs.getString("userpass");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                int user_role_id = rs.getInt("user_role_id");

                return new User(userId, userName, userPass, first_name, last_name, email, user_role_id);
            }
            return null;
        }
    }

    public List<User> getAllUsers() throws SQLException { //Should only be accessed by Fin Mgr

        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select * from users";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                String userPass = rs.getString("userpass");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                int user_role_id = rs.getInt("user_role_id");

                users.add(new User(userId, userName, userPass, first_name, last_name, email, user_role_id));
            }
            return users;
        }
    }

    // (U) Method to allow you to update User info
    public User updateUser(User user) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "update users " +
                    "set username = ?, userpass = ?, first_name = ?, last_name = ?, email = ? " +
                    "where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getUserpass());
            pstmt.setString(3, user.getFirst_name());
            pstmt.setString(4, user.getLast_name());
            pstmt.setString(5, user.getEmail());
            pstmt.setInt(6, user.getId());

            pstmt.executeUpdate();
        }
        return user;
    }

    // (D) Method to allow you to delete a User
    public boolean deleteUserById(int id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "delete from users where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int usersDeleted = pstmt.executeUpdate();
            if (usersDeleted == 1) {
                return true;
            }
        }

        return false;
    }
}
