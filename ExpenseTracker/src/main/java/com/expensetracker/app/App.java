package com.expensetracking.app;

import com.expensetracking.model.Expense;
import com.expensetracking.service.ExpenseService;

import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ExpenseService service = new ExpenseService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Category: ");
                    String cat = sc.next();

                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.next();

                    service.addExpense(new Expense(cat, amt, LocalDate.parse(date)));
                }

                case 2 -> service.getAll().forEach(e ->
                        System.out.println(e.getId() + " " + e.getCategory() + " " + e.getAmount()));

                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.print("New Amount: ");
                    double amt = sc.nextDouble();

                    service.update(id, amt);
                }

                case 4 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    service.delete(id);
                }

                case 5 -> System.exit(0);
            }
        }
    }
}