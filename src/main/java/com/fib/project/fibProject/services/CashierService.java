package com.fib.project.fibProject.services;

import com.fib.project.fibProject.services.currencies.BGN;
import com.fib.project.fibProject.services.currencies.Denomination;
import com.fib.project.fibProject.services.currencies.EUR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashierService {

    private List<Denomination> bgnDenominations = new ArrayList<>();
    private List<Denomination> eurDenominations = new ArrayList<>();

    @Autowired
    private FileWriterService fileWriterService;

    public CashierService() {
        bgnDenominations.add(new BGN(10,50));
        bgnDenominations.add(new BGN(50,10));

        eurDenominations.add(new EUR(10,100));
        eurDenominations.add(new EUR(50,20));
    }

    public void withdrawBGN(List<Denomination> denominations) {
        handleWithdraw(denominations, bgnDenominations, "BGN");
    }

    public void withdrawEUR(List<Denomination> denominations) {
        handleWithdraw(denominations, eurDenominations, "EUR");
    }

    public void depositBGN(List<Denomination> denominations) {
        handleDeposit(denominations, bgnDenominations, "BGN");
    }

    public void depositEUR(List<Denomination> denominations) {
        handleDeposit(denominations, eurDenominations, "EUR");
    }

    public void handleWithdraw(List<Denomination> denominations, List<Denomination> storage, String currency) {
        for (Denomination denomination : denominations) {
            int denom = denomination.getDenomination();
            int count = denomination.getCount();
            boolean exists = false;

            for (Denomination storedDenomination : storage) {
                if (storedDenomination.getDenomination() == denom) {
                    if (storedDenomination.getCount() >= count) {
                        storedDenomination.setCount(storedDenomination.getCount() - count);
                        fileWriterService.logOperation("Withdrawn " + count + " notes of " + denom + currency);
                    } else {
                        throw new IllegalArgumentException("Not enough " + currency + " notes of denomination" + denom);
                    }
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                throw new IllegalArgumentException(currency + " denomination " + denom + " does not exist");
            }
        }
    }

    public void handleDeposit(List<Denomination> denominations, List<Denomination> storage, String currency) {
        for (Denomination denomination : denominations) {
            int denom = denomination.getDenomination();
            int count = denomination.getCount();
            boolean exists = false;

            for (Denomination storedDenomination : storage) {
                if (storedDenomination.getDenomination() == denom) {
                    storedDenomination.setCount(storedDenomination.getCount() + count);
                    fileWriterService.logOperation("Deposited  " + count + " notes of " + denom + currency);
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                Denomination newDenomination = createDenomination(currency, denom, count);
                storage.add(newDenomination);
                fileWriterService.logOperation("Deposited  " + count + " notes of " + denom + currency);
            }
        }
    }

    private Denomination createDenomination(String currency, int denomination, int count) {
        if (currency.equals("BGN")) {
            return new BGN(denomination, count);
        } else if (currency.equals("EUR")) {
            return new EUR(denomination, count);
        } else {
            throw new IllegalArgumentException("Invalid currency: " + currency);
        }
    }

    public List<Denomination> getBgnDenominations() {
        return bgnDenominations;
    }

    public List<Denomination> getEurDenominations() {
        return eurDenominations;
    }

    public int getTotalBGN() {
        return bgnDenominations
                .stream()
                .mapToInt(bgn -> bgn.getDenomination() * bgn.getCount())
                .sum();
    }

    public int getTotalEUR() {
        return eurDenominations
                .stream()
                .mapToInt(eur -> eur.getDenomination() * eur.getCount())
                .sum();
    }

    public void logBalance() {
        String log = "Total BGN:" + getTotalBGN() + "\n Total EUR:" + getTotalEUR();
        fileWriterService.logBalance(log);
    }
}
