package com.exasol.performancetestrecorder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CounterTest {
    @Test
    void testInitialState() {
        final Counter counter = new Counter();
        assertThat(counter.get(), equalTo(0));
    }

    @Test
    void testIncrease() {
        final Counter counter = new Counter();
        counter.increase();
        assertThat(counter.get(), equalTo(1));
    }
}