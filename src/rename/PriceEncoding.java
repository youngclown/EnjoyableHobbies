package rename;

import util.Base64;
import util.Decrypter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PriceEncoding {

    public static void main(String[] args) {

        final String PATH = "D:\\home\\0000000001";
        final String[] inKeyword = {"rtbWin"};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        FileInSearch check = new FileInSearch();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);

        long price = 0;
        int cnt = 0;
        for (String key : check.getMap().keySet()) {

            System.out.println(key + "\t" + check.getMap().get(key));
            byte[] codeString = Base64.decodeBase64(Decrypter.unWebSafeAndPad(key).getBytes());
            byte[] encryptionKeyBytes = Base64.decodeBase64(Decrypter.unWebSafeAndPad("eKey").getBytes());
            byte[] integrityKeyBytes = Base64.decodeBase64(Decrypter.unWebSafeAndPad("iKey").getBytes());
            byte[] plaintext;
            SecretKey encryptionKey = new SecretKeySpec(encryptionKeyBytes, "HmacSHA1");
            SecretKey integrityKey = new SecretKeySpec(integrityKeyBytes, "HmacSHA1");
            try {
                plaintext = Decrypter.decrypt(codeString, encryptionKey, integrityKey);
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(plaintext));
                price += dis.readLong() * check.getMap().get(key);
                cnt += check.getMap().get(key);
                System.out.println(dis.readLong() * check.getMap().get(key));
            } catch (IOException e) {
//                e.printStackTrace();
//            logger.error("Failed to decode ciphertext. {}",encryptionKey,e);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        System.out.println(cnt);
        System.out.println(price);
    }
}
