package com.example.cardihealt.Medico;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cardihealt.Informacion.Informacion;
import com.example.cardihealt.Informes.Informe;
import com.example.cardihealt.LoginSingup.Login;
import com.example.cardihealt.Medico.Informes_Medico.InformesUsuario;
import com.example.cardihealt.Medico.Informes_Medico.MostrarInformes;
import com.example.cardihealt.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragmentMedico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragmentMedico extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CardView cardInformes,cardRecomendacionesMedico,cardCerrarsesion,cardEncuesta,cardInformacion;
    View vista;

    Activity activity;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    public InicioFragmentMedico() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragmentMedico.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragmentMedico newInstance(String param1, String param2) {
        InicioFragmentMedico fragment = new InicioFragmentMedico();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_inicio_medico, container, false);

        cardInformes=vista.findViewById(R.id.informesUsuarios);
        cardInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getContext(), MostrarInformes.class);
                startActivity(i);

            }
        });

        cardCerrarsesion=vista.findViewById(R.id.cerrarsesionMedico);
        cardCerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cerrar cesion
                mAuth.signOut();
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Abrir MainActivity con SigIn button
                        if(task.isSuccessful()){
                            Intent mainActivity = new Intent(getContext(), Login.class);
                            startActivity(mainActivity);

                        }else{
                            Toast.makeText(getContext(), "No se pudo cerrar sesión con google",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        cardEncuesta=vista.findViewById(R.id.encuestaMedico);
        cardEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                Uri uri= Uri.parse("https://forms.gle/gTv2bJMmcZZeugeh8");
                i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);

            }
        });


        cardInformacion=vista.findViewById(R.id.infoMedico);
        cardInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getContext(), Informacion.class);
                startActivity(i);

            }
        });

        return vista;
    }
}