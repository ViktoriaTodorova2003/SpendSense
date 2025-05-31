package com.finance.gdpr_orchestrator_service.Controller;


//import com.finance.gdprorchestrator.service.GdprSagaService;
import com.finance.gdpr_orchestrator_service.Service.GdprSagaService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gdpr")
public class GdprController {

   // @Autowired
    //private GdprSagaService sagaService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final Counter deletionAttempts;
    private final Counter deletionFailures;

    @Autowired
    public GdprController(MeterRegistry meterRegistry) {
        this.deletionAttempts = meterRegistry.counter("gdpr.deletion.attempts");
        this.deletionFailures = meterRegistry.counter("gdpr.deletion.failures");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        boolean expenseDeleted = callDeleteWithRetries("http://expense-service:8080/api/expenses/user/" + userId, 3);
        boolean budgetDeleted = callDeleteWithRetries("http://budget-service:8080/api/budgets/user/" + userId, 3);

        if (expenseDeleted && budgetDeleted) {
            return ResponseEntity.ok("User data deleted from all services.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Partial deletion occurred. Manual intervention might be needed.");
        }
    }

    private boolean callDeleteWithRetries(String url, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            deletionAttempts.increment();
            try {
                restTemplate.delete(url);
                return true;
            } catch (Exception e) {
                deletionFailures.increment();
                System.out.println("Attempt " + (i + 1) + " failed for " + url + ": " + e.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
        return false;
    }


//    @DeleteMapping("/delete/{userId}")
//    public ResponseEntity<String> deleteUserData(@PathVariable String userId) {
//        boolean success = sagaService.deleteUserData(userId);
//        if (success) {
//            return ResponseEntity.ok("User data deleted successfully.");
//        } else {
//            return ResponseEntity.status(500).body("Failed to delete user data.");
//        }
//    }

}
