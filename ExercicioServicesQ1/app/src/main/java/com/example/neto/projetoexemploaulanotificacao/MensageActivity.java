package com.example.neto.projetoexemploaulanotificacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MensageActivity extends AppCompatActivity {

    private TextView pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensage);

        Bundle extras = getIntent().getExtras();

        double piNumber = extras.getDouble("PI",0);

        pi = findViewById(R.id.textView_PI);

        pi.setText(pi.getText()+String.valueOf(piNumber));
    }

    @Override
    protected void onNewIntent(Intent intent) {



    }
}
