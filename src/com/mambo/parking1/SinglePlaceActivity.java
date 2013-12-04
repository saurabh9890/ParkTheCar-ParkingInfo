package com.mambo.parking1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.PrintStream;

public class SinglePlaceActivity extends Activity
  implements LocationListener
{
  AlertDialogManager alert = new AlertDialogManager();
  String currentLatitude;
  String currentLongitude;
  Button findParking;
  GooglePlaces googlePlaces;
  String latitude;
  Location location;
  LocationManager locationManager;
  String longitude;
  Button navigate;
  ProgressDialog pDialog;
  String phone;
  TextView phone1;
  PlaceDetails placeDetails;
  Uri uri;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903051);
    this.phone1 = ((TextView)findViewById(2131034164));
    this.findParking = ((Button)findViewById(2131034163));
    this.navigate = ((Button)findViewById(2131034165));
    this.phone1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = SinglePlaceActivity.this.phone1.getText().toString().trim().substring(7);
        Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
        System.out.println("Phone is:+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + str);
        localIntent.setFlags(268435456);
        SinglePlaceActivity.this.startActivity(localIntent);
      }
    });
    this.locationManager = ((LocationManager)getSystemService("location"));
    Criteria localCriteria = new Criteria();
    String str1 = this.locationManager.getBestProvider(localCriteria, true);
    this.location = this.locationManager.getLastKnownLocation(str1);
    if (this.location != null)
      onLocationChanged(this.location);
    this.locationManager.requestLocationUpdates(str1, 2000L, 0.0F, this);
    Bundle localBundle = getIntent().getExtras();
    String str2 = localBundle.getString("key");
    if (localBundle.getString("visibility") != null)
    {
      System.out.println("Buttttton dsabled^^^^^^^^^^^^^^^^^^^^^^^^^");
      this.findParking.setVisibility(4);
    }
    new LoadSinglePlaceDetails().execute(new String[] { str2 });
  }

  public void onLocationChanged(Location paramLocation)
  {
    this.currentLatitude = String.valueOf(this.location.getLatitude());
    this.currentLongitude = String.valueOf(this.location.getLongitude());
  }

  public void onProviderDisabled(String paramString)
  {
  }

  public void onProviderEnabled(String paramString)
  {
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }

  class LoadSinglePlaceDetails extends AsyncTask<String, String, String>
  {
    ConnectionDetector cd;

    LoadSinglePlaceDetails()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      String str = paramArrayOfString[0];
      SinglePlaceActivity.this.googlePlaces = new GooglePlaces();
      try
      {
        SinglePlaceActivity.this.placeDetails = SinglePlaceActivity.this.googlePlaces.getPlaceDetails(str);
        return null;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }

    protected void onPostExecute(String paramString)
    {
      SinglePlaceActivity.this.pDialog.dismiss();
      SinglePlaceActivity.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (SinglePlaceActivity.this.placeDetails != null)
          {
            String str1 = SinglePlaceActivity.this.placeDetails.status;
            System.out.println("******************" + SinglePlaceActivity.this.placeDetails.result);
            if (str1.equals("OK"))
            {
              String str2;
              String str3;
              String str4;
              TextView localTextView1;
              TextView localTextView2;
              TextView localTextView3;
              String str5;
              SinglePlaceActivity localSinglePlaceActivity2;
              if (SinglePlaceActivity.this.placeDetails.result != null)
              {
                str2 = SinglePlaceActivity.this.placeDetails.result.name;
                str3 = SinglePlaceActivity.this.placeDetails.result.formatted_address;
                str4 = SinglePlaceActivity.this.placeDetails.result.formatted_phone_number;
                SinglePlaceActivity.this.latitude = Double.toString(SinglePlaceActivity.this.placeDetails.result.geometry.location.lat);
                SinglePlaceActivity.this.longitude = Double.toString(SinglePlaceActivity.this.placeDetails.result.geometry.location.lng);
                Log.d("Place ", str2 + str3 + str4 + SinglePlaceActivity.this.latitude + SinglePlaceActivity.this.longitude);
                localTextView1 = (TextView)SinglePlaceActivity.this.findViewById(2131034132);
                localTextView2 = (TextView)SinglePlaceActivity.this.findViewById(2131034166);
                localTextView3 = (TextView)SinglePlaceActivity.this.findViewById(2131034164);
                if (str2 == null)
                  str2 = "Not present";
                if (str3 == null)
                  str3 = "Not present";
                if (str4 == null)
                  str4 = "Not present";
                SinglePlaceActivity localSinglePlaceActivity1 = SinglePlaceActivity.this;
                if (SinglePlaceActivity.this.latitude != null)
                  break label441;
                str5 = "Not present";
                localSinglePlaceActivity1.latitude = str5;
                localSinglePlaceActivity2 = SinglePlaceActivity.this;
                if (SinglePlaceActivity.this.longitude != null)
                  break label456;
              }
              label441: label456: for (String str6 = "Not present"; ; str6 = SinglePlaceActivity.this.longitude)
              {
                localSinglePlaceActivity2.longitude = str6;
                localTextView1.setText(str2);
                localTextView2.setText(str3);
                localTextView3.setText(Html.fromHtml("<b>Phone:</b> " + str4));
                return;
                str5 = SinglePlaceActivity.this.latitude;
                break;
              }
            }
            if (str1.equals("ZERO_RESULTS"))
            {
              SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Near Places", "Sorry no place found.", Boolean.valueOf(false));
              return;
            }
            if (str1.equals("UNKNOWN_ERROR"))
            {
              SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry unknown error occured.", Boolean.valueOf(false));
              return;
            }
            if (str1.equals("OVER_QUERY_LIMIT"))
            {
              SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry query limit to google places is reached", Boolean.valueOf(false));
              return;
            }
            if (str1.equals("REQUEST_DENIED"))
            {
              SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry error occured. Request is denied", Boolean.valueOf(false));
              return;
            }
            if (str1.equals("INVALID_REQUEST"))
            {
              SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry error occured. Invalid Request", Boolean.valueOf(false));
              return;
            }
            SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry error occured.", Boolean.valueOf(false));
            return;
          }
          SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Places Error", "Sorry error occured.", Boolean.valueOf(false));
        }
      });
      this.cd = new ConnectionDetector(SinglePlaceActivity.this.getApplicationContext());
      SinglePlaceActivity.this.findParking.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(SinglePlaceActivity.LoadSinglePlaceDetails.this.cd.isConnectingToInternet()).booleanValue())
          {
            SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("latitude", SinglePlaceActivity.this.latitude);
          localBundle.putString("longitude", SinglePlaceActivity.this.longitude);
          Intent localIntent = new Intent(SinglePlaceActivity.this.getBaseContext(), MainActivity.class);
          localIntent.putExtras(localBundle);
          SinglePlaceActivity.this.startActivity(localIntent);
        }
      });
      SinglePlaceActivity.this.navigate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(SinglePlaceActivity.LoadSinglePlaceDetails.this.cd.isConnectingToInternet()).booleanValue())
          {
            SinglePlaceActivity.this.alert.showAlertDialog(SinglePlaceActivity.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + SinglePlaceActivity.this.latitude + "," + SinglePlaceActivity.this.longitude));
          SinglePlaceActivity.this.startActivity(localIntent);
          System.out.println("URL is:" + SinglePlaceActivity.this.uri);
        }
      });
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      SinglePlaceActivity.this.pDialog = new ProgressDialog(SinglePlaceActivity.this);
      SinglePlaceActivity.this.pDialog.setMessage("Loading profile ...");
      SinglePlaceActivity.this.pDialog.setIndeterminate(false);
      SinglePlaceActivity.this.pDialog.setCancelable(false);
      SinglePlaceActivity.this.pDialog.show();
    }
  }
}

