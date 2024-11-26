package services;

public class UserForm {
    private String nameOne;
    private String nameSecond;
    private String surnameOne;
    private String surnameSecond;
    private String cc;
    private String documentType;
    private String email;
    private String courseName;
    private String preferredSchedule;

    // Agregar los getters y setters para cada campo
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
