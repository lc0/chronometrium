package org.quoDroid.logic;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class QuoteHelper {

	private static JsonParser internetSource;
	private Storage databaseSource;

	public QuoteHelper(Context context) {
		//internetSource = new JsonParser();
		databaseSource = new Storage(context);
	}

	public Quote getRandomQuote() {
		int unreadLeft = databaseSource.unreadCount();
		if (unreadLeft <= 3) {
			List<Quote> quoteList = new ArrayList<Quote>();
			try {
				for (int i = 0; i < 5; i++) {
					//quoteList.add(internetSource.getQuote());
				}
			} catch (Exception e) {
			}
			if (quoteList.size() > 0) {
				databaseSource.storeQuotes(quoteList);
			}
		}
		Quote result = databaseSource.getQuote();
		result.setRead(true);
		databaseSource.storeQuote(result);
		return result;
	}

	public Quote getQuote(String quoteLink) {
		return databaseSource.getQuote(quoteLink);
	}

	public void setFave(Quote quote, boolean fave) {
		quote.setFave(fave);
		databaseSource.storeQuote(quote);
	}

	public List<Quote> findQuote(String query) {
		return databaseSource.findQuotes(query);
	}

	public List<Quote> getFavouriteQuotes() {
		return databaseSource.getFaves();
	}

}
