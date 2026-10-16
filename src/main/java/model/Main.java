package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.dao.userDao;
import com.scc.model.user;

import java.util.Scanner;

public class Main {
    private static final userDao userDaoObj = new userDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseConnection.initializeDatabase();

        while (true) {
            System.out.println("\n=== VEHICLE RENTAL SYSTEM ===");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (Rental Desk Agent / Fleet Supervisor / System Administrator): ");
                    String role = scanner.nextLine();

                    boolean success = userDaoObj.registerUser(username, password, role);
                    if (success) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("Registration failed (Username may already exist).");
                    }
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    user loggedUser = userDaoObj.login(username, password);
                    if (loggedUser != null) {
                        System.out.println("Login successful! Welcome " + loggedUser.username() + " (" + loggedUser.role() + ")");
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}