package org.example.ledger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LedgerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void recordsMovementAndReturnsBalanceAndHistory() throws Exception {
        mockMvc.perform(post("/api/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"type":"DEPOSIT","amount":125.50,"description":"initial funding"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.balanceAfter").value(125.50));

        mockMvc.perform(get("/api/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(125.50));

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type").value("DEPOSIT"));
    }
}
