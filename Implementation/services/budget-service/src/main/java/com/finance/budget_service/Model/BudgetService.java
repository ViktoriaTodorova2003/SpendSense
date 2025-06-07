package com.finance.budget_service.Model;

import com.finance.budget_service.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class BudgetService {
    private static final Logger logger = LoggerFactory.getLogger(BudgetService.class);

    @Autowired
    private BudgetRepository budgetRepository;

    @Transactional
    public void deleteBudgetsByUserId(String userId) {
        budgetRepository.deleteByUserId(userId);
    }

    @Cacheable(value = "budgets", key = "#userId != null ? #userId : 'all'")
    public List<Budget> getBudgets(String userId) {
        logger.info("Fetching budgets from DB for userId: {}", userId);
        if (userId != null) {
            return budgetRepository.findByUserId(userId);
        }
        return budgetRepository.findAll();
    }
}
