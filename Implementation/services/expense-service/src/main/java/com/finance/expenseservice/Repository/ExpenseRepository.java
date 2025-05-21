package com.finance.expenseservice.Repository;

import com.finance.expenseservice.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // You can add custom queries here if needed
}