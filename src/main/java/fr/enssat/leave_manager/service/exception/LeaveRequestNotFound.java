package fr.enssat.leave_manager.service.exception;

public class LeaveRequestNotFound extends NotFoundException {

    public LeaveRequestNotFound(String id) {
        super("Leave request : " + id);
    }
}
