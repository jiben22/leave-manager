package fr.enssat.leave_manager.model;

public class WatchEmployee {
    private String lastname;
    private String firstname;
    private String team;
    private String teamManager;

    public WatchEmployee(String lastname, String firstname, String team, String teamManager) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.team = team;
        this.teamManager = teamManager;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(String teamManager) {
        this.teamManager = teamManager;
    }
}
