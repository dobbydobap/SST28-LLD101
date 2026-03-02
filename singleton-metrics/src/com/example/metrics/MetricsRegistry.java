package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * - Constructor is private and protected against reflection.
 * - getInstance() uses Double-Checked Locking for thread safety and lazy init.
 * - readResolve() implemented to prevent serialization from breaking singleton.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 1. ADDED 'volatile'
    private static volatile MetricsRegistry INSTANCE;

    private final Map<String, Long> counters = new HashMap<>();

    // 2. MADE PRIVATE & BLOCKED REFLECTION
    private MetricsRegistry() {
        // If someone uses Reflection to bypass 'private', this stops them.
        if (INSTANCE != null) {
            throw new IllegalStateException("Instance already exists. Use getInstance().");
        }
    }

    // 3. DOUBLE-CHECKED LOCKING
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) { // First check (no lock, fast)
            synchronized (MetricsRegistry.class) { // Lock for thread safety
                if (INSTANCE == null) { // Second check (ensures only one thread creates it)
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    // 4. PRESERVED SINGLETON ON DESERIALIZATION
    @Serial
    protected Object readResolve() {
        return getInstance();
    }

    // --- Business Logic Methods (Unchanged, already synchronized) ---
    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}