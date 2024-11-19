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

    // Declare all buttons and CardViews for each workshop
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

        // Initialize all buttons and CardViews
        buttontoform = findViewById(R.id.buttontoform);


        showCardButton1 = findViewById(R.id.showCardButton1);
        showCardButton2 = findViewById(R.id.showCardButton2);
        showCardButton3 = findViewById(R.id.showCardButton3);
        showCardButton4 = findViewById(R.id.showCardButton4);

        infoCard1 = findViewById(R.id.infoCard1);
        infoCard2 = findViewById(R.id.infoCard2);
        infoCard3 = findViewById(R.id.infoCard3);
        infoCard4 = findViewById(R.id.infoCard4);

        // Set visibility of the cards to GONE by default
        infoCard1.setVisibility(View.GONE);
        infoCard2.setVisibility(View.GONE);
        infoCard3.setVisibility(View.GONE);
        infoCard4.setVisibility(View.GONE);

        // Set onClickListeners for each showCardButton to toggle visibility
        showCardButton1.setOnClickListener(v -> toggleCard(infoCard1));
        showCardButton2.setOnClickListener(v -> toggleCard(infoCard2));
        showCardButton3.setOnClickListener(v -> toggleCard(infoCard3));
        showCardButton4.setOnClickListener(v -> toggleCard(infoCard4));

        // Set onClickListeners for each form button to navigate to the form activity
        buttontoform.setOnClickListener(v -> openFormActivity());

    }

    // Method to toggle visibility of a CardView
    private void toggleCard(CardView card) {
        if (card.getVisibility() == View.GONE) {
            card.setVisibility(View.VISIBLE); // Show the card
        } else {
            card.setVisibility(View.GONE); // Hide the card
        }
    }

    // Method to open the form activity
    private void openFormActivity() {
        Intent intent = new Intent(talleres.this, formulario.class);
        startActivity(intent);
    }
}
