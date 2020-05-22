package com.example.fitsu;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.util.ArrayList;

public class FragmentDescubrir extends Fragment {
    RecyclerView recyclerPublicaciones;
    ArrayList<Publicacion> listaPublicaciones;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descubrir, container, false);

        listaPublicaciones = new ArrayList<>();
        recyclerPublicaciones = view.findViewById(R.id.publiRecycler);
        recyclerPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        AdaptadorDescubrir adp = new AdaptadorDescubrir(listaPublicaciones);

        recyclerPublicaciones.setAdapter(adp);

        return  view;
    }

    public void llenarLista(){
        listaPublicaciones.add(new Publicacion(R.drawable.image1, "Mariana Echeverria", "Mira mi outfit sugerido de hoy!"));
        listaPublicaciones.add(new Publicacion(R.drawable.image2, "Andrea Bejarano", "Este es mi outfit del dia ;)"));
        listaPublicaciones.add(new Publicacion(R.drawable.image3, "Fernanda Valenzuela", "Les comparto mi sugerencia de este dia muchachos:*"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit4, "La Dua Lipa", "Me encantó el outfit que obtuve hoy, a ustedes no?"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit3, "Angela Aguilar", "Mi papi me presto su chamarra"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit2, "Lana Rhoades", "Hola a todos mis seguidores, como estan hoy?"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit1, "Jessica Alba", "En muchas ocasiones menos es mas;)"));
    }
}
