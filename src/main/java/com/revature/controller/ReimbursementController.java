package com.revature.controller;

//import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResolveReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.io.InputStream;
import java.sql.Time;
import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
        this.jwtService = JWTService.getInstance();
    }

    // This endpoint should only be accessible by trainers

    // Http Requests
    // 1. Request body
    // 2. Request headers
    // 3. Path parameters (1 is the path parameter) /clients/1
    // 4. Query parameters /clients?myQueryParam=something&myOtherQueryParam=somethingelse
    private Handler getAllReimbursements = (ctx) -> {
        // { "Bearer", "" }
        Jws<Claims> token = null;
        try {
            String jwt = ctx.header("Authorization").split(" ")[1];

            token = this.jwtService.parseJwt(jwt);
        } catch (Throwable e) {
            e.printStackTrace();
            if (!token.getBody().get("user_role").equals("Manager")) {
                throw new UnauthorizedResponse("You must be a manager to access this endpoint");
            }
        }
        List<ResolveReimbursementDTO> assignments = this.reimbursementService.getAllReimbursements();

        ctx.json(assignments);
    };

    private Handler getEmployeeReimbursementsById = (ctx) -> {
        if (ctx.header("Authorization") == null) {
            throw new UnauthorizedResponse("You must be logged in to access this endpoint.");
        }
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("Employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String user_id = ctx.pathParam("user_id");
        int userId = Integer.parseInt(user_id);
        if (!token.getBody().get("user_id").equals(userId)) {
            throw new UnauthorizedResponse("You can only access reimbursements that belong to you");
        }

        List<ResolveReimbursementDTO> dtos = this.reimbursementService.getAllReimbursementsById(userId);
        ctx.json(dtos);
    };


    private Handler addReimbursement = (ctx) -> {
        if (ctx.header("Authorization") == null) {
            throw new UnauthorizedResponse("You must be logged in to access this endpoint.");
        }

        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);


        if (!token.getBody().get("user_role").equals("Employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String user_id = ctx.pathParam("user_id");
        int userId = Integer.parseInt(user_id);
        if (!token.getBody().get("user_id").equals(userId)) {
            throw new UnauthorizedResponse("You can only add reimbursements that belong to you");
        }
        int amount = Integer.parseInt(ctx.formParam("amount"));
        String submitted = (ctx.formParam("submitted"));
        String description = ctx.formParam("description");
        String receipt = ctx.formParam("receipt");
        int author = Integer.parseInt(ctx.formParam("author"));
        int status_id = Integer.parseInt(ctx.formParam("status_id"));
        int type_id = Integer.parseInt(ctx.formParam("type_id"));


        AddReimbursementDTO dto = new AddReimbursementDTO();

        dto.setAmount(amount);
        dto.setSubmitted(Time.valueOf(submitted));
        dto.setDescription(description);
        dto.setReceipt(receipt);
        dto.setAuthor(author);
        dto.setStatus_id(status_id);
        dto.setType_id(type_id);


        AddReimbursementDTO getDto = this.reimbursementService.addReimbursement(userId, dto);
        ctx.json(getDto);
    };
    // Authorization logic
    // 1. User role should be student (logged in)
    // 2. user_id should match with who is actually logged in
//    private Handler addAssignment = (ctx) -> {
//        String jwt = ctx.header("Authorization").split(" ")[1];
//
//        Jws<Claims> token = this.jwtService.parseJwt(jwt);
//
//        if (!token.getBody().get("user_role").equals("student")) {
//            throw new UnauthorizedResponse("You must be a student to access this endpoint");
//        }
//
//        String userId = ctx.pathParam("user_id");
//        int id = Integer.parseInt(userId);
//        if (!token.getBody().get("user_id").equals(id)) {
//            throw new UnauthorizedResponse("You cannot add an assignment for a student other than yourself");
//        }
//
////        AddAssignmentDTO dto = ctx.bodyAsClass(AddAssignmentDTO.class);
//
//        // 1. Get the assignment name from the form parameters
//        String reimbursementName = ctx.formParam("reimbursementName");
//        AddReimbursementDTO dto = new AddReimbursementDTO();
//        dto.setReimbursementName(reimbursementName);
//
//        // 2. Get the image from the form parameters as well and detect the file type (MIME type)
//        UploadedFile file = ctx.uploadedFile("image");
//        InputStream is = file.getContent(); // This represents the bytes for the file
//        dto.setReceipt(is);
//
//        ResolveReimbursementDTO getDto = this.reimbursementService.addAssignment(id, dto);
//        ctx.status(201); // 201 Created
//        ctx.json(getDto);
//    };
//
//    private Handler gradeAssignment = (ctx) -> {
//        String jwt = ctx.header("Authorization").split(" ")[1];
//        Jws<Claims> token = this.jwtService.parseJwt(jwt);
//
//        if (!token.getBody().get("user_role").equals("trainer")) {
//            throw new UnauthorizedResponse("You must be logged in as a trainer");
//        }
//
//        String assignmentId = ctx.pathParam("assignment_id");
//        String grade = ctx.queryParam("grade");
//        int userId = token.getBody().get("user_id", Integer.class);
//
//        if (grade == null) {
//            throw new IllegalArgumentException("You need to provide a grade query parameter when attempting to grade the assignment");
//        }
//
//        ResponseAssignmentDTO assignment = this.reimbursementController.gradeAssignment(assignmentId, grade, userId);
//        ctx.json(assignment);
//    };
//
//    private Handler getSpecificStudentAssignments = (ctx) -> {
//        String jwt = ctx.header("Authorization").split(" ")[1];
//        Jws<Claims> token = this.jwtService.parseJwt(jwt);
//
//        if (!token.getBody().get("user_role").equals("student")) {
//            throw new UnauthorizedResponse("You must be a student to access this endpoint");
//        }
//
//        String userId = ctx.pathParam("user_id");
//        int id = Integer.parseInt(userId);
//        if (!token.getBody().get("user_id").equals(id)) {
//            throw new UnauthorizedResponse("You cannot obtain assignments that don't belong to yourself");
//        }
//
//        List<ResponseAssignmentDTO> dtos = this.reimbursementController.getAllAssignmentsByUserId(id);
//        ctx.json(dtos);
//    };
//
//    private Handler getAssignmentImage = (ctx) -> {
////        String jwt = ctx.header("Authorization").split(" ")[1];
////        Jws<Claims> token = this.jwtService.parseJwt(jwt);
////
//        String userId = ctx.pathParam("user_id");
////
////        if (!(token.getBody().get("user_role").equals("student") || token.getBody().get("user_role").equals("trainer"))) {
////            throw new UnauthorizedResponse("You are not allowed to access this endpoint because you are not a student or trainer");
////        }
////
////        if (token.getBody().get("user_role").equals("student") && !("" + token.getBody().get("user_id")).equals(userId)) {
////            throw new UnauthorizedResponse("You are a student, but not accessing an assignment image that belongs to you");
////        }
//
//        String assignmentId = ctx.pathParam("assignment_id");
//        InputStream image = this.reimbursementController.getAssignmentImage(assignmentId, userId);
//
//        Tika tika = new Tika();
//        String mimeType = tika.detect(image);
//
//        ctx.header("Content-Type", mimeType); // tell the client what type of image is being sent in the response
//        ctx.result(image);
//    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements); // manager
        app.get("/users/{user_id}/reimbursements", getEmployeeReimbursementsById); // specific employee
        app.post("/users/{user_id}/reimbursements", addReimbursement); // employee adds their own reimbursement
//        app.get("/users/{user_id}/assignments/{assignment_id}/image", getAssignmentImage);
//        app.patch("/assignments/{assignment_id}", gradeAssignment); // trainers
    }

}
