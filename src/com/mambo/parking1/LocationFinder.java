package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.io.PrintStream;

public class LocationFinder extends Activity
  implements LocationListener
{
  String latitude;
  Location location;
  LocationManager locationManager;
  String longitude;
  String sendLatitude;
  String sendLongitude;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.locationManager = ((LocationManager)getSystemService("location"));
    Criteria localCriteria = new Criteria();
    String str = this.locationManager.getBestProvider(localCriteria, true);
    this.location = this.locationManager.getLastKnownLocation(str);
    if (this.location != null)
      onLocationChanged(this.location);
    this.locationManager.requestLocationUpdates(str, 2000L, 0.0F, this);
    this.sendLatitude = this.latitude;
    this.sendLongitude = this.longitude;
    System.out.println("Sending------------" + this.sendLongitude);
    Bundle localBundle = new Bundle();
    localBundle.putString("latitude1", this.sendLatitude);
    localBundle.putString("longitude1", this.sendLongitude);
    Intent localIntent = new Intent(getBaseContext(), MainActivity.class);
    localIntent.putExtras(localBundle);
    startActivity(localIntent);
  }

  public void onLocationChanged(Location paramLocation)
  {
    this.latitude = String.valueOf(paramLocation.getLatitude());
    this.longitude = String.valueOf(paramLocation.getLongitude());
    System.out.println("Amey's Lat is:" + this.latitude + "Lon is:" + this.longitude);
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
}

