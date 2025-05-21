package com.finance.report_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping
    public String getReports() {
        return "List of reports";  // Placeholder, will later fetch from DB or integrate logic
    }

    @PostMapping
    public String generateReport(@RequestBody String reportCriteria) {
        return "Report generated with criteria: " + reportCriteria;  // Placeholder logic
    }
}