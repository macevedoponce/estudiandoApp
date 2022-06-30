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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddPreguntaActivity extends AppCompatActivity {

    private static final int REC_PREGUNTA = 100,REC_PUNTOS = 101, REC_ALT1=102
            ,REC_ALT2=103,REC_ALT3=104,REC_ALT4=105,REC_RETRO=106;

    EditText edtPregunta,edtPuntos,edtAlternativa1,edtAlternativa2,edtAlternativa3,edtAlternativa4,edtRetroalimentacion;
    Button btnAgregar, btnRegresar;
    ImageButton btnPregunta,btnPuntos,btnAlternativa1,btnAlternativa2,btnAlternativa3,btnAlternativa4,btnRetroalimentacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pregunta);
        btnPregunta = findViewById(R.id.btnPregunta);
        btnPuntos = findViewById(R.id.btnPunto);
        btnAlternativa1 = findViewById(R.id.btnAlternativa1);
        btnAlternativa2 = findViewById(R.id.btnAlternativa2);
        btnAlternativa3 = findViewById(R.id.btnAlternativa3);
        btnAlternativa4 = findViewById(R.id.btnAlternativa4);
        btnRetroalimentacion = findViewById(R.id.btnRetroalimentacion);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnAgregar = findViewById(R.id.btnAgregar);
        edtPregunta = findViewById(R.id.edtPregunta);
        edtPuntos = findViewById(R.id.edtPunto);
        edtAlternativa1 = findViewById(R.id.edtAlternativa1);
        edtAlternativa2 = findViewById(R.id.edtAlternativa2);
        edtAlternativa3 = findViewById(R.id.edtAlternativa3);
        edtAlternativa4 = findViewById(R.id.edtAlternativa4);
        edtRetroalimentacion = findViewById(R.id.edtRetroalimentacion);

        btnPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozPregunta();}
        });
        btnPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozPuntos();}
        });
        btnAlternativa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozAlternativa1();}
        });
        btnAlternativa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozAlternativa2();}
        });
        btnAlternativa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozAlternativa3();}
        });
        btnAlternativa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozAlternativa4();}
        });
        btnRetroalimentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iniciarEntradaVozRetroalimentacion();}
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {agregarPregunta();}
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExamenActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void iniciarEntradaVozPregunta(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_PREGUNTA);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozPuntos(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_PUNTOS);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozAlternativa1(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_ALT1);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozAlternativa2(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_ALT2);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozAlternativa3(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_ALT3);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozAlternativa4(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_ALT4);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEntradaVozRetroalimentacion(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hola, puedes dictarme lo que necesites");
        try {
            startActivityForResult(intent,REC_RETRO);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "No puedo oir nada", Toast.LENGTH_SHORT).show();
        }
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
            case REC_PREGUNTA:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtPregunta.setText(result.get(0));
                }
                break;
            }
            case REC_PUNTOS:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtPuntos.setText(result.get(0));
                }
                break;
            }
            case REC_ALT1:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtAlternativa1.setText(result.get(0));
                }
                break;
            }
            case REC_ALT2:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtAlternativa2.setText(result.get(0));
                }
                break;
            }
            case REC_ALT3:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtAlternativa3.setText(result.get(0));
                }
                break;
            }
            case REC_ALT4:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtAlternativa4.setText(result.get(0));
                }
                break;
            }
            case REC_RETRO:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtRetroalimentacion.setText(result.get(0));
                }
                break;
            }
        }
    }
    //fin agregar lo que hablo en edtTitulo
    private void agregarPregunta() {
        final String pregunta = edtPregunta.getText().toString().trim();
        final String puntos = edtPuntos.getText().toString().trim();
        final String alternativa1 = edtAlternativa1.getText().toString().trim();
        final String alternativa2 = edtAlternativa2.getText().toString().trim();
        final String alternativa3 = edtAlternativa3.getText().toString().trim();
        final String alternativa4 = edtAlternativa4.getText().toString().trim();
        final String retroalimentacion = edtRetroalimentacion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if (pregunta.isEmpty() ||  alternativa1.isEmpty() || alternativa2.isEmpty() || alternativa3.isEmpty() || alternativa4.isEmpty() || retroalimentacion.isEmpty()) {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return;
        } else if (puntos.isEmpty()) {
            Toast.makeText(this, "Ingrese Puntos de la pregunta", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://tdhxqkfq.lucusvirtual.es/android/insertar_pregunta.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Pregunta Registrada exitosamente !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), ExamenActivity.class));
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddPreguntaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("pregunta", pregunta);
                    params.put("puntos", puntos);
                    params.put("alternativa1", alternativa1);
                    params.put("alternativa2", alternativa2);
                    params.put("alternativa3", alternativa3);
                    params.put("alternativa4", alternativa4);
                    params.put("retroalimentacion", retroalimentacion);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AddPreguntaActivity.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

}