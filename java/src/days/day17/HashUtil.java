package days.day17;

import java.security.MessageDigest;

import java.util.*;

public class HashUtil {

    public static String getHash(final String input) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] b = md.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(b);
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
