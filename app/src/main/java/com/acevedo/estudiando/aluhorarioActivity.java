package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class aluhorarioActivity extends AppCompatActivity {

    ListView listView;
    AdapterHorarioAlumnos adapterHorarioAlumnos;

    public static ArrayList<Horarios> horariosArrayList = new ArrayList<>();
    String url="https://pruebasphaway.000webhostapp.com/android/Tareas/horariolist.php";
    Horarios horarios;

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluhorario);
        btnRegresar = findViewById(R.id.btnRegresar);

        listView=findViewById(R.id.listMostrarHorarioAlumno);
        adapterHorarioAlumnos = new AdapterHorarioAlumnos(this,horariosArrayList);
        listView.setAdapter(adapterHorarioAlumnos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),aluhorarioActivity.class);
                startActivity(intent);
            }
        });
        ListarDatos();
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                horariosArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String cursoCodigo = object.getString("cursoCodigo");
                        String cursoNombre = object.getString("cursoNombre");
                        String horario = object.getString("horario");

                        horarios = new Horarios(id, cursoCodigo, cursoNombre, horario);
                        horariosArrayList.add(horarios);
                        adapterHorarioAlumnos.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(aluhorarioActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}