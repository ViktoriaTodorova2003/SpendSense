package com.finance.expenseservice.Controller;

import com.finance.expenseservice.Model.Expense;
import com.finance.expenseservice.Repository.ExpenseRepository;
import com.finance.expenseservice.Service.ExpenseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseProducer expenseProducer;

   // @GetMapping
   // public String getExpenses() {
        // Placeholder logic - return a sample expense list
   //     return "List of expenses";
   // }

   // @PostMapping
   // public String addExpense(@RequestBody String expense) {
        // Placeholder logic - just return the expense received
   //     return "Expense added: " + expense;
    //}

    @GetMapping
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        String message = "Expense details: " + expense.getDescription() + ", " + expense.getAmount() + "euros, " + expense.getCategory();
        expenseProducer.sendExpense(message);
        return expenseRepository.save(expense);
    }
}