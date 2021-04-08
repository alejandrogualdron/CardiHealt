package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cardihealt.Menu;
import com.example.cardihealt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FormularioInfoPersonal2 extends AppCompatActivity implements View.OnClickListener {

    ImageButton  btnSiguiente1;
    CheckBox dormir,verTv,leer,planchar,conducir,cocinar,trotar,saltarC,cicla,caminarL,trabajoE;
    RadioButton disnea0, disnea1, disnea2, disnea3;
    CheckBox regCheckbx;
    double energiaC;
    String actividadF="";
    String disnea="";

    //////*****     VALORES PARA GENERAR INFORME  ******/////

    String nombre="";
    String apellido="";
    String cianosisDb ="";
    String colesterolDb="";
    String diabetesDb="";
    String hipertensionDb="";
    String generoDb="";
    String riesgoPerAb="";
    String cinturaDb="";
    String caderaDb="";
    String numeroCDb="";
    String añosFDb="";
    String pesoDb="";
    String alturaDb="";
    String edadDb="";
    String imcS = "";
    String geneticaDb="";
    String rEdad="";
    String riesgoLetras="";
    String riesgoPor="";
    String fumaDb="";
    int riesgoProm=0;
    int riesgoEpoc = 0;
    int indiceTabaquico=0;
    int riesgoT=0;
    int  numeroCigarrillos=0;
    int añosFumador=0;
    double perAbd=0;
    double imc=0;
    double altura=0;
    double peso=0;
    double cadera=0;
    double cintura=0;


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal2);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSiguiente1 = (ImageButton) findViewById(R.id.btnSiguienteFormul2);

        regCheckbx = (CheckBox) findViewById(R.id.RegCheckbx);

        disnea0 = (RadioButton) findViewById(R.id.RegRadDisnea0);
        disnea1 = (RadioButton) findViewById(R.id.RegRadDisnea1);
        disnea2 = (RadioButton) findViewById(R.id.RegRadDisnea2);
        disnea3 = (RadioButton) findViewById(R.id.RegRadDisnea3);

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
        trabajoE = (CheckBox) findViewById(R.id.cbTrabajoEscritorio);

        btnSiguiente1.setOnClickListener(this);
    }

    public void crearDB(){
        //Base de datos Firebase
        Map<String,Object> map = new HashMap<>();
        map.put("actividad fisica",actividadF);
        map.put("disnea", disnea);

        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase
        mDatabase.child("Usuario").child(id).updateChildren(map);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSiguienteFormul2:
                if (regCheckbx.isChecked() == true||!actividadF.equals("")) {

                    disnea();
                    actividadFis();
                    crearDB();
                    crearDBInforme();

                    Intent intent = new Intent(FormularioInfoPersonal2.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FormularioInfoPersonal2.this, "Acepte la politica de uso de datos", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    public void actividadFis(){
        if( revisarSintomas()>0 && revisarSintomas()<2.0){
            actividadF="Sedentario";
        }
        if( revisarSintomas()>1.9 && revisarSintomas()<3.0){
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
        }if(trabajoE.isChecked() == true){
            energiaC= energiaC+2.0;
        }

        return energiaC;
    }

    public void disnea() {

        if (disnea0.isChecked() == true) {
            disnea = "No";
        }
        if (disnea1.isChecked() == true) {
            disnea = "Si";
            riesgoT = riesgoT+1;
        }
        if (disnea2.isChecked() == true) {
            disnea = "Si";
            riesgoT = riesgoT+2;
        }
        if (disnea3.isChecked() == true) {
            disnea = "Si";
            riesgoT = riesgoT+3;
        }
    }

    public void crearDBInforme() {

        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Obtiene valores de la base de datos
                    nombre = snapshot.child("nombre").getValue().toString();
                    apellido = snapshot.child("apellido").getValue().toString();
                    pesoDb = snapshot.child("peso").getValue().toString();
                    alturaDb = snapshot.child("altura").getValue().toString();
                    cinturaDb = snapshot.child("cintura").getValue().toString();
                    caderaDb = snapshot.child("cadera").getValue().toString();
                    edadDb = snapshot.child("edad").getValue().toString();
                    generoDb = snapshot.child("genero").getValue().toString();
                    numeroCDb= snapshot.child("numero de cigarrillos").getValue().toString();
                    añosFDb = snapshot.child("años de fumador").getValue().toString();
                    geneticaDb = snapshot.child("genetica").getValue().toString();
                    cianosisDb = snapshot.child("cianosis").getValue().toString();
                    colesterolDb = snapshot.child("colesterol").getValue().toString();
                    diabetesDb = snapshot.child("diabetes").getValue().toString();
                    hipertensionDb = snapshot.child("hipertension").getValue().toString();
                    fumaDb=snapshot.child("fumador").getValue().toString();

                    if(fumaDb.equals("Si")){
                        numeroCigarrillos=Integer.parseInt(numeroCDb);
                        añosFumador=Integer.parseInt(añosFDb);
                        indiceTabaquico = numeroCigarrillos*añosFumador/20 ;
                        riesgoPorTabaco();
                    }   if(fumaDb.equals("No")){
                        indiceTabaquico = 0 ;
                        riesgoEpoc=0;
                    }
                    riesgoAntecedentes();
                    riesgoAbdominal();
                    indiceMasaCorporal();
                    riesgoPorEdad();
                    riesgoActividadFisica();
                    riesgoGenetico();
                    riesgoPromedio();
                    informeDatabase(id);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //Riesgo por Tabaco
    public void riesgoPorTabaco(){

        //Calculo del  tabaco riesgo
        if (indiceTabaquico < 10) {
            riesgoEpoc = 0;
        }
        if (indiceTabaquico > 9 && indiceTabaquico < 21) {
            riesgoT=riesgoT+1;
            riesgoEpoc = 33;
        }
        if (indiceTabaquico > 20 && indiceTabaquico < 40) {
            riesgoT=riesgoT+2;
            riesgoEpoc = 66;
        }
        if (indiceTabaquico > 40) {
            riesgoT=riesgoT+3;
            riesgoEpoc = 99;
        }

    }

    //Riesgo Antecedentes
    public void riesgoAntecedentes(){
        //riesgo por antecedentes

        if(cianosisDb.equals("Si")){
            riesgoPor=riesgoPor+"Cianosis ";
            riesgoT=riesgoT+1;
        }
        if(colesterolDb.equals("Si")){
            riesgoPor=riesgoPor+"Colesterol ";
            riesgoT=riesgoT+1;
        }
        if(diabetesDb.equals("Si")){
            riesgoPor=riesgoPor+"Diabetes ";
            riesgoT=riesgoT+1;
        }
        if(hipertensionDb.equals("Si")){
            riesgoPor=riesgoPor+"Hipertensión ";
            riesgoT=riesgoT+1;
        }

    }

    //Riesgo Abdominal
    public void riesgoAbdominal(){

        //perimetro abdominal
       cadera= Integer.parseInt(caderaDb);
       cintura=Integer.parseInt(cinturaDb);
        perAbd=cintura/cadera;
        if(generoDb.equals("Mujer")&&perAbd>0.84){
            riesgoT=riesgoT+1;
            riesgoPerAb="Posee riesgo";
        } if(generoDb.equals("Hombre")&&perAbd>0.93){
            riesgoT=riesgoT+1;
            riesgoPerAb="Posee riesgo";
        }if(generoDb.equals("Mujer")&&perAbd<0.85){
            riesgoPerAb="No posee riesgo";
        }if(generoDb.equals("Hombre")&&perAbd<0.94){
            riesgoPerAb="No posee riesgo";
        }
    }

    //Riesgo indice masa corporal
    public void indiceMasaCorporal(){

        //indice masa corporal
        peso=Integer.parseInt(pesoDb);
        altura=Integer.parseInt(alturaDb);
        altura=altura/100;
        imc = peso/(altura*altura);

        if (imc > 18.4 && imc < 25) {
            imcS = "Normal";
        }
        if (imc > 24.9 && imc < 30) {
            riesgoT=riesgoT+1;
            imcS = "Sobrepeso";
        }
        if (imc > 29.9 && imc < 35) {
            riesgoT=riesgoT+2;
            imcS = "Obesidad I";
        }
        if (imc > 34.9 && imc < 39.9) {
            riesgoT=riesgoT+3;
            imcS = "Obesidad II";
        }
        if (imc > 39.9) {
            riesgoT=riesgoT+4;
            imcS = "Obesidad III";
        }
    }

    //Riesgo por edad
    public void riesgoPorEdad(){
        //Riesgo por edad
        if (generoDb.equals("Hombre")) {
            if (Integer.parseInt( edadDb) > 54) {
                riesgoT=riesgoT+1;
                rEdad = "Si";
            } else {
                rEdad = "No";
            }
        }
        if (generoDb.equals("Mujer")) {
            if (Integer.parseInt(edadDb) > 44) {
                riesgoT=riesgoT+1;
                rEdad = "Si";
            } else {
                rEdad = "No";
            }
        }
    }

    //Riesgo actividad fisica
    public void riesgoActividadFisica(){
        //riesgo actividad F
        if(actividadF.equals("Sedentario")){
            riesgoT=riesgoT+3;
        }if(actividadF.equals("Ligera")){
            riesgoT=riesgoT+2;
        }if(actividadF.equals("Moderada")){
            riesgoT=riesgoT+1;
        }
    }

    //Riesgo genetica
    public void riesgoGenetico(){
        //Riesgo genetica
        if(geneticaDb.equals("Si")){
            riesgoT=riesgoT+2;
        }
    }

    //Riesgo promedio
    public void riesgoPromedio(){
        riesgoProm=(riesgoT*100)/21;
        if(riesgoProm<26){
            riesgoLetras="Bajo";
        }if(riesgoProm>25 &&riesgoProm<51){
            riesgoLetras="Medio";
        }if(riesgoProm>50 &&riesgoProm<76){
            riesgoLetras="Alto";
        }if(riesgoProm>75 &&riesgoProm<101){
            riesgoLetras="Muy alto";
        }
    }

    //Creacion del informe
    public void informeDatabase(String id){

        //Base de datos Firebase
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("apellido", apellido);
        map.put("indice tabaquico", indiceTabaquico + "");
        map.put("riesgo epoc", riesgoEpoc + "%");
        map.put("contextura", imcS);
        map.put("indice masa corporal", imc+"");
        map.put("actividad fisica", actividadF);
        map.put("genero",generoDb );
        map.put("edad",edadDb );
        map.put("riesgo por edad", rEdad);
        map.put("perimetro abdominal",perAbd+"");
        map.put("antecedentes", riesgoPor);
        map.put("riesgo genetico",geneticaDb);
        map.put("riesgo por perimetro abdominal", riesgoPerAb);
        map.put("riesgo estimado", riesgoProm+"%");
        map.put("riesgo", riesgoLetras);
        map.put("puntaje Prueba", riesgoT+"");


        mDatabase.child("Informes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

}

