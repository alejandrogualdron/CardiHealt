package com.example.cardihealt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cardihealt.Formularios.FormularioInfoPersonal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private EditText userReg, passReg;
    private Button btnAtrasReg, btnRegistrarReg;

    private String usuario = "";
    private String contraseña = "";

    private FirebaseAuth firebaseAuth;
    DatabaseReference nDatabase;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        nDatabase= FirebaseDatabase.getInstance().getReference();

        userReg = (EditText) findViewById(R.id.userReg);
        passReg = (EditText) findViewById(R.id.passwordReg);
        btnAtrasReg = (Button) findViewById(R.id.btnRegAtras);
        btnRegistrarReg = (Button) findViewById(R.id.btnRegRegistrar);

        progressDialog=new ProgressDialog(this);

        btnAtrasReg.setOnClickListener(this);
        btnRegistrarReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.btnRegRegistrar:
                registrarUsuario();
                break;

            case R.id.btnRegAtras:

                i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
                break;
        }
    }

    private void registrarUsuario(){
        usuario = userReg.getText().toString().trim();
        contraseña = passReg.getText().toString().trim();

        if(TextUtils.isEmpty(usuario)){
            Toast.makeText(this,"Ingrese un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contraseña)){
            Toast.makeText(this,"Ingrese una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro Online...");
        progressDialog.show();
        //Crea usuario
        firebaseAuth.createUserWithEmailAndPassword(usuario,contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent=new Intent(getApplication(), FormularioInfoPersonal.class);
                            startActivity(intent);
                            finish();

                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){ //Si existe el usuario
                                Toast.makeText(Registro.this,"El usuario ya existe",Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Registro.this,"No se pudo realizar el registro",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }


}