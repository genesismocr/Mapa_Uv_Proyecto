package com.example.dan.mapa_uv_proyecto;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button Busqueda, busqueda2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Busqueda = (Button)findViewById(R.id.btnExterna);
        busqueda2 = (Button)findViewById(R.id.btnInterna);

        Busqueda.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent intencion = new Intent(MainActivity.this, Activity_navigation.class);
                startActivity(intencion);
            }
        });


        busqueda2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent inten = new Intent(MainActivity.this, busquedaInterna.class);
                startActivity(inten);
            }
        });
    }
}
