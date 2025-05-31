package com.finance.expenseservice.Model;

import com.finance.expenseservice.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Transactional
    public void deleteExpensesByUserId(String userId) {
        expenseRepository.deleteByUserId(userId);
    }
}
