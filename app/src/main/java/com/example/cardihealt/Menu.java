package com.example.cardihealt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardihealt.Formularios.FormularioInfoPersonal1;
import com.example.cardihealt.Informes.Informe;
import com.example.cardihealt.Recomendaciones.Recomendaciones;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Menu extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnInforme, btnEntrenamiento,btnActualizar,btnCerrarSesion;
    TextView nombre,apellido;
    String nombreS,apellidoS;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        nombre=(TextView)findViewById(R.id.etNameUser);
        apellido=(TextView)findViewById(R.id.etApellidoUser);

        btnInforme = (ImageButton) findViewById(R.id.informe);
        btnEntrenamiento = (ImageButton) findViewById(R.id.entrenamiento);
        btnActualizar = (ImageButton) findViewById(R.id.actualizar);
        btnCerrarSesion = (ImageButton) findViewById(R.id.cerrarsesion);
        nombreApellido();
        btnInforme.setOnClickListener(this);
        btnEntrenamiento.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);
    }

    //Se controla el boton atrás

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de CardiHealt?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.informe:
                i = new Intent(Menu.this, Informe.class);
                startActivity(i);
                break;

            case R.id.entrenamiento:
                i = new Intent(Menu.this, Recomendaciones.class);
                startActivity(i);
                break;

            case R.id.actualizar:
                i = new Intent(Menu.this, FormularioInfoPersonal1.class);
                startActivity(i);
                break;

            case R.id.cerrarsesion:
                i = new Intent(Menu.this, Informe.class);
                startActivity(i);
                break;
        }
    }

    public void nombreApellido(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Informes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Obtiene valores de la base de datos
                    nombreS = snapshot.child("nombre").getValue().toString();
                    apellidoS = snapshot.child("apellido").getValue().toString();

                    nombre.setText(nombreS);
                    apellido.setText(apellidoS);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}