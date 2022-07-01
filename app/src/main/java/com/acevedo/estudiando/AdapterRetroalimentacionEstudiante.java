package com.acevedo.estudiando;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterRetroalimentacionEstudiante extends ArrayAdapter<ExamenEstudianteRetro> {

    Context context;
    List<ExamenEstudianteRetro> arrayRetroalimentacionEstudiante;

    public AdapterRetroalimentacionEstudiante(@NonNull Context context, List<ExamenEstudianteRetro>arrayRetroalimentacionEstudiante) {
        super(context, R.layout.list_estudiante_retroalimentacion,arrayRetroalimentacionEstudiante);
        this.context=context;
        this.arrayRetroalimentacionEstudiante = arrayRetroalimentacionEstudiante;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_estudiante_retroalimentacion, null,true);

        //Todo lo que quieres listar
        TextView txtPreguntaNum = view.findViewById(R.id.txtPreguntaNum);
        TextView txtPreguntaExamenList = view.findViewById(R.id.txtPreguntaExamenList);
        TextView txtRespuesta = view.findViewById(R.id.txtRespuesta);
        TextView txtRetroalimentacion = view.findViewById(R.id.txtRetroalimentacion);

        txtPreguntaNum.setText(arrayRetroalimentacionEstudiante.get(position).getId());
        txtPreguntaExamenList.setText(arrayRetroalimentacionEstudiante.get(position).getPregunta());
        txtRespuesta.setText(arrayRetroalimentacionEstudiante.get(position).getAlternativa1());
        txtRetroalimentacion.setText(arrayRetroalimentacionEstudiante.get(position).getRetroalimentacion());
        return view;

    }
}
