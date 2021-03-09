package com.example.cardihealt.Formularios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.cardihealt.R;

public class FormularioInfoPersonal2 extends AppCompatActivity implements View.OnClickListener {

    Button btnAnterior1, btnSiguiente1;
    EditText numeroC,añosF;
    RadioButton si;
    CheckBox dormir,verTv,leer,planchar,conducir,cocinar,trotar,saltarC,cicla,caminarL;
    String numeroCigarrillos,añosFumador;
    int energiaC;
    String actividadF="" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal2);

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

                i = new Intent(FormularioInfoPersonal2.this, FormularioInfoPersonal3.class);
                startActivity(i);

                break;
        }
    }

}
