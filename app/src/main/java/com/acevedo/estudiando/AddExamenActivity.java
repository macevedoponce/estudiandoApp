package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.acevedo.estudiando.Util.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddExamenActivity extends AppCompatActivity {

    private  static final int REQ_CODE_SPEECH_INPUT_TITULO=100;

    EditText edtTituloExamen;
    Button btnAgregar,btnRegresar;
    ImageButton btnTituloExamen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_examen);
        btnTituloExamen = findViewById(R.id.btnTituloExamen);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnAgregar = findViewById(R.id.btnAgregar);
        edtTituloExamen = findViewById(R.id.edtTituloExamen);

        btnTituloExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVozTit();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarExamen();
                //llamartopico(); //notificación de nuevo examen
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExamenesActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // notificacion de nuevo examen
    /*
    private void llamartopico() {

        RequestQueue myrequest= Volley.newRequestQueue(getApplicationContext());
        JSONObject json = new JSONObject();

        try {

            String url_foto="https://tarea.co/wp-content/uploads/2020/04/logo.png";
            json.put("to","/topics/"+"enviaratodos");
            JSONObject notificacion=new JSONObject();
            notificacion.put("titulo",edtTitulo.getText().toString());
            notificacion.put("detalle",edtDescripcion.getText().toString());
            notificacion.put("foto",url_foto);

            json.put("data",notificacion);
            String URL="https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String,String>header=new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAACeW3jfs:APA91bE1B9cXQGa8Yc7lYEAxxx7xfGSK3wiRlkxrxEqnahu-GjjhjTmTNY-7jReSLKWx5-BLmu15vYPP36z2pr2M7OMtGehP8SirddZrYHfMlZg-lbkmHetH4I3V7IjJvHtiWZzz3JHX");
                    return  header;

                }
            };
            myrequest.add(request);


        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/
    private void iniciarEntradaVozTit(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT_TITULO);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }

    private void agregarExamen() {
        final String titulo = edtTituloExamen.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if (titulo.isEmpty()) {
            Toast.makeText(this, "Ingrese Titulo de exámen", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.show();
            String url = Util.RUTA+"/insertar_examen.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Exámen Registrado exitosamente !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), ExamenesActivity.class));
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddExamenActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("titulo", titulo);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddExamenActivity.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
    //inicio agregar lo que hablo en edtTitulo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case REQ_CODE_SPEECH_INPUT_TITULO:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtTituloExamen.setText(result.get(0));
                }
                break;
            }
        }
    }
    //fin agregar lo que hablo en edtTitulo
}