package count;

import java.io.*;
import java.net.URLDecoder;

public class Research {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\log\\17_00.txt");
        StringBuilder sbr = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufReader.readLine()) != null) {
            if (!line.contains("0900] \"GET /servlet/rd?")) {
                sbr.append(URLDecoder.decode(line, "UTF-8"));
                sbr.append("\r\n");
            }
        }
        writeFile(sbr);
    }

    private static void writeFile(StringBuilder sbr) throws IOException {
        File fout = new File("D:\\log\\out.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        for (int i = 0; i < 10; i++) {
            osw.write(sbr.toString());
        }
        osw.close();
    }
}
