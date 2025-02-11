package org.fabiano.mark.service;


import org.fabiano.mark.dto.TransactionDTO;
import org.fabiano.mark.model.Transaction;
import org.fabiano.mark.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TransactionDTO> getAllTransactions() {
        return repository.findAll()
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transacción no encontrada con ID: " + id));
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Transaction savedTransaction = repository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionDTO.class);
    }

    public TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transacción no encontrada con ID: " + id));

        modelMapper.map(transactionDTO, existingTransaction);
        Transaction updatedTransaction = repository.save(existingTransaction);
        return modelMapper.map(updatedTransaction, TransactionDTO.class);
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
}
