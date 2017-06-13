package com.company;

/**
 * Created by Steffen on 28.05.2017.
 */
public class NextStep {

    private String description;

    public NextStep(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
