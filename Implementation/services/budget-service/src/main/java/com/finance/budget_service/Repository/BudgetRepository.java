package com.finance.budget_service.Repository;

import com.finance.budget_service.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(String userId);  // ✅ Fetch budgets by userId
    void deleteByUserId(String userId);        // ✅ Delete budgets by userId
    List<Budget> findByUserIdAndDeletedFalse(String userId);
    List<Budget> findByDeletedFalse();
    // Soft delete by userId
    default void softDeleteByUserId(String userId) {
        List<Budget> budgets = findByUserIdAndDeletedFalse(userId);
        for (Budget budget : budgets) {
            budget.setDeleted(true);
        }
        saveAll(budgets);
    }
}