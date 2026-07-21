package com.example.raastqr.service;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubmissionTokenService {

    private final ConcurrentMap<String, Instant> tokens = new ConcurrentHashMap<>();
    private final Clock clock;
    private final Duration tokenTtl;

    @Autowired
    public SubmissionTokenService(@Value("${app.submission-token.ttl-seconds:300}") long tokenTtlSeconds) {
        this(Clock.systemUTC(), Duration.ofSeconds(tokenTtlSeconds));
    }

    SubmissionTokenService(Clock clock, Duration tokenTtl) {
        this.clock = clock;
        this.tokenTtl = tokenTtl;
    }

    public IssuedToken issueToken() {
        removeExpiredTokens();
        String token = UUID.randomUUID().toString();
        Instant expiresAt = clock.instant().plus(tokenTtl);
        tokens.put(token, expiresAt);
        return new IssuedToken(token, tokenTtl.toSeconds());
    }

    public boolean consumeToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        Instant expiresAt = tokens.remove(token);
        return expiresAt != null && expiresAt.isAfter(clock.instant());
    }

    private void removeExpiredTokens() {
        Instant now = clock.instant();
        tokens.entrySet().removeIf(entry -> !entry.getValue().isAfter(now));
    }

    public static class IssuedToken {
        private final String token;
        private final long expiresInSeconds;

        public IssuedToken(String token, long expiresInSeconds) {
            this.token = token;
            this.expiresInSeconds = expiresInSeconds;
        }

        public String getToken() {
            return token;
        }

        public long getExpiresInSeconds() {
            return expiresInSeconds;
        }
    }
}

