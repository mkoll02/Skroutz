package org.example.model;

//βασική κλάση πελάτη
public class Customer {

    private final String fullName;
    private final String email;
    private final String username;
    private final String password;

    public Customer(String fullName, String email, String username, String password) {

        this.fullName = Product.normalizeText(fullName, "Το ονοματεπώνυμο δεν μπορεί να είναι κενό.");

        validateEmail(email);
        this.email = email.trim();

        this.username = Product.normalizeText(username, "Το username δεν μπορεί να είναι κενό.");
        this.password = Product.normalizeText(password, "Το password δεν μπορεί να είναι κενό.");
    }

    //εγκυρότητα email
    private void validateEmail(String email) {
        if (email == null || !email.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Μη έγκυρο email πελάτη.");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean hasPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Ονοματεπώνυμο: " + fullName +
                " | Email: " + email +
                " | Username: " + username;
    }
}