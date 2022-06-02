package com.example.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class principal_estudiante extends AppCompatActivity {
    TextView txtNombre,txtCodigo;
    ImageView imgViewCursos;
    EditText edtUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_estudiante);

        imgViewCursos = (ImageView) findViewById(R.id.imageViewCursos);
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        txtNombre = (TextView) findViewById(R.id.txtnombre);
        txtCodigo = (TextView) findViewById(R.id.txtcodigo);
        imgViewCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscardatos( "http://192.168.1.7:8080/appMobileEstudiando/buscar_curso_alumno.php?aluDNI="+edtUsuario.getText()+"");
            }
        });
    }
    private void buscardatos(String URL){
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtCodigo.setText(jsonObject.getString("curCodigo"));
                        txtNombre.setText(jsonObject.getString("curNombre"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue( this);
        requestQueue.add(JsonArrayRequest);
    }
}