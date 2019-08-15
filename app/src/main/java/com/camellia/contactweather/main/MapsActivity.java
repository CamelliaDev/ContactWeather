package com.camellia.contactweather.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.camellia.contactweather.R;
import com.camellia.contactweather.contacts.DataBaseHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setSupportActionBar((Toolbar) findViewById(R.id.locationToolBar));
        setTitle("Location");

        db = new DataBaseHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm_location:
                LatLng target = mMap.getCameraPosition().target;
                Toast.makeText(this, "lat: " + target.latitude + "\nlon: " + target.longitude, Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                String contactName = intent.getStringExtra("displayName"); // will return "FirstKeyValue"
                String contactPhone = intent.getStringExtra("phone");
                boolean isUpdate = intent.getBooleanExtra("isUpdate", false);

                if (isUpdate) {
                    db.updateContactLocation(contactPhone, target.latitude, target.longitude);
                } else {
                    db.addContact(contactName, contactPhone, target.latitude, target.longitude);
                }

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng location;

        boolean isUpdate = getIntent().getBooleanExtra("isUpdate", false);
        if (isUpdate) {
            double lat = getIntent().getDoubleExtra("latitude", 32);
            double lon = getIntent().getDoubleExtra("longitude", 53);
            location = new LatLng(lat, lon);
        } else {
            location = new LatLng(32, 53);
        }

        // Add a marker in Iran and move the camera
//        mMap.addMarker(new MarkerOptions().position(location).title("Marker in Iran"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 6f));
    }
}
