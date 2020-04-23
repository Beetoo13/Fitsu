package com.example.fitsu;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.DialogInterface.*;

public class FragmentDetallesHistorial extends Fragment {
    Button btnFav;
    View vista;
    //Este boleano todavia no se usa, pero hay que tenerlo en cuenta para la lista de favoritos
    Boolean favorito = false;

    ImageView outfit;
    TextView fechax;
    ImageView comp1, comp2, comp3, comp4;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_detalles_historial, container, false);

        btnFav = vista.findViewById(R.id.favorito);

        outfit = vista.findViewById(R.id.img_detalles);
        fechax = vista.findViewById(R.id.fecha_detalles);
        comp1 = vista.findViewById(R.id.comp1);
        comp2 = vista.findViewById(R.id.comp2);
        comp3 = vista.findViewById(R.id.comp3);
        comp4 = vista.findViewById(R.id.comp4);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorito == false){
                    favorito = true;
                    btnFav.setText("Eliminar de tus favoritos");
                    Toast.makeText(getContext(), "Agregado a favoritos!", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getContext(), fechaExt.getText().toString(), Toast.LENGTH_SHORT).show();
                } else if (favorito == true){
                    favorito = false;
                    btnFav.setText("Agregar a favoritos");
                    Toast.makeText(getContext(), "Eliminado de tus favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        outfit.setImageResource(R.drawable.outfit4);
        //fechax.setText(fechaExt.getText().toString());
        //fecha.setText("Texto de prueba");
        comp1.setImageResource(R.drawable.playera2);
        comp2.setImageResource(R.drawable.short1);
        comp3.setImageResource(R.drawable.collar3);
        comp4.setImageResource(R.drawable.tenis1);

        return vista;
    }

}
