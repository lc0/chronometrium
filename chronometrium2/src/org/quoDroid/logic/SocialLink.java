package org.quoDroid.logic;


public class SocialLink {
		
	public static String getFacebookLink(ActivityP act) {
		return "http://www.facebook.com/sharer.php?u="+
		act.getProfileUrl() + "&t=" + act.getMessage();		
	}
	
	public static String getTwitterLink(ActivityP act) {
		int size = act.getProfileUrl().length();
		return "http://twitter.com/home?status="+act.getMessage().substring(0, 140-size-3)+".. "+
		act.getProfileUrl();		
	}
	
	public static String getVkLink(ActivityP act) {
		return "http://vkontakte.ru/share.php?url="+
		act.getProfileUrl();		
	}	

}
