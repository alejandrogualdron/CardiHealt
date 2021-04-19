package com.example.cardihealt.Entrenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.cardihealt.R;

public class Cronometro extends AppCompatActivity implements View.OnClickListener{
    TextView descripcion,ejercicio;
    Button iniciar, detener, reset;
    boolean running;
    Chronometer chronometer;
    private long pauseoffset;
    String descripcionS,ejercicioS,eje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        iniciar=(Button) findViewById(R.id.btnIniciarCronometro);
        detener=(Button) findViewById(R.id.btnStop);
        reset=(Button) findViewById(R.id.btnReset);

        descripcion=(TextView)findViewById(R.id.descripcion_cronometro);
        ejercicio=(TextView)findViewById(R.id.ejercicio_cronometro);

        chronometer=(Chronometer)findViewById(R.id.tvCronometro);
        chronometer.setFormat("Time: %");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime()-chronometer.getBase())>=600000){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        ejercicioS=getIntent().getStringExtra("tipoE");
        eje=getIntent().getStringExtra("tiempo");

        descripcionS="Realice los ejercicios a su ritmo: " +eje;
        descripcion.setText(descripcionS);
        ejercicio.setText(ejercicioS);

        iniciar.setOnClickListener(this);
        detener.setOnClickListener(this);
        reset.setOnClickListener(this);



    }

    private void startChronometer(){
        if(!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseoffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(){
        if(running){
            chronometer.stop();
            pauseoffset=SystemClock.elapsedRealtime()- chronometer.getBase();
            running=false;
        }
    }
    public void stopCronometro(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseoffset=0;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnIniciarCronometro:
                startChronometer();
                break;
            case R.id.btnStop:
                pauseChronometer();
                break;
            case R.id.btnReset:
                stopCronometro();
                break;
                        }
    }

}