package com.fib.project.fibProject.controllers.models;

import com.fib.project.fibProject.services.currencies.Denomination;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CashOperationRequest {

    @NotNull
    private String operationType;
    @NotNull
    private List<Denomination> denominations;

    public CashOperationRequest() {}

    public CashOperationRequest(String operationType, List<Denomination> denominations) {
        this.operationType = operationType;
        this.denominations = denominations;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations) {
        this.denominations = denominations;
    }
}
