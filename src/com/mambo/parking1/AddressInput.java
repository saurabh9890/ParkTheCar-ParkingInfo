package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class AddressInput extends Activity
{
  AlertDialogManager alert = new AlertDialogManager();
  ConnectionDetector cd;
  EditText cityAddress;
  String concatenatedAddress;
  Button getParking;
  EditText stateAddress;
  EditText streetAddress;
  EditText zipAddress;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    this.getParking = ((Button)findViewById(2131034126));
    this.cd = new ConnectionDetector(getApplicationContext());
    this.getParking.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!Boolean.valueOf(AddressInput.this.cd.isConnectingToInternet()).booleanValue())
          AddressInput.this.alert.showAlertDialog(AddressInput.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
        while (true)
        {
          return;
          AddressInput.this.streetAddress = ((EditText)AddressInput.this.findViewById(2131034119));
          AddressInput.this.cityAddress = ((EditText)AddressInput.this.findViewById(2131034121));
          AddressInput.this.stateAddress = ((EditText)AddressInput.this.findViewById(2131034123));
          AddressInput.this.zipAddress = ((EditText)AddressInput.this.findViewById(2131034125));
          AddressInput.this.concatenatedAddress = (AddressInput.this.streetAddress.getText().toString() + "," + AddressInput.this.cityAddress.getText().toString() + "," + AddressInput.this.stateAddress.getText().toString() + " " + AddressInput.this.zipAddress.getText().toString());
          System.out.println(AddressInput.this.concatenatedAddress);
          List localList = null;
          Geocoder localGeocoder = new Geocoder(AddressInput.this);
          try
          {
            localList = localGeocoder.getFromLocationName(AddressInput.this.concatenatedAddress, 1);
            System.out.println("Gecode is***********************************" + localList);
            if (localList.size() == 0)
              AddressInput.this.alert.showAlertDialog(AddressInput.this, "Wrong Address", "Please Enter The Correct Address", Boolean.valueOf(false));
            if (localList.size() == 0)
              continue;
            String str1 = String.valueOf(((Address)localList.get(0)).getLatitude());
            String str2 = String.valueOf(((Address)localList.get(0)).getLongitude());
            System.out.println("this is epic" + str1 + "," + str2);
            Bundle localBundle = new Bundle();
            localBundle.putString("latitude", str1);
            localBundle.putString("longitude", str2);
            Intent localIntent = new Intent(AddressInput.this, MainActivity.class);
            localIntent.putExtras(localBundle);
            AddressInput.this.startActivity(localIntent);
            return;
          }
          catch (IOException localIOException)
          {
            while (true)
              localIOException.printStackTrace();
          }
        }
      }
    });
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    return true;
  }
}

