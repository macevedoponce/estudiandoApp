package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class addTareaActivity extends AppCompatActivity {

    //private  static final int REQ_CODE_SPEECH_INPUT=100;
    private  static final int REQ_CODE_SPEECH_INPUT_TITULO=100,
            REQ_CODE_SPEECH_INPUT_DESCRIPCION=101,
            REQ_CODE_SPEECH_INPUT_RETROALIMENTACION=102;

    EditText edtTitulo ,edtDescripcion, edtRetroalimentacion;
    Button btnAgregar,btnRegresar;
    ImageButton btnHablar,btnTitulo,btnDescripcion,btnRetroalimentacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarea);
        //btnHablar = findViewById(R.id.btnHablar);
        btnTitulo = findViewById(R.id.btnTit);
        btnDescripcion = findViewById(R.id.btnDesc);
        btnRetroalimentacion = findViewById(R.id.btnRetro);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnAgregar = findViewById(R.id.btnAgregar);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtRetroalimentacion = findViewById(R.id.edtRetroalimentacion);
/*
        btnHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVoz();
            }
        });
*/

        btnTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVozTit();
            }
        });
        btnDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVozDesc();
            }
        });
        btnRetroalimentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVozRetro();
            }
        });



        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarTarea();
                llamartopico();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
    }
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
    }
    /*
        private void iniciarEntradaVoz(){
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
            try {
                startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
            }catch (ActivityNotFoundException e){
                Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
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
    private void iniciarEntradaVozDesc(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT_DESCRIPCION);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozRetro(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT_RETROALIMENTACION);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }

    private void agregarTarea() {
        final String titulo = edtTitulo.getText().toString().trim();
        final String descripcion = edtDescripcion.getText().toString().trim();
        final String retroalimentacion = edtRetroalimentacion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if (titulo.isEmpty()) {
            Toast.makeText(this, "Ingrese Titulo", Toast.LENGTH_SHORT).show();
            return;
        } else if (descripcion.isEmpty()) {
            Toast.makeText(this, "Ingrese Descripción", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://tdhxqkfq.lucusvirtual.es/android/insertar_tarea.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Tarea Registrada exitosamente !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), TareasActivity.class));
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(addTareaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("titulo", titulo);
                    params.put("descripcion", descripcion);
                    params.put("retroalimentacion", retroalimentacion);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(addTareaActivity.this);
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
            /* case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtTitulo.setText(result.get(0));
                }
                break;
            }*/
            case REQ_CODE_SPEECH_INPUT_TITULO:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtTitulo.setText(result.get(0));
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_DESCRIPCION:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtDescripcion.setText(result.get(0));
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_RETROALIMENTACION:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtRetroalimentacion.setText(result.get(0));
                }
                break;
            }
        }
    }
    //fin agregar lo que hablo en edtTitulo
}