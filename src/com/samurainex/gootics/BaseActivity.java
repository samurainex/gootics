package com.samurainex.gootics;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	protected EasyTracker gaTracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 gaTracker = EasyTracker.getInstance(this);
		 super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {

		gaTracker.activityStart(this);
	    
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		
		gaTracker.activityStop(this);

		super.onStop();
	}
}