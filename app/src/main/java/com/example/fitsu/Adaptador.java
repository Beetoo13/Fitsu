package com.example.fitsu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> implements View.OnClickListener {
    private ArrayList <Historial> outfitList;
    private View.OnClickListener click;

    public void setOnClickListener(View.OnClickListener listener){
        this.click = listener;
    }

    @Override
    public void onClick(View v) {
        if (click!=null){
            click.onClick(v);
        }
    }

    public Adaptador(ArrayList<Historial> outfitList) {
        this.outfitList = outfitList;
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

        if (outfitList.get(position).getOutfitG()!=null){
            holder.outfitEnfoque.setImageBitmap(outfitList.get(position).getOutfitG());
        } else {
            holder.outfitEnfoque.setImageResource(R.drawable.outfit1);
        }

        if (outfitList.get(position).getOutfitP()!=null){
            holder.outfitEnfoque.setImageBitmap(outfitList.get(position).getOutfitP());
        } else {
            holder.outfitEnfoque.setImageResource(R.drawable.outfit1);
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
        }
    }
}
