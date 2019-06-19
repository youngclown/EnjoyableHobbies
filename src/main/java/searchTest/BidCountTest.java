package searchTest;


public class BidCountTest extends FileInSearch {

    static int count1 = 0;
    static int count2 = 0;
    public void checkPattern(String line) {
        try {
            if(line.contains("_|_|")) {
                count1++;
            } else if (line.contains("ifa")){
                count2++;
            } else {
                System.out.println(line);
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {

        final String PATH = "D:\\5555";
        final String[] inKeyword = {};
        final String[] orKeyword = {"_|_|","ifa"};
        final String[] exceptKeyword = {};

        BidCountTest check = new BidCountTest();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
        System.out.println(count1);
        System.out.println(count2);
    }
}
