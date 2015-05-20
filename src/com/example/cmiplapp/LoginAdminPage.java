package com.example.cmiplapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginAdminPage  extends Activity implements OnClickListener {

	EditText username, password;
	Button submit;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen); 
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            System.out.println("*** My thread is now configured to allow connection");
        }

		
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		tv = (TextView)findViewById(R.id.tv);
		submit=(Button)findViewById(R.id.submit);
		submit.setOnClickListener(this);
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==submit)
		{
		dialog = ProgressDialog.show(LoginAdminPage.this, "", 
                "Validating user...", true);
         new Thread(new Runnable() {
                public void run() {
                    login();                          
                }
              }).start();       
		
		} 
	}
	void login(){
        try{            
              System.out.println("hello");
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://10.197.105.50:8080/consumer_php/login_ho.php"); // make sure the url is correct.
            //add your data
            System.out.println("hello1");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
            nameValuePairs.add(new BasicNameValuePair("username",username.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",password.getText().toString().trim())); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            System.out.println("hello2");
            // edited by James from coderzheaven.. from here....
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response); 
            runOnUiThread(new Runnable() {
                public void run() {
                    tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                    System.out.println("hello3");
                }
            });
             
            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LoginAdminPage.this,"Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                startActivity(new Intent(LoginAdminPage.this, main_page_admin.class));
            }else{
                showAlert();                
            }
             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
	
		
	 public void showAlert(){
	        LoginAdminPage.this.runOnUiThread(new Runnable() {
	            public void run() {
	                AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdminPage.this);
	                builder.setTitle("Login Error.");
	                builder.setMessage("User not Found.")  
	                       .setCancelable(false)
	                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                           public void onClick(DialogInterface dialog, int id) {
	                           }
	                       });                     
	                AlertDialog alert = builder.create();
	                alert.show();               
	            }
	        });
	    }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     // Inflate the menu items for use in the action bar
	     MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.admin_menu, menu);
	     return super.onCreateOptionsMenu(menu);
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     switch (item.getItemId()) {
	         /*case R.id.action_search:
	           //  openSearch();
	             return true;*/
	         case R.id.admin_Page:
	           //Intent i=new Intent(getBaseContext(), LoginAdminPage.class);
	             //  startActivity(i);
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }

}
