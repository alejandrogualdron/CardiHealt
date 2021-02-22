package com.example.cardihealt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText user, pass;
    private Button btnEntrar, btnRegistrar,btnGmail,btnFacebook;

    private String usuario = "";
    private String contraseña = "";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.userLogin);
        pass = (EditText) findViewById(R.id.passwordLogin);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnGmail=(Button) findViewById(R.id.btnGmail);
        btnFacebook=(Button) findViewById(R.id.btnFacebook);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        btnGmail.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;
        usuario = user.getText().toString();
        contraseña = pass.getText().toString();
        switch (v.getId()) {

            case R.id.btnEntrar:

                if ((!usuario.equals("")) && (!contraseña.equals(""))) {
                    /*
                    if(usuario.equals("administrador@gmail.com") && contraseña.equals("123456")){
                        Toast.makeText(this,"Administrador", Toast.LENGTH_LONG).show();
                        loginAdmin();
                    }else
                        loginUser();*/
                } else {
                    Toast.makeText(this, "Error: Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnRegistrar:

                i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
                break;

            case R.id.btnFacebook:


                break;
            case R.id.btnGmail:


                break;
        }
    }
}