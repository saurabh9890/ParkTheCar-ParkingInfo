package com.mambo.parking1;

import com.google.api.client.util.Key;
import java.io.Serializable;

public class Place
  implements Serializable
{

  @Key
  public String formatted_address;

  @Key
  public String formatted_phone_number;

  @Key
  public Geometry geometry;

  @Key
  public String icon;

  @Key
  public String id;

  @Key
  public String name;

  @Key
  public String reference;

  @Key
  public String vicinity;

  public String toString()
  {
    return this.name + " - " + this.id + " - " + this.reference;
  }

  public static class Geometry
    implements Serializable
  {

    @Key
    public Place.Location location;
  }

  public static class Location
    implements Serializable
  {

    @Key
    public double lat;

    @Key
    public double lng;
  }
}

