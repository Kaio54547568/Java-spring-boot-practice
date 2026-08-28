package com.practice.employees.service;

import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UtilityService {
    private final AtomicLong sequence = new AtomicLong(System.currentTimeMillis() % 100_000);

    public String normalize(String value) {
        String trimmed = value == null ? "" : value.trim();
        return trimmed.replaceAll("\\s+", " ");
    }

    public String normalizeEmail(String email) {
        return normalize(email).toLowerCase(Locale.ROOT);
    }

    public String generateEmployeeCode() {
        return "EMP-%06d".formatted(sequence.incrementAndGet());
    }
}
