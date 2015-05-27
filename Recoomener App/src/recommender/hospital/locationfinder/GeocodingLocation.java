package recommender.hospital.locationfinder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;

public class GeocodingLocation {

    private static final String TAG = "GeocodingLocation";
    String result = null;
public static double longt;
public static double lat;
public void getLatLongFromAddress(String address,final Context context)
{

    Geocoder geoCoder = new Geocoder(context, Locale.getDefault());    
    try 
    {
        List<Address> addresses = geoCoder.getFromLocationName(address , 1);
        if (addresses.size() > 0) 
        {            
            GeoPoint p = new GeoPoint(
                    (int) (addresses.get(0).getLatitude() * 1E6), 
                    (int) (addresses.get(0).getLongitude() * 1E6));

            lat=p.getLatitudeE6()/1E6;
            longt=p.getLongitudeE6()/1E6;

            Log.d("Latitude", ""+lat);
        Log.d("Longitude", ""+longt);
        }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

}
}