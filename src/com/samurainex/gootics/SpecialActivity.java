package com.samurainex.gootics;

import android.os.Bundle;
import android.view.Menu;

public class SpecialActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_special, menu);
		return true;
	}

}
