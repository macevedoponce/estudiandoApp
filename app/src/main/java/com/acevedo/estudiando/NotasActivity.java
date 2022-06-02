package com.acevedo.estudiando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
    }
    public void agregar(View view){
        startActivity(new Intent(getApplicationContext(),addNotaActivity.class));
    }
}