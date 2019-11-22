package fr.enssat.leave_manager.service.exception;

public class LeaveRequestRemainingLeaveException extends RuntimeException {
    public LeaveRequestRemainingLeaveException(double leave, long wanted_leave){
        super("Number of asked leave exceeds available leave (Available: "+leave+", Asked: "+wanted_leave+")!");
    }
}
