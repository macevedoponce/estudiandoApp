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

public class AdapterAlumnos extends ArrayAdapter<Alumnos> {

Context context;
List<Alumnos> arrayAlumnos;

    public AdapterAlumnos(@NonNull Context context, List<Alumnos>arrayAlumnos) {
        super(context, R.layout.list_alumnos,arrayAlumnos);
        this.context=context;
        this.arrayAlumnos = arrayAlumnos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alumnos,null,true);

        TextView txtID = view.findViewById(R.id.txtid);
        TextView txtAlumno = view.findViewById(R.id.txtAlumno);

        txtID.setText(arrayAlumnos.get(position).getId());
        txtAlumno.setText(arrayAlumnos.get(position).getAlumno());
        return view;
    }
}
