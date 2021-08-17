package com.exasol.performancetestrecorder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.TestInfo;

import com.exasol.errorreporting.ExaError;
import com.opencsv.CSVWriter;

/**
 * This class records the execution of commands in a standardized format.
 */
public class PerformanceTestRecorder {
    private static final Logger LOGGER = Logger.getLogger(PerformanceTestRecorder.class.getName());
    private static final PerformanceTestRecorder INSTANCE = new PerformanceTestRecorder();
    private final String projectName;
    private final Path csvFile;
    private final String commitHash;

    private final Map<String, Counter> invocationCounters = new HashMap<>();

    /**
     * Create a new instance of {@link PerformanceTestRecorder}.
     *
     */
    PerformanceTestRecorder() {
        this.csvFile = Path.of("target", "performanceLog-" + System.currentTimeMillis() + ".csv");
        writeRecord(new String[] { "project-name", "git-commit", "test-case", "timestamp", "iteration", "duration" });
        this.projectName = new ProjectArtifactIdGetter().getProjectArtifactId();
        this.commitHash = new GitCommitGetter().getGitCommitHash(Path.of("."));
    }

    /**
     * Get a singleton instance of {@link PerformanceTestRecorder}
     * 
     * @return instance of {@link PerformanceTestRecorder}
     */
    public static PerformanceTestRecorder getInstance() {
        return INSTANCE;
    }

    private void writeRecord(final String[] row) {
        try (final CSVWriter csvWriter = new CSVWriter(new FileWriter(this.csvFile.toFile(), true));) {
            csvWriter.writeNext(row);
            csvWriter.flush();
        } catch (final IOException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-PTRJ-2")
                    .message("Failed to create CSV writer for perfoamce test record.").toString(), exception);
        }
    }

    /**
     * Record the execution of a command.
     * 
     * @param testInfo        JUnit test info (used to get the name of the test-case)
     * @param methodUnderTest function that's execution time is recorded.
     * @throws Exception passed through from methodUnderTest
     */
    public void recordExecution(final TestInfo testInfo, final RunnableWithException methodUnderTest) throws Exception {
        final String testName = new TestNameGetter().getTestName(testInfo);
        this.invocationCounters.putIfAbsent(testName, new Counter());
        final Counter invocationCounter = this.invocationCounters.get(testName);
        LOGGER.log(Level.INFO, "Starting stopwatch for {0}", new Object[] { testName });
        final long startMillis = System.currentTimeMillis();
        methodUnderTest.run();
        final long duration = System.currentTimeMillis() - startMillis;
        LOGGER.log(Level.INFO, "{0} took {1} ms", new Object[] { testName, duration });
        writeRecord(new String[] { this.projectName, this.commitHash, testName, String.valueOf(startMillis),
                String.valueOf(invocationCounter.get()), String.valueOf(duration) });
        invocationCounter.increase();
    }

    /**
     * Get the path to the CSV file with the records.
     * 
     * @return path to the file
     */
    public Path getCsvFile() {
        return this.csvFile;
    }

    /**
     * Functional interface for the method under test that can throw an exception.
     */
    @FunctionalInterface
    public interface RunnableWithException {
        /**
         * Method that can throw exceptions.
         * 
         * @throws Exception if something goes wrong
         */
        @SuppressWarnings("java:S112") // Exception is generic, but we just want to pass through everything here
        public void run() throws Exception;
    }
}
