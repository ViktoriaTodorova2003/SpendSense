package com.finance.expenseservice.Controller;

import com.finance.expenseservice.Model.Expense;
import com.finance.expenseservice.Model.ExpenseService;
import com.finance.expenseservice.Repository.ExpenseRepository;
import com.finance.expenseservice.Service.ExpenseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseProducer expenseProducer;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getExpenses(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return expenseRepository.findByUserId(userId); // ✅ Filter by user
        }
        return expenseRepository.findAll();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        String message = "Expense details: " + expense.getDescription() + ", " + expense.getAmount() + " euros, " + expense.getCategory();
        expenseProducer.sendExpense(message);
        return expenseRepository.save(expense);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteExpensesByUserId(@PathVariable String userId) {
        expenseService.deleteExpensesByUserId(userId);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/user/{userId}")
//    public void deleteExpensesByUserId(@PathVariable String userId) {
//        expenseRepository.deleteByUserId(userId); // ✅ Saga-compatible
//    }
}