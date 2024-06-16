package com.fib.project.fibProject.services.currencies;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EUR implements Denomination {
    @NotNull
    private int denomination;
    @Min(0)
    private int count;

    public EUR() {}

    public EUR(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    @Override
    public int getDenomination() {
        return denomination;
    }

    @Override
    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}
