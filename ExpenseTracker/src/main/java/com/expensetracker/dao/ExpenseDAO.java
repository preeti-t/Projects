package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ExpenseDAO {

    public void addExpense(String category, double amount, String date) {

        String sql = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            stmt.setDouble(2, amount);

            // ✅ FIX HERE
            stmt.setDate(3, Date.valueOf(date));

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}