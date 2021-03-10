package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cardihealt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FormularioInfoPersonal2 extends AppCompatActivity implements View.OnClickListener {

    Button btnAnterior1, btnSiguiente1;
    EditText numeroC,añosF;
    RadioButton si;
    CheckBox dormir,verTv,leer,planchar,conducir,cocinar,trotar,saltarC,cicla,caminarL;
    String numeroCigarrillos,añosFumador,fuma;
    double energiaC,energia;
    String actividadF="";


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal2);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnAnterior1 = (Button) findViewById(R.id.btnAnteriorFormul1);
        btnSiguiente1 = (Button) findViewById(R.id.btnSiguienteFormul2);

        si = (RadioButton) findViewById(R.id.RegRadbtnSi);

        numeroC=(EditText)findViewById(R.id.etNumeroCigarrillos);
        añosF=(EditText)findViewById(R.id.etAñofumador);

        dormir = (CheckBox) findViewById(R.id.cbDormir);
        verTv = (CheckBox) findViewById(R.id.cbVerTv);
        leer = (CheckBox) findViewById(R.id.cbLeer);
        planchar = (CheckBox) findViewById(R.id.cbPlanchar);
        conducir = (CheckBox) findViewById(R.id.cbConducir);
        cocinar = (CheckBox) findViewById(R.id.cbCocinar);
        trotar = (CheckBox) findViewById(R.id.cbTrotar);
        saltarC = (CheckBox) findViewById(R.id.cbSaltarCuerda);
        cicla = (CheckBox) findViewById(R.id.cbCicla);
        caminarL = (CheckBox) findViewById(R.id.cbCaminarLento);

        btnAnterior1.setOnClickListener(this);
        btnSiguiente1.setOnClickListener(this);

    }

    public void crearDB(){
        //Base de datos Firebase
        Map<String,Object> map = new HashMap<>();
        map.put("fumador",fuma);
        map.put("años de fumador",añosFumador);
        map.put("numeros de cigarrillos",numeroCigarrillos);
        map.put("actividad fisica",actividadF);

        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase
        mDatabase.child("Usuario").child(id).updateChildren(map);

       /* mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FormularioInfoPersonal2.this,"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(FormularioInfoPersonal2.this, FormularioInfoPersonal3.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(FormularioInfoPersonal2.this,"No se crearon los datos", Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }



    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnAnteriorFormul1:
                i = new Intent(FormularioInfoPersonal2.this, FormularioInfoPersonal1.class);
                startActivity(i);
                break;

            case R.id.btnSiguienteFormul2:
                energia=revisarSintomas();
                numeroCigarrillos=numeroC.getText().toString();
                añosFumador=añosF.getText().toString();
                fumador();
                actividadFis();

                crearDB();

                Intent intent=new Intent(FormularioInfoPersonal2.this, FormularioInfoPersonal3.class);
                startActivity(intent);
                break;

        }
    }

    public void actividadFis(){
        if( revisarSintomas()>0 && revisarSintomas()<1.5){
            actividadF="Sedentario";
        }
        if( revisarSintomas()>1.4 && revisarSintomas()<3.0){
            actividadF="Ligera";
        }
        if( revisarSintomas()>2.9 && revisarSintomas()<5.9){
            actividadF="Moderada";
        }
        if( revisarSintomas()>6 ){
            actividadF="Rigurosa";
        }
    }

    public  double revisarSintomas(){
        energiaC = 0;

        if(dormir.isChecked() == true){
            energiaC= energiaC+0.9;
        }
        if(verTv.isChecked() == true){
            energiaC= energiaC+1.0;
        }
        if(leer.isChecked() == true){
            energiaC= energiaC+1.8;
        }
        if(planchar.isChecked() == true){
            energiaC= energiaC+1.8;
        }
        if(conducir.isChecked() == true){
            energiaC= energiaC+2.0;
        }
        if(cocinar.isChecked() == true){
            energiaC= energiaC+3.5;
        }
        if(trotar.isChecked() == true){
            energiaC= energiaC+7.0;
        }
        if(saltarC.isChecked() == true){
            energiaC= energiaC+10.0;
        }
        if(cicla.isChecked() == true){
            energiaC= energiaC+14.0;
        }
        if(caminarL.isChecked() == true){
            energiaC= energiaC+2.5;
        }

        return energiaC;
    }

    public void fumador(){
        if(si.isChecked() == true){
            fuma="Fumador";
        }else{
            fuma="No fumador";
        }
    }



}

