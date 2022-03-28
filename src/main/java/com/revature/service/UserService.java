package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public UserService(UserDao mockDao) {
        this.userDao = mockDao;
    }


    // (C) Add user
    public User addUser(User user) throws SQLException {
        validateUserInformation(user);
        User addedUser = userDao.addUser(user);
        return addedUser;
    }

    // (R) Login info provided to controller here

    public User login (String username, String userpass) throws SQLException, UserNotFoundException{

//    try{
        User user = userDao.getUserByUserCredentials(username, userpass);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
//    } catch (IllegalArgumentException e) {
//        throw new IllegalArgumentException("You must provide valid username and password credentials");
//    }
    }

    public User getUserById (String id) throws SQLException, UserNotFoundException{
        try{
            int userId = Integer.parseInt(id);
            User user = userDao.getUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            }
            return user;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("You must provide valid username and password credentials");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    // (U)
    public User updateUser(User user) throws SQLException, UserNotFoundException {
        validateUserInformation(user);

        try {
            if (userDao.getUserByUserCredentials(user.getUsername(), user.getUserpass()) == null) {
                throw new UserNotFoundException("Cannot edit a user that does not exist");
            }
            return userDao.updateUser(user);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("You must provide valid username and password credentials");
        }
    }

    // (D)
    public boolean deleteUserById(String id) throws SQLException{
        try {
            int clientId = Integer.parseInt(id);

            if (userDao.getUserById(clientId) == null ) {
        }
            return userDao.deleteUserById(clientId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("User not found, you must provide valid credentials");
        }
    }


    public void validateUserInformation(User user) {
        user.setUsername(user.getUsername().trim());
        user.setUserpass(user.getUserpass().trim());

        if (!user.getUsername().matches("[a-zA-Z]+('[a-zA-Z])?[a-zA-Z]*")) {
            throw new IllegalArgumentException("Username must only have alphabetical characters.\nInput: " + user.getUsername());
        }

        if (!user.getUserpass().matches("[a-zA-Z]+('[a-zA-Z])?[a-zA-Z]*")) {
            throw new IllegalArgumentException("Password must only have alphabetical characters.\nInput: " + user.getUserpass());
        }
    }

}
