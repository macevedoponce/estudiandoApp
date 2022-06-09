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

public class AdapterHorarioAlumnos extends ArrayAdapter<Horarios> {

    Context context;
    List<Horarios> arrayhorarios;

    public AdapterHorarioAlumnos(@NonNull Context context, List<Horarios>arrayhorarios) {
        super(context, R.layout.list_horarios,arrayhorarios);
        this.context=context;
        this.arrayhorarios = arrayhorarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_horarios,null,true);

        TextView txtCodigo= view.findViewById(R.id.txtcodigo);
        TextView txtNombre = view.findViewById(R.id.txtnombre);
        TextView txtHorario = view.findViewById(R.id.txthorario);

        txtCodigo.setText(arrayhorarios.get(position).getCursoCodigo());
        txtNombre.setText(arrayhorarios.get(position).getCursoNombre());
        txtHorario.setText(arrayhorarios.get(position).getHorario());
        return view;
    }
}
