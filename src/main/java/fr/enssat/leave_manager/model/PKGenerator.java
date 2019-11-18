package fr.enssat.leave_manager.model;

public abstract class PKGenerator {
    protected static  String generatePK(String prefix) {
        // prevent that timestamp length equal 15
        String timestamp = String.valueOf(System.nanoTime()*99999).substring(1, 16);
        int r = (int) (Math.random() * (99999 - 10000) + 10000);
        String generated = String.valueOf(r).substring(1, 5);
        return prefix+"-"+timestamp+"-"+generated;
    }
}
