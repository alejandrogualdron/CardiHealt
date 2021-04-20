package com.example.cardihealt.Medico.Informes_Medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cardihealt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MostrarInformes extends AppCompatActivity  implements View.OnClickListener {


    private InformeAdapter iAdapter;
    private RecyclerView nRecyclerView;
    private ArrayList<InformesUsuario> nInformesList=new ArrayList<>();
    private DatabaseReference nDatabase;
    private FirebaseAuth nAuth;
    private Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_informes);

        btnAtras = (Button) findViewById(R.id.btnAtrasMost);
        btnAtras.setOnClickListener( this);

        nDatabase= FirebaseDatabase.getInstance().getReference();
        nAuth = FirebaseAuth.getInstance();

        nRecyclerView=(RecyclerView)findViewById(R.id.recyclerViewInforme);
        nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getInformeFromFirebase();
    }

    private void getInformeFromFirebase(){

        nDatabase.child("Informes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    nInformesList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String nombreS = ds.child("nombre").getValue().toString();
                        String apellidoS = ds.child("apellido").getValue().toString();
                        String indiceTabacS = ds.child("indice tabaquico").getValue().toString();
                        String  riesgoEpocS = ds.child("riesgo epoc").getValue().toString();
                        String  estadoFisicoS = ds.child("contextura").getValue().toString();
                        String indiceMasaCorporalS = ds.child("indice masa corporal").getValue().toString();
                        String actividadFisicaS = ds.child("actividad fisica").getValue().toString();
                        String  generoS = ds.child("genero").getValue().toString();
                        String edadS = ds.child("edad").getValue().toString();
                        String  riesgoEdadS = ds.child("riesgo por edad").getValue().toString();
                        String  perimetroAbdominalS = ds.child("perimetro abdominal").getValue().toString();
                        String  riesgoEnfermedadesS = ds.child("antecedentes").getValue().toString();
                        String  riesgoAntecedentesS = ds.child("riesgo genetico").getValue().toString();
                        String riesgoPerAbS = ds.child("riesgo por perimetro abdominal").getValue().toString();
                        String riesgoLetaS = ds.child("riesgo").getValue().toString();
                        String  riesgoEstimadoS = ds.child("riesgo estimado").getValue().toString();
                        String  fecha = ds.child("fecha").getValue().toString();

                        nInformesList.add(new InformesUsuario(actividadFisicaS,riesgoEnfermedadesS,
                                apellidoS,estadoFisicoS,edadS,fecha,generoS,indiceMasaCorporalS,indiceTabacS,
                                nombreS,perimetroAbdominalS,riesgoLetaS,riesgoEpocS,riesgoEstimadoS,riesgoAntecedentesS,
                                riesgoPerAbS,riesgoEdadS));
                    }
                    iAdapter =new InformeAdapter(nInformesList,R.layout.informe);
                    nRecyclerView.setAdapter(iAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    @Override
    public void onClick(View v) {

    }
}