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

public class ExamenActivity extends AppCompatActivity {

    ListView listView;
    AdapterExamen adapterExamen;

    public static ArrayList<Examen>examenArrayList = new ArrayList<>();
    String url = "https://tdhxqkfq.lucusvirtual.es/android/Tareas/examenlist.php";
    Examen examen;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);
        btnRegresar = findViewById(R.id.btnRegresar);
        listView = findViewById(R.id.listMostrarPreguntas);
        adapterExamen = new AdapterExamen(this,examenArrayList);
        listView.setAdapter(adapterExamen);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(examenArrayList.get(position).getPregunta());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
                builder.create().show();




            }
        });
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(examenArrayList.get(position).getPregunta());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        switch(i){
                            case 0:
                                //startActivity(new Intent(getApplicationContext(),ExamenActivity.class).putExtra("position",position));
                                break;
                            case 1:
                                //startActivity(new Intent(getApplicationContext(),editExamenActivity.class).putExtra("position",position));
                                break;
                            case 2:
                                //EliminarDatos(examenArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });*/
        ListarDatos();
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExamenesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void EliminarDatos(String id){
        StringRequest request = new StringRequest(Request.Method.POST, "https://tdhxqkfq.lucusvirtual.es/android/eliminar_examen.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ExamenActivity.this, "Pregunta eliminada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ExamenActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamenActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
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
                examenArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String pregunta = object.getString("pregunta");
                        String alternativa1 = object.getString("alternativa1");
                        String alternativa2 = object.getString("alternativa2");
                        String alternativa3 = object.getString("alternativa3");
                        String alternativa4 = object.getString("alternativa4");
                        String puntos = object.getString("puntos");

                        examen = new Examen(id, pregunta, alternativa1, alternativa2, alternativa3, alternativa4, puntos);
                        examenArrayList.add(examen);
                        adapterExamen.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamenActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void agregar(View view){
        startActivity(new Intent(getApplicationContext(),AddPreguntaActivity.class));
    }
}