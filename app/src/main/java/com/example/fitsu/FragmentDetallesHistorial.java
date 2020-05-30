package com.example.fitsu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentDetallesHistorial extends Fragment {
    ImageView img, back;
    TextView fecha, fecha2;
    Button btn;
    String fechaRecibida;

    FragmentHistorial fragmentHistorial = new FragmentHistorial();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalles_historial, container, false);

        img = v.findViewById(R.id.img_detalles);
        fecha = v.findViewById(R.id.fecha_detalles);
        btn = v.findViewById(R.id.favorito);
        back = v.findViewById(R.id.back_btn);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            fechaRecibida = bundle.getString("fecha");
        }

        System.out.println("FechaRecibida:" + fechaRecibida);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHistorial()).commit();
            }
        });

        return v;
    }

}
