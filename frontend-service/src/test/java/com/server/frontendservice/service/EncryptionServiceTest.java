package com.server.frontendservice.service;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EncryptionServiceTest extends TestCase {

    private EncryptionService encryptionService;

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
}
