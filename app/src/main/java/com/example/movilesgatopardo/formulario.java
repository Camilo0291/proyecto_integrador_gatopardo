package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

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
        sendAndNavigateButton = findViewById(R.id.sendAndNavigateButton); // Botón único

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
            if (checkData(mNameOne, mSurnameOne, mEmail, mCC)) {
                Map<String, Object> formData = new HashMap<>();
                formData.put("nameOne", mNameOne);
                formData.put("nameSecond", mNameSecond);
                formData.put("surnameOne", mSurnameOne);
                formData.put("surnameSecond", mSurnameSecond);
                formData.put("cc", mCC);
                formData.put("documentType", mDocumentType);
                formData.put("email", mEmail);
                formData.put("courseName", mCourseName);
                formData.put("preferredSchedule", mPreferredSchedule);

                // Guardar en la base de datos Firebase
                db.collection("forms")
                        .add(formData)
                        .addOnSuccessListener(documentReference -> {
                            showToast("¡Formulario enviado!. ID: " + documentReference.getId(), 8000);
                            clearFields(); // Limpiar los campos después de un envío exitoso

                            // Navegar a la siguiente actividad después de un envío exitoso
                            Intent intent = new Intent(formulario.this, ultima.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e ->
                                showToast("Error al enviar. Intente más tarde.", 8000)
                        );
            } else {
                showToast("Complete y valide todos los campos para poder continuar.", 8000);
            }
        });
    }

    // Método para validar datos del formulario
    private boolean checkData(String nameOne, String surnameOne, String email, String cc) {
        return !nameOne.isEmpty() && !surnameOne.isEmpty() && !email.isEmpty() && !cc.isEmpty()
                && isValidEmail(email) && isValidCC(cc);
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
