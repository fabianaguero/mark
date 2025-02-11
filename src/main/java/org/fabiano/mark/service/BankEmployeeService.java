package org.fabiano.mark.service;


import org.fabiano.mark.dto.BankEmployeeDTO;
import org.fabiano.mark.model.BankEmployee;
import org.fabiano.mark.repository.BankEmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankEmployeeService {

    @Autowired
    private BankEmployeeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BankEmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, BankEmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public BankEmployeeDTO getEmployeeById(Long id) {
        BankEmployee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + id));
        return modelMapper.map(employee, BankEmployeeDTO.class);
    }

    public BankEmployeeDTO createEmployee(BankEmployeeDTO employeeDTO) {
        BankEmployee employee = modelMapper.map(employeeDTO, BankEmployee.class);
        BankEmployee savedEmployee = repository.save(employee);
        return modelMapper.map(savedEmployee, BankEmployeeDTO.class);
    }

    public BankEmployeeDTO updateEmployee(Long id, BankEmployeeDTO employeeDTO) {
        BankEmployee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + id));

        modelMapper.map(employeeDTO, existingEmployee);
        BankEmployee updatedEmployee = repository.save(existingEmployee);
        return modelMapper.map(updatedEmployee, BankEmployeeDTO.class);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
