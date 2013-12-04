package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.PrintStream;

public class LocalAttractions extends Activity
{
  AlertDialogManager alert = new AlertDialogManager();
  Button amusement_park;
  Button aquarium;
  Button artgallery;
  Button cafe;
  Button campground;
  Button casino;
  ConnectionDetector cd;
  Button museum;
  Button restaurant;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903047);
    if (findViewById(2131034133).getTag().equals("320Screen"))
      System.out.println("Using small 320Screen");
    while (true)
    {
      this.amusement_park = ((Button)findViewById(2131034134));
      this.casino = ((Button)findViewById(2131034143));
      this.museum = ((Button)findViewById(2131034145));
      this.campground = ((Button)findViewById(2131034141));
      this.aquarium = ((Button)findViewById(2131034137));
      this.artgallery = ((Button)findViewById(2131034139));
      this.restaurant = ((Button)findViewById(2131034147));
      this.cafe = ((Button)findViewById(2131034149));
      this.cd = new ConnectionDetector(getApplicationContext());
      this.amusement_park.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Boolean localBoolean = Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet());
          System.out.println("Internet present status**************************************************" + localBoolean);
          if (!localBoolean.booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btAmusement_park");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.casino.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btCasino");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.museum.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btMuseum");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.campground.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btCampground");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.artgallery.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btArt_gallery");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.aquarium.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btAquarium");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.restaurant.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btRestaurant");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      this.cafe.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!Boolean.valueOf(LocalAttractions.this.cd.isConnectingToInternet()).booleanValue())
          {
            LocalAttractions.this.alert.showAlertDialog(LocalAttractions.this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
            return;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString("buttonId", "btCafe");
          Intent localIntent = new Intent(LocalAttractions.this, FamousPlaces.class);
          localIntent.putExtras(localBundle);
          LocalAttractions.this.startActivity(localIntent);
        }
      });
      return;
      if (findViewById(2131034133).getTag().equals("480Screen"))
        System.out.println("Using 480 Screeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnnnnnnnn");
      else if (findViewById(2131034133).getTag().equals("240Screen"))
        System.out.println("Using 240 Screeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnnnnnnnn");
      else if (findViewById(2131034133).getTag().equals("720Screen"))
        System.out.println("Using 720 Screeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnnnnnnnn");
      else
        System.out.println("Using default layout............................");
    }
  }
}
