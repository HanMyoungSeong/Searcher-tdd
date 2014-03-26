package search;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SearcherTest {

	private void searchAssertThat(String sentence, String search, String expectedSearchResult) {
		Searcher searcher = new Searcher(sentence);
		SearchResult searchResult = searcher.search(search);
		assertThat(searchResult.getValue(), is(expectedSearchResult));
	}

	@Test
	public void //
	testSentence() {
		searchAssertThat("flex?", "", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void //
	should_throw_an_exception_when_input_sentence_is_null() {
		searchAssertThat(null, "flex", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void //
	should_throw_an_exception_when_input_sentence_is_empty() {
		searchAssertThat("", "flex", null);
	}

	@Test
	public void //
	should_return_not_matched_result_when_search_keyword_is_flex() {
		searchAssertThat("reflex", "flex", null);
		searchAssertThat("yes, a", "flex", null);
		searchAssertThat("yes,a flex1", "flex", null);
		searchAssertThat("yes, aflex1", "flex", null);
		searchAssertThat("yes, aflex1", "", null);
	}

	@Test
	public void //
	should_return_matched_result_when_search_keyword_is_flex() {
		searchAssertThat("flex?", "flex", "{flex}?");
		searchAssertThat("(flex)", "flex", "({flex})");
		searchAssertThat("no a flex", "flex", "no a {flex}");
		searchAssertThat("yes, a flex", "flex", "yes, a {flex}");
		searchAssertThat("yes, a flex and flexible", "flex", "yes, a {flex} and flexible");
	}
}