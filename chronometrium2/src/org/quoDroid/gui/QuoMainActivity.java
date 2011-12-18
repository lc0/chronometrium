package org.quoDroid.gui;

import org.quoDroid.R;
import org.quoDroid.logic.ActivityP;
//import org.quoDroid.logic.Quote;
//import org.quoDroid.logic.QuoteHelper;
import org.quoDroid.logic.SocialLink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class QuoMainActivity extends Activity {
	private TextView textView;
	private TextView textViewAuthor;
	//private QuoteHelper quoteHelper;
	//private Quote currentQuote;
	
	private ActivityP currentActivity;

	public static final int TWITTER_ID = Menu.FIRST;
	public static final int FACEBOOK_ID = Menu.FIRST + 1;
	public static final int VK_ID = Menu.FIRST + 2;
	public static final int FAVE_ID = Menu.FIRST + 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//quoteHelper = new QuoteHelper(getApplicationContext());
		setContentView(R.layout.main);
		onScreenSwitchListener = new RealViewSwitcher.OnScreenSwitchListener() {

			public void onScreenSwitched(int screen) {
				TextView tw = (TextView) findViewById(R.id.quote_view2);
				TextView cur = (TextView) findViewById(R.id.quote_view);
				TextView aw = (TextView) findViewById(R.id.quote_author);
				TextView aw2 = (TextView) findViewById(R.id.quote_author2);
				cur.setText(tw.getText());
				aw.setText(aw2.getText());
				
				RealViewSwitcher r = (RealViewSwitcher) findViewById(R.id.switcher);
				r.setCurrentScreen(0);
				
				//Quote nextQuote = quoteHelper.getRandomQuote();
				currentActivity =  new ActivityP(30.525553, 50.451611, 5, "center of Kiev", "center of Kiev", 1324154786, 2);
				
				TextView ntw = (TextView) findViewById(R.id.quote_view2);
				TextView naw = (TextView) findViewById(R.id.quote_author2);
				ntw.setText(currentActivity.getMessage() );
				naw.setText("Chronometrium");

			}

		};
		RealViewSwitcher r = (RealViewSwitcher) findViewById(R.id.switcher);
		r.setOnScreenSwitchListener(onScreenSwitchListener);
		Intent intent = getIntent();
		//quoteHelper = new QuoteHelper(getApplicationContext());

		if (intent.getAction().equals("android.intent.action.MAIN")) {
			
			
			/**
			 * @TODO getting gps position
			 */
			
			// Acquire a reference to the system Location Manager
			LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

			// Define a listener that responds to location updates
			LocationListener locationListener = new LocationListener() {
			    public void onLocationChanged(Location location) {
			      // Called when a new location is found by the network location provider.
			      trackMyPlace(location);
			    }

			    public void onStatusChanged(String provider, int status, Bundle extras) {}

			    public void onProviderEnabled(String provider) {}

			    public void onProviderDisabled(String provider) {}
			  };

			// Register the listener with the Location Manager to receive location updates
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER , 0, 0, locationListener);
			
			Log.v("LOG_TAG", "Testing is working correctly");
			
			/**
			 * @TODO getting current activity and checking
			 */
			
			//currentQuote = quoteHelper.getRandomQuote();
			currentActivity =  new ActivityP(30.525553, 50.451611, 5, "center of Kiev", "center of Kiev", 1324154786, 2);
			
			textView = (TextView) findViewById(R.id.quote_view);
			textViewAuthor = (TextView) findViewById(R.id.quote_author);
			textView.setText(currentActivity.getMessage() );
			textViewAuthor.setText("Chronomium");
			
			//Quote nextQuote = quoteHelper.getRandomQuote();
			TextView ntw = (TextView) findViewById(R.id.quote_view2);
			TextView naw = (TextView) findViewById(R.id.quote_author2);
			//ntw.setText(nextQuote.getText());
			//naw.setText(nextQuote.getAuthor());
			
			/**
			 * @TODO: next category from list
			 * 
			 */
			
			ntw.setText(currentActivity.getMessage());
			naw.setText("Chronometrium");
		}
	}

	protected void trackMyPlace(Location location) {
		// TODO Auto-generated method stub
		Log.v("LOG_TAG", location.toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);

		menu.add(0, TWITTER_ID, 0, R.string.menu_twitter).setIcon(
				android.R.drawable.ic_menu_preferences);
		menu.add(0, FACEBOOK_ID, 0, R.string.menu_facebook).setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(0, VK_ID, 0, R.string.menu_vk).setIcon(
				android.R.drawable.ic_menu_send);
		menu.add(0, FAVE_ID, 0, R.string.menu_sms).setIcon(
				android.R.drawable.ic_menu_info_details);

		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case TWITTER_ID: {
			invokeSharing(SocialLink.getTwitterLink(currentActivity));
			return true;
		}
		case FACEBOOK_ID: {
			invokeSharing(SocialLink.getFacebookLink(currentActivity));
			return true;
		}
		case VK_ID: {
			invokeSharing(SocialLink.getVkLink(currentActivity));
			return true;
		}
		case FAVE_ID: {
			invokeSharing(SocialLink.getFacebookLink(currentActivity));
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	public void invokeSharing(String text) {
		Intent browserIntent = new Intent("android.intent.action.VIEW", Uri
				.parse(text));
		startActivity(browserIntent);
	}

	private RealViewSwitcher.OnScreenSwitchListener onScreenSwitchListener;// = new RealViewSwitcher.OnScreenSwitchListener() {

//		public void onScreenSwitched(int screen) {
//			TextView tw = (TextView) findViewById(R.id.quote_view2);
//			TextView cur = (TextView) findViewById(R.id.quote_view);
//			cur.setText(tw.getText());
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			RealViewSwitcher r = (RealViewSwitcher) findViewById(R.id.switcher);
//			r.setCurrentScreen(0);
//
//
//		}
//
//	};
}
