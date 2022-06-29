package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.jar.JarException;

public class addNotaActivity extends AppCompatActivity {
    private  static final int REQ_CODE_SPEECH_INPUT=100;
    EditText edtNota;

    Spinner spinnerTareas, spinnerAlumnos;
    ArrayList<String> tareasList = new ArrayList<>();
    ArrayList<String> alumnosList = new ArrayList<>();
    ArrayAdapter<String> tareasAdapter;
    ArrayAdapter<String> alumnosAdapter;
    RequestQueue requestQueue;

    Button btnAgregar,btnRegresar;
    ImageButton btnDictarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nota);
        requestQueue = Volley.newRequestQueue(this);
        spinnerAlumnos = findViewById(R.id.spinnerAlumnos);
        spinnerTareas = findViewById(R.id.spinnerTareas);

        btnDictarNota = findViewById(R.id.btnDictarNota);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnAgregar = findViewById(R.id.btnAgregar);
        edtNota = findViewById(R.id.edtNota);
        String url = "http://trainingcrane.pe/estudiandoadmin/android/Tareas/tareaslist.php";
        String url2 = "hhttp://trainingcrane.pe/estudiandoadmin/android/Tareas/alumnoslist.php";
        //tareas

        btnDictarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVozNota();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarNota();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NotasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("datos");
                    for (int i=0; i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String tareasTitulo = jsonObject.optString("titulo");
                        tareasList.add(tareasTitulo);
                        tareasAdapter = new ArrayAdapter<>(addNotaActivity.this, android.R.layout.simple_spinner_item,tareasList);
                        tareasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spinnerTareas.setAdapter(tareasAdapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        // alumnos
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("datos");
                    for (int i=0; i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String alumno = jsonObject.optString("alumno");
                        alumnosList.add(alumno);
                        alumnosAdapter = new ArrayAdapter<>(addNotaActivity.this, android.R.layout.simple_spinner_item,alumnosList);
                        alumnosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spinnerAlumnos.setAdapter(alumnosAdapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest2);

    }
    private void iniciarEntradaVozNota(){//validad que solo reconozca numeros
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void agregarNota() {
        final String tarea = spinnerTareas.getSelectedItem().toString().trim();
        final String alumno = spinnerAlumnos.getSelectedItem().toString().trim();
        final String nota = edtNota.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if (tarea.isEmpty()) {
            Toast.makeText(this, "Seleccione Tarea", Toast.LENGTH_SHORT).show();
            return;
        } else if (alumno.isEmpty()) {
            Toast.makeText(this, "Seleccione Alumno", Toast.LENGTH_SHORT).show();
            return;
        } else if(nota.isEmpty()){
            Toast.makeText(this, "Ingrese Nota", Toast.LENGTH_SHORT).show();
            return;
        }else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://trainingcrane.pe/estudiandoadmin/android/insertar_nota.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //falta enviar mensaje de error, obtenido desde el api
                    Toast.makeText(getApplicationContext(), "Nota Registrada exitosamente !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), NotasActivity.class));
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(addNotaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("tarea", tarea);
                    params.put("alumno", alumno);
                    params.put("nota", nota);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(addNotaActivity.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
    //inicio agregar lo que hablo en edtNota
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtNota.setText(result.get(0));
                }
                break;
            }
        }
    }
    //fin agregar lo que hablo en edtTitulo
}