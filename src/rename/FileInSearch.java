package rename;

import java.util.HashMap;
import java.util.Map;

public class FileInSearch extends search.FileInSearch {
    public Map<String, Integer> map = new HashMap<>();

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public static void main(String[] args) {
        final String PATH = "D:\\loglog";
        final String[] inKeyword = {};
        final String[] orKeyword = {"20180831-0013631", "20180831-0013723", "20180831-0003285", "20180831-0003482"};
        final String[] exceptKeyword = {};

        FileInSearch check = new FileInSearch();
        check.returnFileInSearch(PATH, inKeyword, orKeyword, exceptKeyword);

        for (String key : check.getMap().keySet()) {
            System.out.println(key + "\t" + check.getMap().get(key));
        }
    }

    public void checkPattern(String line) {
//        System.out.println(line);
		String temp[] = line.split("price=");
		temp = temp[1].split("&sc=");

		if (this.map.get(temp[0]) == null) {
			this.map.put(temp[0], 1);
		} else {
			this.map.put(temp[0], this.map.get(temp[0])+1);
		}
    }


}
