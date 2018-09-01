package com.server.frontendservice.service;

import com.server.frontendservice.model.User;
import com.server.frontendservice.repository.UserJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class LoginService {

    private static final boolean USE_IP_WHITELIST = false;

    @Autowired
    private UserJpaDao userDAO;

    @Autowired
    private EncryptionService encryptionService;

    public User authenticate(@NonNull String username, @NonNull String password) {

        User user = getUser(username);

        if (user == null){

            throw new BadCredentialsException(format("Authentication failed. Could not locate user matching '%s'.", username));
        }

        authenticate(user, password);

        return user;
    }

    private void authenticate(User user, String password) {

        if (USE_IP_WHITELIST) {
            //TODO implement white-listing, backed by UI modifiable property
        } else {
            validatePassword(user.getPassword(), password);
        }
    }

    private void validatePassword(final String encryptedPassword, final String password) {

        final String decryptedPassword = encryptionService.decrypt(encryptedPassword);

        boolean match = decryptedPassword.equals(password);

        if (!match) {

            throw new BadCredentialsException("Authentication failed. Incorrect password.");
        }
    }

    private User getUser(String username) {
        try{
            return userDAO.findByUsernameAndEnabledTrueAndDeletedFalse(username);
        } catch (EmptyResultDataAccessException e) {
            throw new BadCredentialsException(format("Authentication failed. No user found with username '%s'.", username));
        }
    }

    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
}
