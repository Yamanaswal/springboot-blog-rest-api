package com.yaman_springboot.blog.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        generateHS512Password();
    }


    public static void generateHS512Password() {
        // Generate a secure key for HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println("KEY VALUE: "+ key.toString());
        System.out.println("KEY ENCODED: " +key.getEncoded());
        System.out.println(key);

        // Get the actual key length
        int keyLengthBits = key.getEncoded().length * 8;
        System.out.println("Key length: " + keyLengthBits + " bits");
    }

}
