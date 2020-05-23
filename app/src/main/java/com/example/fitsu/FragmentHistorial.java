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

        llenarLista();

        Adaptador adp = new Adaptador(listaOutfits);

        recyclerHistorial.setAdapter(adp);

        return view;
    }

    private void cargarWebService(){
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Consultando imagenes");
        dialog.show();

        //En el minuto 8 del video puedes ver el url que pone aqui para que lo compares con el tuyo
        String url = "";

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

        JSONArray json = response.optJSONArray("historial"); // aqui va el nombre de la tabla

        try{
            for (int i=0; i < json.length(); i++){
                historial = new Historial();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                historial.setFecha(jsonObject.optString("fecha")); // o el nombre de donde va la fecha
                historial.setDatoG(jsonObject.optString("imagenG")); //donde esta la imagen
                historial.setDatoP(jsonObject.optString("imagenP")); //La misma imagen pero para la miniatura
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
