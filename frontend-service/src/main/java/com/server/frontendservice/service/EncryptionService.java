package com.server.frontendservice.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionService {

    //TODO make this a property
    private static final String salt = "6546846464684";

    String decrypt(String encrypted) {

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;

        for (int i = 0; i < encrypted.length(); i++) {
            tmp.append((char) (encrypted.charAt(i) - OFFSET));
        }

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        String string = new String(Base64.getDecoder().decode(reversed));
        return string.replace(salt, "");
    }

    public String encrypt(String plain) {

        plain += salt;

        String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

        // Reverse the string
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }
}
