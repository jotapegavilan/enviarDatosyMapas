package com.gavilan.enviardatosymaps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private float latitud,longitd;
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        latitud = bundle.getFloat("latitud");
        longitd = bundle.getFloat("longitud");
        nombre = bundle.getString("nombre");

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Datos de un punto estatico
        Double latEstatica = -36.827260651417426;
        Double lonEstatica = -73.05020143313516;
        String titutloEstatica = "Plaza Concepción";
        LatLng plaza = new LatLng(latEstatica,lonEstatica);


        LatLng lugar = new LatLng(this.latitud,this.longitd);
        mMap.addMarker(new MarkerOptions().position(lugar).title(this.nombre));

        mMap.addMarker(new MarkerOptions().position(plaza).title(titutloEstatica));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar,17));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}