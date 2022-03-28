package com.revature.service;

import com.revature.dao.ReimbDao;
import com.revature.dto.ResolveReimbursementDTO;
import com.revature.model.Reimbursement;

import java.sql.SQLException;
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

    public List<ResolveReimbursementDTO> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbDao.getAllReimbursements();

        List<ResolveReimbursementDTO> dtos = new ArrayList<>();
        for (Reimbursement r : reimbursements) {
            dtos.add(new ResolveReimbursementDTO(r.getReimb_id(), r.getDescription(), r.getAuthor(),
                    r.getResolver(), r.getReimb_status_id(), r.getReimb_type_id()));
        }

        return dtos;
    }
    public List<ResolveReimbursementDTO> getAllReimbursementsById(int reimb_author) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbDao.getAllReimbursementsById(reimb_author);

        List<ResolveReimbursementDTO> dtos = new ArrayList<>();
        for (Reimbursement r : reimbursements) {
            dtos.add(new ResolveReimbursementDTO(r.getReimb_id(), r.getDescription(), r.getAuthor(),
                    r.getResolver(), r.getReimb_status_id(), r.getReimb_type_id()));
        }

        return dtos;
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