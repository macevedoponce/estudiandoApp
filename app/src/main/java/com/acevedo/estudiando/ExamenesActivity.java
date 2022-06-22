package com.acevedo.estudiando;

import androidx.annotation.Nullable;
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
import java.util.Map;

public class ExamenesActivity extends AppCompatActivity {

    ListView listView;
    AdapterExamenes adapterExamenes;

    public static ArrayList<Examenes>examenesArrayList = new ArrayList<>();
    String url = "https://pruebasphaway.000webhostapp.com/android/Tareas/exameneslist.php";
    Examenes examenes;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenes);
        btnRegresar = findViewById(R.id.btnRegresar);
        listView = findViewById(R.id.listMostrarExamenes);
        adapterExamenes = new AdapterExamenes(this,examenesArrayList);
        listView.setAdapter(adapterExamenes);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(examenesArrayList.get(position).getTitulo());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        switch(i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),ExamenActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                //startActivity(new Intent(getApplicationContext(),editExamenActivity.class).putExtra("position",position));
                                break;
                            case 2:
                                EliminarDatos(examenesArrayList.get(position).getId());
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
        StringRequest request = new StringRequest(Request.Method.POST, "https://pruebasphaway.000webhostapp.com/android/eliminar_examen.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ExamenesActivity.this, "Examen eliminado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ExamenesActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamenesActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
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
                        adapterExamenes.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamenesActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void agregar(View view){
        startActivity(new Intent(getApplicationContext(),AddExamenActivity.class));
    }
}