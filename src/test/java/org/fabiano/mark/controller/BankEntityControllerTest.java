package org.fabiano.mark.controller;

import org.fabiano.mark.dto.BankEntityDTO;
import org.fabiano.mark.service.BankEntityService;
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

@WebMvcTest(BankEntityController.class)
public class BankEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankEntityService service;

    @Test
    void testGetAllBanks() throws Exception {
        when(service.getAllBanks()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/banks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBankById() throws Exception {
        BankEntityDTO bank = new BankEntityDTO();
        when(service.getBankById(1L)).thenReturn(bank);

        mockMvc.perform(get("/api/banks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBank() throws Exception {
        BankEntityDTO bank = new BankEntityDTO();
        when(service.createBank(any())).thenReturn(bank);

        mockMvc.perform(post("/api/banks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateBank() throws Exception {
        BankEntityDTO bank = new BankEntityDTO();
        when(service.updateBank(eq(1L), any())).thenReturn(bank);

        mockMvc.perform(put("/api/banks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBank() throws Exception {
        doNothing().when(service).deleteBank(1L);

        mockMvc.perform(delete("/api/banks/1"))
                .andExpect(status().isNoContent());
    }
}
