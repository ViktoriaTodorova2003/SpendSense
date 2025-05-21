package com.finance.budget_service;

import com.finance.budget_service.Model.Budget;
import com.finance.budget_service.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @GetMapping
    public List<Budget> getBudgets() {
        return budgetRepository.findAll();
    }

    @PostMapping
    public Budget addBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

//    @GetMapping
//    public String getBudgets() {
//        return "List of budgets";  // Placeholder response, will later fetch from DB
//    }
//
//    @PostMapping
//    public String addBudget(@RequestBody String budget) {
//        return "Budget added: " + budget;  // Placeholder logic for adding a budget
//    }
//
//    @PutMapping("/{id}")
//    public String updateBudget(@PathVariable Long id, @RequestBody String budget) {
//        return "Budget with id " + id + " updated: " + budget;  // Placeholder logic
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteBudget(@PathVariable Long id) {
//        return "Budget with id " + id + " deleted";  // Placeholder logic
//    }
}