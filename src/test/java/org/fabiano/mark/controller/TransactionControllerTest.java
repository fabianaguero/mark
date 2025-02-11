package org.fabiano.mark.controller;

import org.fabiano.mark.dto.TransactionDTO;
import org.fabiano.mark.service.TransactionService;
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

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;

    @Test
    void testGetAllTransactions() throws Exception {
        when(service.getAllTransactions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetTransactionById() throws Exception {
        TransactionDTO transaction = new TransactionDTO();
        when(service.getTransactionById(1L)).thenReturn(transaction);

        mockMvc.perform(get("/api/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateTransaction() throws Exception {
        TransactionDTO transaction = new TransactionDTO();
        when(service.createTransaction(any())).thenReturn(transaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateTransaction() throws Exception {
        TransactionDTO transaction = new TransactionDTO();
        when(service.updateTransaction(eq(1L), any())).thenReturn(transaction);

        mockMvc.perform(put("/api/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTransaction() throws Exception {
        doNothing().when(service).deleteTransaction(1L);

        mockMvc.perform(delete("/api/transactions/1"))
                .andExpect(status().isNoContent());
    }
}
