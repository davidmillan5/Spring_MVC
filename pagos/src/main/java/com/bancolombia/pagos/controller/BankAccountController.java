package com.bancolombia.pagos.controller;

import com.bancolombia.pagos.model.BankAccount;
import com.bancolombia.pagos.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/bankaccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
            BankAccount entity = bankAccountService.createBankAccount(bankAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        return ResponseEntity.ok(bankAccount);
    }

    @GetMapping
    public List<BankAccount> getBankAccounts() {
        return bankAccountService.getBankAccounts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        BankAccount existingBankAccount = bankAccountService.getBankAccount(id);
        existingBankAccount.setAccountType(bankAccount.getAccountType());
        existingBankAccount.setBalance(bankAccount.getBalance());
        existingBankAccount.setUser(bankAccount.getUser());
        existingBankAccount.setTransactions(bankAccount.getTransactions());

        BankAccount updatedBankAccount = bankAccountService.updateBankAccount(id, existingBankAccount);
        return ResponseEntity.ok(updatedBankAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
