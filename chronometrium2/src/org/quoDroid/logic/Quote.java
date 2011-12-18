package org.quoDroid.logic;

public class Quote {
	
    private String quoteLink;
	private String quoteText;
	private String quoteAuthor;
	private String quoteLang;

	private Boolean read;
    private Boolean fav;

	Quote (String text, String author, String link, String lang, boolean read, boolean fav) {
		quoteText = text;
		quoteAuthor = author;
		quoteLink = link;
		quoteLang = lang;
		this.read = read;
		this.fav = fav;
	}
	
	public Boolean isFav() {
		return fav;
	}
	
	public void setFave(Boolean on) {
		fav = on;
	}
	
    public Boolean isRead() {
        return read;
    }
    public void setRead(Boolean on) {
        read = on;
    }

	public String getLang() {
		return quoteLang;
	}
	
	public String getText() {
		return quoteText;
	}
	
	public String getAuthor() {
		return quoteAuthor;
	}
	
	public String getLink() {
		return quoteLink;
	}
	
	public String getFormattedText() {
		if (quoteAuthor!="") return quoteText+"/"+quoteAuthor+"/";
		return getText();
	}
}
