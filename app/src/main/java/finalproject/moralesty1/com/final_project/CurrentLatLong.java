package finalproject.moralesty1.com.final_project;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Tyler on 5/4/2015.
 */
public class CurrentLatLong {
    Context mContext;
    private double latitude;
    private double longitude;

    public CurrentLatLong(Context mContext){
        this.mContext = mContext;
    }


    public void findLocation(){
        LocationManager lm = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

}
