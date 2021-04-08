package com.example.cardihealt.Informes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    ImageButton btnMenuInforme;
    TextView nombre,apellido,edad,genero,riesgoEdad,actividadFisica,estadoFisico,indiceMasaCorporal
            ,perimetroAbdominal,riesgoPerAbd,indiceTabac,riesgoEpoc,riesgoAntecedentes
            ,riesgoEnfermedades,riesgoEstimado,riesgoLetra;
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

        btnMenuInforme = (ImageButton) findViewById(R.id.menuInforme);

        nombre=(TextView)findViewById(R.id.nombreInforme);
        apellido=(TextView)findViewById(R.id.apellidoInforme);
        edad=(TextView)findViewById(R.id.edadInforme);
        genero=(TextView)findViewById(R.id.generoInforme);
        riesgoEdad=(TextView)findViewById(R.id.riesgoPorEdadInforme);
        actividadFisica=(TextView)findViewById(R.id.actividadFisicaInforme);
        estadoFisico=(TextView)findViewById(R.id.estadoFisicoInfor);
        indiceMasaCorporal=(TextView)findViewById(R.id.imcInforme);
        perimetroAbdominal=(TextView)findViewById(R.id.perimetroInforme);
        riesgoPerAbd=(TextView)findViewById(R.id.riesgoPerAbdInforme);
        indiceTabac=(TextView)findViewById(R.id.indiceTabaInforme);
        riesgoEpoc=(TextView)findViewById(R.id.riesgoEpoc);
        riesgoAntecedentes=(TextView)findViewById(R.id.riesgoPorAntecedentes);
        riesgoEnfermedades=(TextView)findViewById(R.id.enfermedadesBaseInforme);
        riesgoEstimado=(TextView)findViewById(R.id.riesgoEstimadoInforme);
        riesgoLetra=(TextView)findViewById(R.id.riesgoLetraInforme);

        btnMenuInforme.setOnClickListener(this);
        informe();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.menuInforme:

                    Intent intent = new Intent(Informe.this, Menu.class);
                    startActivity(intent);

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

                   nombre.setText(nombreS);
                   apellido.setText(apellidoS);
                   indiceTabac.setText(indiceTabacS);
                   riesgoEpoc.setText(riesgoEpocS);
                   estadoFisico.setText(estadoFisicoS);
                   indiceMasaCorporal.setText(indiceMasaCorporalS);
                   actividadFisica.setText(actividadFisicaS);
                   genero.setText(generoS);
                   edad.setText(edadS);
                   riesgoEdad.setText(riesgoEdadS);
                   perimetroAbdominal.setText(perimetroAbdominalS);
                   riesgoEnfermedades.setText(riesgoEnfermedadesS);
                   riesgoAntecedentes.setText(riesgoAntecedentesS);
                   riesgoPerAbd.setText(riesgoPerAbS);
                   riesgoLetra.setText(riesgoLetaS);
                   riesgoEstimado.setText(riesgoEstimadoS);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}