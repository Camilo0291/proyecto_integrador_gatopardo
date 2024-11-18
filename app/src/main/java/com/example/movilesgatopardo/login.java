package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    // Inicializamos los elementos
    EditText email, password;
    TextView signup;
    Button login;
    FirebaseAuth mAut = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enlazamos las vistas con los elementos del layout
        email = findViewById(R.id.etEmailS);
        password = findViewById(R.id.etPasswordS);
        signup = findViewById(R.id.signupText1);
        login = findViewById(R.id.loginButton);

        // Configuración del botón inicio de sesión
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se obtiene los textos de campo de entrada
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                // Verificar que los campos no estén vacíos
                if (mEmail.isEmpty() || mPassword.isEmpty()) {
                    showToast("Por favor, complete todos los campos.", 4000);
                    return;
                }

                // Iniciar sesión con Firebase Authentication
                mAut.signInWithEmailAndPassword(mEmail, mPassword)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    showToast("¡Inicio de sesión exitoso!", 4000);
                                    Intent intent = new Intent(login.this, panel.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        showToast("Los datos no coinciden.", 4000);
                                    }
                                }
                            }
                        });
            }
        });

        // Listener para ir a "Registrarse"
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }

    // Método para mostrar Toast con duración extendida
    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(login.this, message, Toast.LENGTH_SHORT);
        toast.show();

        // Manejador para cancelar el Toast después de la duración especificada
        new Handler().postDelayed(toast::cancel, duration);
    }
}
