package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class EstudianteCursoActivity extends AppCompatActivity {
    Button btnRegresar;
    CardView cardExamenes, cardTareas, cardRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_curso);
        cardExamenes = findViewById(R.id.cardExamenes);
        cardTareas = findViewById(R.id.cardTareas);
        cardRegistro = findViewById(R.id.cardRegistros);
        btnRegresar = findViewById(R.id.btnRegresar);

        cardExamenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Falta implementar Examenes", Toast.LENGTH_SHORT).show();
            }
        });
        cardTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(),"Falta implementar Tareas", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),TareasEstudianteActivity.class);
                startActivity(intent);
            }
        });
        cardRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Falta implementar Registro", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("PreferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),EstudianteCursosActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}