package org.fabiano.mark.service;


import org.fabiano.mark.dto.BankEntityDTO;
import org.fabiano.mark.model.BankEntity;
import org.fabiano.mark.repository.BankEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankEntityService {

    @Autowired
    private BankEntityRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BankEntityDTO> getAllBanks() {
        return repository.findAll()
                .stream()
                .map(bank -> modelMapper.map(bank, BankEntityDTO.class))
                .collect(Collectors.toList());
    }

    public BankEntityDTO getBankById(Long id) {
        BankEntity bank = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banco no encontrado con ID: " + id));
        return modelMapper.map(bank, BankEntityDTO.class);
    }

    public BankEntityDTO createBank(BankEntityDTO bankDTO) {
        BankEntity bank = modelMapper.map(bankDTO, BankEntity.class);
        BankEntity savedBank = repository.save(bank);
        return modelMapper.map(savedBank, BankEntityDTO.class);
    }

    public BankEntityDTO updateBank(Long id, BankEntityDTO bankDTO) {
        BankEntity existingBank = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banco no encontrado con ID: " + id));

        modelMapper.map(bankDTO, existingBank);  // Actualiza los campos
        BankEntity updatedBank = repository.save(existingBank);
        return modelMapper.map(updatedBank, BankEntityDTO.class);
    }

    public void deleteBank(Long id) {
        repository.deleteById(id);
    }
}
