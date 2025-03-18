package model.entities;

public class User {
    private String typeUser;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String sexUser;

    public User(String typeUser, String firstName, String lastName, String dateOfBirth, String sexUser) {
        this.typeUser = typeUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sexUser = sexUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "typeUser='" + typeUser + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sexUser='" + sexUser + '\'' +
                '}';
    }

    public String getTypeUser() {
        return typeUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSexUser() {
        return sexUser;
    }
}
