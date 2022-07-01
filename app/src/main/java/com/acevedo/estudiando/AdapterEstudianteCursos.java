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

public class AdapterEstudianteCursos extends ArrayAdapter<EstudianteCursos> {

    Context context;
    List<EstudianteCursos> arrayestudiantecursos;

    public AdapterEstudianteCursos(@NonNull Context context, List<EstudianteCursos>arrayestudiantecursos) {
        super(context, R.layout.list_cursos,arrayestudiantecursos);
        this.context=context;
        this.arrayestudiantecursos = arrayestudiantecursos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_estudiante_cursos,null,true);

        TextView txtCodigo= view.findViewById(R.id.txtcodigo);
        TextView txtNombre = view.findViewById(R.id.txtnombre);

        txtCodigo.setText(arrayestudiantecursos.get(position).getCodigo());
        txtNombre.setText(arrayestudiantecursos.get(position).getNombre());
        return view;
    }
}
