package com.example.geoloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);


        Context ctx = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListenerClass(ctx);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    String[] perms= {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    };
                    ActivityCompat.requestPermissions(MainActivity.this,perms,1);

                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            }


        });

    }
    public class LocationListenerClass implements LocationListener {
        Context context;
        public LocationListenerClass(Context ctx){
            this.context = ctx;
        }
        @Override
        public void onLocationChanged(@NonNull Location location) {
            EditText tvlatitude =((Activity)context).findViewById(R.id.editTextTextPersonName3);
            tvlatitude.setText(String.valueOf(location.getLatitude()));
            EditText tvlongitude =((Activity)context).findViewById(R.id.editTextTextPersonName4);
            tvlongitude.setText(String.valueOf(location.getLongitude()));
            //ISTA BERKANE COORDINATES 34.92994133784715, -2.318167978066086
            Location ISTALocation= new Location("ISTALocation");
            ISTALocation.setLongitude(-2.318167978066086);
            ISTALocation.setLatitude(34.92994133784715);
            float dist=location.distanceTo(ISTALocation);
            TextView tvdistance =((Activity)context).findViewById(R.id.textView6);
            tvdistance.setText("Distance to ISTA Berkane is : " + String.format("%.2f", dist/1000) + " Km.");

        }
    }
}