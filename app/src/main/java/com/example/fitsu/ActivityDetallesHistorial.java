package com.example.fitsu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetallesHistorial extends AppCompatActivity {
    ImageView img, back;
    TextView fecha;
    Button btn;
    String fechaRecibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_historial);

        img = findViewById(R.id.img_detalles);
        fecha = findViewById(R.id.fecha_detalles);
        btn = findViewById(R.id.favorito);
        back = findViewById(R.id.back_btn);

        fechaRecibida = getIntent().getStringExtra("fecha");

        if (fechaRecibida != null) {
            fecha.setText(fechaRecibida);
        }

        System.out.println("FechaRecibida:" + fechaRecibida);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
