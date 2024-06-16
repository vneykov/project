package com.fib.project.fibProject.controllers.models;

import com.fib.project.fibProject.services.currencies.Denomination;

import java.util.List;

public class CashBalanceResponse {

    private int totalBGN;
    private int totalEUR;
    private List<Denomination> bgnDenominations;
    private List<Denomination> eurDenominations;

    public CashBalanceResponse(int totalBGN, int totalEUR, List<Denomination> bgnDenominations,
                               List<Denomination> eurDenominations) {
        this.totalBGN = totalBGN;
        this.totalEUR = totalEUR;
        this.bgnDenominations = bgnDenominations;
        this.eurDenominations = eurDenominations;
    }

    public int getTotalBGN() {
        return totalBGN;
    }

    public void setTotalBGN(int totalBGN) {
        this.totalBGN = totalBGN;
    }

    public int getTotalEUR() {
        return totalEUR;
    }

    public void setTotalEUR(int totalEUR) {
        this.totalEUR = totalEUR;
    }

    public List<Denomination> getBgnDenominations() {
        return bgnDenominations;
    }

    public void setBgnDenominations(List<Denomination> bgnDenominations) {
        this.bgnDenominations = bgnDenominations;
    }

    public List<Denomination> getEurDenominations() {
        return eurDenominations;
    }

    public void setEurDenominations(List<Denomination> eurDenominations) {
        this.eurDenominations = eurDenominations;
    }
}
