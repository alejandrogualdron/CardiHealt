package com.example.cardihealt.Formularios;

import androidx.annotation.NonNull;
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
import com.example.cardihealt.R;
import com.example.cardihealt.UsarioMenu.Menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormularioInfoPersonal extends AppCompatActivity implements View.OnClickListener  {

    EditText etNombre, etApellido,etFechaN;
    ImageButton  btnSiguiente;
    RadioButton hombre,siG,mujer,noG;
    String nombre = "";
    String apellido = "";
    String fechaNacimiento = "";
    String genero="";
    String edad="";
    String genetica="";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etFechaN=(EditText)findViewById(R.id.etFechaEdad);
        siG = (RadioButton) findViewById(R.id.RegRadbtnSiG);
        noG = (RadioButton) findViewById(R.id.RegRadbtnNoG);
        hombre = (RadioButton) findViewById(R.id.RegRadbtnHombre);
        mujer = (RadioButton) findViewById(R.id.RegRadbtnMujer);

        etFechaEdad();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        btnSiguiente = (ImageButton) findViewById(R.id.btnSiguienteFormul);
        btnSiguiente.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.btnSiguienteFormul:

                genero();
                calcEdad();
                genetica();

                nombre = etNombre.getText().toString();
                apellido = etApellido.getText().toString();
                nombre = etNombre.getText().toString();
                fechaNacimiento = etFechaN.getText().toString();

                if (nombre.equals("")){
                    Toast.makeText(FormularioInfoPersonal.this, "Ingrese su nombre", Toast.LENGTH_LONG).show();
                }else if (apellido.equals("")){
                Toast.makeText(FormularioInfoPersonal.this, "Ingrese su apellido", Toast.LENGTH_LONG).show();
                }else if (fechaNacimiento.equals("")){
                Toast.makeText(FormularioInfoPersonal.this, "Ingrese su fecha de nacimiento", Toast.LENGTH_LONG).show();
                }else if(hombre.isChecked() == false&&mujer.isChecked() == false){
                    Toast.makeText(FormularioInfoPersonal.this, "Seleccione su genero", Toast.LENGTH_LONG).show();
                }else if(siG.isChecked() == false&&noG.isChecked() == false){
                    Toast.makeText(FormularioInfoPersonal.this, "Complete toda la información", Toast.LENGTH_LONG).show();
                }else{
                crearDB();

                Intent intent = new Intent(FormularioInfoPersonal.this, FormularioInfoPersonal1.class);
                startActivity(intent);
            }

                break;
        }
    }

    public void genero(){
        if(hombre.isChecked() == true){
            genero="Hombre";
        }else{
            genero="Mujer";
        }
    }

    public void genetica() {
        if (siG.isChecked() == true) {
            genetica = "Si";
        } else {
            genetica = "No";
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

    public void calcEdad(){

        //Fecha actual
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date) + "";
        String[] parts = fecha.split("/");
        int mesActual = Integer.parseInt(parts[1]);
        int añoActual= Integer.parseInt(parts[2]);
        int años=0;

        if(!etFechaN.getText().toString().equals("")){
            //Fecha nacimiento
            String fechaNacimiento= etFechaN.getText().toString();
            String[] parts1 = fechaNacimiento.split("/");
             int mesN = Integer.parseInt(parts1[1]);
            int añoN= Integer.parseInt(parts1[2]);

            if(mesActual<mesN){
                años =añoActual-añoN-1;
            }else {
                años= añoActual-añoN;
            }
            edad = años+"";
        }
    }

    public void crearDB(){
        FirebaseUser user = mAuth.getCurrentUser();
        String  email;
        email = user.getEmail();

        //Base de datos Firebase
        Map<String,Object> map = new HashMap<>();
        map.put("nombre",nombre);
        map.put("apellido",apellido);
        map.put("edad",edad);
        map.put("genero",genero);
        map.put("genetica",genetica);
        map.put("email",email);

        String id= mAuth.getCurrentUser().getUid(); // Obtiene id que da firebase

        mDatabase.child("Usuario").child(id).setValue(map);
     /*  mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    if(!nombre.isEmpty()||!apellido.isEmpty()||!edad.isEmpty()||!genero.isEmpty()){
                        Intent intent=new Intent(FormularioInfoPersonal.this, FormularioInfoPersonal1.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(FormularioInfoPersonal.this,"Complete la informacion", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(FormularioInfoPersonal.this,"No se pudo registrar", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }



}
