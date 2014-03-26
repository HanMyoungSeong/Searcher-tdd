package search;


public class Main {

	public static void main(String[] args) {
		Searcher searcher = new Searcher("flex?");
		SearchResult searchResult = searcher.search("flex");
		System.out.println(searchResult.getValue());
	}
	
	 
}
