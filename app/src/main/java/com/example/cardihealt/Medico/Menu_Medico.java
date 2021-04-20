package com.example.cardihealt.Medico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.cardihealt.R;

public class Menu_Medico extends AppCompatActivity {
    Fragment fragmentInicioMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__medico);
        fragmentInicioMedico=new InicioFragmentMedico();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsMedico,fragmentInicioMedico).commit();
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