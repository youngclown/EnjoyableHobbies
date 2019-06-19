package searchTest;

/**
 * 와이프가 무슨 파일을 분석해달라고 해서 만든 파일.
 */
public class WifeLove extends search.FileInSearch{
	public static void main(String[] args) {
		final String PATH = "D:\\log";
		final String[] inKeyword = {"naver"};
		final String[] exceptKeyword = {};

		WifeLove check = new WifeLove();
//		check.returnFileInSearch(PATH, inKeyword, exceptKeyword);
	}

	@Override
	public void checkPattern(String line) {
		System.out.println(line);
	}
}
