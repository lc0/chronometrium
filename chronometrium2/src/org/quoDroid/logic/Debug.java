package org.quoDroid.logic;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class Debug {

	public static void main(Context context) {
		Storage s = new Storage(context);
		s.saveTimepoint(new TimePoint(0, 20));
		
		System.out.println(s);
		
		//int i = s.unreadCount();
		//List<Quote> rez = s.findQuotes("а11");
		System.out.println("OK");
	}
}