package model.entities;

public class User {
    private String typeUser;
    private String userName;
    private String dateOfBirth;
    private String sexUser;

    public User(String typeUser, String userName, String dateOfBirth, String sexUser) {
        this.typeUser = typeUser;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.sexUser = sexUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "typeUser='" + typeUser + '\'' +
                ", userName='" + userName + '\'' +
                ", DateOfBirth='" + dateOfBirth + '\'' +
                ", sexUser='" + sexUser + '\'' +
                '}';
    }

    public String getTypeUser() {
        return typeUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSexUser() {
        return sexUser;
    }
}
