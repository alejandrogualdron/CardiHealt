package com.example.cardihealt.Informacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cardihealt.R;
import com.example.cardihealt.Recomendaciones.Adapter_Recomendaciones;
import com.example.cardihealt.Recomendaciones.List_Recomendaciones;
import com.example.cardihealt.Recomendaciones.Recomendaciones;

public class Informacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List_Informacion[] myMovieData = new List_Informacion[]{
                new List_Informacion("Iconos","Iconos diseñados por https://www.freepik.com",R.drawable.flaticon),
                new List_Informacion("Imagenes","Imagenes por Gtresonline",R.drawable.pinterest),

        };
        Adapter_Informacion adapterRecome = new Adapter_Informacion(myMovieData, Informacion.this);
        recyclerView.setAdapter(adapterRecome);

    }
}