package com.revature.revabooks.screens;

import com.revature.revabooks.exceptions.InvalidRequestException;
import com.revature.revabooks.models.AppUser;
import com.revature.revabooks.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.revabooks.AppDriver.app;

public class RegisterScreen extends Screen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userService = userService;
//        userService = new UserService(); // tight coupling! we aim for loose coupling
    }

    @Override
    public void render() {

        String firstName, lastName, email, username, password;

        try {

            System.out.println("Sign up for a new account!");
            System.out.print("First name: ");
            firstName = app.getConsole().readLine();
            System.out.print("Last name: ");
            lastName = app.getConsole().readLine();
            System.out.print("Email: ");
            email = app.getConsole().readLine();
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            AppUser newUser = new AppUser(firstName, lastName, username, password, email);
            userService.register(newUser);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }

        } catch (InvalidRequestException e) {
            System.err.println("Registration unsuccessful, invalid values provided.");
        } catch (Exception e) {
            System.err.println("[ERROR] - An unexpected exception occurred: " + e.getMessage());
            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }

    }

}
