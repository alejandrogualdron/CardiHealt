package com.example.cardihealt.Informes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardihealt.Formularios.FormularioInfoPersonal2;
import com.example.cardihealt.Menu;
import com.example.cardihealt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Informe extends AppCompatActivity implements View.OnClickListener {
    Button  btnSiguiente;
    String nombreS,apellidoS,edadS,generoS,riesgoEdadS,actividadFisicaS,estadoFisicoS,indiceMasaCorporalS
            ,perimetroAbdominalS,riesgoPerAbS,indiceTabacS,riesgoEpocS,riesgoAntecedentesS
            ,riesgoEnfermedadesS,riesgoEstimadoS,riesgoLetaS;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSiguiente = (Button) findViewById(R.id.menuInforme);
        btnSiguiente.setOnClickListener(this);
        informe();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuInforme:

                Intent miIntent=new Intent(Informe.this,InformeFinal.class);
                miIntent.putExtra("nombre",nombreS);
                miIntent.putExtra("apellido",apellidoS);
                miIntent.putExtra("edad",edadS);
                miIntent.putExtra("actividadF",actividadFisicaS);
                miIntent.putExtra("riesgoAntecedentes",riesgoAntecedentesS);

                miIntent.putExtra("estadoF",estadoFisicoS);
                miIntent.putExtra("indiceM",indiceMasaCorporalS);
                miIntent.putExtra("perimetroAbdominal",perimetroAbdominalS);
                miIntent.putExtra("indiceT",indiceTabacS);
                miIntent.putExtra("riesgoEnfermedades",riesgoEnfermedadesS);

                miIntent.putExtra("riesgoEdad",riesgoEdadS);
                miIntent.putExtra("riesgoE",riesgoEpocS);
                miIntent.putExtra("riesgoPerAb",riesgoPerAbS);
                miIntent.putExtra("riesgoEstimado",riesgoEstimadoS);
                miIntent.putExtra("riesgoLeta",riesgoLetaS);

                miIntent.putExtra("genero",generoS);

                startActivity(miIntent);
                break;

        }
    }

    public void informe(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Informes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Obtiene valores de la base de datos
                    nombreS = snapshot.child("nombre").getValue().toString();
                    apellidoS = snapshot.child("apellido").getValue().toString();
                    indiceTabacS = snapshot.child("indice tabaquico").getValue().toString();
                    riesgoEpocS = snapshot.child("riesgo epoc").getValue().toString();
                    estadoFisicoS = snapshot.child("contextura").getValue().toString();
                    indiceMasaCorporalS = snapshot.child("indice masa corporal").getValue().toString();
                    actividadFisicaS = snapshot.child("actividad fisica").getValue().toString();
                    generoS = snapshot.child("genero").getValue().toString();
                    edadS = snapshot.child("edad").getValue().toString();
                    riesgoEdadS = snapshot.child("riesgo por edad").getValue().toString();
                    perimetroAbdominalS = snapshot.child("perimetro abdominal").getValue().toString();
                    riesgoEnfermedadesS = snapshot.child("antecedentes").getValue().toString();
                    riesgoAntecedentesS = snapshot.child("riesgo genetico").getValue().toString();
                    riesgoPerAbS = snapshot.child("riesgo por perimetro abdominal").getValue().toString();
                    riesgoLetaS = snapshot.child("riesgo").getValue().toString();
                    riesgoEstimadoS = snapshot.child("riesgo estimado").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}