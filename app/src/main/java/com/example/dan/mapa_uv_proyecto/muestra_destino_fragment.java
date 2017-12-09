package com.example.dan.mapa_uv_proyecto;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class muestra_destino_fragment extends Fragment{

    Button btn;
    ImageView img;
    String recuperada;
    Toast msj;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        View root;
        root = inflater.inflate(R.layout.muestra_destino_fragment, container, false);

        btn = (Button)root.findViewById(R.id.idBotonImagen);
        img = (ImageView)root.findViewById(R.id.idimg);

        recuperada = bundle.getString("destino");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(recuperada.equalsIgnoreCase("un meme")) img.setImageResource(R.drawable.meme);
                else img.setImageResource(R.drawable.stanley);
            }
        });

        return root;
    }
}
