package fr.enssat.leave_manager.model;

public abstract class PKGenerator {
    protected static  String generatePK(String prefix) {
        long timestamp = System.nanoTime();
        int r = (int) (Math.random() * (99999 - 10000) + 10000);
        String generated = String.valueOf(r).substring(1, 5);
        return prefix+"-"+timestamp+"-"+generated;
    }
}
