package rename;

import util.FileUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 특정 경로의 URLEncoding 된 파일들을 전부 Decoding 처리한다.
 * 인터넷에서 받은 파일들을 Decoding 처리하는 용도로 사용한다.
 *
 * @author bymin
 */
public class URLEncodingRenameFile {
    private final static String PATH = "D:\\2345\\123456";

    public static void main(String[] args) {
        URLEncodingRenameFile rename = new URLEncodingRenameFile();
        rename.renameFileList(FileUtil.fileList(PATH));
    }

    private void renameFileList(File[] listOfFiles) {
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    renameFile(file.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("listOfFiles = [" + file.getAbsolutePath() + "]");
                    e.printStackTrace();
                }
            } else {
                File[] list = file.listFiles();
                if (list != null) {
                    if (list.length > 0) {
                        renameFileList(list);
                    } else {
                        String fileNm = file.getAbsolutePath();
                        boolean result = file.delete();
                        if (result) {
                            System.out.println(fileNm + " folder delete!");
                        }
                    }
                } else {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    private void renameFile(String filename) {
        File file = new File(filename);
        try {
            // 한칸 빈값을 지우면서 새로운 파일명으로 변경
            File fileNew = new File(URLDecoder.decode(filename, "UTF-8").replace(" ",""));
            if (file.exists()) {
                boolean result = file.renameTo(fileNew);
                if (result) {
                    System.out.println(fileNew + " name change success!");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
