package com.mambo.parking1;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;
import java.io.PrintStream;
import org.apache.http.client.HttpResponseException;

public class GooglePlaces
{
  private static final String API_KEY = "AIzaSyDwC28IxNDkoRg3u2Q8PzoSXlhimCyK8bo";
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
  private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
  private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
  private double _latitude;
  private double _longitude;
  private double _radius;
  private double rankby;

  public static HttpRequestFactory createRequestFactory(HttpTransport paramHttpTransport)
  {
    return paramHttpTransport.createRequestFactory(new HttpRequestInitializer()
    {
      public void initialize(HttpRequest paramAnonymousHttpRequest)
      {
        GoogleHeaders localGoogleHeaders = new GoogleHeaders();
        localGoogleHeaders.setApplicationName("Amey-Places-Test");
        paramAnonymousHttpRequest.setHeaders(localGoogleHeaders);
        paramAnonymousHttpRequest.addParser(new JsonHttpParser(new JacksonFactory()));
      }
    });
  }

  public PlaceDetails getPlaceDetails(String paramString)
    throws Exception
  {
    try
    {
      HttpRequest localHttpRequest = createRequestFactory(HTTP_TRANSPORT).buildGetRequest(new GenericUrl("https://maps.googleapis.com/maps/api/place/details/json?"));
      localHttpRequest.getUrl().put("key", "AIzaSyDwC28IxNDkoRg3u2Q8PzoSXlhimCyK8bo");
      localHttpRequest.getUrl().put("reference", paramString);
      localHttpRequest.getUrl().put("sensor", "false");
      PlaceDetails localPlaceDetails = (PlaceDetails)localHttpRequest.execute().parseAs(PlaceDetails.class);
      return localPlaceDetails;
    }
    catch (HttpResponseException localHttpResponseException)
    {
      throw localHttpResponseException;
    }
  }

  public PlacesList search(double paramDouble1, double paramDouble2, String paramString)
    throws Exception
  {
    this._latitude = paramDouble1;
    this._longitude = paramDouble2;
    try
    {
      HttpRequest localHttpRequest = createRequestFactory(HTTP_TRANSPORT).buildGetRequest(new GenericUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?"));
      localHttpRequest.getUrl().put("key", "AIzaSyDwC28IxNDkoRg3u2Q8PzoSXlhimCyK8bo");
      localHttpRequest.getUrl().put("location", this._latitude + "," + this._longitude);
      localHttpRequest.getUrl().put("sensor", "false");
      localHttpRequest.getUrl().put("rankby", "distance");
      if (paramString != null)
        localHttpRequest.getUrl().put("types", paramString);
      System.out.println("Request issssssssssssssssssssssssss:" + localHttpRequest);
      PlacesList localPlacesList = (PlacesList)localHttpRequest.execute().parseAs(PlacesList.class);
      System.out.println("************************************************************ List is......................" + localPlacesList);
      return localPlacesList;
    }
    catch (HttpResponseException localHttpResponseException)
    {
    }
    return null;
  }
}

