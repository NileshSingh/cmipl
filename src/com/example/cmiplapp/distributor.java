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
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class distributor extends Activity {
 TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distributor);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
       // text=(TextView) findViewById(R.id.textView1);

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
            HttpGet request = new HttpGet("http://10.254.118.189:8080/consumer_php/distributor.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            data=EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {
             
       JSONArray json=new JSONArray(data);
       for(int i=0;i<json.length(); i++)
       {
        JSONObject obj=json.getJSONObject(i);
        String name=obj.getString("name");
        
        System.out.println("hellods"+name);
       String year=obj.getString("address");
        //String age=obj.getString("age");
        Log.e("STRING", name);
        r.add(name);
       // r.add(year);
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