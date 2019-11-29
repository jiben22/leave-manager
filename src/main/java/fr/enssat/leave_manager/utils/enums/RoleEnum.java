package fr.enssat.leave_manager.utils.enums;

public enum RoleEnum {
    HRD("Directeur des ressources humaines"),
    HR("Ressources humaines"),
    TEAMLEADER("Chef d'équipe"),
    EMPLOYEE("Employé");

    private String fr;

    RoleEnum(String fr) {
        this.fr = fr;
    }

    public String toString() {
        return this.fr;
    }
}
