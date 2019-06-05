package search;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class FileInSearch implements FileSearch {

    public int returnFileInSearch(
            final String PATH,
            final String[] inKeyword,
            final String[] orKeyword,
            final String[] exceptKeyword) {

        int cnt = 0; // 총 카운트 수
        File[] listOfFiles = FileUtil.fileList(PATH);
        for (File file : listOfFiles) {
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufReader = new BufferedReader(fileReader)){

                String line;
                while ((line = bufReader.readLine()) != null) {
                    boolean patternOn = false;

                    // 해당 배열이 or 조건으로 존재하면 무조건 true
                    for (String keyword : orKeyword) {
                        if (line.contains(keyword)) {
                            patternOn = true;
                            break;
                        }
                    }

                    // 해당 배열이 전부 있어야 함.
                    for (String keyword : inKeyword) {
                        if (line.contains(keyword)) {
                            patternOn = true;
                        } else {
                            patternOn = false;
                            break;
                        }
                    }

                    // 해당 배열이 하나라도 있으면 false
                    if (patternOn && exceptKeyword.length > 0) {
                        for (String keyword : exceptKeyword) {
                            if (line.contains(keyword)) {
                                patternOn = false;
                                break;
                            }
                        }
                    }

                    if (patternOn) {
                        try {
                            cnt++;
                            checkPattern(line);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cnt;
    }
}
