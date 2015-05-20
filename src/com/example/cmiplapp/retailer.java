package com.example.cmiplapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class retailer extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        connect();
        
	}

	private void connect() {
	      String data;
	      List<String> r = new ArrayList<String>();
	      
	      ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,r);
	      ListView list=(ListView)findViewById(R.id.listView1);
	      
	     // list.setBackgroundColor(Color.CYAN);
	      //list.set
	        try {
	            DefaultHttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet("http://10.105.15.252:8080/consumer_php/retailer.php");
	            HttpResponse response = client.execute(request);
	            HttpEntity entity=response.getEntity();
	            data=EntityUtils.toString(entity);
	            Log.e("STRING", data);
	            try {
	             
	       JSONArray json=new JSONArray(data);
	       for(int i=0;i<json.length(); i++)
	       {
	        JSONObject obj=json.getJSONObject(i);
	        String name1=obj.getString("name");
	        //String year=obj.getString("year");
	       // String address1=obj.getString("address");
	        Log.e("STRING", name1);
	        r.add(name1);
	        //r.add(address1);
	        //r.add(age);
	        list.setAdapter(adapter);
	        
	       }
	       
	      } catch (JSONException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	      }
	          
	        } catch (ClientProtocolException e) {
	            Log.d("HTTPCLIENT", e.getLocalizedMessage());
	        } catch (IOException e) {
	            Log.d("HTTPCLIENT", e.getLocalizedMessage());
	        }
	      
	      
	    }
}
