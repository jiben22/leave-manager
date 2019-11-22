package fr.enssat.leave_manager.utils.enums;

public enum LeaveStatus {
    PENDING("En attente"),
    CANCELLED("Annulé"),
    ACCEPTED("Accepté"),
    DECLINED("Refusé");

    private String fr;

    LeaveStatus(String fr) {
        this.fr = fr;
    }

    public String toString(){
        return this.fr;
    }
}