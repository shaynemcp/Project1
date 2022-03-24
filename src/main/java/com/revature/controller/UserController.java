package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class UserController implements Controller{
    private UserService userService;

    public UserController() {this.userService = new UserService();}

    private final Handler getAllUsers = (ctx) -> {
        List<User> users = userService.getAllUsers();

        ctx.status(200);
        ctx.json(users);
    };

    private final Handler getUserById = (ctx) -> {
        String userId = ctx.pathParam("id");

        User user = userService.getUserById(userId);
        ctx.status(200);
        ctx.json(user);
    };

    public void mapEndpoints(Javalin app) {
    }
}
