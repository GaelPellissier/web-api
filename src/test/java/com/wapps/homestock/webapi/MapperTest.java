package com.wapps.homestock.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MapperTest {
    @Autowired
    private MockMvc mMockMvc;

    @Test
    public void convertDTOtoEntity() throws Exception {
        /*AccountType type = new AccountType();
        type.setId(1L);
        type.setTypeName("PRO");
        type.setMaxOwnedRealmNbr(2);
        type.setMaxLocationNbr(4);
        type.setMaxContainerPerLocationNbr(5);

        Account account = new Account();
        account.setId(1L);
        account.setUserName("test");*/
        //account.setAccountType(type);

        /*AccountDTO result = mapper.map(account, AccountDTO.class);*/
        Long id =Long.valueOf(1);
        mMockMvc.perform(get("/account/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
    /*@Test
    public void getAccount(Long id) {

    }

    public void convertEntityToDTO() {

    }*/
}
