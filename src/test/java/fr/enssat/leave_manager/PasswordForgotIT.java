package fr.enssat.leave_manager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordForgotIT {

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitPasswordForgotSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/forgotPassword")
                                .with(csrf())
                                .param("templates/email", "info@leavemanager.com")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgotPassword?success"));

        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(1, receivedMessages.length);

        MimeMessage current = receivedMessages[0];
        assertEquals("info@memorynotfound.com", current.getAllRecipients()[0].toString());
    }

    @Test
    public void submitPasswordForgotInvalidEmail() throws Exception {
        this.mockMvc
                .perform(
                        post("/forgotPassword")
                                .with(csrf())
                                .param("templates/email", "invalid@email.com")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "templates/email"))
                .andExpect(status().isOk());
    }

}