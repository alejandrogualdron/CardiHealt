package com.example.cardihealt.Entrenamiento;

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

import com.example.cardihealt.Nutricion.Adapter_Nutricion;
import com.example.cardihealt.Nutricion.List_Nutricion;
import com.example.cardihealt.Nutricion.Nutricion;
import com.example.cardihealt.R;

import java.util.List;

public class Adapter_Entrenamiento extends RecyclerView.Adapter<Adapter_Entrenamiento.ViewHolder>  {
    List_Entrenamiento[] myEntrenamiento;
    Context context;

    public Adapter_Entrenamiento(List_Entrenamiento[] myEntrenamiento, Entrenamiento activity) {
        this.myEntrenamiento = myEntrenamiento;
        this.context = activity;
    }

    @NonNull
    @Override
    public Adapter_Entrenamiento.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lista_entrenamientos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Entrenamiento.ViewHolder holder, int position) {
        final List_Entrenamiento entrenamiento = myEntrenamiento[position];
        holder.ejercicio.setText(entrenamiento.getEjercicio());
        holder.imageEntrenamiento.setImageResource(entrenamiento.getImagenEntrenamiento());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), Informacion_Entrenamiento.class);
                intent.putExtra("dato",entrenamiento.getEjercicio());
                holder.itemView.getContext().startActivity(intent);
                }

        });
    }



    @Override
    public int getItemCount() {
        return myEntrenamiento.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageEntrenamiento;
        TextView ejercicio ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEntrenamiento = itemView.findViewById(R.id.imageEntrenamiento);
            ejercicio = itemView.findViewById(R.id.textEntrenamiento);

        }
    }



}
