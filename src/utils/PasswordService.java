package utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordService {

    private static final SecureRandom random = new SecureRandom();
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int iterations = 10000;
    private static final int keylength = 256;


    public static String generateRandomPasseword(int length){
        int start = 48;
        int end = 122;
        
        return random.ints(start, end + 1)
                    .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                    .toString();
    }

    public static String getSaltvalue(int length){
        StringBuilder value = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            value.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(value);
    }

    public static byte[] hash(char[] password,byte[] salt){
        PBEKeySpec spec = new PBEKeySpec(password,salt,iterations,keylength);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory factory  = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
            //e.printStackTrace();
        }
        finally{
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password,String salt){
        String value =null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        value = Base64.getEncoder().encodeToString(securePassword);
        return value; 
    }

    public static boolean checkPassword(String rawPassword,String securePassword,String salt){
        String encryptPassword = generateSecurePassword(rawPassword, salt);
        return encryptPassword.equals(securePassword);
    }


    public static String encryptPassword(String password){
        
        String key = System.getenv("SECRETKEY_JAVA");
        if(key == null){
            System.out.println("key not found");
        }

        String ecryptedPassword = generateSecurePassword(password, key);

        return ecryptedPassword;
    }


    public static boolean verifyPassword(String password,String encryptPassword){
        String key = System.getenv("SECRETKEY_JAVA");
        String passwordEncypt = generateSecurePassword(password, key);
        return passwordEncypt.equals(encryptPassword);
    }

   

}
