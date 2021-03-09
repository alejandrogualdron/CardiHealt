package com.example.cardihealt.Formularios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardihealt.MainActivity;
import com.example.cardihealt.R;
import com.example.cardihealt.Registro;

public class FormularioInfoPersonal extends AppCompatActivity implements View.OnClickListener  {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_info_personal);

        btnNext = (Button) findViewById(R.id.btnSiguienteFormul);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.btnSiguienteFormul:
                i = new Intent(FormularioInfoPersonal.this, FormularioInfoPersonal1.class);
                startActivity(i);
                break;
        }
    }

    }
