package com.finance.budget_service;

import com.finance.budget_service.Model.Budget;
import com.finance.budget_service.Model.BudgetService;
import com.finance.budget_service.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (userId != null) {
            return budgetRepository.findByUserId(userId); // ✅ Filter budgets by userId
        }
        return budgetRepository.findAll();
    }

    @PostMapping
    public Budget addBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteBudgetsByUserId(@PathVariable String userId) {
        budgetService.deleteBudgetsByUserId(userId);
        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("/user/{userId}")
//    public void deleteBudgetsByUserId(@PathVariable String userId) {
//        budgetRepository.deleteByUserId(userId);  // ✅ Delete budgets by userId for Saga
//    }
}