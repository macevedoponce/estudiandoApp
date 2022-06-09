package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    Button btnCerrar;
    CardView cardCursos,cardExamenes,cardTareas,cardAlumnos,cardRegistros,cardHorarios,cardHorariosEsperado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnCerrar = findViewById(R.id.btnCerrar);
        cardCursos = findViewById(R.id.cardCursos);
        cardHorarios = findViewById(R.id.cardHorarios);
        cardHorariosEsperado = findViewById(R.id.cardHorariosEsperado);
// aqui comienza los cardViews
        cardCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CursosActivity.class);
                startActivity(intent);

            }
        });
        cardHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),aluhorarioActivity.class);
                startActivity(intent);
                //Toast.makeText(getBaseContext(), "Falta implementar Horarios", Toast.LENGTH_SHORT).show();

            }
        });
        cardHorariosEsperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),horarioActivity.class);
                startActivity(intent);
                //Toast.makeText(getBaseContext(), "Falta implementar Horarios", Toast.LENGTH_SHORT).show();

            }
        });

// aqui terminan los cardViews

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}