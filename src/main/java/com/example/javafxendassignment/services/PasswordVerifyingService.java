package com.example.javafxendassignment.services;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerifyingService {

    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
