package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainScreen extends Activity
{
  Button searchParking;
  Button trackCar;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903048);
    this.searchParking = ((Button)findViewById(2131034150));
    this.trackCar = ((Button)findViewById(2131034151));
    this.searchParking.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(MainScreen.this, SearchParking.class);
        MainScreen.this.startActivity(localIntent);
      }
    });
    this.trackCar.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
      }
    });
  }
}

