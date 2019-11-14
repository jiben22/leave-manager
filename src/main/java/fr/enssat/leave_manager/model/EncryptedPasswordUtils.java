package fr.enssat.leave_manager.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        System.out.println("Password" + password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode(password);
        System.out.println(encode);
        return encode;
    }

    public static void test() {
        String password = "123";
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
