package com.example.fitsu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityDetallesHistorial extends AppCompatActivity {
    ImageView imgViewConjunto, imgViewTop, imgViewBottom, imgViewMisc, imgViewShoes, back;
    TextView fecha;
    Button btn;
    String fechaRecibida;
    RequestQueue request, request2;
    String imgBase64TopObtenidoDetalle;
    String imgBase64BottomObtenidoDetalle;
    String imgBase64ShoesObtenidoDetalle;
    String imgBase64MiscObtenidoDetalle;
    String imgBase64ConjuntoObtenidoDetalle;

    public static final int MY_DEFAULT_TIMEOUT = 15000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_historial);

        request = Volley.newRequestQueue(this);

        imgViewConjunto = findViewById(R.id.imgViewConjuntoDetalle);
        imgViewTop = findViewById(R.id.imgViewTopDetalle);
        imgViewBottom = findViewById(R.id.imgViewBottomDetalle);
        imgViewMisc = findViewById(R.id.imgViewMiscDetalle);
        imgViewShoes = findViewById(R.id.imgViewShoesDetalle);
        fecha = findViewById(R.id.fecha_detalles);
        btn = findViewById(R.id.favorito);
        back = findViewById(R.id.back_btn);

        fechaRecibida = getIntent().getStringExtra("fecha");

        if (fechaRecibida != null) {
            fecha.setText(fechaRecibida);
        }

        String url = "http://192.168.1.66:3000/detallesHistorial";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.println("respuesta: " + response);

                try {
                    JSONArray jsonArray = response.getJSONArray("concepts");
                    //System.out.println("array: " + jsonArray);

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject datos = jsonArray.getJSONObject(i);
                        JSONObject datosTop = datos.getJSONObject("imgTop");
                        JSONObject datosBot = datos.getJSONObject("imgBottom");
                        JSONObject datosMisc = datos.getJSONObject("imgMisc");
                        JSONObject datosShoes = datos.getJSONObject("imgShoes");
                        JSONObject datosConjunto = datos.getJSONObject("imgConjunto");

                        imgBase64TopObtenidoDetalle = datosTop.getString("img64top");
                        imgBase64BottomObtenidoDetalle = datosBot.getString("img64bottom");
                        imgBase64MiscObtenidoDetalle = datosMisc.getString("img64misc");
                        imgBase64ShoesObtenidoDetalle = datosShoes.getString("img64shoes");
                        imgBase64ConjuntoObtenidoDetalle = datosConjunto.getString("img64conjunto");
                    }

                    //System.out.println("top: " + imgBase64TopObtenidoDetalle);
                    //System.out.println("bottom: " + imgBase64BottomObtenidoDetalle);
                    //System.out.println("misc: " + imgBase64MiscObtenidoDetalle);
                    //System.out.println("shoes: " + imgBase64ShoesObtenidoDetalle);
                    //System.out.println("conjunto: " + imgBase64ConjuntoObtenidoDetalle);

                    byte[] decodedStringTop = Base64.decode(imgBase64TopObtenidoDetalle, Base64.DEFAULT);
                    Bitmap decodedByteTop = BitmapFactory.decodeByteArray(decodedStringTop, 0, decodedStringTop.length);
                    imgViewTop.setImageBitmap(decodedByteTop);

                    byte[] decodedStringBottom = Base64.decode(imgBase64BottomObtenidoDetalle, Base64.DEFAULT);
                    Bitmap decodedByteBottom = BitmapFactory.decodeByteArray(decodedStringBottom, 0, decodedStringBottom.length);
                    imgViewBottom.setImageBitmap(decodedByteBottom);

                    byte[] decodedStringMisc = Base64.decode(imgBase64MiscObtenidoDetalle, Base64.DEFAULT);
                    Bitmap decodedByteMisc = BitmapFactory.decodeByteArray(decodedStringMisc, 0, decodedStringMisc.length);
                    imgViewMisc.setImageBitmap(decodedByteMisc);

                    byte[] decodedStringShoes = Base64.decode(imgBase64ShoesObtenidoDetalle, Base64.DEFAULT);
                    Bitmap decodedByteShoes = BitmapFactory.decodeByteArray(decodedStringShoes, 0, decodedStringShoes.length);
                    imgViewShoes.setImageBitmap(decodedByteShoes);

                    byte[] decodedStringConjunto = Base64.decode(imgBase64ConjuntoObtenidoDetalle, Base64.DEFAULT);
                    Bitmap decodedByteConjunto = BitmapFactory.decodeByteArray(decodedStringConjunto, 0, decodedStringConjunto.length);
                    imgViewConjunto.setImageBitmap(decodedByteConjunto);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("fecha", fechaRecibida);
                return headers;
            }
        };

        request.add(jsonObjReq);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
