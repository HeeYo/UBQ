package com.example.android_clienttest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.net.Socket;
import java.io.IOException;


public class MainActivity extends Activity {
	Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button1);
		button = setOnClickListener(new ButtonClick());
	}
	
	private Button setOnClickListener(ButtonClick buttonClick) {
		// TODO Auto-generated method stub
		return null;
	}

	class ButtonClick implements View.OnClickListener{
		public void onClick(View v){
			
			process();
		}
	}
	public void process(){
		Toast.makeText(this, "Hi", 3000).show();
				
		try {
			Socket client = new Socket("192.168.0.10", 1234);
		} catch (Exception e) {
           
		}
		
	}

}
