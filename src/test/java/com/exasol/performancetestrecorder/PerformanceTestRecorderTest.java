package com.exasol.performancetestrecorder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class PerformanceTestRecorderTest {
    private static final int ITERATION_COLUMN_INDEX = 4;
    private static final int TIMESTAMP_COLUMN_INDEX = 3;
    private static final int TEST_CASE_COLUMN_INDEX = 2;
    private static final int COMMIT_COLUMN_INDEX = 1;
    private static final int PROJECT_NAME_COLUMN_INDEX = 0;
    private static final int DURATION_COLUMN_INDEX = 5;

    @Test
    @SuppressWarnings("java:S2925") // Thread.sleep() is ok here
    void testRecording(final TestInfo testInfo) throws Exception {
        final long testStart = System.currentTimeMillis();
        final PerformanceTestRecorder recorder = new PerformanceTestRecorder();
        recorder.recordExecution(testInfo, () -> Thread.sleep(100));
        final String secondLine = Files.readString(recorder.getCsvFile()).split("\n")[1];
        final List<String> columns = getColumnsOfCsvLine(secondLine);
        assertAll(//
                () -> assertThat(columns.get(PROJECT_NAME_COLUMN_INDEX), equalTo("performance-test-recorder-java")),
                () -> assertThat(columns.get(COMMIT_COLUMN_INDEX).length(), equalTo(40)),
                () -> assertThat(columns.get(TEST_CASE_COLUMN_INDEX),
                        equalTo(getClass().getSimpleName() + "#testRecording(TestInfo)")),
                () -> assertThat(Long.valueOf(columns.get(TIMESTAMP_COLUMN_INDEX)),
                        Matchers.allOf(Matchers.lessThan(System.currentTimeMillis()), Matchers.greaterThan(testStart))),
                () -> assertThat(columns.get(ITERATION_COLUMN_INDEX), equalTo("0")),
                () -> assertThat(Long.valueOf(columns.get(DURATION_COLUMN_INDEX)),
                        Matchers.allOf(Matchers.lessThan(500L), Matchers.greaterThanOrEqualTo(100L)))//
        );
    }

    @Test
    void testRecordMultipleExecutions(final TestInfo testInfo) throws Exception {
        final PerformanceTestRecorder recorder = new PerformanceTestRecorder();
        recorder.recordExecution(testInfo, this::doNothing);
        recorder.recordExecution(testInfo, this::doNothing);
        final String[] recordLines = Files.readString(recorder.getCsvFile()).split("\n");
        assertAll(//
                () -> assertThat(getColumnsOfCsvLine(recordLines[1]).get(ITERATION_COLUMN_INDEX), equalTo("0")),
                () -> assertThat(getColumnsOfCsvLine(recordLines[2]).get(ITERATION_COLUMN_INDEX), equalTo("1"))//
        );
    }

    private List<String> getColumnsOfCsvLine(final String secondLine) {
        return Arrays.stream(secondLine.split(",")).map(column -> column.replace("\"", "").trim())
                .collect(Collectors.toList());
    }

    private void doNothing() {
        // do nothing
    }

    @Test
    void testGetInstance() {
        assertThat(PerformanceTestRecorder.getInstance(), Matchers.instanceOf(PerformanceTestRecorder.class));
    }
}