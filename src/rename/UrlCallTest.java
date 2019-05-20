package rename;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UrlCallTest extends FileInSearch{

    public void checkPattern(String line) {
        System.out.println(line);
//
//        String temp[] = line.split("rtbWinApi");
//        temp = temp[1].split("bidfloorcur=KRW");
//        BufferedReader in = null;
//        System.out.println("http://www.mediacategory.com/rtb/rtbWinApi"+temp[0]+"bidfloorcur=KRW");
//        try {
//
//            URL obj = new URL("http://www.mediacategory.com/rtb/rtbWinApi"+temp[0]+"bidfloorcur=KRW"); // 호출할 url
//            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
//            con.setRequestMethod("GET");
//
//            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//
//            String line2;
//            while((line2 = in.readLine()) != null) { // response를 차례대로 출력
//                System.out.println(line2);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                Thread.sleep(5000l);
//            } catch (Exception e1) {
//
//            }
//
//        }
//
//        try {
////            Thread.sleep(1000l);
//        } catch (Exception e1) {
//
//        }
    }

    public static void main(String[] args) {

        final String PATH = "D:\\1234\\";
        final String[] inKeyword = {"rtbWinApi"," 400 "};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        UrlCallTest check = new UrlCallTest();
        int cnt = check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
        System.out.println(cnt);
    }
}
