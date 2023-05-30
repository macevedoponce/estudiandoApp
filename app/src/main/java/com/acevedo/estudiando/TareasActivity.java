package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TareasActivity extends AppCompatActivity {

    ListView listView;
    Adapter adapter;

    public static ArrayList<Tareas>tareasArrayList = new ArrayList<>();
    String url= Util.RUTA+"/Tareas/tareaslist.php";
    Tareas tareas;

    Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        btnRegresar = findViewById(R.id.btnRegresar);
        listView=findViewById(R.id.listMostrar);
        adapter = new Adapter(this,tareasArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(tareasArrayList.get(position).getTitulo());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),detalleTareaActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),editTareaActivity.class).putExtra("position",position));
                                break;
                            case 2:
                                EliminarDatos(tareasArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.create().show();
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
    private void EliminarDatos(String id){
        String url= Util.RUTA+"//eliminar_tareas.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TareasActivity.this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TareasActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TareasActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tareasArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String titulo = object.getString("titulo");
                        String descripcion = object.getString("descripcion");
                        String retroalimentacion = object.getString("retroalimentacion");

                        tareas = new Tareas(id, titulo, descripcion, retroalimentacion);
                        tareasArrayList.add(tareas);
                        adapter.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TareasActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void agregar(View view){
        startActivity(new Intent(getApplicationContext(),addTareaActivity.class));
    }

}