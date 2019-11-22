package fr.enssat.leave_manager.service.exception.not_found;

public class HRDNotFoundException extends NotFoundException {
    public HRDNotFoundException(String msg) {
        super("HRD "+msg);
    }
}
