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
import android.widget.TextView;
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

public class editTareaActivity extends AppCompatActivity {

    private  static final int REQ_CODE_SPEECH_INPUT_TITULO=100,
            REQ_CODE_SPEECH_INPUT_DESCRIPCION=101,
            REQ_CODE_SPEECH_INPUT_RETROALIMENTACION=102;

    EditText txtTitulo,txtDescripcion, txtRetroalimentacion;
    TextView txtId;
    Button btnRegresar,btnAgregar;
    ImageButton btnTitulo,btnDescripcion,btnRetroalimentacion;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tarea);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnTitulo = findViewById(R.id.btnTit);
        btnDescripcion = findViewById(R.id.btnDesc);
        btnRetroalimentacion = findViewById(R.id.btnRetro);
        txtId=findViewById(R.id.editId);
        txtTitulo=findViewById(R.id.editTitulo);
        txtDescripcion=findViewById(R.id.editDescripcion);
        txtRetroalimentacion=findViewById(R.id.editRetroalimentacion);

        Intent intent = getIntent();
        position=intent.getExtras().getInt("position");

        txtId.setText(TareasActivity.tareasArrayList.get(position).getId());
        txtTitulo.setText(TareasActivity.tareasArrayList.get(position).getTitulo());
        txtDescripcion.setText(TareasActivity.tareasArrayList.get(position).getDescripcion());
        txtRetroalimentacion.setText(TareasActivity.tareasArrayList.get(position).getRetroalimentacion());

        // reconocimiento de voz inicio

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

        // reconocimiento de voz fin

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar(view);
            }
        });

    }
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

    public void actualizar(View view){
        final String id = txtId.getText().toString().trim();
        final String titulo = txtTitulo.getText().toString().trim();
        final String descripcion = txtDescripcion.getText().toString().trim();
        final String retroalimentacion = txtRetroalimentacion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "https://pruebasphaway.000webhostapp.com/android/editar_tarea.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tarea Actualizada exitosamente !", Toast.LENGTH_SHORT).show();
                //Toast.makeText(editTareaActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TareasActivity.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editTareaActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("titulo",titulo);
                params.put("descripcion",descripcion);
                params.put("retroalimentacion",retroalimentacion);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(editTareaActivity.this);
        requestQueue.add(request);
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
                    txtTitulo.setText(result.get(0));
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_DESCRIPCION:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtDescripcion.setText(result.get(0));
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT_RETROALIMENTACION:{
                if(resultCode==RESULT_OK && null!=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtRetroalimentacion.setText(result.get(0));
                }
                break;
            }
        }
    }
    //fin agregar lo que hablo en edtTitulo
}
/*package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editTareaActivity extends AppCompatActivity {
    EditText txtId,txtTitulo,txtDescripcion, txtRetroalimentacion;
    Button btnRegresar;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tarea);
        btnRegresar = findViewById(R.id.btnRegresar);
        txtId=findViewById(R.id.editId);
        txtTitulo=findViewById(R.id.editTitulo);
        txtDescripcion=findViewById(R.id.editDescripcion);
        txtRetroalimentacion=findViewById(R.id.editRetroalimentacion);

        Intent intent = getIntent();
        position=intent.getExtras().getInt("position");

        txtId.setText(TareasActivity.tareasArrayList.get(position).getId());
        txtTitulo.setText(TareasActivity.tareasArrayList.get(position).getTitulo());
        txtDescripcion.setText(TareasActivity.tareasArrayList.get(position).getDescripcion());
        txtRetroalimentacion.setText(TareasActivity.tareasArrayList.get(position).getRetroalimentacion());

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void actualizar(View view){
        final String id = txtId.getText().toString().trim();
        final String titulo = txtTitulo.getText().toString().trim();
        final String descripcion = txtDescripcion.getText().toString().trim();
        final String retroalimentacion = txtRetroalimentacion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "https://pruebasphaway.000webhostapp.com/android/editar_tarea.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tarea Actualizada exitosamente !", Toast.LENGTH_SHORT).show();
                //Toast.makeText(editTareaActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TareasActivity.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editTareaActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("titulo",titulo);
                params.put("descripcion",descripcion);
                params.put("retroalimentacion",retroalimentacion);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(editTareaActivity.this);
        requestQueue.add(request);
    }
}*/