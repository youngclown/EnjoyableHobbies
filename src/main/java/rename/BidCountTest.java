package rename;


public class BidCountTest extends FileInSearch{

    static int count = 0;
    public void checkPattern(String line) {
//
        String temp[] = line.split("\"bidCnt\":");
        temp = temp[1].split(",\"bidErnAmt\":");
        try {
            count += Integer.parseInt(temp[0]);
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {

        final String PATH = "D:\\workset\\kafka-openRtb.Data.logging.OpenRTBBid_Adfit.2019-05-21_03";
        final String[] inKeyword = {"bidCnt"};
        final String[] orKeyword = {};
        final String[] exceptKeyword = {};

        BidCountTest check = new BidCountTest();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);
        System.out.println(count);
    }
}
