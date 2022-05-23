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

public class Adapter extends ArrayAdapter<Tareas> {

Context context;
List<Tareas> arraytareas;

    public Adapter(@NonNull Context context, List<Tareas>arraytareas) {
        super(context, R.layout.list_tareas,arraytareas);
        this.context=context;
        this.arraytareas = arraytareas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tareas,null,true);

        TextView txtID = view.findViewById(R.id.txtid);
        TextView txtTitulo = view.findViewById(R.id.txttitulo);

        txtID.setText(arraytareas.get(position).getId());
        txtTitulo.setText(arraytareas.get(position).getTitulo());
        return view;
    }
}
