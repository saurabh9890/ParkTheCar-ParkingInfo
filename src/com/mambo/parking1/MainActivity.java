package com.mambo.parking1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity
  implements LocationListener
{
  static String intersect = "";
  static String parkingapi;
  static String streetnum = "";
  LatLng CURRENT;
  AlertDialogManager alert = new AlertDialogManager();
  ConnectionDetector cd;
  private Context context;
  Intent googleIntent;
  String gotLat;
  String gotLon;
  String latFromAddress;
  Location location;
  LocationFinder locationFinder;
  LocationManager locationManager;
  String lonFromAddress;
  private MyAsyncTask mAsyncTask;
  String num_records;
  private ProgressDialog pd;
  String provider;
  String result = null;
  int result1;

  protected void onCreate(Bundle paramBundle)
  {
    System.out.println("hello");
    super.onCreate(paramBundle);
    this.cd = new ConnectionDetector(getApplicationContext());
    this.locationManager = ((LocationManager)getSystemService("location"));
    Criteria localCriteria = new Criteria();
    this.provider = this.locationManager.getBestProvider(localCriteria, true);
    if (this.provider == null)
    {
      startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
      Toast.makeText(getApplicationContext(), "You Need To Turn On Location Services", 1).show();
    }
    while ((!this.cd.isConnectingToInternet()) && (this.provider != null))
    {
      System.out.println("booboo");
      this.alert.showAlertDialog(this, "Internet Connection Error", "Please connect to working Internet connection", Boolean.valueOf(false));
      startActivity(new Intent("android.settings.SETTINGS"));
      return;
      this.location = this.locationManager.getLastKnownLocation(this.provider);
    }
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      this.latFromAddress = localBundle.getString("latitude");
      this.lonFromAddress = localBundle.getString("longitude");
      System.out.println("Lat is----------------------------------------" + this.latFromAddress);
    }
    if ((this.latFromAddress != null) && (this.lonFromAddress != null))
    {
      this.gotLat = this.latFromAddress;
      this.gotLon = this.lonFromAddress;
      System.out.println("From address input----------------------------");
    }
    while (true)
    {
      this.mAsyncTask = ((MyAsyncTask)new MyAsyncTask(null).execute(new String[0]));
      return;
      if (this.location != null)
      {
        onLocationChanged(this.location);
        this.locationManager.requestLocationUpdates(this.provider, 2000L, 0.0F, this);
        System.out.println("Should be current location.......................................");
      }
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131296257, paramMenu);
    return true;
  }

  public void onLocationChanged(Location paramLocation)
  {
    this.gotLat = String.valueOf(paramLocation.getLatitude());
    this.gotLon = String.valueOf(paramLocation.getLongitude());
    System.out.println("Amey's Lat in MainActivity is:" + this.gotLat + "Lon is:" + this.gotLon);
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

  private class MyAsyncTask extends AsyncTask<String, String, String>
  {
    JSONArray Avl;
    ArrayList<HashMap<String, JSONObject>> forMap = new ArrayList();
    List<String> items = null;
    ArrayList<String> lat = new ArrayList();
    ArrayList<String> lon = new ArrayList();
    protected GoogleMap map;

    private MyAsyncTask()
    {
    }

    // ERROR //
    protected String doInBackground(String[] paramArrayOfString)
    {
      // Byte code:
      //   0: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   3: new 62	java/lang/StringBuilder
      //   6: dup
      //   7: ldc 64
      //   9: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   12: aload_0
      //   13: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   16: getfield 73	com/Mahmood/parking1/MainActivity:gotLat	Ljava/lang/String;
      //   19: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   22: ldc 79
      //   24: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   27: aload_0
      //   28: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   31: getfield 82	com/Mahmood/parking1/MainActivity:gotLon	Ljava/lang/String;
      //   34: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   43: new 62	java/lang/StringBuilder
      //   46: dup
      //   47: ldc 93
      //   49: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   52: aload_0
      //   53: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   56: getfield 73	com/Mahmood/parking1/MainActivity:gotLat	Ljava/lang/String;
      //   59: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   62: ldc 95
      //   64: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   67: aload_0
      //   68: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   71: getfield 82	com/Mahmood/parking1/MainActivity:gotLon	Ljava/lang/String;
      //   74: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   77: ldc 97
      //   79: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   82: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   85: putstatic 100	com/Mahmood/parking1/MainActivity:parkingapi	Ljava/lang/String;
      //   88: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   91: new 62	java/lang/StringBuilder
      //   94: dup
      //   95: ldc 102
      //   97: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   100: getstatic 100	com/Mahmood/parking1/MainActivity:parkingapi	Ljava/lang/String;
      //   103: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   109: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   112: new 104	org/apache/http/impl/client/DefaultHttpClient
      //   115: dup
      //   116: invokespecial 105	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
      //   119: astore_2
      //   120: new 107	org/apache/http/client/methods/HttpGet
      //   123: dup
      //   124: getstatic 100	com/Mahmood/parking1/MainActivity:parkingapi	Ljava/lang/String;
      //   127: invokespecial 108	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
      //   130: astore_3
      //   131: aconst_null
      //   132: astore 4
      //   134: aload_2
      //   135: aload_3
      //   136: invokevirtual 112	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
      //   139: astore 36
      //   141: aload 36
      //   143: invokeinterface 118 1 0
      //   148: invokeinterface 124 1 0
      //   153: istore 37
      //   155: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   158: iload 37
      //   160: invokevirtual 127	java/io/PrintStream:println	(I)V
      //   163: aload 36
      //   165: invokeinterface 131 1 0
      //   170: invokeinterface 137 1 0
      //   175: astore 4
      //   177: new 139	java/io/InputStreamReader
      //   180: dup
      //   181: aload 4
      //   183: ldc 141
      //   185: invokespecial 144	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   188: astore 38
      //   190: new 146	java/io/BufferedReader
      //   193: dup
      //   194: aload 38
      //   196: bipush 8
      //   198: invokespecial 149	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
      //   201: astore 39
      //   203: new 62	java/lang/StringBuilder
      //   206: dup
      //   207: invokespecial 150	java/lang/StringBuilder:<init>	()V
      //   210: astore 40
      //   212: aload 39
      //   214: invokevirtual 153	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   217: astore 41
      //   219: aload 41
      //   221: ifnonnull +273 -> 494
      //   224: aload_0
      //   225: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   228: aload 40
      //   230: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   233: putfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   236: aload 4
      //   238: ifnull +8 -> 246
      //   241: aload 4
      //   243: invokevirtual 161	java/io/InputStream:close	()V
      //   246: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   249: ldc 163
      //   251: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   254: aload_0
      //   255: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   258: aload_0
      //   259: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   262: getfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   265: bipush 12
      //   267: invokevirtual 169	java/lang/String:substring	(I)Ljava/lang/String;
      //   270: putfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   273: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   276: aload_0
      //   277: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   280: getfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   283: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   286: new 171	org/json/JSONObject
      //   289: dup
      //   290: aload_0
      //   291: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   294: getfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   297: invokespecial 172	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   300: astore 12
      //   302: aload_0
      //   303: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   306: aload 12
      //   308: ldc 174
      //   310: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   313: putfield 181	com/Mahmood/parking1/MainActivity:num_records	Ljava/lang/String;
      //   316: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   319: new 62	java/lang/StringBuilder
      //   322: dup
      //   323: ldc 183
      //   325: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   328: aload_0
      //   329: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   332: getfield 181	com/Mahmood/parking1/MainActivity:num_records	Ljava/lang/String;
      //   335: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   338: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   341: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   344: aload_0
      //   345: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   348: getfield 181	com/Mahmood/parking1/MainActivity:num_records	Ljava/lang/String;
      //   351: ldc 185
      //   353: invokevirtual 189	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   356: ifeq +26 -> 382
      //   359: aload_0
      //   360: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   363: invokestatic 193	com/Mahmood/parking1/MainActivity:access$2	(Lcom/Mahmood/parking1/MainActivity;)Lcom/Mahmood/parking1/MainActivity$MyAsyncTask;
      //   366: iconst_1
      //   367: invokevirtual 197	com/Mahmood/parking1/MainActivity$MyAsyncTask:cancel	(Z)Z
      //   370: pop
      //   371: aload_0
      //   372: aload_0
      //   373: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   376: getfield 156	com/Mahmood/parking1/MainActivity:result	Ljava/lang/String;
      //   379: invokevirtual 200	com/Mahmood/parking1/MainActivity$MyAsyncTask:onPostExecute	(Ljava/lang/String;)V
      //   382: aload_0
      //   383: aload 12
      //   385: ldc 202
      //   387: invokevirtual 206	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   390: putfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   393: aload_0
      //   394: getfield 36	com/Mahmood/parking1/MainActivity$MyAsyncTask:lat	Ljava/util/ArrayList;
      //   397: ldc 210
      //   399: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   402: pop
      //   403: aload_0
      //   404: getfield 38	com/Mahmood/parking1/MainActivity$MyAsyncTask:lon	Ljava/util/ArrayList;
      //   407: ldc 210
      //   409: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   412: pop
      //   413: aload_0
      //   414: getfield 34	com/Mahmood/parking1/MainActivity$MyAsyncTask:forMap	Ljava/util/ArrayList;
      //   417: aconst_null
      //   418: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   421: pop
      //   422: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   425: aload_0
      //   426: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   429: invokevirtual 219	org/json/JSONArray:length	()I
      //   432: invokevirtual 127	java/io/PrintStream:println	(I)V
      //   435: iconst_1
      //   436: istore 16
      //   438: iload 16
      //   440: aload_0
      //   441: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   444: invokevirtual 219	org/json/JSONArray:length	()I
      //   447: if_icmplt +153 -> 600
      //   450: aload_0
      //   451: getfield 36	com/Mahmood/parking1/MainActivity$MyAsyncTask:lat	Ljava/util/ArrayList;
      //   454: invokevirtual 223	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   457: astore 30
      //   459: aload 30
      //   461: invokeinterface 229 1 0
      //   466: ifne +562 -> 1028
      //   469: aload_0
      //   470: getfield 38	com/Mahmood/parking1/MainActivity$MyAsyncTask:lon	Ljava/util/ArrayList;
      //   473: invokevirtual 223	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   476: astore 32
      //   478: aload 32
      //   480: invokeinterface 229 1 0
      //   485: istore 33
      //   487: iload 33
      //   489: ifne +573 -> 1062
      //   492: aconst_null
      //   493: areturn
      //   494: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   497: aload 41
      //   499: invokevirtual 230	java/lang/String:toString	()Ljava/lang/String;
      //   502: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   505: aload 40
      //   507: new 62	java/lang/StringBuilder
      //   510: dup
      //   511: aload 41
      //   513: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   516: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   519: ldc 236
      //   521: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   524: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   527: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   530: pop
      //   531: goto -319 -> 212
      //   534: astore 7
      //   536: aload 7
      //   538: invokevirtual 240	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   541: pop
      //   542: aload 4
      //   544: ifnull -298 -> 246
      //   547: aload 4
      //   549: invokevirtual 161	java/io/InputStream:close	()V
      //   552: goto -306 -> 246
      //   555: astore 9
      //   557: aload 9
      //   559: invokevirtual 243	java/io/IOException:printStackTrace	()V
      //   562: goto -316 -> 246
      //   565: astore 5
      //   567: aload 4
      //   569: ifnull +8 -> 577
      //   572: aload 4
      //   574: invokevirtual 161	java/io/InputStream:close	()V
      //   577: aload 5
      //   579: athrow
      //   580: astore 6
      //   582: aload 6
      //   584: invokevirtual 243	java/io/IOException:printStackTrace	()V
      //   587: goto -10 -> 577
      //   590: astore 42
      //   592: aload 42
      //   594: invokevirtual 243	java/io/IOException:printStackTrace	()V
      //   597: goto -351 -> 246
      //   600: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   603: new 62	java/lang/StringBuilder
      //   606: dup
      //   607: ldc 245
      //   609: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   612: iload 16
      //   614: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   617: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   620: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   623: aload_0
      //   624: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   627: iload 16
      //   629: invokevirtual 252	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   632: astore 17
      //   634: aload 17
      //   636: ldc 254
      //   638: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   641: astore 18
      //   643: aload 17
      //   645: ldc_w 256
      //   648: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   651: astore 19
      //   653: aload 17
      //   655: ldc_w 258
      //   658: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   661: astore 20
      //   663: aload 17
      //   665: ldc_w 260
      //   668: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   671: astore 21
      //   673: aload 17
      //   675: ldc_w 262
      //   678: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   681: astore 22
      //   683: aload 17
      //   685: ldc_w 264
      //   688: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   691: astore 23
      //   693: new 266	java/util/HashMap
      //   696: dup
      //   697: invokespecial 267	java/util/HashMap:<init>	()V
      //   700: astore 24
      //   702: aload 24
      //   704: aload 19
      //   706: aload 17
      //   708: invokevirtual 271	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   711: pop
      //   712: aload_0
      //   713: getfield 34	com/Mahmood/parking1/MainActivity$MyAsyncTask:forMap	Ljava/util/ArrayList;
      //   716: aload 24
      //   718: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   721: pop
      //   722: aload_0
      //   723: aload 23
      //   725: ldc_w 273
      //   728: invokevirtual 277	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   731: invokestatic 283	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
      //   734: putfield 29	com/Mahmood/parking1/MainActivity$MyAsyncTask:items	Ljava/util/List;
      //   737: aload_0
      //   738: getfield 36	com/Mahmood/parking1/MainActivity$MyAsyncTask:lat	Ljava/util/ArrayList;
      //   741: aload_0
      //   742: getfield 29	com/Mahmood/parking1/MainActivity$MyAsyncTask:items	Ljava/util/List;
      //   745: iconst_0
      //   746: invokeinterface 289 2 0
      //   751: checkcast 165	java/lang/String
      //   754: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   757: pop
      //   758: aload_0
      //   759: getfield 38	com/Mahmood/parking1/MainActivity$MyAsyncTask:lon	Ljava/util/ArrayList;
      //   762: aload_0
      //   763: getfield 29	com/Mahmood/parking1/MainActivity$MyAsyncTask:items	Ljava/util/List;
      //   766: iconst_1
      //   767: invokeinterface 289 2 0
      //   772: checkcast 165	java/lang/String
      //   775: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   778: pop
      //   779: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   782: new 62	java/lang/StringBuilder
      //   785: dup
      //   786: ldc_w 291
      //   789: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   792: aload 18
      //   794: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   797: ldc 236
      //   799: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   802: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   805: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   808: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   811: new 62	java/lang/StringBuilder
      //   814: dup
      //   815: ldc_w 293
      //   818: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   821: aload 19
      //   823: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   826: ldc 236
      //   828: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   831: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   834: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   837: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   840: new 62	java/lang/StringBuilder
      //   843: dup
      //   844: ldc_w 295
      //   847: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   850: aload 20
      //   852: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   855: ldc 236
      //   857: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   860: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   863: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   866: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   869: new 62	java/lang/StringBuilder
      //   872: dup
      //   873: ldc_w 297
      //   876: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   879: aload 21
      //   881: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   884: ldc 236
      //   886: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   889: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   892: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   895: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   898: new 62	java/lang/StringBuilder
      //   901: dup
      //   902: ldc_w 299
      //   905: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   908: aload 22
      //   910: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   913: ldc 236
      //   915: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   918: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   921: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   924: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   927: new 62	java/lang/StringBuilder
      //   930: dup
      //   931: ldc_w 301
      //   934: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   937: aload 23
      //   939: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   942: ldc 236
      //   944: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   947: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   950: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   953: aload 17
      //   955: ldc_w 303
      //   958: invokevirtual 306	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   961: ldc_w 308
      //   964: invokevirtual 206	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   967: pop
      //   968: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   971: new 62	java/lang/StringBuilder
      //   974: dup
      //   975: ldc_w 310
      //   978: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   981: iload 16
      //   983: invokevirtual 248	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   986: ldc_w 312
      //   989: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   992: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   995: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   998: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   1001: aload_0
      //   1002: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   1005: iload 16
      //   1007: invokevirtual 313	org/json/JSONArray:get	(I)Ljava/lang/Object;
      //   1010: invokevirtual 316	java/io/PrintStream:println	(Ljava/lang/Object;)V
      //   1013: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   1016: ldc_w 318
      //   1019: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1022: iinc 16 1
      //   1025: goto -587 -> 438
      //   1028: aload 30
      //   1030: invokeinterface 322 1 0
      //   1035: checkcast 165	java/lang/String
      //   1038: astore 31
      //   1040: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   1043: aload 31
      //   1045: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1048: goto -589 -> 459
      //   1051: astore 10
      //   1053: aload 10
      //   1055: invokevirtual 240	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   1058: pop
      //   1059: goto -567 -> 492
      //   1062: aload 32
      //   1064: invokeinterface 322 1 0
      //   1069: checkcast 165	java/lang/String
      //   1072: astore 34
      //   1074: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   1077: aload 34
      //   1079: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1082: goto -604 -> 478
      //
      // Exception table:
      //   from	to	target	type
      //   134	212	534	java/lang/Exception
      //   212	219	534	java/lang/Exception
      //   224	236	534	java/lang/Exception
      //   494	531	534	java/lang/Exception
      //   547	552	555	java/io/IOException
      //   134	212	565	finally
      //   212	219	565	finally
      //   224	236	565	finally
      //   494	531	565	finally
      //   536	542	565	finally
      //   572	577	580	java/io/IOException
      //   241	246	590	java/io/IOException
      //   254	382	1051	java/lang/Exception
      //   382	435	1051	java/lang/Exception
      //   438	459	1051	java/lang/Exception
      //   459	478	1051	java/lang/Exception
      //   478	487	1051	java/lang/Exception
      //   600	1022	1051	java/lang/Exception
      //   1028	1048	1051	java/lang/Exception
      //   1062	1082	1051	java/lang/Exception
    }

    // ERROR //
    protected void onPostExecute(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   4: getfield 330	com/Mahmood/parking1/MainActivity:cd	Lcom/Mahmood/parking1/ConnectionDetector;
      //   7: invokevirtual 335	com/Mahmood/parking1/ConnectionDetector:isConnectingToInternet	()Z
      //   10: invokestatic 340	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   13: invokevirtual 343	java/lang/Boolean:booleanValue	()Z
      //   16: ifeq +13 -> 29
      //   19: aload_0
      //   20: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   23: getfield 346	com/Mahmood/parking1/MainActivity:provider	Ljava/lang/String;
      //   26: ifnonnull +16 -> 42
      //   29: aload_0
      //   30: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   33: invokestatic 193	com/Mahmood/parking1/MainActivity:access$2	(Lcom/Mahmood/parking1/MainActivity;)Lcom/Mahmood/parking1/MainActivity$MyAsyncTask;
      //   36: iconst_1
      //   37: invokevirtual 197	com/Mahmood/parking1/MainActivity$MyAsyncTask:cancel	(Z)Z
      //   40: pop
      //   41: return
      //   42: aload_0
      //   43: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   46: getfield 181	com/Mahmood/parking1/MainActivity:num_records	Ljava/lang/String;
      //   49: ldc 185
      //   51: invokevirtual 189	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   54: ifeq +136 -> 190
      //   57: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   60: new 62	java/lang/StringBuilder
      //   63: dup
      //   64: ldc_w 348
      //   67: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   70: aload_0
      //   71: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   74: getfield 73	com/Mahmood/parking1/MainActivity:gotLat	Ljava/lang/String;
      //   77: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   80: aload_0
      //   81: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   84: getfield 82	com/Mahmood/parking1/MainActivity:gotLon	Ljava/lang/String;
      //   87: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   90: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   93: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   96: new 350	android/os/Bundle
      //   99: dup
      //   100: invokespecial 351	android/os/Bundle:<init>	()V
      //   103: astore 10
      //   105: aload 10
      //   107: ldc_w 353
      //   110: aload_0
      //   111: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   114: getfield 73	com/Mahmood/parking1/MainActivity:gotLat	Ljava/lang/String;
      //   117: invokevirtual 357	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
      //   120: aload 10
      //   122: ldc_w 359
      //   125: aload_0
      //   126: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   129: getfield 82	com/Mahmood/parking1/MainActivity:gotLon	Ljava/lang/String;
      //   132: invokevirtual 357	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
      //   135: aload_0
      //   136: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   139: new 361	android/content/Intent
      //   142: dup
      //   143: aload_0
      //   144: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   147: ldc_w 363
      //   150: invokespecial 366	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
      //   153: putfield 370	com/Mahmood/parking1/MainActivity:googleIntent	Landroid/content/Intent;
      //   156: aload_0
      //   157: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   160: getfield 370	com/Mahmood/parking1/MainActivity:googleIntent	Landroid/content/Intent;
      //   163: aload 10
      //   165: invokevirtual 374	android/content/Intent:putExtras	(Landroid/os/Bundle;)Landroid/content/Intent;
      //   168: pop
      //   169: aload_0
      //   170: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   173: aload_0
      //   174: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   177: getfield 370	com/Mahmood/parking1/MainActivity:googleIntent	Landroid/content/Intent;
      //   180: invokevirtual 378	com/Mahmood/parking1/MainActivity:startActivity	(Landroid/content/Intent;)V
      //   183: aload_0
      //   184: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   187: invokevirtual 381	com/Mahmood/parking1/MainActivity:finish	()V
      //   190: aload_0
      //   191: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   194: invokestatic 385	com/Mahmood/parking1/MainActivity:access$1	(Lcom/Mahmood/parking1/MainActivity;)Landroid/app/ProgressDialog;
      //   197: ifnull +13 -> 210
      //   200: aload_0
      //   201: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   204: invokestatic 385	com/Mahmood/parking1/MainActivity:access$1	(Lcom/Mahmood/parking1/MainActivity;)Landroid/app/ProgressDialog;
      //   207: invokevirtual 390	android/app/ProgressDialog:dismiss	()V
      //   210: aload_0
      //   211: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   214: ldc_w 391
      //   217: invokevirtual 394	com/Mahmood/parking1/MainActivity:setContentView	(I)V
      //   220: iconst_1
      //   221: istore_3
      //   222: iload_3
      //   223: iconst_m1
      //   224: aload_0
      //   225: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   228: invokevirtual 219	org/json/JSONArray:length	()I
      //   231: iadd
      //   232: if_icmplt +19 -> 251
      //   235: aload_0
      //   236: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   239: new 398	com/Mahmood/parking1/MainActivity$MyAsyncTask$1
      //   242: dup
      //   243: aload_0
      //   244: invokespecial 401	com/Mahmood/parking1/MainActivity$MyAsyncTask$1:<init>	(Lcom/Mahmood/parking1/MainActivity$MyAsyncTask;)V
      //   247: invokevirtual 407	com/google/android/gms/maps/GoogleMap:setOnInfoWindowClickListener	(Lcom/google/android/gms/maps/GoogleMap$OnInfoWindowClickListener;)V
      //   250: return
      //   251: aload_0
      //   252: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   255: aload_0
      //   256: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   259: iload_3
      //   260: invokevirtual 252	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   263: ldc_w 260
      //   266: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   269: invokestatic 413	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   272: aload_0
      //   273: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   276: iload_3
      //   277: invokevirtual 252	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   280: ldc_w 258
      //   283: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   286: invokestatic 413	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   289: isub
      //   290: putfield 417	com/Mahmood/parking1/MainActivity:result1	I
      //   293: aload_0
      //   294: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   297: ifnonnull +26 -> 323
      //   300: aload_0
      //   301: aload_0
      //   302: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   305: invokevirtual 421	com/Mahmood/parking1/MainActivity:getFragmentManager	()Landroid/app/FragmentManager;
      //   308: ldc_w 422
      //   311: invokevirtual 428	android/app/FragmentManager:findFragmentById	(I)Landroid/app/Fragment;
      //   314: checkcast 430	com/google/android/gms/maps/MapFragment
      //   317: invokevirtual 434	com/google/android/gms/maps/MapFragment:getMap	()Lcom/google/android/gms/maps/GoogleMap;
      //   320: putfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   323: aload_0
      //   324: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   327: iconst_1
      //   328: invokevirtual 438	com/google/android/gms/maps/GoogleMap:setMyLocationEnabled	(Z)V
      //   331: aload_0
      //   332: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   335: new 440	com/google/android/gms/maps/model/LatLng
      //   338: dup
      //   339: aload_0
      //   340: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   343: getfield 73	com/Mahmood/parking1/MainActivity:gotLat	Ljava/lang/String;
      //   346: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   349: aload_0
      //   350: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   353: getfield 82	com/Mahmood/parking1/MainActivity:gotLon	Ljava/lang/String;
      //   356: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   359: invokespecial 449	com/google/android/gms/maps/model/LatLng:<init>	(DD)V
      //   362: putfield 453	com/Mahmood/parking1/MainActivity:CURRENT	Lcom/google/android/gms/maps/model/LatLng;
      //   365: aload_0
      //   366: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   369: aload_0
      //   370: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   373: getfield 453	com/Mahmood/parking1/MainActivity:CURRENT	Lcom/google/android/gms/maps/model/LatLng;
      //   376: ldc_w 454
      //   379: invokestatic 460	com/google/android/gms/maps/CameraUpdateFactory:newLatLngZoom	(Lcom/google/android/gms/maps/model/LatLng;F)Lcom/google/android/gms/maps/CameraUpdate;
      //   382: invokevirtual 464	com/google/android/gms/maps/GoogleMap:moveCamera	(Lcom/google/android/gms/maps/CameraUpdate;)V
      //   385: aload_0
      //   386: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   389: ldc_w 454
      //   392: invokestatic 468	com/google/android/gms/maps/CameraUpdateFactory:zoomTo	(F)Lcom/google/android/gms/maps/CameraUpdate;
      //   395: sipush 2000
      //   398: aconst_null
      //   399: invokevirtual 472	com/google/android/gms/maps/GoogleMap:animateCamera	(Lcom/google/android/gms/maps/CameraUpdate;ILcom/google/android/gms/maps/GoogleMap$CancelableCallback;)V
      //   402: aload_0
      //   403: getfield 24	com/Mahmood/parking1/MainActivity$MyAsyncTask:this$0	Lcom/Mahmood/parking1/MainActivity;
      //   406: getfield 417	com/Mahmood/parking1/MainActivity:result1	I
      //   409: ifle +123 -> 532
      //   412: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   415: ldc_w 474
      //   418: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   421: aload_0
      //   422: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   425: new 476	com/google/android/gms/maps/model/MarkerOptions
      //   428: dup
      //   429: invokespecial 477	com/google/android/gms/maps/model/MarkerOptions:<init>	()V
      //   432: ldc_w 479
      //   435: invokevirtual 483	com/google/android/gms/maps/model/MarkerOptions:title	(Ljava/lang/String;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   438: ldc_w 484
      //   441: invokestatic 490	com/google/android/gms/maps/model/BitmapDescriptorFactory:defaultMarker	(F)Lcom/google/android/gms/maps/model/BitmapDescriptor;
      //   444: invokevirtual 494	com/google/android/gms/maps/model/MarkerOptions:icon	(Lcom/google/android/gms/maps/model/BitmapDescriptor;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   447: aload_0
      //   448: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   451: iload_3
      //   452: invokevirtual 252	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   455: ldc_w 256
      //   458: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   461: invokevirtual 497	com/google/android/gms/maps/model/MarkerOptions:snippet	(Ljava/lang/String;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   464: new 440	com/google/android/gms/maps/model/LatLng
      //   467: dup
      //   468: aload_0
      //   469: getfield 38	com/Mahmood/parking1/MainActivity$MyAsyncTask:lon	Ljava/util/ArrayList;
      //   472: iload_3
      //   473: invokevirtual 498	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   476: checkcast 165	java/lang/String
      //   479: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   482: aload_0
      //   483: getfield 36	com/Mahmood/parking1/MainActivity$MyAsyncTask:lat	Ljava/util/ArrayList;
      //   486: iload_3
      //   487: invokevirtual 498	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   490: checkcast 165	java/lang/String
      //   493: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   496: invokespecial 449	com/google/android/gms/maps/model/LatLng:<init>	(DD)V
      //   499: invokevirtual 502	com/google/android/gms/maps/model/MarkerOptions:position	(Lcom/google/android/gms/maps/model/LatLng;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   502: invokevirtual 506	com/google/android/gms/maps/GoogleMap:addMarker	(Lcom/google/android/gms/maps/model/MarkerOptions;)Lcom/google/android/gms/maps/model/Marker;
      //   505: pop
      //   506: iinc 3 1
      //   509: goto -287 -> 222
      //   512: astore 9
      //   514: aload 9
      //   516: invokevirtual 507	java/lang/NumberFormatException:printStackTrace	()V
      //   519: goto -226 -> 293
      //   522: astore 4
      //   524: aload 4
      //   526: invokevirtual 508	org/json/JSONException:printStackTrace	()V
      //   529: goto -236 -> 293
      //   532: getstatic 60	java/lang/System:out	Ljava/io/PrintStream;
      //   535: ldc_w 510
      //   538: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   541: aload_0
      //   542: getfield 396	com/Mahmood/parking1/MainActivity$MyAsyncTask:map	Lcom/google/android/gms/maps/GoogleMap;
      //   545: new 476	com/google/android/gms/maps/model/MarkerOptions
      //   548: dup
      //   549: invokespecial 477	com/google/android/gms/maps/model/MarkerOptions:<init>	()V
      //   552: ldc_w 479
      //   555: invokevirtual 483	com/google/android/gms/maps/model/MarkerOptions:title	(Ljava/lang/String;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   558: fconst_0
      //   559: invokestatic 490	com/google/android/gms/maps/model/BitmapDescriptorFactory:defaultMarker	(F)Lcom/google/android/gms/maps/model/BitmapDescriptor;
      //   562: invokevirtual 494	com/google/android/gms/maps/model/MarkerOptions:icon	(Lcom/google/android/gms/maps/model/BitmapDescriptor;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   565: aload_0
      //   566: getfield 208	com/Mahmood/parking1/MainActivity$MyAsyncTask:Avl	Lorg/json/JSONArray;
      //   569: iload_3
      //   570: invokevirtual 252	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   573: ldc_w 256
      //   576: invokevirtual 178	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   579: invokevirtual 497	com/google/android/gms/maps/model/MarkerOptions:snippet	(Ljava/lang/String;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   582: new 440	com/google/android/gms/maps/model/LatLng
      //   585: dup
      //   586: aload_0
      //   587: getfield 38	com/Mahmood/parking1/MainActivity$MyAsyncTask:lon	Ljava/util/ArrayList;
      //   590: iload_3
      //   591: invokevirtual 498	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   594: checkcast 165	java/lang/String
      //   597: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   600: aload_0
      //   601: getfield 36	com/Mahmood/parking1/MainActivity$MyAsyncTask:lat	Ljava/util/ArrayList;
      //   604: iload_3
      //   605: invokevirtual 498	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   608: checkcast 165	java/lang/String
      //   611: invokestatic 446	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   614: invokespecial 449	com/google/android/gms/maps/model/LatLng:<init>	(DD)V
      //   617: invokevirtual 502	com/google/android/gms/maps/model/MarkerOptions:position	(Lcom/google/android/gms/maps/model/LatLng;)Lcom/google/android/gms/maps/model/MarkerOptions;
      //   620: invokevirtual 506	com/google/android/gms/maps/GoogleMap:addMarker	(Lcom/google/android/gms/maps/model/MarkerOptions;)Lcom/google/android/gms/maps/model/Marker;
      //   623: pop
      //   624: goto -118 -> 506
      //   627: astore 6
      //   629: aload 6
      //   631: invokevirtual 507	java/lang/NumberFormatException:printStackTrace	()V
      //   634: goto -128 -> 506
      //   637: astore 5
      //   639: aload 5
      //   641: invokevirtual 508	org/json/JSONException:printStackTrace	()V
      //   644: goto -138 -> 506
      //
      // Exception table:
      //   from	to	target	type
      //   251	293	512	java/lang/NumberFormatException
      //   251	293	522	org/json/JSONException
      //   402	506	627	java/lang/NumberFormatException
      //   532	624	627	java/lang/NumberFormatException
      //   402	506	637	org/json/JSONException
      //   532	624	637	org/json/JSONException
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      MainActivity.this.pd = new ProgressDialog(MainActivity.this);
      MainActivity.this.pd.setTitle("Processing...");
      MainActivity.this.pd.setMessage("Please wait.");
      MainActivity.this.pd.setCancelable(false);
      MainActivity.this.pd.setIndeterminate(true);
      MainActivity.this.pd.show();
    }
  }
}

