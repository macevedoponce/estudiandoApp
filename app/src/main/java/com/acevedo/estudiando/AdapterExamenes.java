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

public class AdapterExamenes extends ArrayAdapter<Examenes> {

Context context;
List<Examenes> arrayExamenes;

    public AdapterExamenes(@NonNull Context context, List<Examenes>arrayExamenes) {
        super(context, R.layout.list_examenes,arrayExamenes);
        this.context=context;
        this.arrayExamenes = arrayExamenes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_examenes,null,true);

        TextView txtID = view.findViewById(R.id.txtid);
        TextView txtTitulo = view.findViewById(R.id.txttitulo);

        txtID.setText(arrayExamenes.get(position).getId());
        txtTitulo.setText(arrayExamenes.get(position).getTitulo());
        return view;
    }
}
