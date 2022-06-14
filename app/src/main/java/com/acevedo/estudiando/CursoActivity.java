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

public class CursoActivity extends AppCompatActivity {

    Button btnRegresar;
    CardView cardExamenes,cardTareas,cardAlumnos,cardRegistros,cardHorarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        btnRegresar = findViewById(R.id.btnRegresar);
        cardExamenes = findViewById(R.id.cardExamenes);
        cardTareas = findViewById(R.id.cardTareas);
        cardAlumnos = findViewById(R.id.cardAlumnos);
        cardRegistros = findViewById(R.id.cardRegistros);

        // aqui comienza los cardViews
        cardExamenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Examenes", Toast.LENGTH_SHORT).show();

            }
        });
        cardTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);

            }
        });
        cardAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Falta implementar Alumnos", Toast.LENGTH_SHORT).show();

            }
        });
        cardRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NotasActivity.class);
                startActivity(intent);

            }
        });

// aqui terminan los cardViews

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),CursosActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}