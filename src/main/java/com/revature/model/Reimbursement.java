package com.revature.model;

import java.sql.Time;
import java.util.Objects;

public class Reimbursement extends User{

   int reimb_id;
   int amount;
   Time submitted;
   Time resolved;
   String description;
   String receipt;
   int author; // foreign key of id on users table
   int resolver; // foreign key of id on users table
   int reimb_status_id; // foreign key of status id on statuses table
   int reimb_type_id; // foreign key of type id on reimbursement types table

    public Reimbursement() {
    }

    //Used for method to get Reimbursement by Author id
    public Reimbursement(int reimb_id, int author) {
        this.reimb_id = reimb_id;
        this.author = author;
    }

    public Reimbursement(int reimb_id, int amount, Time submitted, Time resolved, String description, String receipt, int author, int resolver, int reimb_status_id, int reimb_type_id) {
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
    }

    // -- GETTERS && SETTERS -- //


    public int getReimb_id() {
        return reimb_id;
    }

    public static int getAmount() {
        return amount;
    }

    public Time getSubmitted() {
        return submitted;
    }

    public Time getResolved() {
        return resolved;
    }

    public String getDescription() {
        return description;
    }

    public String getReceipt() {
        return receipt;
    }

    public int getAuthor() {
        return author;
    }

    public int getResolver() {
        return resolver;
    }

    public int getReimb_status_id() {
        return reimb_status_id;
    }

    public int getReimb_type_id() {
        return reimb_type_id;
    }

    // -- EQUALS, HASHCODE && TOSTRING //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reimbursement that = (Reimbursement) o;
        return reimb_id == that.reimb_id && amount == that.amount && author == that.author && resolver == that.resolver && reimb_status_id == that.reimb_status_id && reimb_type_id == that.reimb_type_id && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reimb_id, amount, submitted, resolved, description, receipt, author, resolver, reimb_status_id, reimb_type_id);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", reimb_status_id=" + reimb_status_id +
                ", reimb_type_id=" + reimb_type_id +
                '}';
    }
}
