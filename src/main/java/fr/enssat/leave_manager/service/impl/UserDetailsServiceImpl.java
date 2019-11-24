package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.controller.TypeOfLeaveController;
import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Logger logger = LoggerFactory.getLogger(UserDetails.class);

        Optional<EmployeeEntity> employee = employeeRepository.findByEmail(email);

        if (!employee.isPresent()) {
//            /*** DEBUG ***/
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            logger.warn(bCryptPasswordEncoder.encode("password"));

            logger.error("Mail not found! " + email);
            throw new UsernameNotFoundException("User mail " + email + " was not found in the database");
        }

        System.out.println("Found mail: " + email);
        // [ROLE_USER, ROLE_ADMIN,..]
        String role = employee.get().getRole(); //this.appRoleDAO.getRoleNames(appUser.getUserId());
        System.out.println("role : " + role);


        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (role != null) {

            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);

        }
        UserDetails userDetails = (UserDetails) new User(employee.get().getEmail(), employee.get().getPassword(), grantList);

        return userDetails;
    }
}
