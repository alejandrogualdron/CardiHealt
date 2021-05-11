
package com.example.cardihealt.Entrenamiento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cardihealt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Informacion_Entrenamiento extends AppCompatActivity implements View.OnClickListener {
    String dato,ejercicio,tipo_ejercicioS,intensidadS,tiempoS,descripcionS;
    String minutos="";
    String segundos="";
    String repeticiones="";
    String series="";
    TextView finalidad,tipo_ejercicio,intensidad,tiempo,descripcion;
    private Button btnCronometro;
    ImageView imagen_entrenamiento;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion__entrenamiento);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        finalidad=(TextView)findViewById(R.id.finalidad);
        tipo_ejercicio=(TextView)findViewById(R.id.tipo_ejercicio);
        intensidad=(TextView)findViewById(R.id.intensidad);
        tiempo=(TextView)findViewById(R.id.tiempo);
        descripcion=(TextView)findViewById(R.id.descripcion);
        imagen_entrenamiento=(ImageView)findViewById(R.id.imagen_entrenamiento);
        btnCronometro=(Button)findViewById(R.id.btnIniciarCronometro);

        btnCronometro.setOnClickListener(this);
        dato= getIntent().getStringExtra("dato");
        ejercicios();
    }

    public void ejercicios() {
        String id = mAuth.getCurrentUser().getUid();

        //**** EJERCICIO 1 ****///
        if (dato.equals("Ejercicio 1")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_1").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.calentamiento_subir_escalera);
                                }
                            }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 2 ****///
        if (dato.equals("Ejercicio 2")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_2").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.calentamiento_trotar);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 3 ****///
        if (dato.equals("Ejercicio 3")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_3").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    repeticiones=snapshot.child("repeticiones").getValue().toString();
                                    series=snapshot.child("series").getValue().toString();
                                    tiempoS = series+" series"+" de "+repeticiones +" repeticiones";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.fuerza_brazos_frontal);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 4 ****///
        if (dato.equals("Ejercicio 4")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_4").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    repeticiones=snapshot.child("repeticiones").getValue().toString();
                                    series=snapshot.child("series").getValue().toString();
                                    tiempoS = series+" series"+" de "+repeticiones +" repeticiones";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.fuerza_brazos_lateral);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 5 ****///
        if (dato.equals("Ejercicio 5")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_5").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_5").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.estiramiento_cuello);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 6 ****///
        if (dato.equals("Ejercicio 6")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_6").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_6").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.estiramiento_espalda);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 7 ****///
        if (dato.equals("Ejercicio 7")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_7").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_7").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.estiramiento_miembros_superiores);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        //**** EJERCICIO 8 ****///
        if (dato.equals("Ejercicio 8")) {
            mDatabase.child("Entrenamientos").child(id).child("ejercicio_8").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        //Obtiene valores de la base de datos
                        ejercicio = snapshot.child("finalidad").getValue().toString();
                        tipo_ejercicioS = snapshot.child("tipo_de_ejercicio").getValue().toString();
                        intensidadS = snapshot.child("intensidad").getValue().toString();
                        mDatabase.child("Entrenamientos").child(id).child("ejercicio_8").child("tiempo").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    minutos=snapshot.child("minutos").getValue().toString();
                                    segundos=snapshot.child("segundos").getValue().toString();
                                    tiempoS = minutos+" minutos"+" con "+segundos +" segundos";
                                    finalidad.setText(ejercicio);
                                    tipo_ejercicio.setText(tipo_ejercicioS);
                                    intensidad.setText(intensidadS);
                                    tiempo.setText(tiempoS);
                                    descripcionS=descripcion(ejercicio);
                                    descripcion.setText(descripcionS);
                                    imagen_entrenamiento.setImageResource(R.drawable.estiramiento_miembros_inferiores);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }

    public String descripcion(String finalidad){
        String descripcionMethod="";
        if (finalidad.equals("Calentamiento")){
            descripcionMethod="El calentamiento previo a actividades deportivas nos ayudara a prevenir" +
                    "lesiones como desgarros, esguinces y torceduras, tambien a ampliar los movimientos de las articulaciones" +
                    "y a aumentarla frecuencia cardiaca y respiratoria";
        }
        if (finalidad.equals("Fuerza")){
            descripcionMethod="Los ejercicios de fuerza mejoran la densidad ósea, disminuyendo así " +
                    "el posible riesgo de osteoporosis o fracturas y protegiendo a la vez nuestras articulaciones." +
                    " Además, logramos prevenir lesiones, ya que músculos, tendones y ligamentos tienen menos riesgo de dañarse," +
                    " pudiendo resistir trabajos con mayor intensidad";
        }
        if (finalidad.equals("Estiramiento")){
            descripcionMethod="El estiramiento disminuye la rigidez muscular y aumenta el rango de movimiento, es decir la flexibilidad" +
                    "tambien reduce el riesgo de lesiones.";
        }

        return descripcionMethod;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.btnIniciarCronometro:

                if(ejercicio.equals("Fuerza")){
                    i = new Intent(Informacion_Entrenamiento.this, Cronometro.class);
                    i.putExtra("tipoE",tipo_ejercicioS);
                    i.putExtra("ejercicio",tiempoS);
                    startActivity(i);
                }
                if(ejercicio.equals("Calentamiento")||ejercicio.equals("Estiramiento")){
                    i = new Intent(Informacion_Entrenamiento.this, Count_Down.class);
                    i.putExtra("minutos",minutos);
                    i.putExtra("segundos",segundos);
                    i.putExtra("tipoE",tipo_ejercicioS);
                    startActivity(i);
                }

                break;

        }


    }
}
