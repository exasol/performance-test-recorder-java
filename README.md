# Performance Test Recorder Java

[![Build Status](https://github.com/exasol/performance-test-recorder-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/performance-test-recorder-java/actions/workflows/ci-build.yml)
[![Maven Central – Performance Test Recorder Java](https://img.shields.io/maven-central/v/com.exasol/performance-test-recorder-java)](https://search.maven.org/artifact/com.exasol/performance-test-recorder-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aperformance-test-recorder-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aperformance-test-recorder-java)

Java library for writing performance test records.

## Usage

```java

class MyTest {
    @Test
    void myTest(TestInfo testInfo) {
        PerformanceTestRecorder.getInstance().recordExecution(testInfo,
                () -> sql.executeQuery("SELECT * FROM TEST"));
    }
}
```

The performance test recorder writes the record to `target/performanceLog-<TIMESTAMP>.csv`.

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)