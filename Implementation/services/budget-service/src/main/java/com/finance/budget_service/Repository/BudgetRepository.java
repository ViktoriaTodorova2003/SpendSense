package com.finance.budget_service.Repository;

import com.finance.budget_service.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(String userId);  // ✅ Fetch budgets by userId
    void deleteByUserId(String userId);        // ✅ Delete budgets by userId
}