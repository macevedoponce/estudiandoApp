package com.acevedo.estudiando;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editTareaActivity extends AppCompatActivity {
    EditText txtId,txtTitulo,txtDescripcion, txtRetroalimentacion;
    Button btnRegresar;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tarea);
        btnRegresar = findViewById(R.id.btnRegresar);
        txtId=findViewById(R.id.editId);
        txtTitulo=findViewById(R.id.editTitulo);
        txtDescripcion=findViewById(R.id.editDescripcion);
        txtRetroalimentacion=findViewById(R.id.editRetroalimentacion);

        Intent intent = getIntent();
        position=intent.getExtras().getInt("position");

        txtId.setText(TareasActivity.tareasArrayList.get(position).getId());
        txtTitulo.setText(TareasActivity.tareasArrayList.get(position).getTitulo());
        txtDescripcion.setText(TareasActivity.tareasArrayList.get(position).getDescripcion());
        txtRetroalimentacion.setText(TareasActivity.tareasArrayList.get(position).getRetroalimentacion());

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void actualizar(View view){
        final String id = txtId.getText().toString().trim();
        final String titulo = txtTitulo.getText().toString().trim();
        final String descripcion = txtDescripcion.getText().toString().trim();
        final String retroalimentacion = txtRetroalimentacion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.7:8080/appMobileEstudiando/editar_tarea.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tarea Actualizada exitosamente !", Toast.LENGTH_SHORT).show();
                //Toast.makeText(editTareaActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TareasActivity.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editTareaActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("titulo",titulo);
                params.put("descripcion",descripcion);
                params.put("retroalimentacion",retroalimentacion);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(editTareaActivity.this);
        requestQueue.add(request);
    }
}