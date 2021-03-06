package com.revature.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement extends User{

   int reimb_id;
   int amount;
   Timestamp submitted; // TODO figure out how to add time (hours & min) readable by PostgreSQL
   Timestamp resolved;
   String description;
   String receipt;
   int author; // foreign key of id on users table
   int resolver; // foreign key of id on users table
   int reimb_status_id; // foreign key of status id on statuses table
   int reimb_type_id; // foreign key of type id on reimbursement types table
    int user_id;
    String username,  user_role;


    private User employee;
    private User manager;

    public Reimbursement() {
    }

    //Used for method to get Reimbursement by Author id
    public Reimbursement(int reimb_id, int author) {
        this.reimb_id = reimb_id;
        this.author = author;
    }


//getAllReimbursements
    public Reimbursement(int reimb_id, int amount, Timestamp submitted, Timestamp resolved, String description, int author, int resolver, int reimb_status_id, int reimb_type_id, String receipt, int user_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
        this.user_id = user_id;

    }
//getAllReimbursements()
    public Reimbursement(int reimbId, int amount, String description, int author, int resolver, int statusId, int typeId, String username, String user_role) {
        this.reimb_id = reimbId;
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.reimb_status_id = statusId;
        this.reimb_type_id = typeId;
        this.username = username;
        this.user_role = user_role;
    }

    //updateReimbursementStatus
    public Reimbursement(int reimbId, int amount, Timestamp submitted, Timestamp resolved, String description, int author, int resolver, int statusId, int typeId, String receipt,  String userRole,String userName, int user_id) {
        this.reimb_id = reimbId;
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.reimb_status_id = statusId;
        this.reimb_type_id = typeId;
        this.user_id = user_id;
        this.username = userName;
        this.user_role = userRole;
        this.receipt = receipt;
    }
//addReimbursement
    public Reimbursement(int reimbId, int amount, String description, String receipt, int author, int reimb_status_id, int reimb_type_id) {
        this.reimb_id = reimbId;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int id, String username, String userpass, String user_role, int reimb_id, int amount, String description, String receipt, int author, int reimb_status_id, int reimb_type_id) {
        super(id, username, userpass, user_role);
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int user_id, String userName, String firstName, String lastName, String email, String userRole, int reimb_id, int amount, String description, String receipt, int author, int reimb_status_id, int reimb_type_id) {
        super(user_id, userName, firstName, lastName, email, userRole);
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int user_id, String userName, String userPass, String firstName, String lastName, String email, String userRole, int reimb_id, int amount, String description, String receipt, int author, int reimb_status_id, int reimb_type_id) {
        super(user_id, userName, userPass, firstName, lastName, email, userRole);
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int eId, String eUsername, String eUserpass, String eFirstName, String eLastName, String eEmail, int eUserRoleId, int reimb_id, int amount, String description, String receipt, int author, int reimb_status_id, int reimb_type_id) {
        super(eId, eUsername, eUserpass, eFirstName, eLastName, eEmail, eUserRoleId);
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int reimbursementId, int amount, String description, int author, int status_id, int reimb_type_id, String receipt) {

    }
//getAllReimbursements
    public Reimbursement(int reimbId, int amount, Timestamp submitted, Timestamp resolved, String description, String receipt, int author, int resolver, int reimb_status_id, int reimb_type_id, int user_id, String username, String user_role) {
        this.reimb_id = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.reimb_status_id = reimb_status_id;
        this.reimb_type_id = reimb_type_id;
        this.user_id = user_id;
        this.username = username;
        this.user_role = user_role;
    }




    // -- GETTERS && SETTERS -- //


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public int getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(int reimb_id) {
        this.reimb_id = reimb_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public  Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
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

    public int getReimb_status_id() {
        return reimb_status_id;
    }

    public void setReimb_status_id(int reimb_status_id) {
        this.reimb_status_id = reimb_status_id;
    }

    public int getReimb_type_id() {
        return reimb_type_id;
    }

    public void setReimb_type_id(int reimb_type_id) {
        this.reimb_type_id = reimb_type_id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUser_role() {
        return user_role;
    }

    @Override
    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }


    // -- EQUALS, HASHCODE && TOSTRING //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reimbursement that = (Reimbursement) o;
        return reimb_id == that.reimb_id && author == that.author && resolver == that.resolver && reimb_status_id == that.reimb_status_id && reimb_type_id == that.reimb_type_id && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt) && Objects.equals(employee, that.employee) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reimb_id, resolved, description, receipt, author, resolver, reimb_status_id, reimb_type_id, employee, manager);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", reimb_status_id=" + reimb_status_id +
                ", reimb_type_id=" + reimb_type_id +
                ", employee=" + employee +
                ", manager=" + manager +
                '}';
    }
}
