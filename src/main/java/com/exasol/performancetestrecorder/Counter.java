package com.exasol.performancetestrecorder;

/**
 * This class counts the invocations of {@link #increase()}.
 */
public class Counter {
    private int counterVariable = 0;

    /**
     * Increase the counter
     */
    public void increase() {
        this.counterVariable++;
    }

    /**
     * Get the counter value.
     * 
     * @return counter value
     */
    public int get() {
        return this.counterVariable;
    }
}
