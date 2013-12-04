package com.mambo.parking1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParkingDetails extends Activity
{
  JSONObject bat;
  String beg;
  JSONObject bert;
  int count = 0;
  String end;
  String from;
  String occ;
  String oper;
  String rate;
  JSONObject rates;
  JSONArray rs;
  String to;
  TextView tv2;
  String type;
  JSONObject valuedfrommain;

  protected void onCreate(Bundle paramBundle)
  {
    System.out.println("hello");
    super.onCreate(paramBundle);
    setContentView(2130903049);
    Bundle localBundle = getIntent().getExtras();
    String str1 = localBundle.getString("name");
    while (true)
    {
      TableLayout localTableLayout;
      int i;
      try
      {
        this.valuedfrommain = new JSONObject(localBundle.getString("jsonvalue"));
        this.type = this.valuedfrommain.getString("TYPE");
        this.occ = this.valuedfrommain.getString("OCC");
        this.oper = this.valuedfrommain.getString("OPER");
        this.rates = this.valuedfrommain.getJSONObject("RATES");
        this.rs = this.rates.getJSONArray("RS");
        System.out.println(str1);
        System.out.println(this.valuedfrommain);
        ((TextView)findViewById(2131034153)).setText("PARKING NAME:" + str1 + "\n\nTYPE(ON/OFF):" + this.type + "\nOPERATIONAL SPOTS:" + this.oper + "\nOCCUPIED:" + this.occ + "\n");
        ((LinearLayout)findViewById(2131034152));
        localTableLayout = (TableLayout)findViewById(2131034155);
        TableRow localTableRow1 = new TableRow(this);
        localTableRow1.setId(10);
        localTableRow1.setBackgroundColor(-7829368);
        localTableRow1.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        TextView localTextView1 = new TextView(this);
        localTextView1.setId(20);
        localTextView1.setText("START");
        localTextView1.setTextColor(-1);
        localTextView1.setPadding(10, 5, 10, 5);
        localTableRow1.addView(localTextView1);
        TextView localTextView2 = new TextView(this);
        localTextView2.setId(20);
        localTextView2.setText("END");
        localTextView2.setTextColor(-1);
        localTextView2.setPadding(10, 5, 10, 5);
        localTableRow1.addView(localTextView2);
        TextView localTextView3 = new TextView(this);
        localTextView3.setId(20);
        localTextView3.setText("RATE");
        localTextView3.setTextColor(-1);
        localTextView3.setPadding(10, 5, 10, 5);
        localTableRow1.addView(localTextView3);
        TextView localTextView4 = new TextView(this);
        localTextView4.setId(21);
        localTextView4.setText("RQ");
        localTextView4.setTextColor(-1);
        localTextView4.setPadding(10, 5, 5, 5);
        localTableRow1.addView(localTextView4);
        localTableLayout.addView(localTableRow1, new TableLayout.LayoutParams(-1, -2));
        i = 0;
        if (i >= this.rs.length())
          return;
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
        continue;
      }
      try
      {
        this.bert = this.rs.getJSONObject(i);
        String str2 = this.bert.getString("BEG");
        String str3 = this.bert.getString("END");
        String str4 = this.bert.getString("RATE");
        String str5 = this.bert.getString("RQ");
        System.out.println("BEG: " + str2 + "\n");
        System.out.println("END: " + str3 + "\n");
        System.out.println("RATE: " + str4 + "\n");
        System.out.println("RQ: " + str5 + "\n");
        TableRow localTableRow2 = new TableRow(this);
        localTableRow2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        TextView localTextView5 = new TextView(this);
        localTextView5.setText(str2);
        localTextView5.setPadding(2, 0, 5, 0);
        localTextView5.setTextColor(-16777216);
        localTableRow2.addView(localTextView5);
        TextView localTextView6 = new TextView(this);
        localTextView6.setText(str3);
        localTextView6.setTextColor(-16776961);
        localTableRow2.addView(localTextView6);
        TextView localTextView7 = new TextView(this);
        localTextView7.setText(str4);
        localTextView7.setPadding(7, 0, 5, 0);
        localTextView7.setTextColor(-16777216);
        localTableRow2.addView(localTextView7);
        TextView localTextView8 = new TextView(this);
        localTextView8.setText(str5);
        localTextView8.setTextColor(-16776961);
        localTableRow2.addView(localTextView8);
        localTableLayout.addView(localTableRow2, new TableLayout.LayoutParams(-1, -2));
        i++;
      }
      catch (JSONException localJSONException2)
      {
        while (true)
          localJSONException2.printStackTrace();
      }
    }
  }
}

