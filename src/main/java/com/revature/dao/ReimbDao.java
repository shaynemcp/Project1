package com.revature.dao;

import com.revature.dto.AddReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbDao {

    public ReimbDao() {
    }

    //TODO fix addReimbursement
//    public Reimbursement addReimbursement(int userId, AddReimbursementDTO dto) throws SQLException {
//        try (Connection con = ConnectionUtility.getConnection()) {
//            con.setAutoCommit(false); // We could set autocommit to false, and at the end, commit the changes
//
//            String sql = "insert into reimbursements (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) " +
//                    "values " +
//                    "(?, ?, null, ?, null, ?, null, 1, ?),";
//
//            PreparedStatement pstmt1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            pstmt1.setInt(1, Reimbursement.getAmount());
//            pstmt1.setTime(2, Reimbursement.getSubmitted() );
//            pstmt1.setString(3, dto.getDescription());
//            pstmt1.setInt(4, dto.getAuthor());
//            pstmt1.setInt(5, dto.getType_id());
//            pstmt1.setInt(6, dto.getReimb_id());
//            pstmt1.executeUpdate();
//
//            ResultSet rs = pstmt1.getGeneratedKeys();
//            rs.next();
//            int reimbursementId = rs.getInt(1); // Our automatically generated id
//
//            String sql2 = "SELECT * FROM users WHERE id = ?";
//            PreparedStatement pstmt2 = con.prepareStatement(sql2);
//            pstmt2.setInt(1, userId);
//
//            ResultSet rs2 = pstmt2.executeQuery();
//            rs2.next();
//            int employeeId = rs2.getInt("id");
//            String employeeUsername = rs2.getString("username");
//            String employeePassword = rs2.getString("userpass");
//            String employeeRole = "Employee";
//
//            User employee = new User(employeeId, employeeUsername, employeePassword, employeeRole);
//
//            Reimbursement reimbursement = new Reimbursement(reimbursementId, Reimbursement.getAmount(),Reimbursement.getSubmitted(),
//                    null, dto.getDescription(), null, dto.getAuthor(), dto.getResolver(), dto.getStatus_id(), dto.getType_id());
//
//            con.commit(); // commit the transaction
//
//            return reimbursement;
//        }
//    }

    public List<Reimbursement> getAllReimbursements() throws SQLException {  //Trainer will be able to view pending reimbursements

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select * " +
                    "from reimbursements ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {

            }

            return reimbursements;
        }
    }

    public List<Reimbursement> getAllReimbursementsById(int reimb_author) throws SQLException {  //Trainer will be able to view pending reimbursements

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select * " +
                    "from reimbursements " +
                    "where reimb_author = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            pstmt.setInt(1, reimb_author);


            while (rs.next()) {

            }

            return reimbursements;

        }
    }

    //TODO 2: 3 Methods for getting Reimbursements by Status
    public List<Reimbursement> getAllPendingReimbursements() throws SQLException {  //Trainer will be able to view pending reimbursements

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select * " +
                    "from reimbursements " +
                    "inner join reimbursement_statuses " +
                    "on reimbursement_statuses.reimb_status_id = reimbursements.reimb_status_id  " +
                    "where reimbursement_statuses.reimb_status = 'Pending';";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

            }

            return reimbursements;
//        }
//        return reimbursements;
        }
    }
    public List<Reimbursement> getAllApprovedReimbursements() throws SQLException {  //Trainer will be able to view pending reimbursements
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select * " +
                    "from reimbursements " +
                    "inner join reimbursement_statuses " +
                    "on reimbursement_statuses.reimb_status_id = reimbursements.reimb_status_id  " +
                    "where reimbursement_statuses.reimb_status = 'Approved';";
            PreparedStatement pstmt = con.prepareStatement(sql);


        }
        return reimbursements;
    }

    public List<Reimbursement> getAllDeniedReimbursements() throws SQLException {  //Trainer will be able to view pending reimbursements
        List<Reimbursement> reimbursements = new ArrayList<>();

            try (Connection con = ConnectionUtility.getConnection()) {
                String sql = "select * " +
                        "from reimbursements " +
                        "inner join reimbursement_statuses " +
                        "on reimbursement_statuses.reimb_status_id = reimbursements.reimb_status_id  " +
                        "where reimbursement_statuses.reimb_status = 'Pending';";
                PreparedStatement pstmt = con.prepareStatement(sql);


            }
        return reimbursements;
    }
    //TODO: Method for obtaining receipt for submitted Reimbursement
}
