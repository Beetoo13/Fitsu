package com.example.fitsu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHistorial extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    //Ye estan creados el recuycler y el array
    RecyclerView recyclerHistorial;
    ArrayList<Historial> listaOutfits;

    //Aqui cree lo que faltaba
    ProgressDialog dialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        listaOutfits = new ArrayList<>();
        recyclerHistorial = view.findViewById(R.id.historial);
        recyclerHistorial.setLayoutManager(new LinearLayoutManager(getContext()));

        //Instanciaciones de lo que agregamos recientemente
        request = Volley.newRequestQueue(getContext());

        cargarWebService();

        //llenarLista();

        Adaptador adp = new Adaptador(listaOutfits);

        recyclerHistorial.setAdapter(adp);

        return view;
    }

    private void cargarWebService(){
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Consultando imagenes");
        dialog.show();

        //En el minuto 8 del video puedes ver el url que pone aqui para que lo compares con el tuyo
        String url = "http://192.168.1.66:3000/historial";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    //Metodos implementados
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Historial historial = null;

        JSONObject jsonConjuntos = null; // aqui va el nombre de la tabla
        int jsonNumeroDocs = 0;

        try {
            jsonConjuntos = response.getJSONObject("conjuntos");
            jsonNumeroDocs = response.getInt("numeroDocs");
            System.out.println("Respuesta historial: " + jsonConjuntos);
            System.out.println("Respuesta num documentos: " + jsonNumeroDocs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            for (int i=0; i < jsonNumeroDocs; i++){
                historial = new Historial();
                String jsonObjectFecha = null;
                JSONObject jsonObjectDatoG = null;
                JSONObject jsonObjectDatoP = null;
                String jsonObjectDatoGObtener = null;
                String jsonObjectDatoPObtener = null;

                jsonObjectFecha = jsonConjuntos.getString("savedAt");
                jsonObjectDatoG = jsonConjuntos.getJSONObject("imgTop");
                jsonObjectDatoP = jsonConjuntos.getJSONObject("imgTop");

                jsonObjectDatoPObtener = jsonObjectDatoP.getString("img64top");
                jsonObjectDatoGObtener = jsonObjectDatoG.getString("img64top");


                //System.out.println("Respuesta del saved at #" + i+1 + ": " + jsonObject);
                System.out.println("savedAt #:" + (i+1) + ": " + jsonObjectFecha);
                System.out.println("datoG #:" + (i+1) + ": " + jsonObjectDatoGObtener);
                System.out.println("datoP #:" + (i+1) + ": " + jsonObjectDatoPObtener);

                historial.setFecha(jsonObjectFecha); // o el nombre de donde va la fecha
                historial.setDatoP(jsonObjectDatoPObtener); //La misma imagen pero para la miniatura
                historial.setDatoG(jsonObjectDatoGObtener); //donde esta la imagen
                listaOutfits.add(historial);
            }
            dialog.hide();
            Adaptador adp = new Adaptador(listaOutfits);
            recyclerHistorial.setAdapter(adp);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" + response, Toast.LENGTH_SHORT).show();
            dialog.hide();
        }

    }

    //Con esto llenaba el recycler y ahora se llena con esto de arriba ^^^

    public void llenarLista(){
        listaOutfits.add(new Historial(R.drawable.outfit1, R.drawable.outfit1, "Lunes 17"));
        listaOutfits.add(new Historial(R.drawable.outfit2, R.drawable.outfit2, "Martes 18"));
        listaOutfits.add(new Historial(R.drawable.outfit3, R.drawable.outfit3, "Miércoles 19"));
        listaOutfits.add(new Historial(R.drawable.outfit4, R.drawable.outfit4, "Jueves 20"));
    }
}
