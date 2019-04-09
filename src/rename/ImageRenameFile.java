package rename;

import java.io.File;
import java.io.IOException;

/**
 * 특정 패턴을 가진 파일의 마지막 명을 찾은 후 다른 확장자 명으로 강제로 변경하기
 *
 * @author bymin
 */
public class ImageRenameFile {
    final static String PATH = "경로를 입력합니다.";
    final static String ORIGINAL_PATH = "log";
    final static String CHANGE_PATH = ".log";

    public ImageRenameFile() {
        subDirList(PATH);
    }

    public void subDirList(String source) {
        File dir = new File(source);
        File[] fileList = dir.listFiles();
        try {
            for (int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if (file.isFile()) {
                    // 파일이 있다면 파일 이름 출력
//                    System.out.println("\t 파일 이름 = " + file.getName());
//                    System.out.println("\t 파일 이름 = " + file.getAbsolutePath());
                    if ((file.getName().lastIndexOf(ORIGINAL_PATH)+ORIGINAL_PATH.length()) == file.getName().length()) {
                        file.renameTo(new File(file.getAbsolutePath()+CHANGE_PATH));
                    }
                } else if (file.isDirectory()) {
//                    System.out.println("디렉토리 이름 = " + file.getName());
                    // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
                    subDirList(file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ImageRenameFile();
    }
}
