package com.server.frontendservice.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

import lombok.val;
import lombok.var;

@Service
public class EncryptionService {

    //TODO make this a property
    private static final String salt = "6546846464684";

    String decrypt(String encrypted) {
        val tmp = new StringBuilder();
        final int OFFSET = 4;

        for (int i = 0; i < encrypted.length(); i++) {
            tmp.append((char) (encrypted.charAt(i) - OFFSET));
        }

        val reversed = new StringBuffer(tmp.toString()).reverse().toString();
        val string = new String(Base64.getDecoder().decode(reversed));
        return string.replace(salt, "");
    }

    public String encrypt(String plain) {

        plain += salt;
        val b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());
        val reverse = new StringBuffer(b64encoded).reverse().toString();
        val tmp = new StringBuilder();
        var OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }
}
