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

        RecyclerView recyclerView = findViewById(R.id.recyclerViewNutricion);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List_Nutricion[] myMovieData = new List_Nutricion[]{

                new List_Nutricion("Cereales","Consumalo en porciones moderadas",R.drawable.nutricion_cereal),
                new List_Nutricion("Vegetales","Consumalo en porciones moderadas",R.drawable.nutricion_vegetales),
                new List_Nutricion("Frutas","Consumalo en porciones moderadas",R.drawable.nutricion_frutas),
                new List_Nutricion("Leche deslactosada","Consumalo en porciones moderadas",R.drawable.nutricion_leche),
                new List_Nutricion("Granos integrales","Consumalo en porciones moderadas",R.drawable.nutricion_nueces),
                new List_Nutricion("Pescado","Consumalo en porciones moderadas",R.drawable.nutricion_pescado),
                new List_Nutricion("Pollo","Consumalo en porciones moderadas",R.drawable.nutricion_pollo),
                new List_Nutricion("Harinas","Consumalo en porciones moderadas",R.drawable.nutricion_harina),
                new List_Nutricion("Aguacate","Consumalo en porciones moderadas",R.drawable.nutricion_aguacate),
                new List_Nutricion("Dulces","Reduzca su consumo",R.drawable.nutricion_golosinas),
                new List_Nutricion("Bebidas azucaradas","Anule su consumo",R.drawable.nutricion_soda),
                new List_Nutricion("Alimentos de paquete","Reduzca su consumo",R.drawable.nutricion_snack),
                new List_Nutricion("Productos Lacteos","Anule su consumo",R.drawable.nutricion_lacteos),
                new List_Nutricion("Carnes rojas","Reduzca su consumo",R.drawable.nutricion_carne),
                new List_Nutricion("Comidas saladas","Anule su consumo",R.drawable.nutricion_sal),
                new List_Nutricion("Alcohol","Anule su consumo",R.drawable.nutricion_cerveza),
                new List_Nutricion("Alimentos fritos","Anule su consumo",R.drawable.nutricion_frito),
        };

        Adapter_Nutricion adapterRecome = new Adapter_Nutricion(myMovieData, Nutricion.this);
        recyclerView.setAdapter(adapterRecome);

    }
    }
