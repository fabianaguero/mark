package org.fabiano.mark.service;


import org.fabiano.mark.dto.CustomerDTO;
import org.fabiano.mark.model.Customer;
import org.fabiano.mark.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = repository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        modelMapper.map(customerDTO, existingCustomer);
        Customer updatedCustomer = repository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
