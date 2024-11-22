package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class talleres extends AppCompatActivity {

    // Declarar todos los botones y las CardViews para cada taller
    Button buttontoform;
    Button showCardButton1, showCardButton2, showCardButton3, showCardButton4;
    CardView infoCard1, infoCard2, infoCard3, infoCard4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres);

        // Configuración de bordes de pantalla para manejar barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar todos los botones y las CardViews
        buttontoform = findViewById(R.id.buttontoform);

        showCardButton1 = findViewById(R.id.showCardButton1);
        showCardButton2 = findViewById(R.id.showCardButton2);
        showCardButton3 = findViewById(R.id.showCardButton3);
        showCardButton4 = findViewById(R.id.showCardButton4);

        infoCard1 = findViewById(R.id.infoCard1);
        infoCard2 = findViewById(R.id.infoCard2);
        infoCard3 = findViewById(R.id.infoCard3);
        infoCard4 = findViewById(R.id.infoCard4);

        // Configurar la visibilidad de las CardViews como GONE por defecto
        infoCard1.setVisibility(View.GONE);
        infoCard2.setVisibility(View.GONE);
        infoCard3.setVisibility(View.GONE);
        infoCard4.setVisibility(View.GONE);

        // Asignar OnClickListeners para cada botón de tarjeta para alternar visibilidad
        showCardButton1.setOnClickListener(v -> toggleCard(infoCard1));
        showCardButton2.setOnClickListener(v -> toggleCard(infoCard2));
        showCardButton3.setOnClickListener(v -> toggleCard(infoCard3));
        showCardButton4.setOnClickListener(v -> toggleCard(infoCard4));

        // Asignar OnClickListener para el botón del formulario y navegar a la actividad del formulario
        buttontoform.setOnClickListener(v -> openFormActivity());
    }

    // Método para alternar la visibilidad de una CardView
    private void toggleCard(CardView card) {
        if (card.getVisibility() == View.GONE) {
            card.setVisibility(View.VISIBLE);
        } else {
            card.setVisibility(View.GONE);
        }
    }

    // Método para abrir la actividad del formulario
    private void openFormActivity() {
        Intent intent = new Intent(talleres.this, formulario.class);
        startActivity(intent);
    }
}
