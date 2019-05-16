package util;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Decrypter {
    private static final int INITIALIZATION_VECTOR_SIZE = 16;
    private static final int SIGNATURE_SIZE = 4;
    private static final int BLOCK_SIZE = 20;

    public static String unWebSafeAndPad(String webSafe) {
        String pad = "";
        if ((webSafe.length() % 4) == 2) {
            pad = "==";
        } else if ((webSafe.length() % 4) == 3) {
            pad = "=";
        }
        return webSafe.replace('-', '+').replace('_', '/') + pad;
    }

    public static byte[] decrypt(byte[] ciphertext, SecretKey encryptionKey, SecretKey integrityKey)
            throws Exception {
        try {
            final int plaintext_length = ciphertext.length - INITIALIZATION_VECTOR_SIZE - SIGNATURE_SIZE;
            if (plaintext_length < 0) {
                throw new RuntimeException("The plain text length can't be negative.");
            }

            byte[] iv = Arrays.copyOf(ciphertext, INITIALIZATION_VECTOR_SIZE);

            final Mac hmacer = Mac.getInstance("HmacSHA1");
            final int ciphertext_end = INITIALIZATION_VECTOR_SIZE + plaintext_length;
            final byte[] plaintext = new byte[plaintext_length];
            boolean add_iv_counter_byte = true;
            for (int ciphertext_begin = INITIALIZATION_VECTOR_SIZE, plaintext_begin = 0;
                 ciphertext_begin < ciphertext_end; ) {
                hmacer.reset();
                hmacer.init(encryptionKey);
                final byte[] pad = hmacer.doFinal(iv);

                int i = 0;
                while (i < BLOCK_SIZE && ciphertext_begin != ciphertext_end) {
                    plaintext[plaintext_begin++] =
                            (byte) (ciphertext[ciphertext_begin++] ^ pad[i++]);
                }

                if (!add_iv_counter_byte) {
                    final int index = iv.length - 1;
                    add_iv_counter_byte = ++iv[index] == 0;
                }

                if (add_iv_counter_byte) {
                    add_iv_counter_byte = false;
                    iv = Arrays.copyOf(iv, iv.length + 1);
                }
            }

            hmacer.reset();
            hmacer.init(integrityKey);
            hmacer.update(plaintext);
            hmacer.update(Arrays.copyOf(ciphertext, INITIALIZATION_VECTOR_SIZE));
            final byte[] computedSignature = Arrays.copyOf(hmacer.doFinal(), SIGNATURE_SIZE);
            final byte[] signature = Arrays.copyOfRange(ciphertext, ciphertext_end, ciphertext_end + SIGNATURE_SIZE);
            if (!Arrays.equals(signature, computedSignature)) {
                throw new Exception("Signature mismatch.");
            }
            return plaintext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("HmacSHA1 not supported.", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Key is invalid for this purpose.", e);
        }
    }
}
