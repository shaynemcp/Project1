//package com.revature.dto;
//
//import java.io.InputStream;
//import java.sql.Time;
//import java.util.Objects;
//import com.revature.model.User;
//
//public class AddReimbursementDTO {
//
//    private int reimb_id;
//    //    private Time submitted;
////    private Time resolved;
//    private String description;
//    private int author;
//    private int resolver;
//    private int status_id;
//    private int type_id;
//
//    public AddReimbursementDTO() {
//    }
//
//    public AddReimbursementDTO(int reimb_id, String description, int author, int resolver, int status_id, int type_id) {
//        this.reimb_id = reimb_id;
//        this.description = description;
//        this.author = author;
//        this.resolver = resolver;
//        this.status_id = status_id;
//        this.type_id = type_id;
//    }
//// -- GETTERS AND SETTERS -- //
//
//    public int getReimb_id() {
//        return reimb_id;
//    }
//
//    public void setReimb_id(int reimb_id) {
//        this.reimb_id = reimb_id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(int author) {
//        this.author = author;
//    }
//
//    public int getResolver() {
//        return resolver;
//    }
//
//    public void setResolver(int resolver) {
//        this.resolver = resolver;
//    }
//
//    public int getStatus_id() {
//        return status_id;
//    }
//
//    public void setStatus_id(int status_id) {
//        this.status_id = status_id;
//    }
//
//    public int getType_id() {
//        return type_id;
//    }
//
//    public void setType_id(int type_id) {
//        this.type_id = type_id;
//    }
//
//
//    // --EQUALS AND HASHCODE -- //
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AddReimbursementDTO that = (AddReimbursementDTO) o;
//        return reimb_id == that.reimb_id && author == that.author && resolver == that.resolver && status_id == that.status_id && type_id == that.type_id && Objects.equals(description, that.description);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(reimb_id, description, author, resolver, status_id, type_id);
//    }
//
//    @Override
//    public String toString() {
//        return "AddReimbursementDTO{" +
//                "reimb_id=" + reimb_id +
//                ", description='" + description + '\'' +
//                ", author=" + author +
//                ", resolver=" + resolver +
//                ", status_id=" + status_id +
//                ", type_id=" + type_id +
//                '}';
//    }
//}
//
