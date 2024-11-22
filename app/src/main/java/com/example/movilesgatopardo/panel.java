package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class panel extends AppCompatActivity {

    // Inicializamos los elementos
    EditText email, password;
    Button registerButtonPanel;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        // Inicializamos los elementos
        email = findViewById(R.id.etEmailP);
        password = findViewById(R.id.etPasswordP);
        registerButtonPanel = findViewById(R.id.registerButttonpanel);

        // Listener para ir a la pantalla de talleres
        registerButtonPanel.setOnClickListener(v -> {
            String enteredEmail = email.getText().toString().trim();
            String enteredPassword = password.getText().toString().trim();

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                showToast("Por favor, complete todos los campos.", 8000);
            } else {
                // Verificar si los datos coinciden con un usuario en Firebase
                auth.signInWithEmailAndPassword(enteredEmail, enteredPassword)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Si la autenticación es exitosa, limpiar los campos y navegar a talleres
                                showToast("¡Inicio exitoso!", 8000);
                                clearFields(); // Limpiar los campos
                                Intent intent = new Intent(panel.this, talleres.class);
                                startActivity(intent);
                            } else {
                                // Si los datos no coinciden, mostrar mensaje de error y limpiar los campos
                                showToast("Correo o contraseña incorrectos.", 8000);
                                clearFields();
                            }
                        });
            }
        });
    }

    // Método para limpiar los campos de email y contraseña
    private void clearFields() {
        email.setText("");
        password.setText("");
    }

    // Método para mostrar Toast con duración extendida
    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(panel.this, message, Toast.LENGTH_SHORT);
        toast.show();

        // Manejador para cancelar el Toast después de la duración especificada
        new Handler().postDelayed(toast::cancel, duration);
    }
}
