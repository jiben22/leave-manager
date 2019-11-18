package fr.enssat.leave_manager.service.exception.not_found;

public class LeaveRequestNotFoundException extends NotFoundException {

    public LeaveRequestNotFoundException(String id) {
        super("Leave request : " + id);
    }
}
