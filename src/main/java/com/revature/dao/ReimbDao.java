package com.revature.dao;

import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResolveReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.utility.ConnectionUtility;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbDao {

    public ReimbDao() {
    }

    //TODO fix addReimbursement
    public Reimbursement addReimbursement(int userId, AddReimbursementDTO dto) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false); // We could set autocommit to false, and at the end, commit the changes

            String sql = "insert into reimbursements  (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, " +
                    "reimb_resolver, reimb_status_id, reimb_type_id, reimb_receipt) " +
                    "values " +
                    "(?, ?, null, ?, ?, null, ?, ?, null),";

            PreparedStatement pstmt1 = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt1.setInt(1, dto.getAmount());
            pstmt1.setTime(2, dto.getSubmitted());
            pstmt1.setTime(3, dto.getResolved());
            pstmt1.setString(4, dto.getDescription());
            pstmt1.setInt(5, dto.getAuthor());
            pstmt1.setInt(6, dto.getResolver());
            pstmt1.setInt(7, dto.getStatus_id());
            pstmt1.setInt(8, dto.getType_id());
            pstmt1.setString(9, dto.getReceipt());

            pstmt1.executeUpdate();

            ResultSet rs = pstmt1.getGeneratedKeys();
            rs.next();
            int reimbursementId = rs.getInt(1); // Our automatically generated id
            int amount = rs.getInt("reimb_amount");
            Time submitted = rs.getTime("reimb_submitted");
            Time resolved = rs.getTime("reimb_resolved");
            String description = rs.getString("reimb_description");
            int author = rs.getInt("reimb_author");
            int resolver = rs.getInt("reimb_resolver");
            int status_id = rs.getInt("reimb_status_id");
            int reimb_type_id = rs.getInt("reimb_type_id");
            String receipt = rs.getString("reimb_receipt");


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

            Reimbursement reimbursement = new Reimbursement(reimbursementId, amount, submitted, resolved, description, author, resolver, status_id, reimb_type_id, receipt);

            con.commit(); // commit the transaction

            return reimbursement;
        }
    }

    public List<Reimbursement> getAllReimbursements() throws SQLException {  //Trainer will be able to view pending reimbursements

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select r.reimb_id, r.reimb_amount, r.reimb_description, r.reimb_author, r.reimb_resolver, r.reimb_status_id, r.reimb_type_id, u.username, ur.user_role " +
                    "from reimbursements r left join users as u on r.reimb_author = u.id left join user_roles ur on u.user_role_id = ur.ers_user_role_id " +
                    "order by r.reimb_id;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                int reimbId = rs.getInt("reimb_id");
                int amount = rs.getInt("reimb_amount");
//                 Time submitted = rs.getTime("reimb_submitted");
//                 Time resolved = rs.getTime("reimb_resolved");
                String description = rs.getString("reimb_description");
//                String receipt = rs.getString("reimb_receipt");
                int author = rs.getInt("reimb_author");
                int resolver = rs.getInt("reimb_resolver");
                int statusId = rs.getInt("reimb_status_id");
                int typeId = rs.getInt("reimb_type_id");
                String userRole = rs.getString("user_role");
                String userName = rs.getString("username");


                reimbursements.add(new Reimbursement(reimbId, amount, description, author, resolver, statusId, typeId, userName, userRole));
            }

            return reimbursements;
        }
    }

    public List<Reimbursement> getAllReimbursementsById(int userId) throws SQLException {  //Trainer will be able to view pending reimbursements

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();

            String sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, r.reimb_author, r.reimb_resolver, r.reimb_status_id, r.reimb_type_id, r.reimb_receipt, u.username, u.userpass, ur.user_role, u.id " +
                    "from reimbursements r " +
                    "left join users u  " +
                    "on r.reimb_author = u.id left join user_roles ur on u.user_role_id = ur.ers_user_role_id " +
                    "where u.id = ?; ";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                int reimbId = rs.getInt("reimb_id");
                int amount = rs.getInt("reimb_amount");
                Time submitted = rs.getTime("reimb_submitted");
                Time resolved = rs.getTime("reimb_resolved");
                String description = rs.getString("reimb_description");
                String receipt = rs.getString("reimb_receipt");
                int author = rs.getInt("reimb_author");
                int resolver = rs.getInt("reimb_resolver");
                int statusId = rs.getInt("reimb_status_id");
                int typeId = rs.getInt("reimb_type_id");
                String userRole = rs.getString("user_role");
                int user_id = rs.getInt("id");
                String userName = rs.getString("username");

//                //Employee User
//                int eId = author;
//                String eUsername = rs.getString("username");
//                String eUserpass = rs.getString("userpass");
//                String eFirstName = rs.getString("first_name");
//                String eLastName = rs.getString("last_name");
//                String eEmail = rs.getString("email");
//                int eUserRoleId = 1;
//
//                User employee = new User(eId, eUsername, eUserpass, eFirstName, eLastName, eEmail, eUserRoleId);
//
//              //  manager User
//                int mId = author;
//                String mUsername = rs.getString("username");
//                String mUserpass = rs.getString("userpass");
//                String mFirstName = rs.getString("first_name");
//                String mLastName = rs.getString("last_name");
//                String mEmail = rs.getString("email");
//                int mUserRoleId = 2;
//
//                User manager = new User(mId, mUsername, mUserpass, mFirstName, mLastName, mEmail, mUserRoleId);


                reimbursements.add(new Reimbursement(reimbId, amount, submitted, resolved, description, author, resolver, statusId, typeId, receipt, userRole, userName, user_id));
            }

            return reimbursements;

        }
    }

    public Reimbursement updateReimbursementStatus(int reimbId, int status) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false); // We could set autocommit to false, and at the end, commit the changes

            String sql = "update reimbursements r " +
                    "set reimb_status_id = ?, reimb_resolver = ? " +
                    "where reimb_id = ?;";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, status);
            pstmt.setInt(2, 1);
            pstmt.setInt(3, reimbId);

            pstmt.executeUpdate();

            String sql2 = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, r.reimb_author, r.reimb_resolver," +
                    " r.reimb_status_id, r.reimb_type_id, r.reimb_receipt, u.username, u.userpass, ur.user_role, u.id " +
                    "from reimbursements r " +
                    "left join users u " +
                    "on r.reimb_author = u.id left join user_roles ur on u.user_role_id = ur.ers_user_role_id " +
                    "where u.id = ?;";

            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1, reimbId);

            ResultSet rs2 = pstmt2.executeQuery();


            rs2.next();
                int rId = rs2.getInt("reimb_id");
                int amount = rs2.getInt("reimb_amount");
                Time submitted = rs2.getTime("reimb_submitted");
                Time resolved = rs2.getTime("reimb_resolved");
                String description = rs2.getString("reimb_description");
                String receipt = rs2.getString("reimb_receipt");
                int author = rs2.getInt("reimb_author");
                int resolver = rs2.getInt("reimb_resolver");
                int statusId = rs2.getInt("reimb_status_id");
                int typeId = rs2.getInt("reimb_type_id");
                String userRole = rs2.getString("user_role");
                int user_id = rs2.getInt("id");
                String userName = rs2.getString("username");

                Reimbursement reimbursement = new Reimbursement(rId, amount, submitted, resolved, description, author, resolver, statusId, typeId, userName, userRole, receipt, user_id);


                con.commit(); // commit the transaction

                return reimbursement;

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
