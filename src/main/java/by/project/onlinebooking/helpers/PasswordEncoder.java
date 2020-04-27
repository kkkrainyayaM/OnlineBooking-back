package by.project.onlinebooking.helpers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncoder {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private PasswordEncoder() {
    }

    public static String encode(String password) {
        return PASSWORD_ENCODER.encode(password);
    }
}
