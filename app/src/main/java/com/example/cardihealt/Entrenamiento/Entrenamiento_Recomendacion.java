package com.example.cardihealt.Entrenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cardihealt.LoginSingup.Login;
import com.example.cardihealt.LoginSingup.Registro;
import com.example.cardihealt.R;

public class Entrenamiento_Recomendacion extends AppCompatActivity implements View.OnClickListener {
    private Button btnEntrenamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento__recomendacion);
        btnEntrenamiento = (Button) findViewById(R.id.entrenamientobtn);
        btnEntrenamiento.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.entrenamientobtn:

                i = new Intent(Entrenamiento_Recomendacion.this, Entrenamiento.class);
                startActivity(i);
                break;

        }
    }
}