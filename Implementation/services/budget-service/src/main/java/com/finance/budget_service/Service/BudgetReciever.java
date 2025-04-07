package com.finance.budget_service.Service;

import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class BudgetReciever {

    @RabbitListener(queuesToDeclare = @Queue("expenseQueue"))
    public void receiveExpenseMessage(String message) {
        System.out.println("Budget Service received expense message: " + message);

        // Here, you can implement logic to process the received expense
        // For example, updating user budget limits based on new expense
    }

}
