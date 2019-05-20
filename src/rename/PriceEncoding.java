package rename;

import util.Base64;
import util.Decrypter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class PriceEncoding {

    public static void main(String[] args) {

        final String PATH = "D:\\1234\\";
        final String[] inKeyword = {"rtbWin"};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        FileInSearch check = new FileInSearch();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);

        long price = 0;
        int cnt = 0;
        for (String key : check.getMap().keySet()) {

//            System.out.println(key + "\t" + check.getMap().get(key));
            byte[] codeString = Base64.decodeBase64(Decrypter.unWebSafeAndPad(key).getBytes());
            byte[] encryptionKeyBytes = Base64.decodeBase64(Decrypter.unWebSafeAndPad(getKey("file input")).getBytes());
            byte[] integrityKeyBytes = Base64.decodeBase64(Decrypter.unWebSafeAndPad(getKey("file input")).getBytes());
            byte[] plaintext;
            SecretKey encryptionKey = new SecretKeySpec(encryptionKeyBytes, "HmacSHA1");
            SecretKey integrityKey = new SecretKeySpec(integrityKeyBytes, "HmacSHA1");
            try {
                plaintext = Decrypter.decrypt(codeString, encryptionKey, integrityKey);
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(plaintext));
                price += dis.readLong() * check.getMap().get(key);
                cnt += check.getMap().get(key);
//                System.out.println(dis.readLong() * check.getMap().get(key));
            } catch (Exception e) {
                System.out.println("Failed to decode ciphertext. "+encryptionKey);
            }
        }

        // 4706 449
        // 2441	221

        // 4706 : 449 = 2551 : 221x
        // x =


        System.out.println(cnt);
        System.out.println(price/1000/1000);
    }

    static String getKey(String path){
        StringBuilder eb = new StringBuilder();
        try{
            //파일 객체 생성
            File file = new File(path);
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                eb.append(line);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
        return eb.toString();
    }
}
