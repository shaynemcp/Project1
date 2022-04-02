package com.revature.dto;


//import java.sql.Time;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import java.sql.Time;
import java.util.Objects;

public class ResolveReimbursementDTO {
    private  int user_id;
    private int reimb_id;

    private Time submitted;
    private Time resolved;
    private int amount;
    private String description;
    private String receipt;
    private int author;
    private int resolver;
    private int status_id;
    private int type_id;

    private String username;
    private String user_role;

    private User user;


//    public ResolveReimbursementDTO(int reimb_id, String description, int status_id, int type_id, String emplUsername, String mgrUsername) {
//        this.reimb_id = reimb_id;
//        this.description = description;
//        this.status_id = status_id;
//        this.type_id = type_id;
//        this.emplUsername = emplUsername;
//        this.mgrUsername = mgrUsername;
//    }

    public ResolveReimbursementDTO() {
    }
//AddReimbursmentDTO
    public ResolveReimbursementDTO(int reimb_id, int amount, Time submitted, Time resolved, String description, int author, int resolver, int status_id, int type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;

    }
    //getAllReimbursementsDTO
    public ResolveReimbursementDTO(int reimb_id, int amount, String description, int author, int resolver, int status_id, int type_id, String username, String user_role) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
        this.username = username;
        this.user_role = user_role;
    }
//getEmployeeReimbursementsDTO
    public ResolveReimbursementDTO(int reimb_id, int amount, String description, int author, int resolver, int status_id, int type_id, String username, String user_role, int user_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
        this.username = username;
        this.user_role = user_role;
        this.user_id = user_id;
    }

    //updateReimbursementStatusDTO
    public ResolveReimbursementDTO(int reimb_id, int amount, Time submitted, Time resolved, String description, int author, int resolver, int status_id, int type_id, String username, String user_role, String receipt, int user_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
        this.username = username;
        this.user_role = user_role;
        this.receipt = receipt;
        this.user_id = user_id;
    }


//    public ResolveReimbursementDTO(int reimb_id, int amount, String description, int author, int resolver, int status_id, int type_id, String emplUsername, String mgrUsername) {
//        this.reimb_id = reimb_id;
//        this.amount = amount;
//        this.description = description;
//        this.author = author;
//        this.resolver = resolver;
//        this.status_id = status_id;
//        this.type_id = type_id;
//        this.emplUsername = emplUsername;
//        this.mgrUsername = mgrUsername;
//    }


    // -- GETTERS & SETTERS -- //


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserrole() {
        return user_role;
    }

    public void setUserrole(String userrole) {
        this.user_role = userrole;
    }

    public int getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(int reimb_id) {
        this.reimb_id = reimb_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolveReimbursementDTO that = (ResolveReimbursementDTO) o;
        return reimb_id == that.reimb_id && amount == that.amount && author == that.author && resolver == that.resolver && status_id == that.status_id && type_id == that.type_id && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimb_id, amount, description, receipt, author, resolver, status_id, type_id);
    }

    @Override
    public String toString() {
        return "ResolveReimbursementDTO{" +
                "reimb_id=" + reimb_id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}

