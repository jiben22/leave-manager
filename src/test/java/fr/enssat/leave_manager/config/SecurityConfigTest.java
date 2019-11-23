package fr.enssat.leave_manager.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = SecurityMockMvcRequestBuilders.formLogin()
                .user("tony.stark@marvel.com")
                .password("Ironman12*").loginProcessingUrl("/j_spring_security_check");

        mockMvc.perform(login)
                .andExpect(MockMvcResultMatchers.redirectedUrl("/map"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated().withUsername("tony.stark@marvel.com"));
    }

    @Test
    public void logoutValidUser() throws Exception {
        SecurityMockMvcRequestBuilders.LogoutRequestBuilder logout = SecurityMockMvcRequestBuilders.logout();
        mockMvc.perform(logout)
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = SecurityMockMvcRequestBuilders.formLogin()
                .user("invalid")
                .password("invalidpassword");

        mockMvc.perform(login)
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/map"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/RH/dashboard"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
                /*.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("/login"));
            */
    }

//    @Test
//    // TODO
//    @WithMockUser(username = "spiderman@marvel.com", password = "Spiderman1*", roles = "HR")
//    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/RH/dashboard"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}