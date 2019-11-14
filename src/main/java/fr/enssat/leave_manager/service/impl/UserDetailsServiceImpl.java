package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        Optional<EmployeeEntity> employee = employeeRepository.findByEmail(email);

        if (!employee.isPresent()) {
            BCryptPasswordEncoder t = new BCryptPasswordEncoder();
            String v = t.encode("ttt");
            System.out.println(v);
            System.out.println("Mail not found! " + email);
            throw new UsernameNotFoundException("User mail " + email + " was not found in the database");
        }

        System.out.println("Found mail: " + email);
        // [ROLE_USER, ROLE_ADMIN,..]
        String role = employee.get().getRole(); //this.appRoleDAO.getRoleNames(appUser.getUserId());


        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (role != null) {

            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);

        }

        return new org.springframework.security.core.userdetails.User(employee.get().getEmail(), employee.get().getPassword(), grantList);
    }
}
