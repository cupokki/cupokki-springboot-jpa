package io.cupokki.webmvcboilerplate.util;

import org.springframework.security.crypto.bcrypt.BCrypt;


// BCrypt를 직접 사용한 방법, PasswordEncoder 인터페이스를 사용하면 쓸일 없다.
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
