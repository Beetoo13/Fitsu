package com.example.fitsu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> implements View.OnClickListener, Serializable {
    private ArrayList<Historial> outfitList;
    private Context context;
    private View.OnClickListener click;
    Fragment fragmentDetallesHisto = new Fragment();

    Bundle bundle = new Bundle();

    String fechaEnviar = "";

    public void setOnClickListener(View.OnClickListener listener) {
        this.click = listener;
    }

    @Override
    public void onClick(View v) {
        if (click != null) {
            click.onClick(v);
        }
    }

    public Adaptador(Context context, ArrayList<Historial> outfitList) {
        this.context = context;
        this.outfitList = outfitList;
    }

    // Define listener member variable
    private static OnRecyclerViewItemClickListener mListener;

    // Define the listener interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClicked(String text);
    }

    // Define the method that allows the parent activity or fragment to define the listener.
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outfit_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.outfitEnfoque.setImageResource(outfitList.get(position).getOutfitEnfoque());
        //holder.outfitPequeño.setImageResource(outfitList.get(position).getOutfitPequeño());
        holder.fecha.setText(outfitList.get(position).getFecha());

        if (outfitList.get(position).getOutfitG() != null) {
            holder.outfitEnfoque.setImageBitmap(outfitList.get(position).getOutfitG());
        } else {
            holder.outfitEnfoque.setImageResource(R.drawable.outfit1);
        }

        if (outfitList.get(position).getOutfitP() != null) {
            holder.outfitPequeño.setImageBitmap(outfitList.get(position).getOutfitP());
        } else {
            holder.outfitPequeño.setImageResource(R.drawable.outfit1);
        }
    }

    @Override
    public int getItemCount() {
        return outfitList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView outfitEnfoque, outfitPequeño;
        TextView fecha;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            outfitEnfoque = itemView.findViewById(R.id.outfit_card);
            outfitPequeño = itemView.findViewById(R.id.miniOut_card);
            fecha = itemView.findViewById(R.id.fecha_card);

            this.outfitEnfoque.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // send the text to the listener, i.e Activity.
                    fechaEnviar = fecha.getText().toString();
                    System.out.println("fechaEnviar: " + fechaEnviar);
                    bundle.putString("fecha", fechaEnviar);
                    fragmentDetallesHisto.setArguments(bundle);
                    //((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentDetallesHistorial()).commit();
                }
            });

        }
    }
}
