package com.exasol.performancetestrecorder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestNameGetterTest {

    @Test
    void testRegularTest(final TestInfo testInfo) {
        assertThat(new TestNameGetter().getTestName(testInfo), equalTo("TestNameGetterTest#testRegularTest(TestInfo)"));
    }

    @ParameterizedTest
    @ValueSource(ints = { 2 })
    void testParametrizedTest(final int parameter, final TestInfo testInfo) {
        assertThat(new TestNameGetter().getTestName(testInfo), equalTo("TestNameGetterTest#testParametrizedTest[1] 2"));
    }
}