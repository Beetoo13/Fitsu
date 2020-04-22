package com.example.fitsu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList <Entidad> outfitList;

    public Adaptador(Context context, ArrayList<Entidad> outfitList) {
        this.context = context;
        this.outfitList = outfitList;
    }

    @Override
    public int getCount() {
        return outfitList.size();
    }

    @Override
    public Object getItem(int position) {
        return outfitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad Item = (Entidad) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.outfit_item, null);
        ImageView outfit_item = convertView.findViewById(R.id.outfit);
        TextView tv_fecha = convertView.findViewById(R.id.fecha);

        outfit_item.setImageResource(Item.getImg_historial());
        tv_fecha.setText(Item.getFecha());

        return convertView;
    }
}
