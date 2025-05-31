package com.finance.budget_service.Model;

import com.finance.budget_service.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Transactional
    public void deleteBudgetsByUserId(String userId) {
        budgetRepository.deleteByUserId(userId);
    }
}
