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

 import com.acevedo.estudiando.EstudianteCursosActivity;
 import com.acevedo.estudiando.MainActivity;
 import com.acevedo.estudiando.R;
 import com.acevedo.estudiando.aluhorarioActivity;

 public class PrincipalEstudianteActivity extends AppCompatActivity {

     Button  btnCerrarEstudiante;
     CardView cardCursoEstudiante,cardHorarioEstudiante;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_principal_estudiante);
         btnCerrarEstudiante = findViewById(R.id.btnCerrarEstudiante);
         cardCursoEstudiante = findViewById(R.id.cardCursoEstudiante);
         cardHorarioEstudiante = findViewById(R.id.cardHorarioEstudiante);
         cardCursoEstudiante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), EstudianteCursosActivity.class);
                 startActivity(intent);

             }
         });
         cardHorarioEstudiante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), aluhorarioActivity.class);
                 startActivity(intent);
                 //Toast.makeText(getBaseContext(), "Falta implementar Horarios", Toast.LENGTH_SHORT).show();

             }
         });

// aqui terminan los cardViews

         btnCerrarEstudiante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                 preferences.edit().clear().commit();

                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
     }
 }