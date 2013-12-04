package com.mambo.parking1;

import com.google.api.client.util.Key;
import java.io.Serializable;

public class PlaceDetails
  implements Serializable
{

  @Key
  public Place result;

  @Key
  public String status;

  public String toString()
  {
    if (this.result != null)
      return this.result.toString();
    return super.toString();
  }
}

