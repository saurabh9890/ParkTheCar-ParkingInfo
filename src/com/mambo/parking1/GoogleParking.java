package com.mambo.parking1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GoogleParking extends Activity
{
  public static String KEY_NAME = "name";
  public static String KEY_REFERENCE = "reference";
  public static String KEY_VICINITY = "vicinity";
  LatLng CURRENT;
  AlertDialogManager alert = new AlertDialogManager();
  ConnectionDetector cd;
  GoogleMap googleMap;
  GooglePlaces googlePlaces;
  String gotLat;
  String gotLon;
  double latitude;
  double longitude;
  ListView lv;
  PlacesList nearPlaces;
  ProgressDialog pDialog;
  PlaceDetails placeDetails;
  ArrayList<HashMap<String, String>> placesListItems = new ArrayList();
  String valued;

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    System.out.println("#$$%%^&&*$#&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    super.onConfigurationChanged(paramConfiguration);
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131034129);
    if (paramConfiguration.orientation == 2)
    {
      localLinearLayout.setOrientation(0);
      return;
    }
    localLinearLayout.setOrientation(1);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    this.cd = new ConnectionDetector(getApplicationContext());
    if (!Boolean.valueOf(this.cd.isConnectingToInternet()).booleanValue())
    {
      this.alert.showAlertDialog(this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
      return;
    }
    Bundle localBundle = getIntent().getExtras();
    this.gotLat = localBundle.getString("latitude1");
    this.gotLon = localBundle.getString("longitude1");
    new LoadPlaces().execute(new String[0]);
  }

  class LoadPlaces extends AsyncTask<String, String, String>
  {
    ArrayList<Double> parkingLat = new ArrayList();
    ArrayList<Double> parkingLon = new ArrayList();
    ArrayList<String> parkingName = new ArrayList();

    LoadPlaces()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      GoogleParking.this.googlePlaces = new GooglePlaces();
      try
      {
        GoogleParking.this.latitude = Double.parseDouble(GoogleParking.this.gotLat);
        GoogleParking.this.longitude = Double.parseDouble(GoogleParking.this.gotLon);
        GoogleParking.this.nearPlaces = GoogleParking.this.googlePlaces.search(GoogleParking.this.latitude, GoogleParking.this.longitude, "parking");
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
      GoogleParking.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          String str = GoogleParking.this.nearPlaces.status;
          if (str.equals("OK"))
          {
            Iterator localIterator1;
            Iterator localIterator2;
            label79: Iterator localIterator3;
            if (GoogleParking.this.nearPlaces.results != null)
            {
              localIterator1 = GoogleParking.this.nearPlaces.results.iterator();
              if (localIterator1.hasNext())
                break label360;
              localIterator2 = GoogleParking.LoadPlaces.this.parkingLat.iterator();
              if (localIterator2.hasNext())
                break label506;
              localIterator3 = GoogleParking.LoadPlaces.this.parkingLon.iterator();
              label101: if (localIterator3.hasNext())
                break label529;
              if (GoogleParking.this.googleMap == null)
                GoogleParking.this.googleMap = ((MapFragment)GoogleParking.this.getFragmentManager().findFragmentById(2131034130)).getMap();
              GoogleParking.this.CURRENT = new LatLng(GoogleParking.this.latitude, GoogleParking.this.longitude);
              GoogleParking.this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GoogleParking.this.CURRENT, 18.0F));
              GoogleParking.this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(12.0F), 2000, null);
              GoogleParking.this.googleMap.addMarker(new MarkerOptions().title("POI").icon(BitmapDescriptorFactory.defaultMarker(240.0F)).position(new LatLng(GoogleParking.this.latitude, GoogleParking.this.longitude)));
              GoogleParking.this.googleMap.setMyLocationEnabled(true);
            }
            for (int i = 0; ; i++)
            {
              if (i >= GoogleParking.LoadPlaces.this.parkingLat.size())
              {
                GoogleParking.this.googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
                {
                  public void onInfoWindowClick(Marker paramAnonymous2Marker)
                  {
                    if (!Boolean.valueOf(GoogleParking.this.cd.isConnectingToInternet()).booleanValue())
                    {
                      GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
                      return;
                    }
                    String str = paramAnonymous2Marker.getSnippet();
                    if (paramAnonymous2Marker.getTitle().equalsIgnoreCase("POI"))
                    {
                      System.out.println("No activity %%%%%%%%%%%%%%%%%%%");
                      return;
                    }
                    for (int i = 0; ; i++)
                    {
                      if (i >= GoogleParking.this.placesListItems.size())
                      {
                        Intent localIntent = new Intent(GoogleParking.this, SinglePlaceActivity.class);
                        Bundle localBundle = new Bundle();
                        localBundle.putString("key", GoogleParking.this.valued);
                        localBundle.putString("visibility", "visi");
                        localIntent.putExtras(localBundle);
                        GoogleParking.this.startActivity(localIntent);
                        return;
                      }
                      if (((HashMap)GoogleParking.this.placesListItems.get(i)).get(str) != null)
                      {
                        System.out.println("Place Itemi s****************************" + (String)((HashMap)GoogleParking.this.placesListItems.get(i)).get(str));
                        GoogleParking.this.valued = ((String)((HashMap)GoogleParking.this.placesListItems.get(i)).get(str));
                      }
                    }
                  }
                });
                return;
                label360: Place localPlace = (Place)localIterator1.next();
                HashMap localHashMap = new HashMap();
                System.out.println("------shitty--------" + localPlace.geometry.location.lat);
                GoogleParking.LoadPlaces.this.parkingLat.add(Double.valueOf(localPlace.geometry.location.lat));
                GoogleParking.LoadPlaces.this.parkingLon.add(Double.valueOf(localPlace.geometry.location.lng));
                GoogleParking.LoadPlaces.this.parkingName.add(localPlace.name);
                localHashMap.put(localPlace.name, localPlace.reference);
                GoogleParking.this.placesListItems.add(localHashMap);
                break;
                label506: Double localDouble1 = (Double)localIterator2.next();
                System.out.println(localDouble1);
                break label79;
                label529: Double localDouble2 = (Double)localIterator3.next();
                System.out.println(localDouble2);
                break label101;
              }
              GoogleParking.this.googleMap.addMarker(new MarkerOptions().title("parking").snippet((String)GoogleParking.LoadPlaces.this.parkingName.get(i)).position(new LatLng(((Double)GoogleParking.LoadPlaces.this.parkingLat.get(i)).doubleValue(), ((Double)GoogleParking.LoadPlaces.this.parkingLon.get(i)).doubleValue())));
            }
          }
          if (str.equals("ZERO_RESULTS"))
          {
            GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Near Places", "Sorry no places found. Try to change the types of places", Boolean.valueOf(false));
            return;
          }
          if (str.equals("UNKNOWN_ERROR"))
          {
            GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Places Error", "Sorry unknown error occured.", Boolean.valueOf(false));
            return;
          }
          if (str.equals("OVER_QUERY_LIMIT"))
          {
            GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Places Error", "Sorry query limit to google places is reached", Boolean.valueOf(false));
            return;
          }
          if (str.equals("REQUEST_DENIED"))
          {
            GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Places Error", "Sorry error occured. Request is denied", Boolean.valueOf(false));
            return;
          }
          if (str.equals("INVALID_REQUEST"))
          {
            GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Places Error", "Sorry error occured. Invalid Request", Boolean.valueOf(false));
            return;
          }
          GoogleParking.this.alert.showAlertDialog(GoogleParking.this, "Places Error", "Sorry error occured.", Boolean.valueOf(false));
        }
      });
    }
  }
}
