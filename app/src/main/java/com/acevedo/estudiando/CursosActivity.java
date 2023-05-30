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

public class CursosActivity extends AppCompatActivity {

    ListView listView;
    AdapterCursos adapterCursos;

    public static ArrayList<Cursos> cursosArrayList = new ArrayList<>();
    String url= Util.RUTA+"/Tareas/cursoslist.php";
    Cursos cursos;

    Button btnRegresar;
    //CardView cardCurso,cardCurso2,cardCurso3,cardCurso4,cardCurso5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        btnRegresar = findViewById(R.id.btnRegresar);

        listView=findViewById(R.id.listMostrarCurso);
        adapterCursos = new AdapterCursos(this,cursosArrayList);
        listView.setAdapter(adapterCursos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),CursoActivity.class);
                startActivity(intent);
            }
        });
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());



                CharSequence[] dialogoItem = {"Ver","Editar","Eliminar"};
                builder.setTitle(cursosArrayList.get(position).getCodigo());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getApplicationContext(),CursoActivity.class).putExtra("position",position);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });*/
        ListarDatos();

        /*cardCurso = findViewById(R.id.cardCurso);
        cardCurso2 = findViewById(R.id.cardCurso2);
        cardCurso3 = findViewById(R.id.cardCurso3);
        cardCurso4 = findViewById(R.id.cardCurso4);
        cardCurso5 = findViewById(R.id.cardCurso5);*/


/*
        // aqui comienza los cardViews
        cardCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CursoActivity.class);
                startActivity(intent);

            }
        });
        cardCurso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Curso 2", Toast.LENGTH_SHORT).show();

            }
        });
        cardCurso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Curso 3", Toast.LENGTH_SHORT).show();

            }
        });
        cardCurso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Curso 4", Toast.LENGTH_SHORT).show();

            }
        });
        cardCurso5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Curso 5", Toast.LENGTH_SHORT).show();

            }
        });

// aqui terminan los cardViews

 */

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
                cursosArrayList.clear();
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

                        cursos = new Cursos(id, codigo, nombre, docente, grado, seccion);
                        cursosArrayList.add(cursos);
                        adapterCursos.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CursosActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}