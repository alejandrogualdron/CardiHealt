package com.example.cardihealt.LoginSingup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.cardihealt.Formularios.FormularioInfoPersonal;
import com.example.cardihealt.Medico.Menu_Medico;
import com.example.cardihealt.UsarioMenu.Menu;
import com.example.cardihealt.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText user, pass;
    private Button btnEntrar, btnRegistrar;
    private ImageButton btnGmail;

    private String usuario = "";
    private String contraseña = "";

    private FirebaseAuth firebaseAuth;
    DatabaseReference nDatabase;

    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignInLoginActivity";
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        nDatabase= FirebaseDatabase.getInstance().getReference();

        user = (EditText) findViewById(R.id.userLogin);
        pass = (EditText) findViewById(R.id.passwordLogin);

        btnEntrar = (Button) findViewById(R.id.btnIniciarCronometro);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnGmail = (ImageButton) findViewById(R.id.btnGmail);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        btnGmail.setOnClickListener(this);
        googlesSignInOptions();
    }

    public void googlesSignInOptions(){
        // Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Crear un GoogleSignInClient con las opciones especificadas por gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In fallido, actualizar GUI
                    Log.w(TAG, "Google sign in failed", e);
                }
            } else {
                Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
                Toast.makeText(this, "Cancelado",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            if (user != null) { //si no es null el usuario ya esta logueado
                                String id= firebaseAuth.getCurrentUser().getUid();
                                nDatabase.child("Informes").child(id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot)
                                    {
                                        if(snapshot.exists()){
                                            Intent dashboardActivity = new Intent(Login.this, Menu.class);
                                            startActivity(dashboardActivity);
                                        }else{

                                                Intent dashboardActivity = new Intent(Login.this, FormularioInfoPersonal.class);
                                                startActivity(dashboardActivity);
                                            }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        FirebaseUser user = firebaseAuth.getCurrentUser();
    }

    private void entrar() {
            usuario = user.getText().toString().trim();
            contraseña = pass.getText().toString().trim();

            if(TextUtils.isEmpty(usuario)){
                Toast.makeText(this,"Ingrese un email",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(contraseña)){
                Toast.makeText(this,"Ingrese una contraseña",Toast.LENGTH_LONG).show();
                return;
            }

            //Login de usuario
            firebaseAuth.signInWithEmailAndPassword(usuario,contraseña)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                    String id= firebaseAuth.getCurrentUser().getUid();
                                    nDatabase.child("Informes").child(id).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()){
                                                Intent dashboardActivity = new Intent(Login.this, Menu.class);
                                                startActivity(dashboardActivity);
                                            }else{
                                                //Ingresar medico
                                                if (usuario.equals("medico@gmail.com")){
                                                    Intent dashboardActivity = new Intent(Login.this, Menu_Medico.class);
                                                    startActivity(dashboardActivity);
                                                }else{
                                                    Intent dashboardActivity = new Intent(Login.this, FormularioInfoPersonal.class);
                                                    startActivity(dashboardActivity);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                            }else{
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){ //Si existe el usuario
                                    Toast.makeText(Login.this,"El usuario ya existe",Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(Login.this,"No se pudo realizar el registro",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }

    @Override
    public void onClick(View v) {

        Intent i;
        usuario = user.getText().toString();
        contraseña = pass.getText().toString();
        switch (v.getId()) {

            case R.id.btnIniciarCronometro:

                entrar();

                break;

            case R.id.btnRegistrar:

                i = new Intent(Login.this, Registro.class);
                startActivity(i);
                break;

            case R.id.btnGmail:
                signIn();
                break;
        }
    }

}
