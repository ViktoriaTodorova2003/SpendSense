package com.finance.expenseservice.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseProducer {

    private static final String EXPENSE_QUEUE = "expenseQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendExpense(String expense) {
        System.out.println("Sending expense data: " + expense);
        rabbitTemplate.convertAndSend(EXPENSE_QUEUE, expense);
    }
}
