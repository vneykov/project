package com.fib.project.fibProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fib.project.fibProject.services.CashierService;
import com.fib.project.fibProject.controllers.models.CashBalanceResponse;
import com.fib.project.fibProject.controllers.models.CashOperationRequest;
import com.fib.project.fibProject.services.currencies.BGN;
import com.fib.project.fibProject.services.currencies.Denomination;
import com.fib.project.fibProject.services.currencies.EUR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @Autowired
    Environment environment;

    @PostMapping(value = "/cash-operation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cashOperation(@RequestHeader("x-api-key") String apiKey,
                                                @RequestBody @Valid CashOperationRequest request) {

        validateApiKey(apiKey);

        switch (request.getOperationType()) {
            case "withdrawBGN":
                cashierService.withdrawBGN(request.getDenominations());
                break;
            case "withdrawEUR":
                cashierService.withdrawEUR(request.getDenominations());
                break;
            case "depositBGN":
                cashierService.depositBGN(request.getDenominations());
                break;
            case "depositEUR":
                cashierService.depositEUR(request.getDenominations());
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }

        return ResponseEntity.ok("Operation completed successfully");
    }

    @GetMapping("/cash-balance")
    public ResponseEntity<CashBalanceResponse> getCashBalance(@RequestHeader("x-api-key") String apiKey) {
        validateApiKey(apiKey);

        cashierService.logBalance();

        CashBalanceResponse response = new CashBalanceResponse(
                cashierService.getTotalBGN(),
                cashierService.getTotalEUR(),
                cashierService.getBgnDenominations(),
                cashierService.getEurDenominations()
        );

        return ResponseEntity.ok(response);
    }

    private void validateApiKey(String apiKey) {
        if(!environment.getProperty("api.key").equals(apiKey)) {
            throw new SecurityException("Invalid API key!");
        }
    }
}
