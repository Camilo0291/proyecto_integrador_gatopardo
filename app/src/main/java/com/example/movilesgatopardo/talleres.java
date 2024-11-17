package com.example.movilesgatopardo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class talleres extends AppCompatActivity {

    Button buttonWorkshops, buttontoform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        // Configuración de bordes de pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar ambos botones
        buttonWorkshops = findViewById(R.id.buttonWorkshops);
        buttontoform = findViewById(R.id.buttontoform); // Asegúrate de que el ID coincida con el XML

        // Listener para el primer botón (abrir URL)
        buttonWorkshops.setOnClickListener(view -> {
            String url = "https://buid-wpx4.vercel.app/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Listener para el segundo botón (ir a formulario)
        buttontoform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(talleres.this, formulario.class);
                startActivity(intent);
            }
        });


    }

}

