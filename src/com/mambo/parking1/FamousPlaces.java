package com.mambo.parking1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FamousPlaces extends Activity
  implements LocationListener
{
  public static String KEY_NAME = "name";
  public static String KEY_REFERENCE = "reference";
  public static String KEY_VICINITY = "vicinity";
  AlertDialogManager alert = new AlertDialogManager();
  ConnectionDetector cd;
  Context context;
  GooglePlaces googlePlaces;
  String gotId;
  String gotLat;
  String gotLon;
  Boolean isInternetPresent = Boolean.valueOf(false);
  double latitude;
  Location location;
  LocationManager locationManager;
  double longitude;
  ListView lv;
  PlacesList nearPlaces;
  ProgressDialog pDialog;
  ArrayList<HashMap<String, String>> placesListItems = new ArrayList();
  String provider;
  String types;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    this.cd = new ConnectionDetector(getApplicationContext());
    this.context = this;
    this.isInternetPresent = Boolean.valueOf(this.cd.isConnectingToInternet());
    if (!this.isInternetPresent.booleanValue())
    {
      this.alert.showAlertDialog(this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
      return;
    }
    this.gotId = getIntent().getExtras().getString("buttonId");
    this.locationManager = ((LocationManager)getSystemService("location"));
    Criteria localCriteria = new Criteria();
    this.provider = this.locationManager.getBestProvider(localCriteria, true);
    if (this.provider == null)
    {
      startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
      Toast.makeText(getApplicationContext(), "You Need To Turn On Location Services", 1).show();
    }
    while (true)
    {
      if (this.location != null)
      {
        onLocationChanged(this.location);
        this.locationManager.requestLocationUpdates(this.provider, 2000L, 0.0F, this);
      }
      this.lv = ((ListView)findViewById(2131034128));
      new LoadPlaces().execute(new String[0]);
      this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          String str = ((TextView)paramAnonymousView.findViewById(2131034131)).getText().toString();
          Intent localIntent = new Intent(FamousPlaces.this.getApplicationContext(), SinglePlaceActivity.class);
          System.out.println("Reference in Famous is --------------------" + str);
          localIntent.putExtra("key", str);
          FamousPlaces.this.startActivity(localIntent);
        }
      });
      return;
      this.location = this.locationManager.getLastKnownLocation(this.provider);
    }
  }

  public void onLocationChanged(Location paramLocation)
  {
    this.latitude = this.location.getLatitude();
    this.longitude = this.location.getLongitude();
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

  class LoadPlaces extends AsyncTask<String, String, String>
  {
    LoadPlaces()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      FamousPlaces.this.googlePlaces = new GooglePlaces();
      try
      {
        System.out.println("Button id issssssssssssssssssssssssssssssss:" + FamousPlaces.this.gotId);
        if (FamousPlaces.this.gotId.equalsIgnoreCase("btAmusement_park"))
        {
          System.out.println("I AM CONSIDERING ONLY AMUSEMENT PARKS");
          FamousPlaces.this.types = "amusement_park";
          FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
        }
        else if (FamousPlaces.this.gotId.equalsIgnoreCase("btCasino"))
        {
          FamousPlaces.this.types = "casino";
          FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      if (FamousPlaces.this.gotId.equalsIgnoreCase("btMuseum"))
      {
        FamousPlaces.this.types = "museum";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      else if (FamousPlaces.this.gotId.equalsIgnoreCase("btCampground"))
      {
        FamousPlaces.this.types = "campground";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      else if (FamousPlaces.this.gotId.equalsIgnoreCase("btArt_gallery"))
      {
        FamousPlaces.this.types = "art_gallery";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      else if (FamousPlaces.this.gotId.equalsIgnoreCase("btAquarium"))
      {
        FamousPlaces.this.types = "aquarium";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      else if (FamousPlaces.this.gotId.equalsIgnoreCase("btRestaurant"))
      {
        FamousPlaces.this.types = "restaurant";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      else if (FamousPlaces.this.gotId.equalsIgnoreCase("btCafe"))
      {
        FamousPlaces.this.types = "cafe";
        FamousPlaces.this.nearPlaces = FamousPlaces.this.googlePlaces.search(FamousPlaces.this.latitude, FamousPlaces.this.longitude, FamousPlaces.this.types);
      }
      return null;
    }

    protected void onPostExecute(String paramString)
    {
      FamousPlaces.this.pDialog.dismiss();
      FamousPlaces.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          String str = FamousPlaces.this.nearPlaces.status;
          if (str.equals("OK"))
          {
            Iterator localIterator;
            if (FamousPlaces.this.nearPlaces.results != null)
              localIterator = FamousPlaces.this.nearPlaces.results.iterator();
            while (true)
            {
              if (!localIterator.hasNext())
              {
                FamousPlaces localFamousPlaces = FamousPlaces.this;
                ArrayList localArrayList = FamousPlaces.this.placesListItems;
                String[] arrayOfString = new String[2];
                arrayOfString[0] = FamousPlaces.KEY_REFERENCE;
                arrayOfString[1] = FamousPlaces.KEY_NAME;
                SimpleAdapter localSimpleAdapter = new SimpleAdapter(localFamousPlaces, localArrayList, 2130903046, arrayOfString, new int[] { 2131034131, 2131034132 });
                FamousPlaces.this.lv.setAdapter(localSimpleAdapter);
                return;
              }
              Place localPlace = (Place)localIterator.next();
              HashMap localHashMap = new HashMap();
              localHashMap.put(FamousPlaces.KEY_REFERENCE, localPlace.reference);
              localHashMap.put(FamousPlaces.KEY_NAME, localPlace.name);
              FamousPlaces.this.placesListItems.add(localHashMap);
            }
          }
          if (str.equals("ZERO_RESULTS"))
          {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(FamousPlaces.this.context);
            localBuilder.setMessage("Sorry no such place found in your vicinity").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                FamousPlaces.this.finish();
              }
            });
            localBuilder.create().show();
            return;
          }
          if (str.equals("UNKNOWN_ERROR"))
          {
            FamousPlaces.this.alert.showAlertDialog(FamousPlaces.this, "Places Error", "Sorry unknown error occured.", Boolean.valueOf(false));
            return;
          }
          if (str.equals("OVER_QUERY_LIMIT"))
          {
            FamousPlaces.this.alert.showAlertDialog(FamousPlaces.this, "Places Error", "Sorry query limit to google places is reached", Boolean.valueOf(false));
            return;
          }
          if (str.equals("REQUEST_DENIED"))
          {
            FamousPlaces.this.alert.showAlertDialog(FamousPlaces.this, "Places Error", "Sorry error occured. Request is denied", Boolean.valueOf(false));
            return;
          }
          if (str.equals("INVALID_REQUEST"))
          {
            FamousPlaces.this.alert.showAlertDialog(FamousPlaces.this, "Places Error", "Sorry error occured. Invalid Request", Boolean.valueOf(false));
            return;
          }
          FamousPlaces.this.alert.showAlertDialog(FamousPlaces.this, "Places Error", "Sorry error occured.", Boolean.valueOf(false));
        }
      });
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      FamousPlaces.this.pDialog = new ProgressDialog(FamousPlaces.this);
      FamousPlaces.this.pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
      FamousPlaces.this.pDialog.setIndeterminate(false);
      FamousPlaces.this.pDialog.setCancelable(false);
      FamousPlaces.this.pDialog.show();
    }
  }
}

