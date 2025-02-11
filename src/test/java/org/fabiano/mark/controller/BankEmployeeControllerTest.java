package org.fabiano.mark.controller;

import org.fabiano.mark.dto.BankEmployeeDTO;
import org.fabiano.mark.service.BankEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankEmployeeController.class)
public class BankEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankEmployeeService service;

    @Test
    void testGetAllEmployees() throws Exception {
        when(service.getAllEmployees()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        BankEmployeeDTO employee = new BankEmployeeDTO();
        when(service.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateEmployee() throws Exception {
        BankEmployeeDTO employee = new BankEmployeeDTO();
        when(service.createEmployee(any())).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        BankEmployeeDTO employee = new BankEmployeeDTO();
        when(service.updateEmployee(eq(1L), any())).thenReturn(employee);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(service).deleteEmployee(1L);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }
}
