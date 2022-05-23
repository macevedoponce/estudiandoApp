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

public class CursosActivity extends AppCompatActivity {

    Button btnRegresar;
    CardView cardCurso,cardCurso2,cardCurso3,cardCurso4,cardCurso5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        btnRegresar = findViewById(R.id.btnRegresar);
        cardCurso = findViewById(R.id.cardCurso);
        cardCurso2 = findViewById(R.id.cardCurso2);
        cardCurso3 = findViewById(R.id.cardCurso3);
        cardCurso4 = findViewById(R.id.cardCurso4);
        cardCurso5 = findViewById(R.id.cardCurso5);


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
}