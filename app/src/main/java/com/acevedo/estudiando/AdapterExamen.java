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

public class AdapterExamen extends ArrayAdapter<Examen> {

Context context;
List<Examen> arrayExamen;

    public AdapterExamen(@NonNull Context context, List<Examen>arrayExamen) {
        super(context, R.layout.list_examen,arrayExamen);
        this.context = context;
        this.arrayExamen = arrayExamen;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_examen,null,true);

        //TextView txtID = view.findViewById(R.id.txtid);
        TextView txtPregunta = view.findViewById(R.id.txtPregunta);
        TextView txtAlternativa1 = view.findViewById(R.id.txtAlternativa1);
        TextView txtAlternativa2 = view.findViewById(R.id.txtAlternativa2);
        TextView txtAlternativa3 = view.findViewById(R.id.txtAlternativa3);
        TextView txtAlternativa4 = view.findViewById(R.id.txtAlternativa4);
        TextView txtPuntos = view.findViewById(R.id.txtPuntos);

        //txtID.setText(arrayExamen.get(position).getId());


        txtPregunta.setText(arrayExamen.get(position).getPregunta());
        txtAlternativa1.setText(arrayExamen.get(position).getAlternativa1());
        txtAlternativa2.setText(arrayExamen.get(position).getAlternativa2());
        txtAlternativa3.setText(arrayExamen.get(position).getAlternativa3());
        txtAlternativa4.setText(arrayExamen.get(position).getAlternativa4());
        txtPuntos.setText(arrayExamen.get(position).getPuntos());
        return view;
    }
}
