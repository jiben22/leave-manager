package fr.enssat.leave_manager.model;

public abstract class PKGenerator {
    protected String generatePK(String prefix) {
        long timestamp = System.nanoTime()/10;
        int r = (int) (Math.random() * (99999 - 10000) + 10000);
        String generated = String.valueOf(r).substring(1, 5);
        return prefix+"-"+timestamp+"-"+generated;
    }
}
