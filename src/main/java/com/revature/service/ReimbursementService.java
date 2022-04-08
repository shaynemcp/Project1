package com.revature.service;

import com.revature.dao.ReimbDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResolveReimbursementDTO;
import com.revature.model.Reimbursement;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    private ReimbDao reimbDao;

    public ReimbursementService() {
        this.reimbDao = new ReimbDao();
    }

    public ReimbursementService(ReimbDao mockDao) {
        this.reimbDao = mockDao;
    }

    public ResolveReimbursementDTO addReimbursement(int user_id, AddReimbursementDTO dto) throws SQLException {
        Reimbursement a = this.reimbDao.addReimbursement(user_id, dto);


        return new ResolveReimbursementDTO(a.getReimb_id(), a.getAmount(), a.getSubmitted(), a.getResolved(),
                a.getDescription(), a.getAuthor(), a.getResolver(), a.getReimb_status_id(), a.getReimb_type_id(), a.getReceipt());
    }

    public List<ResolveReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbDao.getAllReimbursements();

        List<ResolveReimbursementDTO> dtos = new ArrayList<>();
        for (Reimbursement r : reimbursements) {
            int reimb_id = r.getReimb_id();
            int amount = r.getAmount();
            Timestamp submitted = r.getSubmitted();
            Timestamp resolved = r.getResolved();
            String description = r.getDescription();
            int author = r.getAuthor();
            int resolver = r.getResolver();
            int status_id = r.getReimb_status_id();
            int type_id = r.getReimb_type_id();
            int user_id = r.getUser_id();

            dtos.add(new ResolveReimbursementDTO(reimb_id, amount, submitted, resolved, description, author, resolver, status_id, type_id, r.getUsername(), r.getUser_role(), r.getReceipt(), user_id));
        }

        return dtos;
    }
    public List<ResolveReimbursementDTO> getAllReimbursementsById(int userId) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbDao.getAllReimbursementsById(userId);

        List<ResolveReimbursementDTO> dtos = new ArrayList<>();
        for (Reimbursement r : reimbursements) {
            int reimb_id = r.getReimb_id();
            int amount = r.getAmount();
            Timestamp submitted = r.getSubmitted();
            Timestamp resolved = r.getResolved();
            String description = r.getDescription();
            int author = r.getAuthor();
            int resolver = r.getResolver();
            int status_id = r.getReimb_status_id();
            int type_id = r.getReimb_type_id();
            int user_id = r.getUser_id();

            dtos.add(new ResolveReimbursementDTO(reimb_id, amount, submitted, resolved, description, author, resolver, status_id, type_id, r.getUsername(), r.getUser_role(),r.getReceipt(), user_id));
        }

        return dtos;
    }

    public ResolveReimbursementDTO updateReimbursementStatus(String reimbId, String status) throws SQLException {
        try{

            System.out.println(reimbId);
            System.out.print(status);

            int rId = Integer.parseInt(reimbId);
            int sId = Integer.parseInt(status);
//            int uId = Integer.parseInt(userId);
            int managerId = 1;


           Reimbursement r =  this.reimbDao.updateReimbursementStatus(rId, sId, managerId);  //replace 'uId' here & Reimbursement to repair method so params are (rId, sId, uId)

           return new ResolveReimbursementDTO(r.getReimb_id(), r.getAmount(), r.getSubmitted(), r.getResolved(), r.getDescription(),
                   r.getAuthor(), r.getResolver(), r.getReimb_status_id(), r.getReimb_type_id(), r.getUsername(),r.getUser_role(), r.getReceipt(), r.getUser_id());
          //  Reimbursement(rId, amount, submitted, resolved, description, author, resolver, statusId, typeId, userName, userRole, receipt, user_id)

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Must provide valid ID for reimbursement and status");
        }
    }


//    public ResponseAssignmentDTO gradeAssignment(String assignmentId, String grade, int graderId) throws SQLException {
//        try {
//            int intAssignmentId = Integer.parseInt(assignmentId);
//            int intGrade = Integer.parseInt(grade);
//
//            Assignment assignment = this.reimbDao.gradeAssignment(intAssignmentId, intGrade, graderId);
//
//            return new ResponseAssignmentDTO(assignment.getId(), assignment.getAssignmentName(), assignment.getGrade(),
//                    assignment.getStudent().getUsername(), assignment.getGrader().getUsername());
//        } catch(NumberFormatException e) {
//            throw new IllegalArgumentException("Assignment ID and grade provided must be int values");
//        }
//    }
//
//    public ResponseAssignmentDTO addAssignment(int studentId, AddAssignmentDTO dto) throws SQLException, InvalidImageException, IOException {
//
//        Tika tika = new Tika();
//        String mimeType = tika.detect(dto.getImage());
//
//        if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")) {
//            throw new InvalidImageException("Image must be a JPEG, PNG, or GIF");
//        }
//
//        Assignment assignmentAdded = this.reimbDao.addAssignment(studentId, dto);
//
//        return new ResponseAssignmentDTO(assignmentAdded.getId(), assignmentAdded.getAssignmentName(),
//                assignmentAdded.getGrade(), assignmentAdded.getStudent().getUsername(), null);
//    }
//
//    public List<ResponseAssignmentDTO> getAllAssignments() throws SQLException {
//        List<Assignment> assignments = this.reimbDao.getAllAssignments();
//
//        List<ResponseAssignmentDTO> assignmentDTOs = new ArrayList<>();
//
//        for (Assignment assignment : assignments) {
//            assignmentDTOs.add(new ResponseAssignmentDTO(assignment.getId(), assignment.getAssignmentName(),
//                    assignment.getGrade(), assignment.getStudent().getUsername(), assignment.getGrader().getUsername()));
//        }
//
//        return assignmentDTOs;
//    }
//
//    public InputStream getAssignmentImage(String assignmentId, String userId) throws SQLException, ImageNotFoundException {
//        try {
//            int aId = Integer.parseInt(assignmentId);
//            int uId = Integer.parseInt(userId);
//
//            InputStream is = this.reimbDao.getAssignmentImage(aId, uId);
//
//            if (is == null) {
//                throw new ImageNotFoundException("Assignment id " + assignmentId + " does not have an image");
//            }
//
//            return is;
//        } catch(NumberFormatException e) {
//            throw new IllegalArgumentException("Assignment and/or user id must be an int value");
//        }
//    }
}