package searchTest;

public class UrlCallTest extends FileInSearch {

    public void checkPattern(String line) {
        System.out.println(line);
    }

    public static void main(String[] args) {

        final String PATH = "D:\\1234\\";
        final String[] inKeyword = {"rtbWinApi"," 400 "};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        UrlCallTest check = new UrlCallTest();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
    }
}
