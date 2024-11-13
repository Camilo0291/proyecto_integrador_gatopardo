package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    // Inicializamos los elementos
    EditText email, password;
    CheckBox checkBox;
    TextView sig;
    Button register;
    FirebaseAuth mAut = FirebaseAuth.getInstance();

    // Expresión regular para verificar que el correo tenga el (@)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(".*@.*");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Conectamos las vistas con los componentes del layout
        email = findViewById(R.id.etEmailR);
        password = findViewById(R.id.etPasswordR);
        sig = findViewById(R.id.sigRegisterText);
        checkBox = findViewById(R.id.checkBox);
        register = findViewById(R.id.registerButton);

        // Configuración del botón de registro
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                // Verificación de que los campos no estén vacíos
                if (!mEmail.isEmpty() && !mPassword.isEmpty()) {
                    // Verificamos que el correo tenga el (@)
                    if (isValidEmailDomain(mEmail)) {
                        // Verificamos si el checkbox está marcado
                        if (checkBox.isChecked()) {
                            // Intentamos registrar al usuario con Firebase Authentication
                            mAut.createUserWithEmailAndPassword(mEmail, mPassword)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            showToast("Registro exitoso", 4000);
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), talleres.class));
                                        } else {
                                            // Verificar si el error es debido a que el usuario ya existe
                                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                                showToast("Este correo ya está registrado", 4000);
                                            } else {
                                                showToast("La contraseña debe tener al menos 6 dígitos", 4000);
                                            }
                                        }
                                    });
                        } else {
                            showToast("Debes aceptar los términos para registrarte", 4000);
                        }
                    } else {
                        showToast("El correo debe tener el (@)", 4000);
                    }
                } else {
                    showToast("Por favor, complete todos los campos", 4000);
                }
            }
        });

        // Listener para ir a "Iniciar sesión"
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }

    // Método que verifica si el correo electrónico tiene un formato válido utilizando una expresión regular
    private boolean isValidEmailDomain(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Método para mostrar Toast con duración extendida
    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(register.this, message, Toast.LENGTH_SHORT);
        toast.show();

        // Manejador para cancelar el Toast después de la duración especificada
        new Handler().postDelayed(toast::cancel, duration);
    }
}
