package com.expensetracking.dao;

import com.expensetracking.model.Expense;
import com.expensetracking.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private static final Logger log = LoggerFactory.getLogger(ExpenseDAO.class);

    public void addExpense(Expense e) {

        String sql = "INSERT INTO expenses(category, amount, date) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getCategory());
            ps.setDouble(2, e.getAmount());
            ps.setDate(3, Date.valueOf(e.getDate()));

            ps.executeUpdate();

            log.info("Expense added: {}", e.getCategory());

        } catch (Exception ex) {
            log.error("Error adding expense", ex);
        }
    }

    public List<Expense> getAll() {

        List<Expense> list = new ArrayList<>();

        String sql = "SELECT * FROM expenses";

        try (Connection conn = DBUtil.getDataSource().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Expense e = new Expense();
                e.setId(rs.getInt("id"));
                e.setCategory(rs.getString("category"));
                e.setAmount(rs.getDouble("amount"));
                e.setDate(rs.getDate("date").toLocalDate());

                list.add(e);
            }

            log.info("Fetched all expenses");

        } catch (Exception ex) {
            log.error("Error fetching expenses", ex);
        }

        return list;
    }

    public void updateAmount(int id, double amount) {

        String sql = "UPDATE expenses SET amount=? WHERE id=?";

        try (Connection conn = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, id);

            ps.executeUpdate();

            log.info("Updated expense id={}", id);

        } catch (Exception ex) {
            log.error("Update failed", ex);
        }
    }

    public void deleteExpense(int id) {

        String sql = "DELETE FROM expenses WHERE id=?";

        try (Connection conn = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            log.info("Deleted expense id={}", id);

        } catch (Exception ex) {
            log.error("Delete failed", ex);
        }
    }
}
