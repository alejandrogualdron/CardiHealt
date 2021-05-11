package com.example.cardihealt.UsarioMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

import com.example.cardihealt.Formularios.FormularioInfoPersonal;
import com.example.cardihealt.Formularios.FormularioInfoPersonal1;
import com.example.cardihealt.Formularios.FormularioInfoPersonal2;
import com.example.cardihealt.R;
import com.example.cardihealt.UsarioMenu.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Menu extends AppCompatActivity   {

    Fragment fragmentInicio;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fragmentInicio=new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
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




}