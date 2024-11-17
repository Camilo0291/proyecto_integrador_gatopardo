package com.example.movilesgatopardo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class formulario extends AppCompatActivity {

    // Declaración de campos del formulario
    EditText nameOne, nameSecond, surnameOne, surnameSecond, cc, documentType, emailFormulary, courseName, preferredSchedule;
    Button endButton, sendButton;

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
        endButton = findViewById(R.id.endButton);
        sendButton = findViewById(R.id.sendButton);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Listener para guardar datos en Firebase
        sendButton.setOnClickListener(v -> {
            String mNameOne = nameOne.getText().toString();
            String mNameSecond = nameSecond.getText().toString();
            String mSurnameOne = surnameOne.getText().toString();
            String mSurnameSecond = surnameSecond.getText().toString();
            String mCC = cc.getText().toString();
            String mDocumentType = documentType.getText().toString();
            String mEmail = emailFormulary.getText().toString();
            String mCourseName = courseName.getText().toString();
            String mPreferredSchedule = preferredSchedule.getText().toString();

            if (checkData(mNameOne, mSurnameOne, mEmail)) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("nameOne", mNameOne);
                userData.put("nameSecond", mNameSecond);
                userData.put("surnameOne", mSurnameOne);
                userData.put("surnameSecond", mSurnameSecond);
                userData.put("cc", mCC);
                userData.put("documentType", mDocumentType);
                userData.put("email", mEmail);
                userData.put("courseName", mCourseName);
                userData.put("preferredSchedule", mPreferredSchedule);

                db.collection("users")
                        .add(userData)
                        .addOnSuccessListener(documentReference ->
                                Toast.makeText(formulario.this,
                                        "Registro exitoso. ID: " + documentReference.getId(),
                                        Toast.LENGTH_LONG).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(formulario.this,
                                        "Error al registrar. Inténtelo más tarde.",
                                        Toast.LENGTH_LONG).show()
                        );
            } else {
                Toast.makeText(formulario.this,
                        "Debe diligenciar todos los datos obligatorios.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listener para el segundo botón (ir a otra actividad)
        endButton.setOnClickListener(view -> {
            Intent intent = new Intent(formulario.this, ultima.class);
            startActivity(intent);
        });
    }

    // Método para validar datos del formulario
    private boolean checkData(String nameOne, String surnameOne, String email) {
        return !nameOne.isEmpty() && !surnameOne.isEmpty() && !email.isEmpty();
    }
}
