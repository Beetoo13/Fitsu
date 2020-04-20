package com.example.fitsu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView imgViewPlayera, imgViewShort, imgViewCollar, imgViewTenis;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PRUEBA DEL GIT PARA AARÓN.

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId((R.id.nav_inicio));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_inicio:
                    selectedFragment = new FragmentHome();
                    break;
                case R.id.nav_descubrir:
                    selectedFragment = new FragmentDescubrir();
                    break;
                case R.id.nav_historial:
                    selectedFragment = new FragmentHistorial();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    public void sugerenciaClick(View v){

        requestQueue = Volley.newRequestQueue(this);

        String URLAPI = "http://192.168.0.3:3000/clima";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLAPI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Respuesta: " + response);
                try {
                    System.out.println("Respuesta upperbody: " + response.getJSONArray("upperbody").get(0));
                    System.out.println("Respuesta lowerbody: " + response.get("lowerbody"));
                    System.out.println("Respuesta shoes: " + response.get("shoes"));
                    System.out.println("Respuesta misc: " + response.getJSONArray("misc").get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error);
            }
        });

        requestQueue.add(jsonObjectRequest);

        imgViewPlayera = findViewById(R.id.imgViewPlayera);
        imgViewShort = findViewById(R.id.imgViewShort);
        imgViewCollar = findViewById(R.id.imgViewCollar);
        imgViewTenis = findViewById(R.id.imgViewTenis);

        int [] imgPlayera = {R.drawable.playera1, R.drawable.playera2, R.drawable.playera3};
        int [] imgShort = {R.drawable.short1, R.drawable.short2, R.drawable.short3};
        int [] imgCollar = {R.drawable.collar1, R.drawable.collar2, R.drawable.collar3};
        int [] imgTenis = {R.drawable.tenis1, R.drawable.tenis2, R.drawable.tenis3};

        Random random1 = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        int numRandom1 = random1.nextInt(3);
        Log.w("random1: ", "Valor:" + numRandom1);
        int numRandom2 = random2.nextInt(3);
        Log.w("random2: ", "Valor:" + numRandom2);
        int numRandom3 = random3.nextInt(3);
        Log.w("random3: ", "Valor:" + numRandom3);
        int numRandom4 = random4.nextInt(3);
        Log.w("random4: ", "Valor:" + numRandom4);

        imgViewPlayera.setImageResource(imgPlayera[numRandom1]);
        imgViewShort.setImageResource(imgShort[numRandom2]);
        imgViewCollar.setImageResource(imgCollar[numRandom3]);
        imgViewTenis.setImageResource(imgTenis[numRandom4]);



    }

    public void onClick (View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentDescubrir()).commit();
        bottomNav.setSelectedItemId(R.id.nav_descubrir);
        Toast.makeText(this, "Click en una foto", Toast.LENGTH_LONG).show();

    }

}
