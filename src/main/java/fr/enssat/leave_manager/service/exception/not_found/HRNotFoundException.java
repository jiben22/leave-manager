package fr.enssat.leave_manager.service.exception.not_found;

public class HRNotFoundException extends NotFoundException {
    public HRNotFoundException(String msg) {
        super("HR "+msg);
    }
}
