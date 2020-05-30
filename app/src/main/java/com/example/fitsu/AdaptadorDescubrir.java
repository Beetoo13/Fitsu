package com.example.fitsu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDescubrir extends RecyclerView.Adapter<AdaptadorDescubrir.MyViewHolder> implements View.OnClickListener {
    private ArrayList <Publicacion> publicacionesList;
    private Context context;
    private View.OnClickListener click;

    public void setOnClickListener(View.OnClickListener listener) {
        this.click = listener;
    }

    @Override
    public void onClick(View v) {
        if (click!=null){
            click.onClick(v);
        }
    }

    public AdaptadorDescubrir(Context context, ArrayList<Publicacion> publicacionesList) {
        this.context = context;
        this.publicacionesList = publicacionesList;
    }

    @NonNull
    @Override
    public AdaptadorDescubrir.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicacion_item, parent, false);
        return new AdaptadorDescubrir.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDescubrir.MyViewHolder holder, int position) {
        //holder.outfitPublicacion.setImageResource(publicacionesList.get(position).getOutfitPost());
        holder.nameP.setText(publicacionesList.get(position).getNameP());
        holder.contentP.setText(publicacionesList.get(position).getContentP());

        if (publicacionesList.get(position).getOutfitBit() != null) {
            holder.outfitPublicacion.setImageBitmap(publicacionesList.get(position).getOutfitBit());
        } else {
            holder.outfitPublicacion.setImageResource(R.drawable.outfit1);
        }
    }

    @Override
    public int getItemCount() {
        return publicacionesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView outfitPublicacion;
        TextView nameP, contentP;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            outfitPublicacion = itemView.findViewById(R.id.outfitPost);
            nameP = itemView.findViewById(R.id.namePost);
            contentP = itemView.findViewById(R.id.contentPost);
        }
    }
}
