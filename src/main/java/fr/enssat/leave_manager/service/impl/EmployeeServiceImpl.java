package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.repository.EmployeeRepository;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.exception.already_exists.EmployeeAlreadyExistException;
import fr.enssat.leave_manager.service.exception.not_found.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public EmployeeEntity getEmployee(String id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeEntity getEmployeeByEmail(String email) {
        return repository.findByEmail(email).orElse(null); //orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Override
    public List<EmployeeEntity> getEmployeeByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    @Override
    public List<EmployeeEntity> getEmployeeByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    @Override
    public List<EmployeeEntity> getEmployees() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        if (repository.existsById(employee.getEid()))
            throw new EmployeeAlreadyExistException(employee);
        return repository.saveAndFlush(employee);
    }


    @Override
    public EmployeeEntity editEmployee(EmployeeEntity employee) {
        if (!repository.existsById(employee.getEid()))
            throw new EmployeeNotFoundException(employee.getEid());
        return repository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }

    @Override
    //@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<EmployeeEntity> employee = repository.findByEmail(email);
        System.out.println(email);
        if (!employee.isPresent()) {
            /*** DEBUG ***/
            System.out.println("Mail not found! " + email);
            throw new UsernameNotFoundException("User mail " + email + " was not found in the database");
        }

        System.out.println("Found mail: " + email);
        // [ROLE_USER, ROLE_ADMIN,..]
        return new User(employee.get().getEmail(), employee.get().getPassword(), mapRolesToAuthorities(employee.get().getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    /*@Override
    public void updatePassword(String password, String userId) {
        repository.updatePassword(password, userId);
    }*/
}
