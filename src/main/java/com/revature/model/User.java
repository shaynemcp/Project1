package com.revature.model;

import java.util.Objects;

public class User {

    int id;
    String username;
    String userpass;
    String first_name;
    String last_name;
    String email;
    int user_role_id;
    String user_role; // "Fin Mgr"  or "Employee"
    int ers_role_id; // (1) Fin Mgr    (2) Employee

    public User() {

    }

    public User(int id, String username, String userpass, String user_role) {
        this.id = id;
        this.username = username;
        this.userpass = userpass;
        this.user_role = user_role;
    }

    public User(int id, String username, String userpass, String first_name, String last_name, String email, int user_role_id) {
        this.id = id;
        this.username = username;
        this.userpass = userpass;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.user_role_id = user_role_id;
    }

  //  -- GETTERS & SETTERS -- //


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }


   // -- EQUALS, HASHCODE, TOSTRING --  //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && user_role_id == user.user_role_id && Objects.equals(username, user.username) && Objects.equals(userpass, user.userpass) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userpass, first_name, last_name, email, user_role_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", user_role_id=" + user_role_id +
                '}';
    }
}
