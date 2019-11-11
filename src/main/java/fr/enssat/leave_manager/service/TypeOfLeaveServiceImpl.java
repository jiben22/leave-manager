package fr.enssat.leave_manager.service;

import fr.enssat.leave_manager.repository.TypeOfLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeOfLeaveServiceImpl implements TypeOfLeaveService {

    private final TypeOfLeaveRepository typeOfLeaveRepository;

    @Autowired
    public TypeOfLeaveServiceImpl(TypeOfLeaveRepository typeOfLeaveRepository) {
        this.typeOfLeaveRepository = typeOfLeaveRepository;
    }
}
