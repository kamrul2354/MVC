/*
 * Author: Kamrul Hasan

 * Student ID: 991769795
 * 
 * Description:
 * This class represents a Loan object that includes loan details 
 * such as loan amount, annual interest rate, and duration. 
 * It also includes methods to compute monthly and total payments.
 */

package ca.sheridan.hasankam;

public class LoanClass {

    // Instance variables for loan details
    private double loanAmount;
    private double annualInterestRate;
    private int duration; // in years

    // No-arg Constructor
    public LoanClass() {
    }

    // Constructor with parameters
    public LoanClass(double loanAmount, double annualInterestRate, int duration) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.duration = duration;
    }

    // Getters and Setters
    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Method to compute monthly payment
    public double getMonthlyPayment() {
        double monthlyInterestRate = (annualInterestRate / 100.0) / 12.0;
        int months = duration * 12;
        return loanAmount * (monthlyInterestRate / (1 - Math.pow((1 + monthlyInterestRate), -months)));
    }

    // Method to compute total payment
    public double getTotalPayment() {
        return getMonthlyPayment() * (duration * 12);
    }
}
