package com.example.cardihealt.Recomendaciones;
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
import com.example.cardihealt.Entrenamiento.Entrenamiento_Recomendacion;
import com.example.cardihealt.Nutricion.Nutricion;
import com.example.cardihealt.R;


public class Adapter_Recomendaciones extends RecyclerView.Adapter<Adapter_Recomendaciones.ViewHolder>  {

    List_Recomendaciones[] myRecomendacion;
    Context context;

    public Adapter_Recomendaciones(List_Recomendaciones[] myRecomendacion,Recomendaciones activity) {
        this.myRecomendacion = myRecomendacion;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lista_recomendaciones,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final List_Recomendaciones recomendacion = myRecomendacion[position];
        holder.textViewName.setText(recomendacion.getRecomendacion());
        holder.textViewDate.setText(recomendacion.getMovieDate());
        holder.movieImage.setImageResource(recomendacion.getMovieImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(recomendacion.getRecomendacion().equals("Rutina Fisica")){
                    Intent intent = new Intent(holder.itemView.getContext(), Entrenamiento_Recomendacion.class);
                    holder.itemView.getContext().startActivity(intent);


                }if(recomendacion.getRecomendacion().equals("Nutrición")){
                    Intent intent = new Intent(holder.itemView.getContext(), Nutricion.class);
                    holder.itemView.getContext().startActivity(intent);
                }

                Toast.makeText(context, recomendacion.getRecomendacion(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRecomendacion.length;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);

        }
    }

}

