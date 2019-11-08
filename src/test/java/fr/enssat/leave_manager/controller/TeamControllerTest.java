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
public class TeamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/equipes")).andExpect(status().isOk());
    }
}