package ru.anton.test2.Facade;

public class User {
    private String firstName;

    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private String email;

    private String password;

    private int role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static  UserBuilder builder(){
        return new UserBuilder();
    }

    public  static class UserBuilder{
        private String firstName;

        private String lastName;

        private String email;

        private String password;

        private int role;
    }
}
