package com.example.dan.mapa_uv_proyecto;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    int status;
    String cadena;
    Dialog aviso;
    private Marker pin, marcador;
    double lat = 0, lon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {

            aviso = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            aviso.show();
        }
    }


    public void agregaMarcador(double lat, double lon) {

        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate miLocalizacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (pin != null) pin.remove();

        pin = myMap.addMarker(new MarkerOptions().position(coordenadas).title("Ubicacion actual").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

        //myMap.animateCamera(miLocalizacion);
    }


    public void actualizaUbicacion(Location loc) {

        if (loc != null) {

            lat = loc.getLatitude();
            lon = loc.getLongitude();
            agregaMarcador(lat, lon);
        }
    }


    LocationListener escuchaLocalizacion = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizaUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    public void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationManager locM = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location localiza = locM.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        actualizaUbicacion(localiza);

        locM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, escuchaLocalizacion);
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

        myMap = googleMap;
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings ajustes = myMap.getUiSettings();
        ajustes.setZoomControlsEnabled(true);

        Button fondita;
        Button ciber;

        fondita = (Button)findViewById(R.id.btnFonda);
        ciber = (Button)findViewById(R.id.btnCiber);

        fondita.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if(marcador != null) myMap.clear();

                LatLng kitchen1 = new LatLng(19.166335, -96.115643);
                marcador = myMap.addMarker(new MarkerOptions().position(kitchen1).title("Aquii"));

                LatLng kitchen2 = new LatLng(19.164261, -96.114789);
                marcador = myMap.addMarker(new MarkerOptions().position(kitchen2).title("Doña tita y don Ramón"));
            }
        });


        ciber.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if(marcador != null) myMap.clear();

                LatLng pap = new LatLng(19.16261, -96.112958);
                marcador = myMap.addMarker(new MarkerOptions().position(pap).title("Copy Master"));
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        myMap.setMyLocationEnabled(true);

        miUbicacion();
        //myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veracruz, 16));
    }
}
