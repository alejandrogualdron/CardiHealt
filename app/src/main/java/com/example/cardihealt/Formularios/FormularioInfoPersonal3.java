package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cardihealt.Menu;
import com.example.cardihealt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FormularioInfoPersonal3 extends AppCompatActivity implements View.OnClickListener {


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    RadioButton disnea0,disnea1,disnea2,disnea3,siG;
    CheckBox cbCianosis,cbColesterol,cbHipertension,cbDiabetes,regCheckbx;
    Button btnAnterior, btnSiguiente;
    String disnea,cianosis,colesterol,hipertension,diabetes,genetica;
    int riesgo,riesgoD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal3);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnAnterior = (Button) findViewById(R.id.btnAnteriorFormul2);
        btnSiguiente = (Button) findViewById(R.id.btnSiguienteFormul3);

        disnea0 = (RadioButton) findViewById(R.id.RegRadDisnea0);
        disnea1 = (RadioButton) findViewById(R.id.RegRadDisnea1);
        disnea2 = (RadioButton) findViewById(R.id.RegRadDisnea2);
        disnea3 = (RadioButton) findViewById(R.id.RegRadDisnea3);

        siG = (RadioButton) findViewById(R.id.RegRadbtnSiG);

        cbCianosis = (CheckBox) findViewById(R.id.cbCianosis);
        cbColesterol= (CheckBox) findViewById(R.id.cbColesterol);
        cbHipertension= (CheckBox) findViewById(R.id.cbHipertension);
        cbDiabetes= (CheckBox) findViewById(R.id.cbDiabetes);
        regCheckbx= (CheckBox) findViewById(R.id.RegCheckbx);


        btnSiguiente.setOnClickListener(this);
        btnAnterior.setOnClickListener(this);

    }

    public void genetica(){
        if(siG.isChecked() == true){
            genetica="Si";
        }else{
            genetica="No";
        }
    }

    public void disnea(){

        if(disnea0.isChecked() == true){
            disnea="No";
            riesgoD=0;
        } if(disnea1.isChecked() == true){
            disnea="Si";
            riesgoD=1;
        } if(disnea2.isChecked() == true){
            disnea="Si";
            riesgoD=2;
        } if(disnea3.isChecked() == true){
            disnea="Si";
            riesgoD=3;

        }
    }

    public void cianosis(){
        if(cbCianosis.isChecked() == true){
            cianosis="Si";
        }else{
            genetica="No";
        }
    }

    public void colesterol(){
        if(cbColesterol.isChecked() == true){
            colesterol="Si";
        }else{
            colesterol="No";
        }
    }

    public void diabetes(){
        if(cbDiabetes.isChecked() == true){
            diabetes="Si";
        }else{
            diabetes="No";
        }
    }

    public void hipertension(){
        if(cbHipertension.isChecked() == true){
            hipertension="Si";
        }else{
            hipertension="No";
        }
    }

    public void crearDB(){
        //Base de datos Firebase
        Map<String,Object> map = new HashMap<>();
        map.put("disnea",disnea);
        map.put("cianosis",cianosis);
        map.put("colesterol",colesterol);
        map.put("diabetes",diabetes);
        map.put("hipertension",hipertension);

        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase
        mDatabase.child("Usuario").child(id).updateChildren(map);

       /* mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FormularioInfoPersonal3.this,"Se ha registrado exitosamente",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(FormularioInfoPersonal3.this, Menu.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(FormularioInfoPersonal3.this,"No se crearon los datos", Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnAnteriorFormul2:
                i = new Intent(FormularioInfoPersonal3.this, FormularioInfoPersonal2.class);
                startActivity(i);
                break;

            case R.id.btnSiguienteFormul3:
                if(regCheckbx.isChecked() == true){
                    disnea();
                    genetica();
                    cianosis();
                    colesterol();
                    diabetes();
                    hipertension();

                    crearDB();

                    Intent intent=new Intent(FormularioInfoPersonal3.this, Menu.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(FormularioInfoPersonal3.this,"Acepte la politica de uso de datos", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}