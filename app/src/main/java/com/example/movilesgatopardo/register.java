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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.User;
import services.UserService;

public class register extends AppCompatActivity {

    // Elementos de la interfaz
    private EditText email, password;
    private CheckBox checkBox;
    private TextView sig;
    private Button register;

    // Instancia de Firebase Auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // Expresión regular para verificar el formato del correo
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

        // Conectar elementos de la interfaz
        email = findViewById(R.id.etEmailR);
        password = findViewById(R.id.etPasswordR);
        sig = findViewById(R.id.sigRegisterText);
        checkBox = findViewById(R.id.checkBox);
        register = findViewById(R.id.registerButton);

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.128.10:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        // Configuración del botón de registro
        register.setOnClickListener(view -> {
            String mEmail = email.getText().toString();
            String mPassword = password.getText().toString();

            if (!mEmail.isEmpty() && !mPassword.isEmpty()) {
                if (isValidEmailDomain(mEmail)) {
                    if (checkBox.isChecked()) {
                        // Registro en Firebase Authentication
                        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        showToast("¡Registro exitoso en Firebase!", 8000);
                                        // Registro en el backend
                                        registerUserWithBackend(userService, mEmail, mPassword);
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            showToast("Este correo ya está registrado en Firebase.", 8000);
                                        } else {
                                            showToast("La contraseña debe tener al menos 6 dígitos.", 8000);
                                        }
                                    }
                                });
                    } else {
                        showToast("Debes aceptar los términos y condiciones para registrarte.", 8000);
                    }
                } else {
                    showToast("El correo debe tener un formato válido (@).", 8000);
                }
            } else {
                showToast("Por favor, completa todos los campos.", 8000);
            }
        });

        // Navegación a la pantalla de login
        sig.setOnClickListener(view -> {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
        });
    }

    // Método para registrar en el backend
    private void registerUserWithBackend(UserService userService, String email, String password) {
        User newUser = new User(email, password);

        Call<Void> call = userService.registerUser(newUser);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("¡Registro exitoso en el backend!", 8000);
                    // Navegar a la actividad de talleres después del registro
                    navigateToTalleres();
                } else {
                    showToast("Error al registrar en el backend: " + response.code(), 8000);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Error de conexión con el backend: " + t.getMessage(), 8000);
            }
        });
    }

    // Método para navegar a la actividad Talleres
    private void navigateToTalleres() {
        Intent intent = new Intent(register.this, talleres.class);
        startActivity(intent);
        finish(); // Opcional: cerrar la actividad actual
    }

    // Validar el formato del correo
    private boolean isValidEmailDomain(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Método para mostrar un Toast con duración extendida
    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(register.this, message, Toast.LENGTH_SHORT);
        toast.show();
        new Handler().postDelayed(toast::cancel, duration);
    }
}
