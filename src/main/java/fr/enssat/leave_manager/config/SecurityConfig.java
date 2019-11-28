package fr.enssat.leave_manager.config;

import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.web.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeService userDetailsService;
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("xos45.mjt.lu/**").permitAll();
        // The pages does not require login

        // For HR & HRD only.
        http.authorizeRequests().antMatchers("/RH/*").access("hasRole('ROLE_HR') or hasRole('ROLE_HRD')");

        // Authorize resources
        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**", "/scss/**").permitAll();

        // Authorize pages
        http.authorizeRequests()
                .antMatchers("/connexion**", "/deconnexion***", "/reinitialisation-mot-de-passe**").permitAll();

        // Config for Login Form
        http.authorizeRequests().and()
                .formLogin()//
                // Submit URL of login page.
                //.loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/connexion")//
                .defaultSuccessUrl("/")//
                //.failureUrl("/connexion?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/connexion?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
        ;
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
