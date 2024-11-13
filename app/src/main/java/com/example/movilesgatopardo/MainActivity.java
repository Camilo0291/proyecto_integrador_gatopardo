package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Usar un Handler para retrasar la acción de cambiar de actividad
        new Handler().postDelayed(() -> {
            // Crear el Intent para ir a la actividad de login
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
