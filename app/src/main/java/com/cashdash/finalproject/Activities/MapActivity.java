package com.cashdash.finalproject.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;

import com.cashdash.finalproject.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap map;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    Geocoder coder;
    List<Address> address;
    LatLng p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        coder = new Geocoder(MapActivity.this);
        p1 = null;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                map.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            map.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }



    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Your Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = map.addMarker(markerOptions);


        //item1
        LatLng item1 = new LatLng(22.297076,73.1957373);
        map.addMarker(new MarkerOptions().position(item1).title("item1 , Gujarat"));

        //item2
        LatLng item2 = new LatLng(22.307076,73.1957373);
        map.addMarker(new MarkerOptions().position(item2).title("item2 , Gujarat"));

        //item3
        LatLng item3 = new LatLng(22.297076,73.2557373);
        map.addMarker(new MarkerOptions().position(item3).title("item3 , Gujarat"));

        //item4
        LatLng item4 = new LatLng(22.297076,73.2657373);
        map.addMarker(new MarkerOptions().position(item4).title("item4 , Gujarat"));

        //item5
        LatLng item5 = new LatLng(22.287076,73.2757373);
        map.addMarker(new MarkerOptions().position(item5).title("item5 , Gujarat"));

        //item6
        LatLng item6 = new LatLng(22.277076,73.2857373);
        map.addMarker(new MarkerOptions().position(item6).title("item4 , Gujarat"));

        //item7
        LatLng item7 = new LatLng(22.267076,73.3057373);
        map.addMarker(new MarkerOptions().position(item7).title("item4 , Gujarat"));

        //item8
        LatLng item8 = new LatLng(22.317076,73.4057373);
        map.addMarker(new MarkerOptions().position(item8).title("item4 , Gujarat"));

        //item9
        LatLng item9 = new LatLng(22.327076,73.4257373);
        map.addMarker(new MarkerOptions().position(item9).title("item4 , Gujarat"));

        //item10
        LatLng item10 = new LatLng(22.297076,73.3857373);
        map.addMarker(new MarkerOptions().position(item10).title("item4 , Gujarat"));

        try {
            address = coder.getFromLocationName("Surat", 5);
            Address loc = address.get(0);
            p1 = new LatLng(loc.getLatitude(), loc.getLongitude() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.addMarker(new MarkerOptions().position(p1).title("Surat , Gujarat"));


        //move map camera
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void getCash(View view)
    {
        startActivity(new Intent(MapActivity.this,OrderActivity.class));
    }

}

