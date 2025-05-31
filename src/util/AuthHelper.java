package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthHelper {

    // Hacher un mot de passe avec SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Vérifier un mot de passe en comparant le hash
    public static boolean verifyPassword(String enteredPassword, String storedHash) {
        return hashPassword(enteredPassword).equals(storedHash);
    }
}
