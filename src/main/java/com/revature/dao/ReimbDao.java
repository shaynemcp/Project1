package com.revature.dao;

import com.revature.model.Reimbursement;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbDao {

    public ReimbDao() {
    }
    //
    public List<Reimbursement> getReimbursementByStatus() throws SQLException {  //Trainer will be able to view pending reimbursements
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "Select * from "

        }
        return reimbursements;
    }
}
