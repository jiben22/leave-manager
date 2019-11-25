package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    List<EmployeeEntity> findByFirstname(String firstname);
    List<EmployeeEntity> findByLastname(String lastname);
    Optional<EmployeeEntity> findByEmail(String email);

    /*@Modifying(clearAutomatically = true)
    @Query(value = "update employee set password = :password where eid = :eid", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("eid") String eid);*/

}
