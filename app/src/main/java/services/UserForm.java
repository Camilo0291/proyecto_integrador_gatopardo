package services;

import com.google.gson.annotations.SerializedName;

public class UserForm {

    @SerializedName("Primer_Nombre")
    private String nameOne;

    @SerializedName("Segundo_Nombre")
    private String nameSecond;

    @SerializedName("Primer_Apellido")
    private String surnameOne;

    @SerializedName("Segundo_Apellido")
    private String surnameSecond;

    @SerializedName("Tipo_De_Documento")
    private String cc;

    @SerializedName("Numero_de_documento")
    private String documentType;

    @SerializedName("Correo_Electronico")
    private String email;

    @SerializedName("Nombre_Del_Curso")
    private String courseName;

    @SerializedName("Hora_De_Preferencia")
    private String preferredSchedule;


    // Getters y Setters
    public String getNameOne() {
        return nameOne;
    }

    public void setNameOne(String nameOne) {
        this.nameOne = nameOne;
    }

    public String getNameSecond() {
        return nameSecond;
    }

    public void setNameSecond(String nameSecond) {
        this.nameSecond = nameSecond;
    }

    public String getSurnameOne() {
        return surnameOne;
    }

    public void setSurnameOne(String surnameOne) {
        this.surnameOne = surnameOne;
    }

    public String getSurnameSecond() {
        return surnameSecond;
    }

    public void setSurnameSecond(String surnameSecond) {
        this.surnameSecond = surnameSecond;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPreferredSchedule() {
        return preferredSchedule;
    }

    public void setPreferredSchedule(String preferredSchedule) {
        this.preferredSchedule = preferredSchedule;
    }
}
