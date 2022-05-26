package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class detalleTareaActivity extends AppCompatActivity {
    TextView tvId,tvTitulo,tvDescripcion, tvRetroalimentacion;
    Button btnRegresar;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);
        btnRegresar=findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
            }
        });
        tvTitulo=findViewById(R.id.tvTitulo);
        tvDescripcion=findViewById(R.id.tvDescripcion);
        tvRetroalimentacion=findViewById(R.id.tvRetroalimentacion);
        tvId=findViewById(R.id.tvId);

        Intent intent=getIntent();
        position = intent.getExtras().getInt("position");

        tvId.setText("ID: " + TareasActivity.tareasArrayList.get(position).getId());
        tvTitulo.setText("TITULO: " + TareasActivity.tareasArrayList.get(position).getTitulo());
        tvDescripcion.setText("DESCRIPCIÓN: " + TareasActivity.tareasArrayList.get(position).getDescripcion());
        tvRetroalimentacion.setText("RETROALIMENTACION:" + TareasActivity.tareasArrayList.get(position).getRetroalimentacion());

    }
}