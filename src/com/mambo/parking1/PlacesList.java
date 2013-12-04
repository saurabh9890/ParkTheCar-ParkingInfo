package com.mambo.parking1;

import com.google.api.client.util.Key;
import java.io.Serializable;
import java.util.List;

public class PlacesList
  implements Serializable
{

  @Key
  public List<Place> results;

  @Key
  public String status;
}

