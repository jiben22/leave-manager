package fr.enssat.leave_manager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@RunWith(SpringRunner.class)
public class DashboardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testShowDashboardPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}