package com.finance.gdpr_orchestrator_service.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GdprSagaService {
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean deleteUserData(String userId) {
        try {
            // Budget Service Deletion
            restTemplate.delete("http://budget-service:8080/api/budgets/user/" + userId);

            // Expense Service Deletion
            restTemplate.delete("http://expense-service:8080/api/expenses/user/" + userId);

            // 🔜 Optional: Keycloak deletion
            return true;
        } catch (Exception e) {
            System.out.println("Error during GDPR deletion: " + e.getMessage());
            return false;
        }
    }
}
