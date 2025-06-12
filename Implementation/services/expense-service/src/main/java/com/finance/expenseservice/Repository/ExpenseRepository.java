package com.finance.expenseservice.Repository;

import com.finance.expenseservice.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(String userId); // ✅
    void deleteByUserId(String userId); // ✅ For Saga step

    List<Expense> findByUserIdAndDeletedFalse(String userId);
    List<Expense> findByDeletedFalse();
    // Soft delete by userId
    default void softDeleteByUserId(String userId) {
        List<Expense> expenses = findByUserIdAndDeletedFalse(userId);
        for (Expense expense : expenses) {
            expense.setDeleted(true);
        }
        saveAll(expenses);
    }
    // Restore (undo soft delete) all expenses for a user
    default void restoreByUserId(String userId) {
        List<Expense> expenses = findByUserId(userId);
        for (Expense expense : expenses) {
            if (expense.isDeleted()) {
                expense.setDeleted(false);
            }
        }
        saveAll(expenses);
    }
}