package com.bancolombia.pagos.service;

import com.bancolombia.pagos.model.Transaction;
import com.bancolombia.pagos.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class TransactionService {

    static final String HTTP404 = "The transaction does not exist, enter a valid transaction id";
    static final String HTTP400 = "You should enter a valid amount and date";

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404));
    }

    public void updateTransaction(Long id, Transaction updatedTransaction) {
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404);
        }
        updatedTransaction.setId(id);
        transactionRepository.save(updatedTransaction);
    }

    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, HTTP404);
        }
        transactionRepository.deleteById(id);
    }


}
