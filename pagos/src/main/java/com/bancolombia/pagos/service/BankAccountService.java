package com.bancolombia.pagos.service;

import com.bancolombia.pagos.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccount account;
}
