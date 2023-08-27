package com.example.expense_tracker;

public class ExpenseEntry {
    private String amount;
    private String description;

    public ExpenseEntry(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Description: " + description;
    }
}
