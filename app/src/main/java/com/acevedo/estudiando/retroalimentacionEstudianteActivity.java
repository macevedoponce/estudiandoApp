package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class retroalimentacionEstudianteActivity extends AppCompatActivity {

    Button Salir;
    ListView listView;
    AdapterRetroalimentacionEstudiante adapter;

    public static ArrayList<ExamenEstudianteRetro> examenEstudianteRetroArrayList = new ArrayList<>();
    String url = "https://pruebasphaway.000webhostapp.com/android/mostrar_preguntas.php";

    ExamenEstudianteRetro ExamenEstudianteRetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroalimentacion_estudiante);

        listView=findViewById(R.id.listMostrarRetroalimentacionEstudiante);
        adapter = new AdapterRetroalimentacionEstudiante(this,examenEstudianteRetroArrayList);
        listView.setAdapter(adapter);
        Salir=findViewById(R.id.btnSalirExamen);

        ListarDatos();

        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExamenesEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                examenEstudianteRetroArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String pregunta = object.getString("pregunta");
                        String respuesta = object.getString("alternativa1");
                        String retroalimentacion = object.getString("retroalimentacion");

                        ExamenEstudianteRetro = new ExamenEstudianteRetro("Pregunta Nº "+id,pregunta,"Respuesta: "+ respuesta,retroalimentacion);
                        examenEstudianteRetroArrayList.add(ExamenEstudianteRetro);
                        adapter.notifyDataSetChanged();

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(retroalimentacionEstudianteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}