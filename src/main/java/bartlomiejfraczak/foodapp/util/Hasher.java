package bartlomiejfraczak.foodapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thefr
 */
public class Hasher {

    private static Hasher instancja;

    public static Hasher getInstancja() {
        if (instancja == null) {
            instancja = new Hasher();
        }
        return instancja;
    }

    private Hasher() {
    }

    public String hash(String str) {
        MessageDigest messageDigest;
        String hash = str;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            hash = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
}
