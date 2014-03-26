package search;

public class Searcher {
	private String sentence = "";

	public Searcher(String sentence) {
		this.sentence = sentence;
		inspectSentence();
	}

	public SearchResult search(String keyword) {
		if (isEmpty(keyword) || isNotContains(keyword) || isPreviousChar(keyword) || isNextChar(keyword))
			return new SearchResult(null);
		else
			return new SearchResult(accent(keyword));
	}

	public String accent(String keyword) {
		String previousSentence = sentence.substring(0, sentence.indexOf(keyword));
		String nextSentence = sentence.substring(sentence.indexOf(keyword) + keyword.length());
		return previousSentence + "{" + keyword + "}" + nextSentence;
	}

	private boolean isNotContains(String keyword) {
		return !sentence.contains(keyword);
	}

	private void inspectSentence() {
		if (sentence == null || isEmpty(sentence))
			throw new IllegalArgumentException("input sentence : '" + sentence + "'");
	}

	private boolean isEmpty(String value) {
		return value.equals("");
	}

	private boolean isPreviousChar(String keyword) {
		int index = sentence.indexOf(keyword) - 1;
		if (index <= 0)
			return false;
		return isAlphNumberUnderline(sentence.charAt(index));
	}

	private boolean isNextChar(String keyword) {
		int index = sentence.indexOf(keyword);
		int pickupLength = index + keyword.length();
		if (pickupLength >= sentence.length())
			return false;
		return isAlphNumberUnderline(sentence.charAt(pickupLength));
	}

	private boolean isAlphNumberUnderline(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ('_' == ch);
	}
}