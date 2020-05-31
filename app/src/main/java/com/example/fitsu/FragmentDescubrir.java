package com.example.fitsu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class FragmentDescubrir extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    //Creados el recycler y arrayList
    RecyclerView recyclerPublicaciones;
    ArrayList<Publicacion> listaPublicaciones;

    //Aqui cree lo que faltaba
    ProgressDialog dialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descubrir, container, false);

        listaPublicaciones = new ArrayList<>();
        recyclerPublicaciones = view.findViewById(R.id.publiRecycler);
        recyclerPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        context = getActivity().getApplicationContext();

        //Instanciaciones de lo que agregamos recientemente
        request = Volley.newRequestQueue(getContext());

        cargarWebService();

        //llenarLista();

        AdaptadorDescubrir adp = new AdaptadorDescubrir(context, listaPublicaciones);

        recyclerPublicaciones.setAdapter(adp);

        //Si funciona el metodo del otro fragmento entonces seria poner el mismo aqui

        return  view;
    }

    public void cargarWebService(){
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Consultando historial");
        dialog.show();

        //String url = "http://192.168.1.66:3000/historial";
        String url = "http://192.168.8.105:3000/historial";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Publicacion publicacion = null;

        JSONArray jsonConjuntos = null; // aqui va el nombre de la tabla
        int jsonNumeroDocs = 0;

        try {
            jsonConjuntos = response.getJSONArray("conjuntos");//seria cambiar las tablas y lo que sea necesario
            jsonNumeroDocs = response.getInt("numeroDocs");
            System.out.println("Respuesta historial: " + jsonConjuntos);
            System.out.println("Respuesta num documentos: " + jsonNumeroDocs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonNumeroDocs; i++) {
            publicacion = new Publicacion();
            JSONObject jsonObjectFecha = null;
            JSONObject jsonObjectDatoImagen = null;

            try {
                jsonObjectFecha = jsonConjuntos.getJSONObject(i);
                //System.out.println("jsonObject #" + (i+1) + ": " + jsonObjectFecha);
                jsonObjectDatoImagen = jsonObjectFecha.getJSONObject("imgConjunto");
                //System.out.println("jsonObjImg: " + jsonObjectDatoImagen);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Aqui va igual que en el FragmentHistorial pero con sus respectivas tablas y su informacion
            //publicacion.setNameP();
            //publicacion.setContentP();
            //publicacion.setOutfitStr();

            listaPublicaciones.add(publicacion);
        }
        dialog.hide();
        AdaptadorDescubrir adp = new AdaptadorDescubrir(context, listaPublicaciones);
        recyclerPublicaciones.setAdapter(adp);
    }

    /*

    public void llenarLista(){
        listaPublicaciones.add(new Publicacion(R.drawable.image1, "Mariana Echeverria", "Mira mi outfit sugerido de hoy!"));
        listaPublicaciones.add(new Publicacion(R.drawable.image2, "Andrea Bejarano", "Este es mi outfit del dia ;)"));
        listaPublicaciones.add(new Publicacion(R.drawable.image3, "Fernanda Valenzuela", "Les comparto mi sugerencia de este dia muchachos:*"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit4, "La Dua Lipa", "Me encantó el outfit que obtuve hoy, a ustedes no?"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit3, "Angela Aguilar", "Mi papi me presto su chamarra"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit2, "Lana Rhoades", "Hola a todos mis seguidores, como estan hoy?"));
        listaPublicaciones.add(new Publicacion(R.drawable.outfit1, "Jessica Alba", "En muchas ocasiones menos es mas;)"));
    }

     */
}
