package com.example.bohra.savvysavingappfinal;

import java.util.Date;

public class FixedPayment {


    private Period recurrment;
    private SpendingCategory fixedCategory;
    private int fixedAmount;
    private Date dateOccur;

    public FixedPayment(Period recurrment, SpendingCategory fixedCategory, int fixedAmount) {
        this.recurrment = recurrment;
        this.fixedAmount = fixedAmount;
        this.fixedCategory = fixedCategory;
    }

    public int getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(int fixedAmount) {
        this.fixedAmount = fixedAmount;
    }


    public SpendingCategory getFixedCategory() {
        return fixedCategory;
    }

    public void setFixedCategory(SpendingCategory fixedCategory) {
        this.fixedCategory = fixedCategory;
    }

    public Period getRecurrment() {
        return recurrment;
    }

    public void setRecurrment(Period recurrment) {
        this.recurrment = recurrment;
    }





}
