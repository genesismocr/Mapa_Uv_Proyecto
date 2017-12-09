package com.example.dan.mapa_uv_proyecto;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Activity_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, destinoListener{

    SupportMapFragment mapFragment;
    private GoogleMap myMap;
    int status;
    Dialog aviso;
    Toast msj;
    private Marker pin, marcador;
    FragmentManager fm = getFragmentManager();
    double lat = 0, lon = 0;
    int stCiber = 0, stPapeleria = 0, stFonda = 0, stOxxo = 0, stFarmacia = 0, stUnidad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaa);
            //mapFragment.getMapAsync(this);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sfm = getSupportFragmentManager();
        int id = item.getItemId();


        if (id == R.id.idCiberr) {

            //msj = Toast.makeText(getApplicationContext(), "has presionado en ciber", Toast.LENGTH_SHORT);
            //msj.show();

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 1;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 0;

            mapFragment.getMapAsync(this);


        } else if (id == R.id.idPapeleria) {

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 1;
            stUnidad = 0;

            mapFragment.getMapAsync(this);

        } else if (id == R.id.idFondaa) {

            //msj = Toast.makeText(getApplicationContext(), "has presionado en fonda", Toast.LENGTH_SHORT);
            //msj.show();

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 1;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 0;

            mapFragment.getMapAsync(this);

        } else if (id == R.id.idFarmacia) {

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 0;
            stFarmacia = 1;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 0;

            mapFragment.getMapAsync(this);

        } else if (id == R.id.idDeportivo) {

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 1;

            mapFragment.getMapAsync(this);

        } else if (id == R.id.idOxxo){

            if( mapFragment.isHidden() ){
                sfm.beginTransaction().show(mapFragment).commit();
            }

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 1;
            stPapeleria = 0;
            stUnidad = 0;

            mapFragment.getMapAsync(this);
        }
        /*else if(id == R.id.idInstrucciones){

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 0;

            if(mapFragment.isAdded()){
                sfm.beginTransaction().hide(mapFragment).commit();
            }
                                                                                    //Esta parte es para mostrar otro fragment
            fm.beginTransaction().replace(R.id.content_frame, new instrucciones_fragment()).commit();
        }
        else{

            stCiber = 0;
            stFarmacia = 0;
            stFonda = 0;
            stOxxo = 0;
            stPapeleria = 0;
            stUnidad = 0;

            if(mapFragment.isAdded()){
                sfm.beginTransaction().hide(mapFragment).commit();
            }

            fm.beginTransaction().replace(R.id.content_frame, new recibe_destino_fragment()).commit();

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        myMap = googleMap;
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings ajustes = myMap.getUiSettings();
        ajustes.setZoomControlsEnabled(true);

        if(stCiber == 1){

            if(marcador != null) myMap.clear();

            LatLng copyMaster = new LatLng(19.16261, -96.112958);
            LatLng laNaranaja = new LatLng(19.162622, -96.113009);
            LatLng constancia = new LatLng(19.162656, -96.113109);
            LatLng university = new LatLng(19.162703, -96.113432);
            LatLng internet = new LatLng(19.162863, -96.114207);
            LatLng internet2 = new LatLng(19.162974, -96.114919);
            LatLng tere = new LatLng(19.163013,  -96.115094);

            marcador = myMap.addMarker(new MarkerOptions().position(copyMaster).title("Copy Master"));
            marcador = myMap.addMarker(new MarkerOptions().position(laNaranaja).title("La naranja"));
            marcador = myMap.addMarker(new MarkerOptions().position(constancia).title("De las constancias"));
            marcador = myMap.addMarker(new MarkerOptions().position(university).title("University"));
            marcador = myMap.addMarker(new MarkerOptions().position(internet).title("Internet"));
            marcador = myMap.addMarker(new MarkerOptions().position(internet2).title("Internet2"));
            marcador = myMap.addMarker(new MarkerOptions().position(tere).title("Servicios tere"));
        }
        else if(stFonda == 1){

            if(marcador != null) myMap.clear();

            LatLng kitchen1 = new LatLng(19.166335, -96.115643);
            LatLng kitchen2 = new LatLng(19.164261, -96.114789);
            LatLng lima = new LatLng(19.162660, -96.113212);
            LatLng dnegri = new LatLng(9.162826,  -96.114098);
            LatLng desyunos = new LatLng(19.162974, -96.114919);
            LatLng tere = new LatLng(19.163013,  -96.115094);
            LatLng rellenita = new LatLng(19.163075, -96.115476);
            LatLng chilam = new LatLng(19.163090, -96.116577);
            LatLng blanca = new LatLng(19.164489, -96.114283);

            marcador = myMap.addMarker(new MarkerOptions().position(kitchen1).title("Aqui"));
            marcador = myMap.addMarker(new MarkerOptions().position(kitchen2).title("Doña tita y don Ramon"));
            marcador = myMap.addMarker(new MarkerOptions().position(lima).title("Tortas lima"));
            marcador = myMap.addMarker(new MarkerOptions().position(dnegri).title("Hojaldras"));
            marcador = myMap.addMarker(new MarkerOptions().position(desyunos).title("Desayunos"));
            marcador = myMap.addMarker(new MarkerOptions().position(tere).title("Comidas tere"));
            marcador = myMap.addMarker(new MarkerOptions().position(rellenita).title("La rellenita"));
            marcador = myMap.addMarker(new MarkerOptions().position(chilam).title("Tortas chilam"));
            marcador = myMap.addMarker(new MarkerOptions().position(blanca).title("Antojitos"));
        }
        else if(stPapeleria == 1){

            if(marcador != null) myMap.clear();

            LatLng profe = new LatLng(19.162907, -96.114475);

            marcador = myMap.addMarker(new MarkerOptions().position(profe).title("El profe"));
        }
        else if(stOxxo == 1){

            if(marcador != null) myMap.clear();

            LatLng yepas = new LatLng(19.162982, -96.115664);
            LatLng oxxo = new LatLng(19.158647, -96.113644);

            marcador = myMap.addMarker(new MarkerOptions().position(yepas).title("Yepas"));
            marcador = myMap.addMarker(new MarkerOptions().position(oxxo).title("Oxxo"));
        }
        else if(stUnidad == 1){

            if(marcador != null) myMap.clear();

            LatLng unidad = new LatLng(19.162843, -96.114896);

            marcador = myMap.addMarker(new MarkerOptions().position(unidad).title("FEFUV"));
        }
        else {

            if(marcador != null) myMap.clear();

            LatLng ahorro = new LatLng(19.165833, -96.112954);
            LatLng simi = new LatLng(19.165936, -96.113226);

            marcador = myMap.addMarker(new MarkerOptions().position(ahorro).title("Ahorro"));
            marcador = myMap.addMarker(new MarkerOptions().position(simi).title("Similares"));
        }


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

    }



    @Override
    public void destino_elegido_prueba(String texto){

        Fragment frag = new muestra_destino_fragment();     //Creo instancia del fragment al que le quiero pasar datos
        Bundle bund = new Bundle();

        bund.putString("destino", texto);
        frag.setArguments(bund);

        fm.beginTransaction().replace(R.id.content_frame, frag).commit();  //Muestro el fragment y a la misma vez mando los datos

    }
}
