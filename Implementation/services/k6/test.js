import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '30s', target: 50 },  // Ramp-up to 50 users in 30s
        { duration: '1m', target: 100 },  // Stay at 100 users for 1 min
        { duration: '30s', target: 0 },   // Ramp-down to 0
    ],
};

export default function () {

    let res1 = http.get('http://expense-service:8080/api/expenses');
    let res2 = http.get('http://budget-service:8080/api/budgets');


    check(res1, {
        'Expense API status is 200': (r) => r.status === 200,
        'Expense API response time is acceptable': (r) => r.timings.duration < 1000,
    });
    check(res2, {
        'Budget API status is 200': (r) => r.status === 200,
        'Budget API response time is acceptable': (r) => r.timings.duration < 1000,
    });


    let expensePayload = JSON.stringify({
        description: 'Test expense',
        category: 'Test category',
        amount: 100,
    });

    let postExpenseHeaders = { 'Content-Type': 'application/json' };
    let res3 = http.post('http://expense-service:8080/api/expenses', expensePayload, { headers: postExpenseHeaders });

    check(res3, {
        'POST Expense API status is 200 or 201': (r) => r.status === 200 || r.status === 201,  // Assuming 201 is the created status code
        'POST Expense API response time is acceptable': (r) => r.timings.duration < 1000, // Example: response time < 1 second
    });

    // POST requests for Budget
    let budgetPayload = JSON.stringify({
        category: 'Test Budget',
        amount: 70,
    });

    let postBudgetHeaders = { 'Content-Type': 'application/json' };
    let res4 = http.post('http://budget-service:8080/api/budgets', budgetPayload, { headers: postBudgetHeaders });

    check(res4, {
        'POST Budget API status is 200 or 201': (r) => r.status === 200 || r.status === 201,  // Assuming 201 is the created status code
        'POST Budget API response time is acceptable': (r) => r.timings.duration < 1000, // Example: response time < 1 second
    });

    sleep(1); // Pause 1 second between requests
}