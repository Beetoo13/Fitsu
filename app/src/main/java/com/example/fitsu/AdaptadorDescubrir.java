package com.example.fitsu;

import android.content.Context;
import android.content.Intent;
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

    String nombreEnviar, contentEnviar;

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

    // Define listener member variable
    private static Adaptador.OnRecyclerViewItemClickListener mListener;

    // Define the listener interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClicked(String text);
    }

    // Define the method that allows the parent activity or fragment to define the listener.
    public void setOnRecyclerViewItemClickListener(Adaptador.OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AdaptadorDescubrir.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicacion_item, parent, false);
        return new AdaptadorDescubrir.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDescubrir.MyViewHolder holder, int position) {
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

        //Contexto
        Context context;

        ImageView outfitPublicacion;
        TextView nameP, contentP;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context =itemView.getContext();

            outfitPublicacion = itemView.findViewById(R.id.outfitPost);
            nameP = itemView.findViewById(R.id.namePost);
            contentP = itemView.findViewById(R.id.contentPost);

            /*
            this.outfitPublicacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nombreEnviar = nameP.getText().toString();
                    contentEnviar = contentP.getText().toString();
                    //Faltaria la imagen pero no se como se pasa en el intent

                    //Aqui iria intent = new intent (ctx, actvtyDesc.class)
                    intent.putExtra("nombre", nombreEnviar);
                    intent.putExtra("contenido", contentEnviar);
                    //Aqui faltaria añadir la imagen tambien

                    context.startActivity(intent);
                }
            });

             */
        }
    }
}
