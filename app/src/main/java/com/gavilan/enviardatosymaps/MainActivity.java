package com.gavilan.enviardatosymaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity {

    EditText txtLat, txtLon, txtNombre;
    Button btnMapa, btnLimpiar;
    FusedLocationProviderClient fused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);
        txtNombre = findViewById(R.id.txtNombre);
        btnMapa = findViewById(R.id.btnMap);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        // Inicializando el objeto fusedLocation
        fused = LocationServices.getFusedLocationProviderClient(MainActivity.this);


        txtLat.setEnabled(false);
        txtLon.setEnabled(false);

        obtenerCoordenadas();


        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lat, lon;
                String nombre;

                lat = Float.parseFloat(txtLat.getText().toString());
                lon = Float.parseFloat(txtLon.getText().toString());
                nombre = txtNombre.getText().toString();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("latitud", lat);
                intent.putExtra("longitud", lon);
                intent.putExtra("nombre", nombre);
                startActivity(intent);


            }
        });
    }

    //Método que obtendra las coordenadas
    public void obtenerCoordenadas() {


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
        fused.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // En este caso programo acciones
                    txtLat.setText(String.valueOf(location.getLatitude()));
                    txtLon.setText(location.getLongitude() + "");
                }
            }
        });
    }


}