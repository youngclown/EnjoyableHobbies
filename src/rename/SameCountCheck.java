package rename;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 두개의 파일에서 같은 파일들을 검색하여, 틀릴 경우 틀린 데이터를 보여줍니다.
 */
public class SameCountCheck {
    public static void main(String[] args) {
        ArrayList<String> list = returnListChk("D:\\test001\\1.txt");
        ArrayList<String> list2 = returnListChk("D:\\test001\\2.txt");
        for (String string : list) {
            boolean chk = true;
            for (String string2 : list2) {
                if (string.equals(string2)) {
                    chk = false;
                }
            }
            if (chk) {
                System.out.println(string);
            }
        }
    }

    private static ArrayList<String> returnListChk(String path) {
        File file = new File(path);
        ArrayList<String> list = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            while ((line = bufReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
