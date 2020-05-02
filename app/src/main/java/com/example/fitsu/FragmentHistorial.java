package com.example.fitsu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentHistorial extends Fragment {
    RecyclerView recyclerHistorial;
    ArrayList<Historial> listaOutfits;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        listaOutfits = new ArrayList<>();
        recyclerHistorial = view.findViewById(R.id.historial);
        recyclerHistorial.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        Adaptador adp = new Adaptador(listaOutfits);

        recyclerHistorial.setAdapter(adp);

        return view;
    }

    public void llenarLista(){
        listaOutfits.add(new Historial(R.drawable.outfit1, R.drawable.outfit1, "Lunes 17"));
        listaOutfits.add(new Historial(R.drawable.outfit2, R.drawable.outfit2, "Martes 18"));
        listaOutfits.add(new Historial(R.drawable.outfit3, R.drawable.outfit3, "Miércoles 19"));
        listaOutfits.add(new Historial(R.drawable.outfit4, R.drawable.outfit4, "Jueves 20"));
    }
}
