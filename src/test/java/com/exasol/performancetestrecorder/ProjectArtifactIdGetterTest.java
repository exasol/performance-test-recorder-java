package com.exasol.performancetestrecorder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class ProjectArtifactIdGetterTest {

    @Test
    void testGetArtifactId() {
        assertThat(new ProjectArtifactIdGetter().getProjectArtifactId(), equalTo("performance-test-recorder-java"));
    }
}