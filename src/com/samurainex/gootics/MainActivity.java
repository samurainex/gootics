package com.samurainex.gootics;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

import com.google.analytics.tracking.android.ExceptionReporter;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.StandardExceptionParser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		Uri uri = intent.getData();

	    // Send a screen view using any available campaign or referrer data.
	    gaTracker.send(
	    		MapBuilder.createAppView().setAll(getReferrerMapFromUri(uri)).build());
		
		Button specialButt = (Button) findViewById(R.id.special_button);
		specialButt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent tenten = new Intent(MainActivity.this, SpecialActivity.class);
				startActivity(tenten);
			}
		});

		UncaughtExceptionHandler exHandler = new ExceptionReporter(
			    GoogleAnalytics.getInstance(this).getTracker("UA-46161255-2"), 				// Tracker, may return null if not yet initialized.
			    GAServiceManager.getInstance(),                        						// GAServiceManager singleton.
			    Thread.getDefaultUncaughtExceptionHandler(), MainActivity.this);			// Current default uncaught exception handler.

		// Make myHandler the new default uncaught exception handler.
		Thread.setDefaultUncaughtExceptionHandler(exHandler);
		
		int[] i = new int[6];
		try {
			i[7] = 0;
			
			} catch (Exception e) {
			// May return null if EasyTracker has not yet been initialized with a
			// property ID.
		
			// StandardExceptionParser is provided to help get meaningful Exception descriptions.
			gaTracker.send(MapBuilder
			      .createException(new StandardExceptionParser(this, null)				// Context and optional collection of package names to be used in reporting the exception.
			      .getDescription(Thread.currentThread().getName(),						// The name of the thread on which the exception occurred.
			       e),								                                 	// The exception.
			       false)																// False indicates a fatal exception
			      .build()
			);
			}
		i[7] = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/*
	 * Given a URI, returns a map of campaign data that can be sent with
	 * any GA hit.
	 *
	 * @param uri A hierarchical URI that may or may not have campaign data
	 *     stored in query parameters.
	 *
	 * @return A map that may contain campaign or referrer
	 *     that may be sent with any Google Analytics hit.
	 */
	Map<String,String> getReferrerMapFromUri(Uri uri) {

	    MapBuilder paramMap = new MapBuilder();

	    // If no URI, return an empty Map.
	    if (uri == null) { return paramMap.build(); }

	    // Source is the only required campaign field. No need to continue if not
	    // present.
	    if (uri.getQueryParameter("utm_source") != null) {

	    // MapBuilder.setCampaignParamsFromUrl parses Google Analytics campaign
	    // ("UTM") parameters from a string URL into a Map that can be set on
	    // the Tracker.
	    paramMap.setCampaignParamsFromUrl(uri.toString());

	    // If no source parameter, set authority to source and medium to
	    // "referral".
	    } else if (uri.getAuthority() != null) {

	    	paramMap.set(Fields.CAMPAIGN_MEDIUM, "referral");
	    	paramMap.set(Fields.CAMPAIGN_SOURCE, uri.getAuthority());

	    }

	    return paramMap.build();
	}
}
