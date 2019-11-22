package fr.enssat.leave_manager.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/connexion"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowLockPage() throws Exception {
        mockMvc.perform(get("/verrouillage"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowForgotPasswordPage() throws Exception {
        mockMvc.perform(get("/demande-mot-de-passe"))
                .andExpect(status().isOk());
    }
}