package com.exasol.performancetestrecorder;

import java.lang.reflect.Method;

import org.junit.jupiter.api.TestInfo;

/**
 * This class gets a readable name for a test method.
 */
class TestNameGetter {

    /**
     * Get a readable name for a test method.
     *
     * @param testInfo test info to read from
     * @return readable test name
     */
    String getTestName(final TestInfo testInfo) {
        final String className = testInfo.getTestClass().map(aClass -> aClass.getSimpleName() + "#").orElse("");
        final String methodName = testInfo.getTestMethod().map(Method::getName).orElse("");
        final String displayName = testInfo.getDisplayName();
        if (displayName.startsWith(methodName)) {
            return className + displayName;
        } else {
            return className + methodName + displayName;
        }
    }
}
