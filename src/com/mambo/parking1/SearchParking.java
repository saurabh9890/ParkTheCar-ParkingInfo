package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchParking extends Activity
{
  AlertDialogManager alert = new AlertDialogManager();
  Button btAddress;
  Button btCurrentLocation;
  Button btFamous;
  ConnectionDetector cd;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903050);
    this.btCurrentLocation = ((Button)findViewById(2131034156));
    this.btFamous = ((Button)findViewById(2131034157));
    this.btAddress = ((Button)findViewById(2131034160));
    this.btCurrentLocation.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchParking.this.cd = new ConnectionDetector(SearchParking.this.getApplicationContext());
        if (Boolean.valueOf(SearchParking.this.cd.isConnectingToInternet()).booleanValue())
        {
          Intent localIntent = new Intent(SearchParking.this, MainActivity.class);
          SearchParking.this.startActivity(localIntent);
          return;
        }
        SearchParking.this.alert.showAlertDialog(SearchParking.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
      }
    });
    this.btFamous.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SearchParking.this, LocalAttractions.class);
        SearchParking.this.startActivity(localIntent);
      }
    });
    this.btAddress.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SearchParking.this, AddressInput.class);
        SearchParking.this.startActivity(localIntent);
      }
    });
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 0, 0, "About The App");
    paramMenu.add(0, 1, 0, "About Us");
    paramMenu.add(0, 2, 0, "Contact Us");
    return super.onCreateOptionsMenu(paramMenu);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    super.onOptionsItemSelected(paramMenuItem);
    switch (paramMenuItem.getItemId())
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return false;
      startActivity(new Intent(this, AboutApp.class));
      continue;
      startActivity(new Intent(this, AboutUs.class));
      continue;
      Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "parkthecar13@gmail.com", null));
      localIntent.putExtra("android.intent.extra.SUBJECT", "Feedback");
      startActivity(Intent.createChooser(localIntent, "Send email..."));
    }
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return super.onPrepareOptionsMenu(paramMenu);
  }
}

