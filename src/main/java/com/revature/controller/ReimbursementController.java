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
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
        this.jwtService = JWTService.getInstance();
    }

    //Managers ONLY
    private Handler getAllReimbursements = (ctx) -> {
        // { "Bearer", "" }
//        Jws<Claims> token = null;
//        try {
            String jwt = ctx.header("Authorization").split(" ")[1];

           Jws<Claims> token = this.jwtService.parseJwt(jwt);
//        } catch (Throwable e) {
//            e.printStackTrace();
            if (!token.getBody().get("user_role").equals("manager")) {
                throw new UnauthorizedResponse("You must be a manager to access this endpoint");
            }
//        }
        List<ResolveReimbursementDTO> assignments = this.reimbursementService.getAllReimbursements();

        ctx.json(assignments);
    };

    //Employees Viewing Their Reimbursements
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

        System.out.println(token.getBody().get("user_role"));
        if (!token.getBody().get("user_role").equals("Employee")) {
            throw new UnauthorizedResponse("You must be an employee to access this endpoint");
        }

        String user_id = ctx.pathParam("user_id");
        int userId = Integer.parseInt(user_id);
        if (!token.getBody().get("user_id").equals(userId)) {
            throw new UnauthorizedResponse("You can only add reimbursements that belong to you");
        }
        int amount = Integer.parseInt(ctx.formParam("amount"));
//        Timestamp submitted = new Timestamp(System.currentTimeMillis());
        String description = ctx.formParam("description");
        String receipt = ctx.formParam("receipt");
        int author = Integer.parseInt(ctx.formParam("author"));
        int status_id = Integer.parseInt(ctx.formParam("status_id"));
        int type_id = Integer.parseInt(ctx.formParam("type_id"));
//        int resolver = Integer.parseInt(ctx.formParam("resolver"));

        AddReimbursementDTO dto = new AddReimbursementDTO();

        dto.setAmount(amount);
//        dto.setSubmitted(submitted);
        dto.setDescription(description);
        dto.setReceipt(receipt);
        dto.setAuthor(author);
        dto.setStatus_id(status_id);
        dto.setType_id(type_id);
//        dto.setResolver(resolver);


        ResolveReimbursementDTO getDto = this.reimbursementService.addReimbursement(userId, dto);
        ctx.json(getDto);
    };

    private Handler updateReimbursementStatus = ctx -> {
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if (!token.getBody().get("user_role").equals("manager")) {
            throw new UnauthorizedResponse("You must be a manager to access this endpoint");
        }
        String reimb_id = ctx.pathParam("reimb_id");
        String status_id = ctx.formParam("reimb_status_id");
        String resolver = ctx.formParam("resolver");
        String username = ctx.formParam("username");
        String user_id = ctx.formParam("user_id");

        ResolveReimbursementDTO reimbursement = this.reimbursementService.updateReimbursementStatus(reimb_id, status_id,user_id);
        ctx.json(reimbursement);
    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/reimbursements", getAllReimbursements); // manager
        app.get("/users/{user_id}/reimbursements", getEmployeeReimbursementsById); // specific employee
        app.post("/users/{user_id}/reimbursements", addReimbursement); // employee adds their own reimbursement
//        app.get("/users/{user_id}/reimbursements/{reimb_id}/image", getReimbursementImage);
        app.patch("/reimbursements/{reimb_id}", updateReimbursementStatus); // managers
    }

}
