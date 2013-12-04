package com.mambo.parking1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutApp extends Activity
{
  WebView webView;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    this.webView = ((WebView)findViewById(2131034117));
    this.webView.loadUrl("file:///android_asset/aboutApp.html");
  }
}

