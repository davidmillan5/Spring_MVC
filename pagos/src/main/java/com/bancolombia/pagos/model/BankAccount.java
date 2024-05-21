package com.bancolombia.pagos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "BankAccount")
public class BankAccount {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;


    public BankAccount() {
    }

    public BankAccount(Long id, String name, double balance, User user, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.user = user;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                ", transactions=" + transactions +
                '}';
    }
}
