package io.cupokki.webmvcboilerplate.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    /**
     * 평문 암호화
     * @param raw
     * @return
     */
    public static String hash(String raw) {
        String hashed = BCrypt.hashpw(raw, BCrypt.gensalt());
        return hashed;
    }

    public static boolean matches(String raw, String encrypted) {
        return BCrypt.checkpw(raw, encrypted);
    }
}
