package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class FormularioInfoPersonal1 extends AppCompatActivity implements View.OnClickListener{

     EditText etPeso,etAltura,etCadera,etCintura, numeroC,añosF;
     CheckBox cbCianosis, cbColesterol, cbHipertension, cbDiabetes;
     RadioButton si,no;
     ImageButton btnSiguiente;
     String peso = "";
     String altura = "";
     String numeroCigarrillos="";
     String añosFumador="";
     String fuma="";
     String cintura="";
     String cadera="";
     String cianosis="";
     String colesterol="";
     String hipertension="";
     String diabetes="";



    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal1);

        si = (RadioButton) findViewById(R.id.RegRadbtnSi);
        no = (RadioButton) findViewById(R.id.RegRadbtnNo);

        numeroC=(EditText)findViewById(R.id.etNumeroCigarrillos);
        añosF=(EditText)findViewById(R.id.etAñofumador);


        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);
        etCadera = (EditText) findViewById(R.id.cintura);
        etCintura = (EditText) findViewById(R.id.cadera);

        cbCianosis = (CheckBox) findViewById(R.id.cbCianosis);
        cbColesterol = (CheckBox) findViewById(R.id.cbColesterol);
        cbHipertension = (CheckBox) findViewById(R.id.cbHipertension);
        cbDiabetes = (CheckBox) findViewById(R.id.cbDiabetes);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSiguiente = (ImageButton) findViewById(R.id.btnSiguienteFormul1);

        btnSiguiente.setOnClickListener(this);

    }


    public void crearDB(){
        //Base de datos Firebase
        Map<String,Object> map = new HashMap<>();
        map.put("fumador",fuma);
        map.put("años de fumador",añosFumador);
        map.put("numero de cigarrillos",numeroCigarrillos);
        map.put("peso",peso);
        map.put("altura",altura);
        map.put("cintura",cintura);
        map.put("cadera",cadera);
        map.put("cianosis", cianosis);
        map.put("colesterol", colesterol);
        map.put("diabetes", diabetes);
        map.put("hipertension", hipertension);


        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase
        mDatabase.child("Usuario").child(id).updateChildren(map);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnSiguienteFormul1:

                if(si.isChecked()==true||no.isChecked()==true){
                    fumador();
                    numeroCigarrillos=numeroC.getText().toString();
                    añosFumador=añosF.getText().toString();
                    peso = etPeso.getText().toString();
                    altura = etAltura.getText().toString();
                    cadera = etCadera.getText().toString();
                    cintura = etCintura.getText().toString();

                    if(fuma.equals("Fumador")&& numeroCigarrillos.equals("")|| fuma.equals("Fumador")&&añosFumador.equals("")){
                        Toast.makeText(FormularioInfoPersonal1.this,"Complete la informacion", Toast.LENGTH_LONG).show();
                    }else {
                        cianosis();
                        colesterol();
                        diabetes();
                        hipertension();
                        crearDB();

                        Intent intent=new Intent(FormularioInfoPersonal1.this, FormularioInfoPersonal2.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(FormularioInfoPersonal1.this,"Complete la informacion", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    public void fumador(){
        if(si.isChecked() == true){
            fuma="Si";
        }else{
            fuma="No";
        }
    }

    public void cianosis() {
        if (cbCianosis.isChecked() == true) {
            cianosis = "Si";
        } else {
            cianosis = "No";
        }
    }

    public void colesterol() {
        if (cbColesterol.isChecked() == true) {
            colesterol = "Si";
        } else {
            colesterol = "No";
        }
    }

    public void diabetes() {
        if (cbDiabetes.isChecked() == true) {
            diabetes = "Si";
        } else {
            diabetes = "No";
        }
    }

    public void hipertension() {
        if (cbHipertension.isChecked() == true) {
            hipertension = "Si";
        } else {
            hipertension = "No";
        }
    }

}