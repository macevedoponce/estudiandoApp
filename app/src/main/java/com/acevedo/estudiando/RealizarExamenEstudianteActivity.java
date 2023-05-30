package com.acevedo.estudiando;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acevedo.estudiando.Util.Util;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RealizarExamenEstudianteActivity extends AppCompatActivity {

    private TextView txt_NumeroPregunta, txtPregunta;
    private Button btnSiguiente, btnSalir;
    private RadioButton Opcion1, Opcion2, Opcion3, Opcion4;
    int contador = 1; //cuenta el numero de preguntas y las identifica
    double notaObtenida = 0.00; // Guarda los puntos obtenios en cada pregunta
    boolean check = false; // valida que no se dejen preguntas vacias
    String url = Util.RUTA+"/mostrar_preguntas.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_examen_estudiante);

        txt_NumeroPregunta = findViewById(R.id.txtNumeroPregunta);
        txtPregunta = findViewById(R.id.txtPreguntaExamen);

        Opcion1 = findViewById(R.id.Opcion1);
        Opcion2 = findViewById(R.id.Opcion2);
        Opcion3 = findViewById(R.id.Opcion3);
        Opcion4 = findViewById(R.id.Opcion4);

        llenardatos(contador);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Opcion1.isChecked()){

                    verificarRespuesta(contador,Opcion1.getText().toString());
                    check = true;
                }else if(Opcion2.isChecked()){

                    verificarRespuesta(contador,Opcion2.getText().toString());
                    check = true;
                }else if(Opcion3.isChecked()){

                    verificarRespuesta(contador,Opcion3.getText().toString());
                    check = true;
                }else if(Opcion4.isChecked()){

                    verificarRespuesta(contador,Opcion4.getText().toString());
                    check = true;

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Seleccione una Respuesta por favor",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(check){

                    if(contador < 10){
                        contador++;
                        llenardatos(contador);
                        // Opcion1.setChecked(false);
                        //Opcion2.setChecked(false);
                        // Opcion3.setChecked(false);
                        //Opcion4.setChecked(false);
                    }else{
                        mostrarresultados();
                    }
                }
            }
        });
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salir();
            }
        });
    }

    private void llenardatos(int numeroPregunta){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);

                        int id_Pregunta = jsonObj.getInt("id");

                        if(id_Pregunta == numeroPregunta){
                            String Pregunta = jsonObj.getString("pregunta");
                            String respuesta = jsonObj.getString("alternativa1");
                            String alternativa1 = jsonObj.getString("alternativa2");
                            String alternativa2 = jsonObj.getString("alternativa3");
                            String alternativa3 = jsonObj.getString("alternativa4");

                            txt_NumeroPregunta.setText("Pregunta Numero: " + id_Pregunta);
                            txtPregunta.setText(Pregunta);

                            List<String> opciones = Arrays.asList(respuesta,alternativa1,alternativa2,alternativa3);

                            Collections.shuffle(opciones);

                            Opcion1.setText(opciones.get(0));
                            Opcion2.setText(opciones.get(1));
                            Opcion3.setText(opciones.get(2));
                            Opcion4.setText(opciones.get(3));
                        }

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RealizarExamenEstudianteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    private void verificarRespuesta(int numeroPregunta, String respuestaDada){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");

                    for(int i = 0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        int id_Pregunta = jsonObj.getInt("id");

                        if(id_Pregunta == numeroPregunta){
                            String respuesta = jsonObj.getString("alternativa1");
                            if(respuesta.equals(respuestaDada)){

                                int puintos = jsonObj.getInt("puntos");
                                notaObtenida = notaObtenida + puintos;
                            }
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RealizarExamenEstudianteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    public void mostrarresultados(){

        txt_NumeroPregunta.setText("Su nota es de: "+ notaObtenida);

        if(notaObtenida> 10){
            txtPregunta.setText("Aprobado...!!!");
        }else{
            txtPregunta.setText("Desaprobado..!! ");
        }

        Opcion1.setVisibility(View.INVISIBLE);
        Opcion2.setVisibility(View.INVISIBLE);
        Opcion3.setVisibility(View.INVISIBLE);
        Opcion4.setVisibility(View.INVISIBLE);
        btnSiguiente.setText("FeedBack");
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),retroalimentacionEstudianteActivity.class);
                startActivity(intent);
                //int numeroPregunta = 1;
                //mostrarRetroalimentacion(numeroPregunta);
            }
        });

    }
    public void Salir(){
        Intent intent = new Intent(getApplicationContext(),ExamenesEstudianteActivity.class);
        startActivity(intent);
    }
    /*public void mostrarRetroalimentacion(int numeroPregunta){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    AlertDialog.Builder builder = new AlertDialog.Builder(RealizarExamenEstudianteActivity.this);
                    builder.setTitle("RETROALIMENTACION");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        int id_Pregunta = jsonObj.getInt("id");
                        if(id_Pregunta == numeroPregunta){
                            String NumPregunta = jsonObj.getString("id");
                            String Pregunta = jsonObj.getString("pregunta");
                            String respuesta = jsonObj.getString("alternativa1");
                            String retroalimentacion = jsonObj.getString("retroalimentacion");
                            builder.setMessage("Pregunta Numero: " + NumPregunta+"\n" + Pregunta+"\n" +respuesta+"\n" +retroalimentacion)
                                    .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(getApplicationContext(),ExamenesEstudianteActivity.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RealizarExamenEstudianteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }*/
}