package org.fabiano.mark.controller;


import org.fabiano.mark.dto.BankAccountDTO;
import org.fabiano.mark.service.BankAccountService;
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

@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService service;

    @Test
    void testGetAllAccounts() throws Exception {
        when(service.getAllAccounts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAccountById() throws Exception {
        BankAccountDTO account = new BankAccountDTO();
        when(service.getAccountById(1L)).thenReturn(account);

        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateAccount() throws Exception {
        BankAccountDTO account = new BankAccountDTO();
        when(service.createAccount(any())).thenReturn(account);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateAccount() throws Exception {
        BankAccountDTO account = new BankAccountDTO();
        when(service.updateAccount(eq(1L), any())).thenReturn(account);

        mockMvc.perform(put("/api/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAccount() throws Exception {
        doNothing().when(service).deleteAccount(1L);

        mockMvc.perform(delete("/api/accounts/1"))
                .andExpect(status().isNoContent());
    }
}