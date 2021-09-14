package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cardihealt.UsarioMenu.Menu;
import com.example.cardihealt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        btnSiguiente1 = (ImageButton) findViewById(R.id.btnSiguienteFormulario2);

        regCheckbx = (CheckBox) findViewById(R.id.RegCheckbx);

        disnea0 = (RadioButton) findViewById(R.id.RegRadDisnea0);
        disnea1 = (RadioButton) findViewById(R.id.RegRadDisnea1);
        disnea2 = (RadioButton) findViewById(R.id.RegRadDisnea2);
        disnea3 = (RadioButton) findViewById(R.id.RegRadDisnea3);

        dormir = (CheckBox) findViewById(R.id.cbDormir);
        verTv = (CheckBox) findViewById(R.id.cbVerTelevision);
        leer = (CheckBox) findViewById(R.id.cbPollo);
        planchar = (CheckBox) findViewById(R.id.cbPlanchar);
        conducir = (CheckBox) findViewById(R.id.cbConducir);
        cocinar = (CheckBox) findViewById(R.id.cbCocinar);
        trotar = (CheckBox) findViewById(R.id.cbTrotar);
        saltarC = (CheckBox) findViewById(R.id.cbSaltarCuerda);
        cicla = (CheckBox) findViewById(R.id.cbMontarCicla);
        caminarL = (CheckBox) findViewById(R.id.cbCaminar);
        trabajoE = (CheckBox) findViewById(R.id.cbTiempoSentado);

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

            case R.id.btnSiguienteFormulario2:

                if (disnea0.isChecked() == false&&disnea1.isChecked() == false&&disnea2.isChecked() == false
                        && disnea3.isChecked() == false){
                    Toast.makeText(FormularioInfoPersonal2.this, "Responda si posee sensacion de ahogo", Toast.LENGTH_LONG).show();
                }else if(revisarSintomas()==0){
                    Toast.makeText(FormularioInfoPersonal2.this, "Seleccione alguna actividad", Toast.LENGTH_LONG).show();
                }else if (regCheckbx.isChecked() == true) {
                    disnea();
                    actividadFis();
                    crearDB();
                    crearDBInforme();
                  //  planEntrenamiento();
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
        }else if( revisarSintomas()>1.9 && revisarSintomas()<3.0){
            actividadF="Ligera";
        }else if( revisarSintomas()>2.9 && revisarSintomas()<5.9){
            actividadF="Moderada";
        }else if( revisarSintomas()>6 ){
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
            energiaC= energiaC+1.0;
        }

        return energiaC;
    }

    public void disnea() {

        if (disnea0.isChecked() == true) {
            disnea = "No";
        }else if (disnea1.isChecked() == true) {
            disnea = "Si";
            riesgoT = riesgoT+1;
        }else if (disnea2.isChecked() == true) {
            disnea = "Si";
            riesgoT = riesgoT+2;
        }else if (disnea3.isChecked() == true) {
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
                    } else  if(fumaDb.equals("No")){
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
        }else if (indiceTabaquico > 9 && indiceTabaquico < 21) {
            riesgoT=riesgoT+1;
            riesgoEpoc = 33;
        }else if (indiceTabaquico > 20 && indiceTabaquico < 40) {
            riesgoT=riesgoT+2;
            riesgoEpoc = 66;
        }else if (indiceTabaquico > 40) {
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
        if(generoDb.equals("Mujer")&&perAbd>0.86){
            riesgoT=riesgoT+1;
            riesgoPerAb="Si";
        }else if(generoDb.equals("Mujer")&&perAbd<0.85) {
            riesgoPerAb = "No";
        }else if(generoDb.equals("Hombre")&&perAbd>0.93){
            riesgoT=riesgoT+1;
            riesgoPerAb="Si";
        }else if(generoDb.equals("Hombre")&&perAbd<0.94){
            riesgoPerAb="No";
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
        }else if (imc > 24.9 && imc < 30) {
            riesgoT=riesgoT+1;
            imcS = "Sobrepeso";
        }else if (imc > 29.9 && imc < 35) {
            riesgoT=riesgoT+2;
            imcS = "Obesidad I";
        }else if (imc > 34.9 && imc < 39.9) {
            riesgoT=riesgoT+3;
            imcS = "Obesidad II";
        }else if (imc > 39.9) {
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
        }else if (generoDb.equals("Mujer")) {
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
        }else if(actividadF.equals("Ligera")){
            riesgoT=riesgoT+2;
        }else if(actividadF.equals("Moderada")){
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
        }else if(riesgoProm>25 &&riesgoProm<51){
            riesgoLetras="Medio";
        }else if(riesgoProm>50 &&riesgoProm<76){
            riesgoLetras="Alto";
        }else if(riesgoProm>75 &&riesgoProm<101){
            riesgoLetras="Muy alto";
        }
        ejerciciosPlanPersonaliado(riesgoLetras);
    }


    //Creacion del informe
    public void informeDatabase(String id){
        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";

        FirebaseUser user = mAuth.getCurrentUser();
        String  email;
        email = user.getEmail();

        //Base de datos Firebase
        Map<String, Object> map = new HashMap<>();
        map.put("email",email);
        map.put("fecha",fecha);
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
                Toast.makeText(FormularioInfoPersonal2.this, "Se ha generado su informe", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ejerciciosPlanPersonaliado(String riesgo){

        if(riesgo=="Bajo"){
            planEntrenamientoBajo();
        } else if(riesgo=="Medio"){
            planEntrenamientoMedio();
        } else if(riesgo=="Alto"){
            planEntrenamientoAlto();
        } else if(riesgo=="Muy alto"){
            planEntrenamientoMuyAlto();
        }
    }

    public void planEntrenamientoBajo(){

        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";


        //Base de datos Firebase
        Map<String,Object> ejercio1 = new HashMap<>();
        Map<String,Object> ejercio2 = new HashMap<>();
        Map<String,Object> ejercio3 = new HashMap<>();
        Map<String,Object> ejercio4 = new HashMap<>();
        Map<String,Object> ejercio5 = new HashMap<>();
        Map<String,Object> ejercio6 = new HashMap<>();
        Map<String,Object> ejercio7 = new HashMap<>();
        Map<String,Object> ejercio8 = new HashMap<>();

        //EJERCICIO 1
        ejercio1.put("finalidad","Calentamiento");
        ejercio1.put("tipo_de_ejercicio","Subir escaleras");
        ejercio1.put("intensidad","Normal");
        Map<String,Object> tiempo1 = new HashMap<>();
        tiempo1.put("minutos","" + "07");
        tiempo1.put("segundos","00");
        ejercio1.put("tiempo",tiempo1);

        //EJERCICIO 2
        ejercio2.put("finalidad","Calentamiento");
        ejercio2.put("tipo_de_ejercicio","caminata");
        ejercio2.put("intensidad","Normal");
        Map<String,Object> tiempo2 = new HashMap<>();
        tiempo2.put("minutos","30");
        tiempo2.put("segundos","00");
        ejercio2.put("tiempo",tiempo2);

        if (rEdad.equals("Si")){

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","10");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 3
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","10");
            ejercio4.put("tiempo",tiempo4);

        }
        else{

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","17");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 4
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","17");
            ejercio4.put("tiempo",tiempo4);
        }

        //EJERCICIO 5
        ejercio5.put("finalidad","Estiramiento");
        ejercio5.put("tipo_de_ejercicio","Cuello");
        ejercio5.put("intensidad","Normal");
        Map<String,Object> tiempo5 = new HashMap<>();
        tiempo5.put("minutos","00");
        tiempo5.put("segundos","30");
        ejercio5.put("tiempo",tiempo5);

        //EJERCICIO 6
        ejercio6.put("finalidad","Estiramiento");
        ejercio6.put("tipo_de_ejercicio","Espalda");
        ejercio6.put("intensidad","Normal");
        Map<String,Object> tiempo6 = new HashMap<>();
        tiempo6.put("minutos","00");
        tiempo6.put("segundos","30");
        ejercio6.put("tiempo",tiempo6);

        //EJERCICIO 7
        ejercio7.put("finalidad","Estiramiento");
        ejercio7.put("tipo_de_ejercicio","Miembros superiores");
        ejercio7.put("intensidad","Normal");
        Map<String,Object> tiempo7 = new HashMap<>();
        tiempo7.put("minutos","00");
        tiempo7.put("segundos","30");
        ejercio7.put("tiempo",tiempo7);

        //EJERCICIO 8
        ejercio8.put("finalidad","Estiramiento");
        ejercio8.put("tipo_de_ejercicio","Miembros inferiores");
        ejercio8.put("intensidad","Normal");
        Map<String,Object> tiempo8 = new HashMap<>();
        tiempo8.put("minutos","00");
        tiempo8.put("segundos","30");
        ejercio8.put("tiempo",tiempo8);

        Map<String,Object> ejercicios = new HashMap<>();
        ejercicios.put("fecha",fecha);
        ejercicios.put("ejercicio_1",ejercio1);
        ejercicios.put("ejercicio_2",ejercio2);
        ejercicios.put("ejercicio_3",ejercio3);
        ejercicios.put("ejercicio_4",ejercio4);
        ejercicios.put("ejercicio_5",ejercio5);
        ejercicios.put("ejercicio_6",ejercio6);
        ejercicios.put("ejercicio_7",ejercio7);
        ejercicios.put("ejercicio_8",ejercio8);


        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase

        mDatabase.child("Entrenamientos").child(id).setValue(ejercicios).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                }else{
                    Toast.makeText(FormularioInfoPersonal2.this,"No se pudo crear plan", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void planEntrenamientoMedio(){

        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";


        //Base de datos Firebase
        Map<String,Object> ejercio1 = new HashMap<>();
        Map<String,Object> ejercio2 = new HashMap<>();
        Map<String,Object> ejercio3 = new HashMap<>();
        Map<String,Object> ejercio4 = new HashMap<>();
        Map<String,Object> ejercio5 = new HashMap<>();
        Map<String,Object> ejercio6 = new HashMap<>();
        Map<String,Object> ejercio7 = new HashMap<>();
        Map<String,Object> ejercio8 = new HashMap<>();

        //EJERCICIO 1
        ejercio1.put("finalidad","Calentamiento");
        ejercio1.put("tipo_de_ejercicio","Subir escaleras");
        ejercio1.put("intensidad","Normal");
        Map<String,Object> tiempo1 = new HashMap<>();
        tiempo1.put("minutos","" + "10");
        tiempo1.put("segundos","00");
        ejercio1.put("tiempo",tiempo1);

        //EJERCICIO 2
        ejercio2.put("finalidad","Calentamiento");
        ejercio2.put("tipo_de_ejercicio","caminata");
        ejercio2.put("intensidad","Normal");
        Map<String,Object> tiempo2 = new HashMap<>();
        tiempo2.put("minutos","27");
        tiempo2.put("segundos","00");
        ejercio2.put("tiempo",tiempo2);

        if (rEdad.equals("Si")){

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","10");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 3
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","10");
            ejercio4.put("tiempo",tiempo4);

        }
        else{

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","15");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 4
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","15");
            ejercio4.put("tiempo",tiempo4);
        }

        //EJERCICIO 5
        ejercio5.put("finalidad","Estiramiento");
        ejercio5.put("tipo_de_ejercicio","Cuello");
        ejercio5.put("intensidad","Normal");
        Map<String,Object> tiempo5 = new HashMap<>();
        tiempo5.put("minutos","00");
        tiempo5.put("segundos","30");
        ejercio5.put("tiempo",tiempo5);

        //EJERCICIO 6
        ejercio6.put("finalidad","Estiramiento");
        ejercio6.put("tipo_de_ejercicio","Espalda");
        ejercio6.put("intensidad","Normal");
        Map<String,Object> tiempo6 = new HashMap<>();
        tiempo6.put("minutos","00");
        tiempo6.put("segundos","30");
        ejercio6.put("tiempo",tiempo6);

        //EJERCICIO 7
        ejercio7.put("finalidad","Estiramiento");
        ejercio7.put("tipo_de_ejercicio","Miembros superiores");
        ejercio7.put("intensidad","Normal");
        Map<String,Object> tiempo7 = new HashMap<>();
        tiempo7.put("minutos","00");
        tiempo7.put("segundos","30");
        ejercio7.put("tiempo",tiempo7);

        //EJERCICIO 8
        ejercio8.put("finalidad","Estiramiento");
        ejercio8.put("tipo_de_ejercicio","Miembros inferiores");
        ejercio8.put("intensidad","Normal");
        Map<String,Object> tiempo8 = new HashMap<>();
        tiempo8.put("minutos","00");
        tiempo8.put("segundos","30");
        ejercio8.put("tiempo",tiempo8);

        Map<String,Object> ejercicios = new HashMap<>();
        ejercicios.put("fecha",fecha);
        ejercicios.put("ejercicio_1",ejercio1);
        ejercicios.put("ejercicio_2",ejercio2);
        ejercicios.put("ejercicio_3",ejercio3);
        ejercicios.put("ejercicio_4",ejercio4);
        ejercicios.put("ejercicio_5",ejercio5);
        ejercicios.put("ejercicio_6",ejercio6);
        ejercicios.put("ejercicio_7",ejercio7);
        ejercicios.put("ejercicio_8",ejercio8);


        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase

        mDatabase.child("Entrenamientos").child(id).setValue(ejercicios).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                }else{
                    Toast.makeText(FormularioInfoPersonal2.this,"No se pudo crear plan", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void planEntrenamientoAlto(){


        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";


        //Base de datos Firebase
        Map<String,Object> ejercio1 = new HashMap<>();
        Map<String,Object> ejercio2 = new HashMap<>();
        Map<String,Object> ejercio3 = new HashMap<>();
        Map<String,Object> ejercio4 = new HashMap<>();
        Map<String,Object> ejercio5 = new HashMap<>();
        Map<String,Object> ejercio6 = new HashMap<>();
        Map<String,Object> ejercio7 = new HashMap<>();
        Map<String,Object> ejercio8 = new HashMap<>();

        //EJERCICIO 1
        ejercio1.put("finalidad","Calentamiento");
        ejercio1.put("tipo_de_ejercicio","Subir escaleras");
        ejercio1.put("intensidad","Normal");
        Map<String,Object> tiempo1 = new HashMap<>();
        tiempo1.put("minutos","" + "12");
        tiempo1.put("segundos","00");
        ejercio1.put("tiempo",tiempo1);

        //EJERCICIO 2
        ejercio2.put("finalidad","Calentamiento");
        ejercio2.put("tipo_de_ejercicio","caminata");
        ejercio2.put("intensidad","Normal");
        Map<String,Object> tiempo2 = new HashMap<>();
        tiempo2.put("minutos","30");
        tiempo2.put("segundos","00");
        ejercio2.put("tiempo",tiempo2);

        if (rEdad.equals("Si")){

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","8");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 3
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","8");
            ejercio4.put("tiempo",tiempo4);

        }
        else{

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","17");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 4
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","17");
            ejercio4.put("tiempo",tiempo4);
        }

        //EJERCICIO 5
        ejercio5.put("finalidad","Estiramiento");
        ejercio5.put("tipo_de_ejercicio","Cuello");
        ejercio5.put("intensidad","Normal");
        Map<String,Object> tiempo5 = new HashMap<>();
        tiempo5.put("minutos","00");
        tiempo5.put("segundos","30");
        ejercio5.put("tiempo",tiempo5);

        //EJERCICIO 6
        ejercio6.put("finalidad","Estiramiento");
        ejercio6.put("tipo_de_ejercicio","Espalda");
        ejercio6.put("intensidad","Normal");
        Map<String,Object> tiempo6 = new HashMap<>();
        tiempo6.put("minutos","00");
        tiempo6.put("segundos","30");
        ejercio6.put("tiempo",tiempo6);

        //EJERCICIO 7
        ejercio7.put("finalidad","Estiramiento");
        ejercio7.put("tipo_de_ejercicio","Miembros superiores");
        ejercio7.put("intensidad","Normal");
        Map<String,Object> tiempo7 = new HashMap<>();
        tiempo7.put("minutos","00");
        tiempo7.put("segundos","30");
        ejercio7.put("tiempo",tiempo7);

        //EJERCICIO 8
        ejercio8.put("finalidad","Estiramiento");
        ejercio8.put("tipo_de_ejercicio","Miembros inferiores");
        ejercio8.put("intensidad","Normal");
        Map<String,Object> tiempo8 = new HashMap<>();
        tiempo8.put("minutos","00");
        tiempo8.put("segundos","30");
        ejercio8.put("tiempo",tiempo8);

        Map<String,Object> ejercicios = new HashMap<>();
        ejercicios.put("fecha",fecha);
        ejercicios.put("ejercicio_1",ejercio1);
        ejercicios.put("ejercicio_2",ejercio2);
        ejercicios.put("ejercicio_3",ejercio3);
        ejercicios.put("ejercicio_4",ejercio4);
        ejercicios.put("ejercicio_5",ejercio5);
        ejercicios.put("ejercicio_6",ejercio6);
        ejercicios.put("ejercicio_7",ejercio7);
        ejercicios.put("ejercicio_8",ejercio8);


        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase

        mDatabase.child("Entrenamientos").child(id).setValue(ejercicios).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                }else{
                    Toast.makeText(FormularioInfoPersonal2.this,"No se pudo crear plan", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void planEntrenamientoMuyAlto(){


        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";


        //Base de datos Firebase
        Map<String,Object> ejercio1 = new HashMap<>();
        Map<String,Object> ejercio2 = new HashMap<>();
        Map<String,Object> ejercio3 = new HashMap<>();
        Map<String,Object> ejercio4 = new HashMap<>();
        Map<String,Object> ejercio5 = new HashMap<>();
        Map<String,Object> ejercio6 = new HashMap<>();
        Map<String,Object> ejercio7 = new HashMap<>();
        Map<String,Object> ejercio8 = new HashMap<>();

        //EJERCICIO 1
        ejercio1.put("finalidad","Calentamiento");
        ejercio1.put("tipo_de_ejercicio","Subir escaleras");
        ejercio1.put("intensidad","Normal");
        Map<String,Object> tiempo1 = new HashMap<>();
        tiempo1.put("minutos","" + "12");
        tiempo1.put("segundos","00");
        ejercio1.put("tiempo",tiempo1);

        //EJERCICIO 2
        ejercio2.put("finalidad","Calentamiento");
        ejercio2.put("tipo_de_ejercicio","caminata");
        ejercio2.put("intensidad","Normal");
        Map<String,Object> tiempo2 = new HashMap<>();
        tiempo2.put("minutos","30");
        tiempo2.put("segundos","00");
        ejercio2.put("tiempo",tiempo2);

        if (rEdad.equals("Si")){

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","2");
            tiempo3.put("repeticiones","8");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 3
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","2");
            tiempo4.put("repeticiones","8");
            ejercio4.put("tiempo",tiempo4);

        }
        else{

            //EJERCICIO 3
            ejercio3.put("finalidad","Fuerza");
            ejercio3.put("tipo_de_ejercicio","Subir los brazos en frontal");
            ejercio3.put("intensidad","2 libras");
            Map<String,Object> tiempo3 = new HashMap<>();
            tiempo3.put("series","3");
            tiempo3.put("repeticiones","15");
            ejercio3.put("tiempo",tiempo3);

            //EJERCICIO 4
            ejercio4.put("finalidad","Fuerza");
            ejercio4.put("tipo_de_ejercicio","Subir los brazos en lateral");
            ejercio4.put("intensidad","2 libras");
            Map<String,Object> tiempo4 = new HashMap<>();
            tiempo4.put("series","3");
            tiempo4.put("repeticiones","15");
            ejercio4.put("tiempo",tiempo4);
        }

        //EJERCICIO 5
        ejercio5.put("finalidad","Estiramiento");
        ejercio5.put("tipo_de_ejercicio","Cuello");
        ejercio5.put("intensidad","Normal");
        Map<String,Object> tiempo5 = new HashMap<>();
        tiempo5.put("minutos","00");
        tiempo5.put("segundos","30");
        ejercio5.put("tiempo",tiempo5);

        //EJERCICIO 6
        ejercio6.put("finalidad","Estiramiento");
        ejercio6.put("tipo_de_ejercicio","Espalda");
        ejercio6.put("intensidad","Normal");
        Map<String,Object> tiempo6 = new HashMap<>();
        tiempo6.put("minutos","00");
        tiempo6.put("segundos","30");
        ejercio6.put("tiempo",tiempo6);

        //EJERCICIO 7
        ejercio7.put("finalidad","Estiramiento");
        ejercio7.put("tipo_de_ejercicio","Miembros superiores");
        ejercio7.put("intensidad","Normal");
        Map<String,Object> tiempo7 = new HashMap<>();
        tiempo7.put("minutos","00");
        tiempo7.put("segundos","30");
        ejercio7.put("tiempo",tiempo7);

        //EJERCICIO 8
        ejercio8.put("finalidad","Estiramiento");
        ejercio8.put("tipo_de_ejercicio","Miembros inferiores");
        ejercio8.put("intensidad","Normal");
        Map<String,Object> tiempo8 = new HashMap<>();
        tiempo8.put("minutos","00");
        tiempo8.put("segundos","30");
        ejercio8.put("tiempo",tiempo8);

        Map<String,Object> ejercicios = new HashMap<>();
        ejercicios.put("fecha",fecha);
        ejercicios.put("ejercicio_1",ejercio1);
        ejercicios.put("ejercicio_2",ejercio2);
        ejercicios.put("ejercicio_3",ejercio3);
        ejercicios.put("ejercicio_4",ejercio4);
        ejercicios.put("ejercicio_5",ejercio5);
        ejercicios.put("ejercicio_6",ejercio6);
        ejercicios.put("ejercicio_7",ejercio7);
        ejercicios.put("ejercicio_8",ejercio8);


        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase

        mDatabase.child("Entrenamientos").child(id).setValue(ejercicios).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                }else{
                    Toast.makeText(FormularioInfoPersonal2.this,"No se pudo crear plan", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

