package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class detalleTareaEstudianteActivity extends AppCompatActivity {
    TextView tvId, tvTitulo, tvDescripcion, tvRetroalimentacion;
    Button btnRegresar;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea_estudiante);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasEstudianteActivity.class);
                startActivity(intent);
            }
        });
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvRetroalimentacion = findViewById(R.id.tvRetroalimentacion);
        //tvId = findViewById(R.id.tvId);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        //tvId.setText(TareasEstudianteActivity.tareasArrayList.get(position).getId());
        tvTitulo.setText(TareasEstudianteActivity.tareasArrayList.get(position).getTitulo());
        tvDescripcion.setText(TareasEstudianteActivity.tareasArrayList.get(position).getDescripcion());
        tvRetroalimentacion.setText(TareasEstudianteActivity.tareasArrayList.get(position).getRetroalimentacion());
    }
}