package com.finance.expenseservice.Repository;

import com.finance.expenseservice.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(String userId); // ✅
    void deleteByUserId(String userId); // ✅ For Saga step
}