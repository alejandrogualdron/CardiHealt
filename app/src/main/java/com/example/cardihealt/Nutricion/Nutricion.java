package com.example.cardihealt.Nutricion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cardihealt.R;
import com.example.cardihealt.Recomendaciones.Adapter_Recomendaciones;
import com.example.cardihealt.Recomendaciones.List_Recomendaciones;
import com.example.cardihealt.Recomendaciones.Recomendaciones;

public class Nutricion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricion);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List_Nutricion[] myMovieData = new List_Nutricion[]{
                new List_Nutricion("Cereales","Consumalo en porciones moderadas",R.drawable.cereales),
                new List_Nutricion("Vegetales","Consumalo en porciones moderadas",R.drawable.vegetal),
        };

        Adapter_Nutricion adapterRecome = new Adapter_Nutricion(myMovieData, Nutricion.this);
        recyclerView.setAdapter(adapterRecome);

    }
    }
