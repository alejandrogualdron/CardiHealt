package com.example.cardihealt.Recomendaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListAdapter;

import com.example.cardihealt.R;

import java.util.ArrayList;
import java.util.List;

public class Recomendaciones extends AppCompatActivity {

    List<List_Recomendaciones> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List_Recomendaciones[] myMovieData = new List_Recomendaciones[]{
                new List_Recomendaciones("Rutina Fisica","Vea aquí los ejercicios fisicos diarios",R.drawable.entrenador1),
                new List_Recomendaciones("Nutrición","Vea aquí los alimentos que puede consumir",R.drawable.alimentacion_saludable),
        };

        Adapter_Recomendaciones adapterRecome = new Adapter_Recomendaciones(myMovieData,Recomendaciones.this);
        recyclerView.setAdapter(adapterRecome);

    }



}