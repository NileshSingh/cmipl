package com.example.cmiplapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class main_page extends Activity implements OnClickListener {
	
	Button new_customer,sync,trade_cov,order_mgt,reports;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main_page); 
			
			new_customer=(Button) findViewById(R.id.new_cust);
			new_customer.setOnClickListener(this);
			
			sync=(Button) findViewById(R.id.sync);
			sync.setOnClickListener(this);
			
			reports=(Button) findViewById(R.id.reports);
			reports.setOnClickListener(this);
			
			order_mgt=(Button) findViewById(R.id.order_mgt);
			order_mgt.setOnClickListener(this);
			

}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(v==new_customer)
			{
				 //Intent i=new Intent(getBaseContext(),new_customer.class);
	               //startActivity(i);
			}
			
			if(v==sync)
			{
				// Intent i=new Intent(getBaseContext(),sync_database.class);
	              // startActivity(i);
			}
			if(v==reports)
			{
				// Intent i=new Intent(getBaseContext(),retailer.class);
	             //startActivity(i);
			}
			if(v==order_mgt)
			{
				 Intent i=new Intent(getBaseContext(),distributor.class);
	            startActivity(i);
			}
		}
}