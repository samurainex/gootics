package com.samurainex.gootics;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import android.os.Bundle;
import android.view.Menu;

public class SpecialActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special);
		
		gaTracker.send(MapBuilder
			.createEvent("custom", "open", "activity", 1l)
		    .set(Fields.customDimension(1), "premiumUser")
		    .set(Fields.customMetric(1), "5")
		    .build()
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_special, menu);
		return true;
	}

}
