package com.example.cmiplapp;


import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

public class Splash_screen extends Activity {

	private static final int TIME = 4 * 1000;// 4 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		//ActionBar ab = getActionBar(); 
        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0066FF"));     
        //ab.setBackgroundDrawable(colorDrawable);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(Splash_screen.this,
						LoginPage.class);
				startActivity(intent);

				Splash_screen.this.finish();

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

			}
		}, TIME);
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			         } 
			    }, TIME);

	}

	
	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}


