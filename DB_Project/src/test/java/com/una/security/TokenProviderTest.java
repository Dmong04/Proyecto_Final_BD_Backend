package com.una.security;

import com.una.security.token.AppToken;
import com.una.security.token.TokenProvider;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    void testGoodToken() {
        final Integer userId = 1234;
        final String username = "USER";
        final String role = "ADMIN";
        final Instant expiresDate = Instant.now().plus(5, ChronoUnit.MINUTES);

        AppToken appToken = new AppToken();
        appToken.setUserId(userId);
        appToken.setUsername(username);
        appToken.setRole(role);
        appToken.setExpiration(expiresDate);

        Optional<String> optToken = tokenProvider.encrypt(appToken);
        Assertions.assertTrue(optToken.isPresent());
        String token = optToken.get();
        Assertions.assertNotNull(token);
        log.info(token);

        Optional<AppToken> optAppToken = tokenProvider.decrypt(token);
        Assertions.assertTrue(optAppToken.isPresent());
        AppToken decodedAppToken = optAppToken.get();

        Assertions.assertNotNull(decodedAppToken);
        Assertions.assertEquals(userId, decodedAppToken.getUserId());
        Assertions.assertEquals(username, decodedAppToken.getUsername());
        Assertions.assertEquals(username, decodedAppToken.getRole());
        Assertions.assertEquals(expiresDate, decodedAppToken.getExpiration());
    }
}
