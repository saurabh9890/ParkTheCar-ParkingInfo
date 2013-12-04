package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.io.PrintStream;

public class SplashScreen extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903052);
    new Thread()
    {
      public void run()
      {
        try
        {
          sleep(2000L);
          SplashScreen.this.finish();
          Intent localIntent3;
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
          Intent localIntent2;
          return;
        }
        finally
        {
          System.out.println("Inside finally");
          Intent localIntent1 = new Intent(SplashScreen.this.getBaseContext(), SearchParking.class);
          SplashScreen.this.startActivity(localIntent1);
        }
      }
    }
    .start();
  }
}

