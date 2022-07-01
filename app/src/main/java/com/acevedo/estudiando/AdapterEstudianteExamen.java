package com.acevedo.estudiando;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterEstudianteExamen extends ArrayAdapter<Examenes> {

    Context context;
    List<Examenes> arrayExamenEstudiante;

    public AdapterEstudianteExamen(@NonNull Context context, List<Examenes>arrayExamenEstudiante) {
        super(context, R.layout.list_estudiante_examen,arrayExamenEstudiante);
        this.context=context;
        this.arrayExamenEstudiante = arrayExamenEstudiante;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_estudiante_examen, null, true);

        //todo lo que quieres listar
        TextView txtID = view.findViewById(R.id.txtid);
        TextView txtTitulo = view.findViewById(R.id.txttitulo);

        txtID.setText(arrayExamenEstudiante.get(position).getId());
        txtTitulo.setText(arrayExamenEstudiante.get(position).getTitulo());
        return view;
    }
}
