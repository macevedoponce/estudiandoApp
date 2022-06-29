package com.acevedo.estudiando;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class NotasActivity extends AppCompatActivity {

    ListView listView;
    AdapterAlumnos adapterAlumnos;

    public static ArrayList<Alumnos> alumnosArrayList = new ArrayList<>();
    String url="http://trainingcrane.pe/estudiandoadmin/android/Tareas/alumnoslist.php";
    Alumnos alumnos;

    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        btnRegresar = findViewById(R.id.btnRegresar);

        listView=findViewById(R.id.listMostrarAlumnos);
        adapterAlumnos = new AdapterAlumnos(this,alumnosArrayList);
        listView.setAdapter(adapterAlumnos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),NotaPersonalActivity.class);
               startActivity(intent);
            }
        });

        ListarDatos();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CursoActivity.class);
                startActivity(intent);
            }
        });

    }
    public void agregar(View view){
        startActivity(new Intent(getApplicationContext(),addNotaActivity.class));
    }
    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alumnosArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String alumno = object.getString("alumno");

                        alumnos = new Alumnos(id, alumno);
                        alumnosArrayList.add(alumnos);
                        adapterAlumnos.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NotasActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}