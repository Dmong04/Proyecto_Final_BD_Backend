package com.una.security.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.paseto4j.commons.PasetoException;
import org.paseto4j.commons.SecretKey;
import org.paseto4j.commons.Version;
import org.paseto4j.version3.Paseto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;

@Log4j2
@Service
public class TokenProvider {

    @Value("${app.token.secret}")
    String secret;
    @Value("${app.token.footer}")
    String footer;

    public Optional<String> encrypt(AppToken appToken) {
        String payload;
        try {
            payload = mapper().writeValueAsString(appToken);
            return Optional.of(Paseto.encrypt(key(), payload, footer));
        } catch (JsonProcessingException | PasetoException e) {
            log.error("Error al encriptar token", e);
            return Optional.empty();
        }
    }

    public Optional<AppToken> decrypt(String token) {
        try {
            String payload = Paseto.decrypt(key(), token, footer);
            AppToken appToken = mapper().readValue(payload, AppToken.class);

            log.info("Token valido para usuario: {}", appToken);

            if (Instant.now().isAfter(appToken.getExpiration())) {
                return Optional.empty();
            }
            return Optional.of(appToken);
        } catch (JsonProcessingException | PasetoException e) {
            log.error("Error al desencriptar token", e);
            return Optional.empty();
        }
    }

    private SecretKey key() {
        return new org.paseto4j.commons.SecretKey(this.secret.getBytes(StandardCharsets.UTF_8), Version.V3);
    }

    private static final  JsonMapper mapper = (JsonMapper) new JsonMapper().registerModule(new JavaTimeModule());
    private JsonMapper mapper() {
        return mapper;
    }
}
