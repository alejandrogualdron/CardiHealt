package com.example.cardihealt.Informacion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardihealt.Entrenamiento.Entrenamiento;
import com.example.cardihealt.Nutricion.Nutricion;
import com.example.cardihealt.R;
import com.example.cardihealt.Recomendaciones.Adapter_Recomendaciones;
import com.example.cardihealt.Recomendaciones.List_Recomendaciones;


public class Adapter_Informacion extends RecyclerView.Adapter<Adapter_Informacion.ViewHolder> {

    List_Informacion[] myInformacion;
    Context context;

    public Adapter_Informacion(List_Informacion[] myInformacion, Informacion activity) {
        this.myInformacion = myInformacion;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lista_informacion,parent,false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final List_Informacion informacion = myInformacion[position];
        holder.textInfo.setText(informacion.getInformacion());
        holder.descripcionInfo.setText(informacion.getDescripcion());
        holder.image_info.setImageResource(informacion.getMovieImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(informacion.getInformacion().equals("Rutina Fisica")){
                    Intent intent = new Intent(holder.itemView.getContext(), Entrenamiento.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }
    public int getItemCount() {
        return myInformacion.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_info;
        TextView textInfo;
        TextView descripcionInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_info = itemView.findViewById(R.id.image_info);
            descripcionInfo = itemView.findViewById(R.id.descripcionInfo);
            textInfo = itemView.findViewById(R.id.textInfo);

        }
    }

}
