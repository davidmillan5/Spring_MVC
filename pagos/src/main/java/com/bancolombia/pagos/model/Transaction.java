package com.bancolombia.pagos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double amount;
    private String type; // deposit/withdrawal
    private String date;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;


    public Transaction() {
    }

    public Transaction(Long id, double amount, String type, String date, BankAccount bankAccount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
