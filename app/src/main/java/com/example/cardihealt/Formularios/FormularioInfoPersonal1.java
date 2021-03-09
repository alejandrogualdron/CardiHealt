package com.example.cardihealt.Formularios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.cardihealt.MainActivity;
import com.example.cardihealt.R;
import com.example.cardihealt.Registro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormularioInfoPersonal1 extends AppCompatActivity implements View.OnClickListener{

     EditText etNombre, etApellido,etPeso,etAltura,etPerimetroAbdominal,etFechaN;
     Button btnAnterior, btnSiguiente;
     RadioButton hombre;

     String nombre = "";
     String apellido = "";
     String peso = "";
     String altura = "";
     String perimetroAbdominal = "";
     String fechaNacimiento = "";
     String genero="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal1);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);
        etPerimetroAbdominal = (EditText) findViewById(R.id.etPerimetroAbdominal);
        etFechaN=(EditText)findViewById(R.id.etFechaEdad);

        etFechaEdad();

        btnAnterior = (Button) findViewById(R.id.btnAnteriorFormul);
        btnSiguiente = (Button) findViewById(R.id.btnSiguienteFormul1);

        btnSiguiente.setOnClickListener(this);
        btnAnterior.setOnClickListener(this);
        hombre = (RadioButton) findViewById(R.id.RegRadbtnHombre);

        genero();
        nombre =etNombre.getText().toString();
        apellido =etApellido.getText().toString();
        peso =etPeso.getText().toString();
        altura =etAltura.getText().toString();
        perimetroAbdominal =etPerimetroAbdominal.getText().toString();
        nombre =etNombre.getText().toString();
        fechaNacimiento=etFechaN.getText().toString();


    }

    public void genero(){
        if(hombre.isChecked() == true){
           genero="Hombre";
        }else{
            genero="Mujer";
        }
    }

    public void etFechaEdad(){
        etFechaN.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    etFechaN.setText(current);
                    etFechaN.setSelection(sel < current.length() ? sel : current.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*public void calcEdad(){

        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";
        String[] parts = fecha.split("/");
        int mesActual = Integer.parseInt(parts[1]);
        int añoActual= Integer.parseInt(parts[2]);
        int años=0;

        if(!etEdad.getText().toString().equals("")){
            //Fecha nacimiento
            String fechaNacimiento= etEdad.getText().toString();
            String[] parts1 = fechaNacimiento.split("/");
            int mesN = Integer.parseInt(parts1[1]);
            int añoN= Integer.parseInt(parts1[2]);

            if(mesActual<mesN){
                años =añoN-añoActual-1;
            }else {
                años= añoN-añoActual;
            }
            edad = años+"";
        }
    }*/

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnAnteriorFormul:

                i = new Intent(FormularioInfoPersonal1.this, FormularioInfoPersonal.class);
                startActivity(i);

                break;

            case R.id.btnSiguienteFormul1:

                i = new Intent(FormularioInfoPersonal1.this, FormularioInfoPersonal2.class);
                startActivity(i);

                break;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltura() {
        return altura;
    }

    public String getPerimetroAbdominal() {
        return perimetroAbdominal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }



}