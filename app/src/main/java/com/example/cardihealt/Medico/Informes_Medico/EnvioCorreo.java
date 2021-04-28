package com.example.cardihealt.Medico.Informes_Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cardihealt.R;

public class EnvioCorreo extends AppCompatActivity implements View.OnClickListener {

    private Button btnEnviarCorreo;
    EditText tvMensajeoEnvio;
    TextView asunto,tvCorreo;
    String correoEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_correo);
        correoEnvio= getIntent().getStringExtra("email");


        btnEnviarCorreo = (Button) findViewById(R.id.btnConfirmar);
        btnEnviarCorreo.setOnClickListener( this);

        tvCorreo = (TextView) findViewById(R.id.tvCorreoEnvio);
        tvCorreo.setOnClickListener( this);
        tvCorreo.setText(correoEnvio);

        asunto = (TextView) findViewById(R.id.asunto);
        asunto.setOnClickListener( this);

        tvMensajeoEnvio = (EditText) findViewById(R.id.tvMensajeoEnvio);
        tvMensajeoEnvio.setOnClickListener( this);



    }

    private void sendMail() {

        String asuntoEn= asunto.getText().toString();
        String message= tvMensajeoEnvio.getText().toString();

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,correoEnvio);
        intent.putExtra(Intent.EXTRA_SUBJECT,asuntoEn);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"choose an email client"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnConfirmar:
                sendMail();
                break;
        }
    }
}