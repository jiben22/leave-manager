package fr.enssat.leave_manager.repository;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfLeaveRepository extends JpaRepository<TypeOfLeaveEntity, String> {
    List<TypeOfLeaveEntity> findByNameAndIsArchivedFalse(String name);
    List<TypeOfLeaveEntity> findAllByIsArchivedFalse();
    List<TypeOfLeaveEntity> findAllByIsArchivedTrue();
}
