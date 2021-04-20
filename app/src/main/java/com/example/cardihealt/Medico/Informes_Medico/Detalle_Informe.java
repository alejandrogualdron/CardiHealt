package com.example.cardihealt.Medico.Informes_Medico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.cardihealt.Informes.InfoFragment;
import com.example.cardihealt.Informes.InformeFinal;
import com.example.cardihealt.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Detalle_Informe extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private String dato1;
    private String dato2;
    private String dato3;
    private String dato4;
    private String dato5;
    private InformesUsuario itemDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__informe);

        //assign variable
        tabLayout=findViewById(R.id.tab_detalles);
        viewPager=findViewById(R.id.view_detalles);

        //Initialize array list
        ArrayList<String> arrayList= new ArrayList<>();

        //add title in array list
        arrayList.add("Infrormación Personal");
        arrayList.add("Estado Actual");
        arrayList.add("Riesgo");

        //prepare view pager
        prepareViewPager(viewPager,arrayList);

        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);
    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        //Initialize main adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        //initialize main fragment
        InformesMedicoFragment fragment= new InformesMedicoFragment();

        //use for loop
        for (int i=0;i<arrayList.size();i++){

            //initialize bundle
            Bundle bundle= new Bundle();

            //put String
            bundle.putString("dato1", editarText1(arrayList.get(i)));
            bundle.putString("dato2",editarText2(arrayList.get(i)));
            bundle.putString("dato3",editarText3(arrayList.get(i)));
            bundle.putString("dato4",editarText4(arrayList.get(i)));
            bundle.putString("dato5",editarText5(arrayList.get(i)));

            //set Argument
            fragment.setArguments(bundle);
            //Add fragment
            adapter.addFragment(fragment,arrayList.get(i));
            //define new fragment
            fragment=new InformesMedicoFragment();
        }
        //set adapter
        viewPager.setAdapter(adapter);
    }

    public String editarText1(String titulo){

        if(titulo.equals("Infrormación Personal")){
            dato1= "Nombre: "+getIntent().getStringExtra("nombre");;
        }
        if(titulo.equals("Estado Actual")){
            dato1= "Estado fisico: "+getIntent().getStringExtra("contextura");
        }
        if(titulo.equals("Riesgo")){
            dato1= "Riesgo por edad: "+getIntent().getStringExtra("riesgoEdad");
        }

        return dato1;
    }

    public String editarText2(String titulo){

        if(titulo.equals("Infrormación Personal")){
            dato2= "Apellido: "+getIntent().getStringExtra("apellido");
        }
        if(titulo.equals("Estado Actual")){
            dato2= "Indice Tabaquico: "+ getIntent().getStringExtra("indiceTabaquico");
        }
        if(titulo.equals("Riesgo")){
            dato2= "Riesgo de EPOC: "+getIntent().getStringExtra("riesgoEpoc");
        }

        return dato2;
    }

    public String editarText3(String titulo){

        if(titulo.equals("Infrormación Personal")){
            dato3="Edad: "+ getIntent().getStringExtra("edad")+" años";
        }
        if(titulo.equals("Estado Actual")){
            String sCadena = getIntent().getStringExtra("perimetroAbdominal");
            String sSubCadena = sCadena.substring(0,4);
            dato3= "Perimetro Abdominal: "+ sSubCadena;
        }
        if(titulo.equals("Riesgo")){
            dato3= "Riesgo por Perimetro Abdominal: "+getIntent().getStringExtra("riesgoPerimetroAbd");
        }

        return dato3;
    }

    public String editarText4(String titulo){

        if(titulo.equals("Infrormación Personal")){
            dato4= "Actividad fisica: "+getIntent().getStringExtra("actividadFisica");
        }

        if(titulo.equals("Estado Actual")){
            String sCadena = getIntent().getStringExtra("indiceMasaCorporal");
            String sSubCadena = sCadena.substring(0,4);
            dato4= "Indice Masa Corporal: "+ sSubCadena;
        }
        if(titulo.equals("Riesgo")){
            dato4= "Riesgo estimado: "+getIntent().getStringExtra("riesgoEstimado");
        }
        return dato4;
    }

    public String editarText5(String titulo){

        if(titulo.equals("Infrormación Personal")){
            dato5= "Riesgo por antecedentes: "+getIntent().getStringExtra("riesgoEstimado");
        }
        if(titulo.equals("Estado Actual")){
            dato5= "Enfermedades base: "+ getIntent().getStringExtra("riesgoGenetico");
        }
        if(titulo.equals("Riesgo")){
            dato5= "Posee un riesgo: "+getIntent().getStringExtra("riesgo");
        }
        return dato5;
    }


    private class MainAdapter extends FragmentPagerAdapter {
        //Initialize array list
        ArrayList<String> arrayList=new ArrayList<>();
        List<Fragment> fragmentList=new ArrayList<>();

        public void addFragment(Fragment fragment,String title){
            //Add Title
            arrayList.add(title);
            //Add Fragment
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //return fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //return fragment list size
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return arrayList position
            return arrayList.get(position);
        }
    }


}