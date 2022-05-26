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

public class AdapterCursos extends ArrayAdapter<Cursos> {

    Context context;
    List<Cursos> arraycursos;

    public AdapterCursos(@NonNull Context context, List<Cursos>arraycursos) {
        super(context, R.layout.list_cursos,arraycursos);
        this.context=context;
        this.arraycursos = arraycursos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cursos,null,true);

        TextView  txtCodigo= view.findViewById(R.id.txtcodigo);
        TextView txtNombre = view.findViewById(R.id.txtnombre);

        txtCodigo.setText(arraycursos.get(position).getCodigo());
        txtNombre.setText(arraycursos.get(position).getNombre());
        return view;
    }
}
