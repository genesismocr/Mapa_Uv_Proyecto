package com.example.dan.mapa_uv_proyecto;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class recibe_destino_fragment extends Fragment{


    private Button enviar;
    Toast msj;
    public static int ok = 1;
    private destinoListener escuchador;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.recibe_destino_fragment, container, false);

        enviar = (Button)root.findViewById(R.id.idbtn);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                destino_pulsado(v);
            }
        });

        return root;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        destinoListener a;

        if(context instanceof destinoListener){

            a = (destinoListener)context;

            try{
                escuchador = a;

            }catch (ClassCastException e){

                msj = Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                msj.show();
            }

        }
    }


    public void destino_pulsado(View v){

        if(null == escuchador){

            return;
        }

        if( ((Button)v).getText().equals("Aceptar") ){

            escuchador.destino_elegido_prueba( ((EditText)getActivity().findViewById(R.id.idtxt)).getText().toString() );
        }
    }
}
