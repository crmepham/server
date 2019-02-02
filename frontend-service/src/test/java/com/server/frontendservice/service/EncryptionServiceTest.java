package com.server.frontendservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import junit.framework.TestCase;

public class EncryptionServiceTest extends TestCase {

    private EncryptionService encryptionService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeClass
    public void setUp() throws Exception {
        encryptionService = new EncryptionService();
    }

    @Test
    public void testEncryptSuccess() {
        String encrypted = encryptionService.encrypt("abc");
        System.err.println(encrypted);
        assertThat(encryptionService.decrypt(encrypted)).isEqualTo("abc");
    }

    @Test
    public void testBycryptEncoding() {
        String encrypted = new BCryptPasswordEncoder().encode("abc");
        System.out.println(encrypted);
    }
}
