package com.sascom.stockpricebackend.application.company;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/subscribe/id/{companyId}")
    public String subscribeByCompanyId(@PathVariable Long companyId) {
        try {
            companyService.subscribeByCompanyId(companyId);
            return "Subscribed to company with ID: " + companyId;
        } catch (IOException e) {
            return "Failed to subscribe to company with ID: " + companyId;
        } catch (IllegalArgumentException e) {
            return "Company with ID: " + companyId + " not found.";
        }
    }

    @GetMapping("/subscribe/name/{companyName}")
    public String subscribeByCompanyName(@PathVariable String companyName) {
        try {
            companyService.subscribeByCompanyName(companyName);
            return "Subscribed to company with name: " + companyName;
        } catch (IOException e) {
            return "Failed to subscribe to company with name: " + companyName;
        } catch (IllegalArgumentException e) {
            return "Company with name: " + companyName + " not found.";
        }
    }

    @GetMapping("/cancel/id/{companyId}")
    public String cancelByCompanyId(@PathVariable Long companyId) {
        try {
            companyService.cancelByCompanyId(companyId);
            return "Cancelled subscription for company with ID: " + companyId;
        } catch (IOException e) {
            return "Failed to cancel subscription for company with ID: " + companyId;
        } catch (IllegalArgumentException e) {
            return "Company with ID: " + companyId + " not found.";
        }
    }

    @GetMapping("/cancel/name/{companyName}")
    public String cancelByCompanyName(@PathVariable String companyName) {
        try {
            companyService.cancelByCompanyName(companyName);
            return "Cancelled subscription for company with name: " + companyName;
        } catch (IOException e) {
            return "Failed to cancel subscription for company with name: " + companyName;
        } catch (IllegalArgumentException e) {
            return "Company with name: " + companyName + " not found.";
        }
    }

}
