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

public class ExamenesEstudianteActivity extends AppCompatActivity {

    ListView listView;
    AdapterEstudianteExamen adapter;

    public static ArrayList<Examenes>examenesArrayList = new ArrayList<>();
    String url = Util.RUTA+"/Tareas/exameneslist.php";
    Examenes examenes;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenes_estudiante);
        btnRegresar = findViewById(R.id.btnSalirExamen);
        listView = findViewById(R.id.listMostrarRetroalimentacionEstudiante);
        adapter = new AdapterEstudianteExamen(this,examenesArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"Realizar Examen"};
                builder.setTitle(examenesArrayList.get(position).getTitulo());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),RealizarExamenEstudianteActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                //startActivity(new Intent(getApplicationContext(),editar.class).putExtra("position",position));
                                break;
                            case 2:
                                // EliminarDatos(tareasArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarDatos();
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EstudianteCursoActivity.class);
                startActivity(intent);
            }
        });
    }
    private void listarDatos(){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                examenesArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String titulo = object.getString("titulo");

                        examenes = new Examenes(id, titulo);
                        examenesArrayList.add(examenes);
                        adapter.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamenesEstudianteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}