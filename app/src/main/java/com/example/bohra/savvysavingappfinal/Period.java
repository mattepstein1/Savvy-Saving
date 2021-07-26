package com.example.bohra.savvysavingappfinal;

/**
 * This class describes the period units available. and their inherent day value
 *
 */
public enum Period {
    Day(1),
    Week(7),
    Fortnite(14),
    Month(30);

    private int dayCount;

    private int getDayCount(){
        return dayCount;
    }


    private Period(int dayCount){
        this.dayCount = dayCount;
    }
}
