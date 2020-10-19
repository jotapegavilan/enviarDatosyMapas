package com.gavilan.enviardatosymaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtLat, txtLon, txtNombre;
    Button btnMapa, btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);
        txtNombre = findViewById(R.id.txtNombre);
        btnMapa = findViewById(R.id.btnMap);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lat, lon;
                String nombre;

                lat = Float.parseFloat(txtLat.getText().toString());
                lon = Float.parseFloat(txtLon.getText().toString());
                nombre = txtNombre.getText().toString();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("latitud",lat);
                intent.putExtra("longitud",lon);
                intent.putExtra("nombre",nombre);
                startActivity(intent);


            }
        });
    }
}