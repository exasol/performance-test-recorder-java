# Performance Test Recorder Java 0.1.2, released 2022-10-26

Code name: Updated dependencies

## Summary

Dependency check build failed : updated dependencies to fix issue.
Fixed vulnerability:
* org.apache.commons:commons-text:jar:1.9
 https://ossindex.sonatype.org/component/pkg:maven/org.apache.commons/commons-text@1.9?utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* [CVE-2022-42889] CWE-94: Improper Control of Generation of Code ('Code Injection') (9.8); https://ossindex.sonatype.org/vulnerability/CVE-2022-42889?component-type=maven&component-name=org.apache.commons%2Fcommons-text&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* By updating `opencsv` dependency to `5.7.1`.

## Features

* #6: Dependency check build fails: updated dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.0`
* Updated `com.opencsv:opencsv:5.6` to `5.7.1`
* Updated `org.eclipse.jgit:org.eclipse.jgit:6.2.0.202206071550-r` to `6.3.0.202209071007-r`
* Updated `org.junit.jupiter:junit-jupiter-api:5.9.0` to `5.9.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.0` to `5.9.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.0` to `5.9.1`
* Updated `org.mockito:mockito-junit-jupiter:4.7.0` to `4.8.1`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.6.2` to `2.8.0`
