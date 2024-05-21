package com.bancolombia.pagos.service;

import com.bancolombia.pagos.model.BankAccount;
import com.bancolombia.pagos.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BankAccountService {

    static final String HTTP404 = "The bank account does not exist, enter a valid bank account id";
    static final String HTTP400 = "Invalid bank account data";

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        if (bankAccount.getUser() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), HTTP400);
        }
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccount(Long id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), HTTP404));
    }

    public void updateBankAccount(Long id, BankAccount bankAccount) {
        if (!bankAccountRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), HTTP404);
        }
        bankAccount.setId(id);
        bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), HTTP404);
        }
        bankAccountRepository.deleteById(id);
    }
}
