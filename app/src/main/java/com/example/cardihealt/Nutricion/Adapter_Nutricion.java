package com.example.cardihealt.Nutricion;
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

import com.example.cardihealt.Nutricion.Nutricion;
import com.example.cardihealt.R;
import com.example.cardihealt.Recomendaciones.Adapter_Recomendaciones;


public class Adapter_Nutricion extends RecyclerView.Adapter<Adapter_Nutricion.ViewHolder> {

    List_Nutricion[] myNutricion;
    Context context;

    public Adapter_Nutricion(List_Nutricion[] myRecomendacion, Nutricion activity) {
        this.myNutricion = myRecomendacion;
        this.context = activity;
    }


    @NonNull
    @Override
    public Adapter_Nutricion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lista_nutricion,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Nutricion.ViewHolder holder, int position) {
        final List_Nutricion nutricion = myNutricion[position];
        holder.alimentoName.setText(nutricion.getAlimento());
        holder.historiaAlimento.setText(nutricion.getLeyendaAlimento());
        holder.imageAlimento.setImageResource(nutricion.getImagenAlimento());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, nutricion.getLeyendaAlimento(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myNutricion.length;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAlimento;
        TextView alimentoName;
        TextView historiaAlimento ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAlimento = itemView.findViewById(R.id.imageAlimento);
            alimentoName = itemView.findViewById(R.id.textAlimento);
            historiaAlimento = itemView.findViewById(R.id.historiaAlimento);

        }
    }

}
