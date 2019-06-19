package test;


public class ADIDTest extends FileInSearch {

    static int count1 = 0;
    static int count2 = 0;
    public void checkPattern(String line) {
        try {
            if(line.contains("is adid not found")) {
                count1++;
            } else if (line.contains("is adid found")){
                count2++;
            } else {
                System.out.println(line);
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {

        final String PATH = "D:\\TEST01";
        final String[] inKeyword = {"is adid"};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        ADIDTest check = new ADIDTest();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
        System.out.println(count1);
        System.out.println(count2);
    }
}
