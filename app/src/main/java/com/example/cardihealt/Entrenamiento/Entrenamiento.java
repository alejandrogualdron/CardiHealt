package com.example.cardihealt.Entrenamiento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cardihealt.Nutricion.Adapter_Nutricion;
import com.example.cardihealt.Nutricion.List_Nutricion;
import com.example.cardihealt.Nutricion.Nutricion;
import com.example.cardihealt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Entrenamiento extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    List_Entrenamiento[] myMovieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mostrar();
    }

    public void mostrar(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEntrenamiento);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Entrenamiento.this));


        myMovieData = new List_Entrenamiento[]{
                new List_Entrenamiento("Ejercicio 1",R.drawable.entrenamiento_calentamiento),
                new List_Entrenamiento("Ejercicio 2",R.drawable.entrenamiento_calentamiento2),
                new List_Entrenamiento("Ejercicio 3",R.drawable.entrenamiento_fuerza),
                new List_Entrenamiento("Ejercicio 4",R.drawable.entrenamiento_fuerza2),
                new List_Entrenamiento("Ejercicio 5",R.drawable.entrenamiento_estiramiento),
                new List_Entrenamiento("Ejercicio 6",R.drawable.entrenamiento_estiramiento2),
                new List_Entrenamiento("Ejercicio 7",R.drawable.entrenamiento_estiramiento3),
                new List_Entrenamiento("Ejercicio 8",R.drawable.entrenamiento_estiramiento4),
        };
        Adapter_Entrenamiento adapterRecome = new Adapter_Entrenamiento(myMovieData, Entrenamiento.this);
        recyclerView.setAdapter(adapterRecome);
    }

}