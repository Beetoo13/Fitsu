package com.example.fitsu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    ImageView imgViewPlayera, imgViewShort, imgViewCollar, imgViewTenis;
    int fav = 0;
    RequestQueue requestQueue;
    String imgTopName, imgBottomName, imgMiscName, imgShoesName;
    String imgTopTipo, imgBottomTipo, imgMiscTipo, imgShoesTipo;
    String imgTopBase64, imgBottomBase64, imgMiscBase64, imgShoesBase64;
    String imgBase64TopObtenido, imgBase64BottomObtenido, imgBase64ShoesObtenido, imgBase64MiscObtenido;
    String sugerenciaTopObtenido, sugerenciaBottomObtenido, sugerenciaMiscObtenido, sugerenciaShoesObtenido;
    Historial historial = new Historial();
    public static final int MY_DEFAULT_TIMEOUT = 15000;
    Context contextMain;

    JSONObject jsonBody = new JSONObject();
    JSONObject jsonImgTop = new JSONObject();
    JSONObject jsonImgTopTipo = new JSONObject();
    JSONObject jsonImgBottom = new JSONObject();
    JSONObject jsonImgBottomTipo = new JSONObject();
    JSONObject jsonImgMisc = new JSONObject();
    JSONObject jsonImgMiscTipo = new JSONObject();
    JSONObject jsonImgShoes = new JSONObject();
    JSONObject jsonImgShoesTipo = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId((R.id.nav_inicio));
        contextMain = getApplicationContext();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            ImageView imageView = findViewById(R.id.imgViewPlayera);

            try {
                //Selección de la imagen
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);

                //imagen a base64
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                //Encoding de la imagen a un string de base64
                imgTopBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                //Log.w("imgtopbase64", "base 64: " + imgTopBase64);

                //Config de nombre y tipo
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_name, null);

                final EditText editTextNombre = mView.findViewById(R.id.editTxtNombre);

                Button btnNombreSup = mView.findViewById(R.id.btnNombre);

                final Spinner spinner = mView.findViewById(R.id.spinnerId);
                ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.top_array, android.R.layout.simple_spinner_item);
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnNombreSup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextNombre.getText().toString().isEmpty() || spinner.getSelectedItem().toString().equalsIgnoreCase("Selecciona un tipo...")) {
                            Toast.makeText(MainActivity.this, "Los campos no pueden quedar vacíos", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nombre guardado: " + editTextNombre.getText().toString(), Toast.LENGTH_SHORT).show();
                            imgTopName = editTextNombre.getText().toString().trim();
                            imgTopTipo = spinner.getSelectedItem().toString();
                            dialog.dismiss();
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (resultCode == RESULT_OK && requestCode == 2) {
            ImageView imageView = findViewById(R.id.imgViewShort);

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);

                //imagen a base64
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                //Encoding de la imagen a un string de base64
                imgBottomBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_name, null);

                final EditText editTextNombre = mView.findViewById(R.id.editTxtNombre);

                Button btnNombreBottom = mView.findViewById(R.id.btnNombre);

                final Spinner spinner = mView.findViewById(R.id.spinnerId);
                ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.bot_array, android.R.layout.simple_spinner_item);
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnNombreBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextNombre.getText().toString().isEmpty() || spinner.getSelectedItem().toString().equalsIgnoreCase("Selecciona un tipo...")) {
                            Toast.makeText(MainActivity.this, "Este campo no puede quedar vacío", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nombre de prenda superior guardado: " + editTextNombre.getText().toString(), Toast.LENGTH_SHORT).show();
                            imgBottomName = editTextNombre.getText().toString().trim();
                            imgBottomTipo = spinner.getSelectedItem().toString();
                            dialog.dismiss();
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == 3) {
            ImageView imageView = findViewById(R.id.imgViewCollar);

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);

                //imagen a base64
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                //Encoding de la imagen a un string de base64
                imgMiscBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_name, null);

                final EditText editTextNombre = mView.findViewById(R.id.editTxtNombre);

                Button btnNombreMisc = mView.findViewById(R.id.btnNombre);

                final Spinner spinner = mView.findViewById(R.id.spinnerId);
                ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.misc_array, android.R.layout.simple_spinner_item);
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnNombreMisc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextNombre.getText().toString().isEmpty() || spinner.getSelectedItem().toString().equalsIgnoreCase("Selecciona un tipo...")) {
                            Toast.makeText(MainActivity.this, "Este campo no puede quedar vacío", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nombre de prenda superior guardado: " + editTextNombre.getText().toString(), Toast.LENGTH_SHORT).show();
                            imgMiscName = editTextNombre.getText().toString().trim();
                            imgMiscTipo = spinner.getSelectedItem().toString();
                            dialog.dismiss();
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == 4) {
            ImageView imageView = findViewById(R.id.imgViewTenis);

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);

                //imagen a base64
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                //Encoding de la imagen a un string de base64
                imgShoesBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_name, null);

                final EditText editTextNombre = mView.findViewById(R.id.editTxtNombre);

                Button btnNombreShoes = mView.findViewById(R.id.btnNombre);

                final Spinner spinner = mView.findViewById(R.id.spinnerId);
                ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.shoes_array, android.R.layout.simple_spinner_item);
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                btnNombreShoes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextNombre.getText().toString().isEmpty() || spinner.getSelectedItem().toString().equalsIgnoreCase("Selecciona un tipo...")) {
                            Toast.makeText(MainActivity.this, "Este campo no puede quedar vacío", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nombre de prenda superior guardado: " + editTextNombre.getText().toString(), Toast.LENGTH_SHORT).show();
                            imgShoesName = editTextNombre.getText().toString().trim();
                            imgShoesTipo = spinner.getSelectedItem().toString();
                            dialog.dismiss();
                        }
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectImageTop(View view) {
        Intent intentTop = new Intent(Intent.ACTION_GET_CONTENT);
        intentTop.setType(("image/*"));
        startActivityForResult(intentTop.createChooser(intentTop, "Select your top clothes image"), 1);
    }

    public void selectImageBottom(View view) {
        Intent intentTop = new Intent(Intent.ACTION_GET_CONTENT);
        intentTop.setType(("image/*"));
        startActivityForResult(intentTop.createChooser(intentTop, "Select your bottom clothes image"), 2);
    }

    public void selectImageMisc(View view) {
        Intent intentTop = new Intent(Intent.ACTION_GET_CONTENT);
        intentTop.setType(("image/*"));
        startActivityForResult(intentTop.createChooser(intentTop, "Select your miscelaneous image"), 3);
    }

    public void selectImageShoes(View view) {
        Intent intentTop = new Intent(Intent.ACTION_GET_CONTENT);
        intentTop.setType(("image/*"));
        startActivityForResult(intentTop.createChooser(intentTop, "Select your shoes image"), 4);
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

    public void guardarConjunto(View view) {

        requestQueue = Volley.newRequestQueue(this);

        String urlAPI = "http://192.168.1.66:3000/conjunto";

        try {

            //PARTE SUPERIOR
            jsonImgTop.put("name", imgTopName);
            jsonImgTop.put("img64top", imgTopBase64);
            jsonImgTopTipo.put("tipo", imgTopTipo);

            //PARTE BAJA
            jsonImgBottom.put("name", imgBottomName);
            jsonImgBottom.put("img64bottom", imgBottomBase64);
            jsonImgBottomTipo.put("tipo", imgBottomTipo);

            //ACCESORIO-COMPLEMENTO
            jsonImgMisc.put("name", imgMiscName);
            jsonImgMisc.put("img64misc", imgMiscBase64);
            jsonImgMiscTipo.put("tipo", imgMiscTipo);

            //ZAPATOS-TENIS
            jsonImgShoes.put("name", imgShoesName);
            jsonImgShoes.put("img64shoes", imgShoesBase64);
            jsonImgShoesTipo.put("tipo", imgShoesTipo);

            jsonBody.put("imgTop", jsonImgTop);
            jsonBody.put("imgTopTipo", jsonImgTopTipo);

            jsonBody.put("imgBottom", jsonImgBottom);
            jsonBody.put("imgBottomTipo", jsonImgBottomTipo);

            jsonBody.put("imgMisc", jsonImgMisc);
            jsonBody.put("imgMiscTipo", jsonImgMiscTipo);

            jsonBody.put("imgShoes", jsonImgShoes);
            jsonBody.put("imgShoesTipo", jsonImgShoesTipo);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAPI, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("Response: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Error: " + error.networkResponse);
                }

            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Log.i("RESPONSESTRING", "responseString: " + response);
                        Log.i("RESPONSE", "response: : " + response);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            Log.i("Catch final", e.toString());
        }

    }

    public void sugerenciaClick(View v) {

        requestQueue = Volley.newRequestQueue(this);

        imgViewPlayera = findViewById(R.id.imgViewPlayera);
        imgViewShort = findViewById(R.id.imgViewShort);
        imgViewCollar = findViewById(R.id.imgViewCollar);
        imgViewTenis = findViewById(R.id.imgViewTenis);

        String URLAPI = "http://192.168.1.66:3000/climaUpper";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLAPI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //System.out.println("Respuesta Upper: " + response.toString());

                try {
                    imgBase64TopObtenido = (String) response.get("imgTop");
                    imgTopBase64 = imgBase64TopObtenido;

                    sugerenciaTopObtenido = (String) response.get("tipoTop");
                    imgTopTipo = sugerenciaTopObtenido;

                    imgTopName = "defaultTopName";

                    byte[] decodedStringTop = Base64.decode(imgBase64TopObtenido, Base64.DEFAULT);
                    Bitmap decodedByteTop = BitmapFactory.decodeByteArray(decodedStringTop, 0, decodedStringTop.length);
                    imgViewPlayera.setImageBitmap(decodedByteTop);

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

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);

        String urlApi2 = "http://192.168.1.66:3000/climaLower";

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, urlApi2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.println("Respuesta lower: " + response.toString());


                try {
                    imgBase64BottomObtenido = (String) response.get("imgBottom");
                    imgBottomBase64 = imgBase64BottomObtenido;

                    sugerenciaBottomObtenido = (String) response.get("tipoBottom");
                    imgBottomTipo = sugerenciaBottomObtenido;

                    imgBottomName = "defaultBottomName";

                    byte[] decodedStringBottom = Base64.decode(imgBase64BottomObtenido, Base64.DEFAULT);
                    Bitmap decodedByteBottom = BitmapFactory.decodeByteArray(decodedStringBottom, 0, decodedStringBottom.length);
                    imgViewShort.setImageBitmap(decodedByteBottom);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest1);

        String urlApi3 = "http://192.168.1.66:3000/climaShoes";

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, urlApi3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.println("Respuesta shoes: " + response.toString());

                try {
                    imgBase64ShoesObtenido = (String) response.get("imgShoes");
                    imgShoesBase64 = imgBase64ShoesObtenido;

                    sugerenciaShoesObtenido = (String) response.get("tipoShoes");
                    imgShoesTipo = sugerenciaShoesObtenido;

                    imgShoesName = "defaultShoesName";

                    byte[] decodedStringShoes = Base64.decode(imgBase64ShoesObtenido, Base64.DEFAULT);
                    Bitmap decodedByteShoes = BitmapFactory.decodeByteArray(decodedStringShoes, 0, decodedStringShoes.length);
                    imgViewTenis.setImageBitmap(decodedByteShoes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest2);

        String urlApi4 = "http://192.168.1.66:3000/climaMisc";

        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, urlApi4, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.println("Respuesta misc: " + response.toString());

                try {
                    imgBase64MiscObtenido = (String) response.get("imgMisc");
                    imgMiscBase64 = imgBase64MiscObtenido;

                    sugerenciaMiscObtenido = (String) response.get("tipoMisc");
                    imgMiscTipo = sugerenciaMiscObtenido;

                    imgMiscName = "defaultMiscName";

                    byte[] decodedStringMisc = Base64.decode(imgBase64MiscObtenido, Base64.DEFAULT);
                    Bitmap decodedByteMisc = BitmapFactory.decodeByteArray(decodedStringMisc, 0, decodedStringMisc.length);
                    imgViewCollar.setImageBitmap(decodedByteMisc);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest3);

    }

    public void openFragmentComentarios(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentComentarios()).commit();
    }

    public void favorito(View v) {
        if (fav == 1) {
            v.setBackgroundResource(R.drawable.star);
            fav = 0;
        } else {
            v.setBackgroundResource(R.drawable.star_border);
            fav = 1;
        }
    }

    public void btn1(View v) {
        Toast.makeText(getApplicationContext(), "Presionaste boton 1", Toast.LENGTH_SHORT).show();
    }

    public void btn2(View v) {
        Toast.makeText(getApplicationContext(), "Presionaste boton 2", Toast.LENGTH_SHORT).show();
    }

}
