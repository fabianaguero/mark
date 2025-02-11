package org.fabiano.mark.service;


import org.fabiano.mark.dto.BankAccountDTO;
import org.fabiano.mark.model.BankAccount;
import org.fabiano.mark.repository.BankAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BankAccountDTO> getAllAccounts() {
        return repository.findAll()
                .stream()
                .map(account -> modelMapper.map(account, BankAccountDTO.class))
                .collect(Collectors.toList());
    }

    public BankAccountDTO getAccountById(Long id) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));
        return modelMapper.map(account, BankAccountDTO.class);
    }

    public BankAccountDTO createAccount(BankAccountDTO accountDTO) {
        BankAccount account = modelMapper.map(accountDTO, BankAccount.class);
        BankAccount savedAccount = repository.save(account);
        return modelMapper.map(savedAccount, BankAccountDTO.class);
    }

    public BankAccountDTO updateAccount(Long id, BankAccountDTO accountDTO) {
        BankAccount existingAccount = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));

        modelMapper.map(accountDTO, existingAccount);
        BankAccount updatedAccount = repository.save(existingAccount);
        return modelMapper.map(updatedAccount, BankAccountDTO.class);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}
