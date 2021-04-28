package com.example.cardihealt.Medico.Informes_Medico;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardihealt.R;

import java.io.Serializable;
import java.util.ArrayList;

public class InformeAdapter extends RecyclerView.Adapter<InformeAdapter.ViewHolder> {

    private int resourse;
    private ArrayList<InformesUsuario> informesList;

    public InformeAdapter(ArrayList<InformesUsuario> informesList,int resourse){
        this.informesList=informesList;
        this.resourse=resourse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourse,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InformesUsuario informes=informesList.get(position);
        holder.nombre.setText(informes.getNombre());
        holder.apellido.setText(informes.getApellido());
        holder.riesgo.setText(informes.getRiesgo());


        //interactua con los items
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actividadFisica=informes.getActividadFisica();
                String antecedentes=informes.getAntecedentes();
                String apellido=informes.getApellido();
                String contextura=informes.getContextura();
                String edad=informes.getEdad();
                String fecha=informes.getFecha();
                String genero=informes.getGenero();
                String indiceMasaCorporal=informes.getIndiceMasaCorporal();
                String indiceTabaquico=informes.getIndiceTabaquico();
                String nombre=informes.getNombre();
                String perimetroAbdominal=informes.getPerimetroAbdominal();
                String riesgo=informes.getRiesgo();
                String riesgoEpoc=informes.getRiesgoEpoc();
                String riesgoEstimado=informes.getRiesgoEstimado();
                String riesgoGenetico=informes.getRiesgoGenetico();
                String riesgoPerimetroAbd=informes.getRiesgoPerimetroAbd();
                String riesgoEdad=informes.getRiesgoEdad();
                String email=informes.getEmail();

                Intent intent=new Intent(holder.itemView.getContext(),Detalle_Informe.class);

                intent.putExtra("email",email);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                intent.putExtra("actividadFisica", actividadFisica);
                intent.putExtra("antecedentes", antecedentes);
                intent.putExtra("contextura", contextura);
                intent.putExtra("edad", edad);
                intent.putExtra("fecha", fecha);
                intent.putExtra("genero", genero);
                intent.putExtra("indiceMasaCorporal", indiceMasaCorporal);
                intent.putExtra("indiceTabaquico", indiceTabaquico);
                intent.putExtra("perimetroAbdominal", perimetroAbdominal);
                intent.putExtra("riesgo", riesgo);
                intent.putExtra("riesgoEpoc", riesgoEpoc);
                intent.putExtra("riesgoEstimado", riesgoEstimado);
                intent.putExtra("riesgoGenetico", riesgoGenetico);
                intent.putExtra("riesgoEdad", riesgoEdad);
                intent.putExtra("riesgoPerimetroAbd", riesgoPerimetroAbd);

                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return informesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView apellido;
        private TextView riesgo;

        public View view;
        public ViewHolder(View view ){
            super(view);
            this.view=view;
            this.nombre=(TextView)view.findViewById(R.id.textNombreInforme);
            this.apellido=(TextView)view.findViewById(R.id.textNombreApellido);
            this.riesgo=(TextView)view.findViewById(R.id.textNombreRiesgo);

        }
    }


}
