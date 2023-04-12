package com.wapps.homestock.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapps.homestock.lib.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {
    @Autowired
    private MockMvc mMockMvc;

    @Test
    public void testAccount() throws Exception {
        init();
        ObjectMapper mapper = new ObjectMapper();
        AccountDTO dto = new AccountDTO();
        dto.setUsername("Foo");
        dto.setPassword("123456");
        dto.setEmail("foo@test.com");
        dto.setTypeId(Long.valueOf(1));

        try {
            String json = mapper.writeValueAsString(dto);
            createAccount(json);
            System.out.println("Test ended");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*getAccountById(Long.valueOf(1));

        try {
            String json = mapper.writeValueAsString(dto);
            createAccount(json);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }

        getAccountById(Long.valueOf(2));

        dto.setUsername("Bar");
        dto.setEmail("bar@test.com");
        dto.setTypeId(Long.valueOf(2));

        try {
            String json = mapper.writeValueAsString(dto);
            updateAccount(Long.valueOf(2), json);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }
        getAccounts();*/
    }

    private void init(){
        System.out.println("Spring version: " + SpringVersion.getVersion());
        System.out.println("JDK version: " + System.getProperty("java.version"));
        System.out.println("Java version: " + JavaVersion.getJavaVersion().toString());
    }

    private void createAccount(String accountAsJSON) throws Exception {
        mMockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountAsJSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    private void getAccounts() throws Exception {
        mMockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void getAccountById(Long id) throws Exception {
        mMockMvc.perform(get("/account/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void updateAccount(Long id, String accountAsJSON) throws Exception {
        mMockMvc.perform(put("/account/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountAsJSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void deleteAccountById(Long id) throws Exception {
        mMockMvc.perform(delete("/account/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
