package com.expensetracking.service;

import com.expensetracking.dao.ExpenseDAO;
import com.expensetracking.model.Expense;

import java.util.List;

public class ExpenseService {

    private final ExpenseDAO dao = new ExpenseDAO();

    public void addExpense(Expense e) {
        dao.addExpense(e);
    }

    public List<Expense> getAll() {
        return dao.getAll();
    }

    public void update(int id, double amount) {
        dao.updateAmount(id, amount);
    }

    public void delete(int id) {
        dao.deleteExpense(id);
    }
}