package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.FormService;
import services.UserForm;

public class formulario extends AppCompatActivity {

    // Declaración de campos del formulario
    EditText nameOne, nameSecond, surnameOne, surnameSecond, cc, documentType, emailFormulary, courseName, preferredSchedule;
    Button sendAndNavigateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // Inicialización de los elementos del formulario
        nameOne = findViewById(R.id.nameOne);
        nameSecond = findViewById(R.id.nameSecond);
        surnameOne = findViewById(R.id.surnameOne);
        surnameSecond = findViewById(R.id.surnameSecond);
        cc = findViewById(R.id.cc);
        documentType = findViewById(R.id.documentType);
        emailFormulary = findViewById(R.id.emailFormulary);
        courseName = findViewById(R.id.courseName);
        preferredSchedule = findViewById(R.id.preferredSchedule);
        sendAndNavigateButton = findViewById(R.id.sendAndNavigateButton);

        // Firebase instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.128.10:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Interface de Retrofit para JSON Server
        FormService formService = retrofit.create(FormService.class);

        // Listener para el botón que envía los datos y navega a la siguiente actividad
        sendAndNavigateButton.setOnClickListener(v -> {
            String mNameOne = nameOne.getText().toString();
            String mNameSecond = nameSecond.getText().toString();
            String mSurnameOne = surnameOne.getText().toString();
            String mSurnameSecond = surnameSecond.getText().toString();
            String mCC = cc.getText().toString();
            String mDocumentType = documentType.getText().toString();
            String mEmail = emailFormulary.getText().toString();
            String mCourseName = courseName.getText().toString();
            String mPreferredSchedule = preferredSchedule.getText().toString();

            // Validación de los campos
            if (checkData(mNameOne, mSurnameOne, mEmail, mCC, mCourseName, mPreferredSchedule)) {
                // Crear el objeto UserForm
                UserForm userForm = new UserForm();
                userForm.setNameOne(mNameOne);
                userForm.setNameSecond(mNameSecond);
                userForm.setSurnameOne(mSurnameOne);
                userForm.setSurnameSecond(mSurnameSecond);
                userForm.setCc(mCC);
                userForm.setDocumentType(mDocumentType);
                userForm.setEmail(mEmail);
                userForm.setCourseName(mCourseName);
                userForm.setPreferredSchedule(mPreferredSchedule);

                // Guardar en Firebase Firestore
                db.collection("forms")
                        .add(userForm)  // Guardar directamente el objeto UserForm
                        .addOnSuccessListener(documentReference -> {
                            showToast("¡Formulario enviado a Firebase!. ID: " + documentReference.getId(), 8000);
                            clearFields();

                            // Navegar a la siguiente actividad después de un envío exitoso
                            Intent intent = new Intent(formulario.this, ultima.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> showToast("Error al enviar a Firebase. Intente más tarde.", 8000));

                // Enviar el formulario a JSON Server usando Retrofit
                formService.submitFormulary(userForm).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            showToast("Formulario enviado a JSON Server.", 8000);
                        } else {
                            showToast("Error al enviar a JSON Server. Intente más tarde.", 8000);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showToast("Error al enviar a JSON Server. Intente más tarde.", 8000);
                    }
                });
            } else {
                showToast("Complete y valide todos los campos para poder continuar.", 8000);
            }
        });
    }

    // Método para validar datos del formulario
    private boolean checkData(String nameOne, String surnameOne, String email, String cc, String courseName, String preferredSchedule) {
        return !nameOne.isEmpty()
                && !surnameOne.isEmpty()
                && !email.isEmpty()
                && !cc.isEmpty()
                && !courseName.isEmpty()
                && !preferredSchedule.isEmpty()
                && isValidEmail(email)
                && isValidCC(cc);
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    // Método para validar la longitud del número de cédula
    private boolean isValidCC(String cc) {
        return cc.length() >= 8 && cc.length() <= 12 && cc.matches("[0-9]+");
    }

    // Método para limpiar los campos del formulario
    private void clearFields() {
        nameOne.setText("");
        nameSecond.setText("");
        surnameOne.setText("");
        surnameSecond.setText("");
        cc.setText("");
        documentType.setText("");
        emailFormulary.setText("");
        courseName.setText("");
        preferredSchedule.setText("");
    }

    // Método para mostrar Toast con duración extendida
    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(formulario.this, message, Toast.LENGTH_SHORT);
        toast.show();

        // Manejador para cancelar el Toast después de la duración especificada
        new Handler().postDelayed(toast::cancel, duration);
    }
}
