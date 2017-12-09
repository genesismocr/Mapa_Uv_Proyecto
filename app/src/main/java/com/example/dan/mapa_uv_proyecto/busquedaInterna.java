package com.example.dan.mapa_uv_proyecto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class busquedaInterna extends AppCompatActivity{

    Button buscar;
    AutoCompleteTextView text;
    String texto, letra;
    Toast msj;
    ImageView img, img2;
    ImageButton tacha;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_interna);

        buscar = (Button)findViewById(R.id.btnIr);
        text = (AutoCompleteTextView)findViewById(R.id.txtBuscar);
        img = (ImageView)findViewById(R.id.idimg);
        img2 = (ImageView)findViewById(R.id.idimg2);
        tacha = (ImageButton)findViewById(R.id.boton_img);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line , F);

        text.setThreshold(1);
        text.setAdapter(adapter);
        text.requestFocus();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(text.getText().toString().isEmpty()){
                    msj = Toast.makeText(getApplicationContext(), "Debes rellenar el campo de busqueda", Toast.LENGTH_SHORT);
                    msj.show();
                }
                else{

                    texto = text.getText().toString();

                    if(texto.equalsIgnoreCase("f-ja")){

                        img.setImageResource(R.drawable.edificio_f_mapa);
                        img2.setImageResource(R.drawable.salon_fja);
                    }
                    else if(texto.equalsIgnoreCase("posgrado-01")
                            || texto.equalsIgnoreCase("posgrado-02") || texto.equalsIgnoreCase("posgrado-03") ){

                        img.setImageResource(R.drawable.edificio_posgrado_mapa);
                        img2.setImageResource(R.drawable.edificio_posgrados);
                    }
                    else if(texto.equalsIgnoreCase("vinculacion")){

                        img.setImageResource(R.drawable.edificio_vinculacion_mapa);
                        img2.setImageResource(R.drawable.edificio_vinc);
                    }
                    else if(texto.equalsIgnoreCase("sala-usos-multiples")){

                        img.setImageResource(R.drawable.edificio_usos_mapa);
                        img2.setImageResource(R.drawable.edificio_usos);
                    }
                    else if(texto.equalsIgnoreCase("aula-magna")){

                        img.setImageResource(R.drawable.edificio_magna_mapa);
                        img2.setImageResource(R.drawable.edificio_magna);
                    }
                    else if(texto.equalsIgnoreCase("direccion")){

                        img.setImageResource(R.drawable.edificio_a_mapa);
                        img2.setImageResource(R.drawable.edificio_direccion);
                    }
                    else if(texto.equalsIgnoreCase("microna")){

                        img.setImageResource(R.drawable.edificio_microna_mapa);
                        img2.setImageResource(R.drawable.edificio_microna);
                    }
                    else if(texto.equalsIgnoreCase("recolectron")){

                        img.setImageResource(R.drawable.recolectron);
                        img2.setImageResource(R.drawable.reco);
                    }
                    else if(texto.equalsIgnoreCase("biblioteca")){

                        img.setImageResource(R.drawable.edificio_j_mapa);
                        img2.setImageResource(R.drawable.biblio);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-quimica")){

                        img.setImageResource(R.drawable.edificio_l_mapa);
                        img2.setImageResource(R.drawable.jefatura_quimica);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-arquitectura")){

                        img.setImageResource(R.drawable.edificio_l_mapa);
                        img2.setImageResource(R.drawable.jefatura_arqui);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-informatica")
                            || (texto.equalsIgnoreCase("jefatura-electrica")) || (texto.equalsIgnoreCase("jefatura-electronica"))
                             || (texto.equalsIgnoreCase("jefatura-mecatronica"))){

                        img.setImageResource(R.drawable.edificio_f_mapa);
                        img2.setImageResource(R.drawable.jefaturaelectronicamecanicaelectricamecatronica);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-mecanica")){

                        img.setImageResource(R.drawable.edificio_a_mapa);
                        img2.setImageResource(R.drawable.jefatura_mecanica);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-civil")){

                        img.setImageResource(R.drawable.edificio_h_mapa);
                        img2.setImageResource(R.drawable.jefatura_civil);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-naval")){

                        img.setImageResource(R.drawable.edificio_j_mapa);
                        img2.setImageResource(R.drawable.jefatura_naval);
                    }
                    else if(texto.equalsIgnoreCase("jefatura-topografo")){

                        img.setImageResource(R.drawable.edificio_j_mapa);
                        img2.setImageResource(R.drawable.jefatura_topografo);
                    }
                    else{
                        texto = text.getText().toString();
                        letra = texto.substring(0,1);     //[inicio, fin]

                        if(letra.equalsIgnoreCase("f")){

                            img.setImageResource(R.drawable.edificio_f_mapa);
                            img2.setImageResource(R.drawable.edificio_f);
                        }
                        else if(letra.equalsIgnoreCase("h")){

                            img.setImageResource(R.drawable.edificio_h_mapa);
                            img2.setImageResource(R.drawable.edificio_h);
                        }
                        else if(letra.equalsIgnoreCase("i")){

                            img.setImageResource(R.drawable.edificio_i_mapa);
                            img2.setImageResource(R.drawable.edificio_i);
                        }
                        else if(letra.equalsIgnoreCase("l")){

                            img.setImageResource(R.drawable.edificio_l_mapa);
                            img2.setImageResource(R.drawable.edificio_l);
                        }
                        else if(letra.equalsIgnoreCase("a")){

                            img.setImageResource(R.drawable.edificio_a_mapa);
                            img2.setImageResource(R.drawable.edificio_a);
                        }
                        else{
                            msj = Toast.makeText(getApplicationContext(), "No existe ese lugar", Toast.LENGTH_SHORT);
                            msj.show();
                        }
                    }
                }

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(), 0);
            }
        });




        tacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text.setText("");
            }
        });

    }


    private String F[] = {"F-JA", "F-11", "F-13", "F-14", "F-22" ,"F-10", "F-12", "F-23", "F-01", "F-15", "Jefatura-Quimica", "Jefatura-Informatica", "Jefatura-Electrica",
    "H-11", "H-12", "H-13", "H-14", "H-21", "H-22", "H-23", "H-24", "H-25", "I-01", "I-02", "I-03", "I-04", "I-05", "I-11", "I-12", "I-13", "I-14", "I-15",
    "I-21", "I-22", "I-23", "I-24", "L-05", "L-06", "L-07", "L-08", "L-09", "L-10", "A-01", "A-02", "A-03", "A-04", "A-05", "Posgrado-01", "Posgrado-02",
    "Posgrado-03", "Vinculacion", "Sala-usos-multiples", "Aula-magna", "Jefatura-Arquitectura", "Direccion", "Jefatura-Electronica", "Jefatura-Mecanica",
    "Jefatura-Mecatronica", "Jefatura-Civil", "Jefatura-Naval", "Jefatura-Topografo", "Microna", "Recolectron", "Biblioteca"};
}
