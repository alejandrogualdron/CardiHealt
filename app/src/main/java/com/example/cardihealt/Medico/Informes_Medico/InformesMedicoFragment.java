package com.example.cardihealt.Medico.Informes_Medico;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardihealt.Informacion.Informacion;
import com.example.cardihealt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformesMedicoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformesMedicoFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton mensaje;
    String email;

    public InformesMedicoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformesMedicoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformesMedicoFragment newInstance(String param1, String param2) {
        InformesMedicoFragment fragment = new InformesMedicoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize view
        View view=inflater.inflate(R.layout.fragment_informes_medico, container, false);


        //Initialize and assign variable
        TextView dato1=view.findViewById(R.id.dato1);
        TextView dato2=view.findViewById(R.id.dato2);
        TextView dato3=view.findViewById(R.id.dato3);
        TextView dato4=view.findViewById(R.id.dato4);
        TextView dato5=view.findViewById(R.id.dato5);

        mensaje = view.findViewById(R.id.mensaje);
        mensaje.setOnClickListener( this);

        //Get title
        String dato1S=getArguments().getString("dato1");
        String dato2S=getArguments().getString("dato2");
        String dato3S=getArguments().getString("dato3");
        String dato4S=getArguments().getString("dato4");
        String dato5S=getArguments().getString("dato5");
        email = getArguments().getString("email");

        //set Title
        dato1.setText(dato1S);
        dato2.setText(dato2S);
        dato3.setText(dato3S);
        dato4.setText(dato4S);
        dato5.setText(dato5S);


        //returnView
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mensaje:
                Intent i;
                i = new Intent(getContext(), EnvioCorreo.class);
                i.putExtra("email",email);
                startActivity(i);

                break;
        }

    }
}