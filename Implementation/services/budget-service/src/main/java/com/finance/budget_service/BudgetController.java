package com.finance.budget_service;

import com.finance.budget_service.Model.Budget;
import com.finance.budget_service.Model.BudgetService;
import com.finance.budget_service.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/budgets", "/api/budgets/"})
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getBudgets(@RequestParam(required = false) String userId) {
        return budgetService.getBudgets(userId);
    }

    @PostMapping
    @CacheEvict(value = "budgets", allEntries = true)
    public Budget addBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    // SOFT DELETE: default endpoint
    @DeleteMapping("/user/{userId}")
    @CacheEvict(value = "budgets", allEntries = true)
    public ResponseEntity<Void> softDeleteBudgetsByUserId(@PathVariable String userId) {
        budgetService.softDeleteBudgetsByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    // HARD DELETE: explicit endpoint
    @DeleteMapping("/user/{userId}/hard")
    @CacheEvict(value = "budgets", allEntries = true)
    public ResponseEntity<Void> hardDeleteBudgetsByUserId(@PathVariable String userId) {
        budgetService.hardDeleteBudgetsByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}