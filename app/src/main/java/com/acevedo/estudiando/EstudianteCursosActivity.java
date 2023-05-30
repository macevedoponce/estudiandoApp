package com.acevedo.estudiando;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;

public class EstudianteCursosActivity extends AppCompatActivity {

    ListView listView;
    AdapterEstudianteCursos adapterEstudianteCursos;

    public static ArrayList<EstudianteCursos> estudianteCursosArrayList = new ArrayList<>();
    String url= Util.RUTA+"/Tareas/cursos_por_grado.php";
    EstudianteCursos estudianteCursos;

    Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_cursos);
        btnRegresar = findViewById(R.id.btnRegresar);

        listView=findViewById(R.id.listMostrarCursoEstudiante);
        adapterEstudianteCursos = new AdapterEstudianteCursos(this,estudianteCursosArrayList);
        listView.setAdapter(adapterEstudianteCursos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),EstudianteCursoActivity.class); // a donde vamos a mandar al hacer click
                startActivity(intent);
            }
        });

        ListarDatos();
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(), PrincipalEstudianteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                estudianteCursosArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String codigo = object.getString("codigo");
                        String nombre = object.getString("nombre");
                        String docente = object.getString("docente");
                        String grado = object.getString("grado");
                        String seccion = object.getString("seccion");

                        estudianteCursos = new EstudianteCursos(id, codigo, nombre, docente, grado, seccion);
                        estudianteCursosArrayList.add(estudianteCursos);
                        adapterEstudianteCursos.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EstudianteCursosActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}