package com.example.cardihealt.Entrenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardihealt.R;

public class Count_Down extends AppCompatActivity implements View.OnClickListener {
    TextView cronometro,descripcion,ejercicio;
    Button iniciar, detener, reset;
    String minData,descripcionS,ejercicioS,segData,time;
    CountDownTimer temporizador;
    boolean relojCorriendo;
    int min;
    int seg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        iniciar=(Button) findViewById(R.id.btnIniciarCronometro);
        detener=(Button) findViewById(R.id.btnStop);
        reset=(Button) findViewById(R.id.btnReset);
        cronometro=(TextView)findViewById(R.id.tvCronometro);
        descripcion=(TextView)findViewById(R.id.descripcion_cronometro);
        ejercicio=(TextView)findViewById(R.id.ejercicio_cronometro);

        ejercicioS=getIntent().getStringExtra("tipoE");


        minData= getIntent().getStringExtra("minutos");
        segData= getIntent().getStringExtra("segundos");
        time=minData+" : "+segData;
        cronometro.setText(time);


        descripcionS="Realice los ejercicios a su ritmo";
        descripcion.setText(descripcionS);
        ejercicio.setText(ejercicioS);

        min=(Integer.valueOf(minData)*60)*1000;
        seg=Integer.valueOf(segData)*1000;

        iniciar.setOnClickListener(this);
        detener.setOnClickListener(this);
        reset.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.btnIniciarCronometro:
                if(relojCorriendo==false){
                    iniciarCuenta();
                }
                break;

            case R.id.btnStop:
                if(relojCorriendo==true) {
                    pauseTimer();
                }
                break;

            case R.id.btnReset:
                    resetTimer();

                break;
        }

    }

    private void resetTimer(){
        relojCorriendo=false;
        temporizador.cancel();
        cronometro.setText(time);
        min=(Integer.valueOf(minData)*60)*1000;
        seg=Integer.valueOf(segData)*1000;
    }

    private void pauseTimer(){
        relojCorriendo=false;
        String tiempoRestante= cronometro.getText().toString();
        String[] parts = tiempoRestante.split(" : ");
        String minS = parts[0]; // 123
        String segS = parts[1]; // 654321
        min=(Integer.valueOf(minS)*60)*1000;
        seg=Integer.valueOf(segS)*1000;
        temporizador.cancel();
    }


    private void iniciarCuenta(){

        long valor=min+seg;
         temporizador = new CountDownTimer(valor,1000) {
            @Override
            public void onTick(long l) {
                long tiempo= l/1000;
                int minutos= (int) (tiempo/60);
                long segundos= tiempo % 60;
                String minutos_mostrar=String.format("%02d",minutos);
                String segundos_mostrar=String.format("%02d",segundos);
                cronometro.setText(minutos_mostrar+" : "+segundos_mostrar);
            }

            @Override
            public void onFinish() {
                Toast.makeText(Count_Down.this,"Finalizo",Toast.LENGTH_SHORT);
                cronometro.setText("00 : 00");
            }
        }.start();
        relojCorriendo=true;


    }
}