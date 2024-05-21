package com.bancolombia.pagos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccounts;


    public User() {
    }

    public User(Long id, String name, List<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.bankAccounts = bankAccounts;
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

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}
