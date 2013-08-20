package com.tw.webviewexample;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	WebView webView;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		webView = new WebView(this);
		setContentView(webView);
		context = this;
		WebSettings settings =  webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new WebViewInterface(), "MainActivityInterface");
		
		try {
			InputStream stream = this.getAssets().open("article.html");
			int streamSize = stream.available();
			byte[] buffer = new byte[streamSize];
			stream.read(buffer);
			stream.close();
			String html = new String(buffer);
			webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class WebViewInterface{
		
		@JavascriptInterface
		public void showToast(){
			Toast.makeText(context, "JavaScript webinterface called", Toast.LENGTH_LONG).show();
		}
		
	}
	

}
