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
public class RequestToBeProcessedControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testShowRequestsToBeProcessedPage() throws Exception {
//        mockMvc.perform(get("/demandes"))
//                .andExpect(status().isOk());
    }
}