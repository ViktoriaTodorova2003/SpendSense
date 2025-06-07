import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BudgetController {

    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @GetMapping
    @Cacheable(value = "budgets", key = "#userId != null ? #userId : 'all'")
    public List<Budget> getBudgets(@RequestParam(required = false) String userId) {
        System.out.println("Fetching budgets from DB for userId: " + userId);
        if (userId != null) {
            return budgetRepository.findByUserId(userId); // ✅ Filter budgets by userId
        }
        return budgetRepository.findAll();
    }
}