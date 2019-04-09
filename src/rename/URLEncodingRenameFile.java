package rename;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import util.FileUtil;

/**
 * 특정 경로의 URLEncoding 된 파일들을 전부 Decoding 처리한다.
 * 인터넷에서 받은 파일들을 Decoding 처리하는 용도로 사용한다.
 *
 * @author bymin
 */
public class URLEncodingRenameFile {
    final static String PATH = "경로를 삽입합니다!";

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
                            System.out.println(fileNm + "폴더 삭제!");
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
        File fileNew = new File(URLDecoder.decode(filename, StandardCharsets.UTF_8));
        if (file.exists()) {
            boolean result = file.renameTo(fileNew);
            if (result) {
                System.out.println(fileNew + "으로 이름변경 성공!");
            }
        }
    }
}
