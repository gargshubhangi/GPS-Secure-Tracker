package minor.project;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class CurLocActivity extends Activity {
    
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    public static double longi=0.0;
    public static double lati=0.0;
    
   
    
    protected LocationManager locationManager;
    protected Location currentLocation;
    protected Button retrieveLocationButton;
    protected Button reverseGeocodingButton;
    protected Button encdecryButton;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cur_loc);

        retrieveLocationButton = (Button) findViewById(R.id.enc_msg);
        reverseGeocodingButton = (Button) findViewById(R.id.reverse_geocoding_button);
        encdecryButton = (Button)findViewById(R.id.retreive_location_button);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 
                MINIMUM_TIME_BETWEEN_UPDATES, 
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );
        
    retrieveLocationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Toast.makeText(CurLocActivity.this, "WELCOME..JUST WAIT FOR A SECOND !!",
                        Toast.LENGTH_LONG).show();
            	
                showCurrentLocation();
            }
    }); 
    
    reverseGeocodingButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	CurLocActivity.this.startActivity(new Intent(CurLocActivity.this,GeocodeActivity.class));
    }	
    });
    
    encdecryButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	CurLocActivity.this.startActivity(new Intent(CurLocActivity.this,EncDecActivity.class));
    }	
    });
    
   
        
    }  
    
    

    protected void showCurrentLocation() {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
        	longi=location.getLongitude();
        	lati=location.getLatitude();
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(CurLocActivity.this, message,
                    Toast.LENGTH_LONG).show();
        }

    }   

    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(CurLocActivity.this, message, Toast.LENGTH_LONG).show();
        }

        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(CurLocActivity.this, "Provider status changed",
                    Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(CurLocActivity.this,
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }

        public void onProviderEnabled(String s) {
            Toast.makeText(CurLocActivity.this,
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }

    }
    
   
    	
    	     

    }
    
