package com.example.kromannreumert.integrationTest.casee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    private final String BASEURL = "/api/v1/cases";

    @Test
    void context() {
        assertNotNull(context);
    }


    @Test
    void shouldAllowAuthorizedUsersToGetAllCases() throws Exception {
        String[][] users = {
                {"admin", "ADMIN"},
                {"partner01", "PARTNER"},
                {"worker01", "SAGSBEHANDLER"}
        };

        for (String[] u : users) {
            try {
                mockMvc.perform(get(BASEURL)
                                .with(user(u[0]).roles(u[1])))
                                .andExpect(status().isOk());
                System.out.println("User " + u[0] + " with role " + u[1] + " succeeded.");
            } catch (Exception e) {
                System.err.println("User " + u[0] + " with role " + u[1] + " failed: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }
    }


}
