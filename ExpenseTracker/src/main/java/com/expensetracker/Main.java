package com.expensetracker;

import com.expensetracker.dao.ExpenseDAO;

public class Main {

    public static void main(String[] args) {

        ExpenseDAO dao = new ExpenseDAO();

        // TEST INSERT
        dao.addExpense("Food", 15.50, "2026-04-13");

    }
}