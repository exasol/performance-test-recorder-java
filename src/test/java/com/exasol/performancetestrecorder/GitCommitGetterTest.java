package com.exasol.performancetestrecorder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GitCommitGetterTest {

    @Test
    void testGetCommitId(@TempDir final Path tempDir) throws GitAPIException, IOException {
        try (final Git git = Git.init().setDirectory(tempDir.toFile()).call()) {
            Files.writeString(tempDir.resolve("file1.txt"), "test");
            git.add().addFilepattern("file1.txt").call();
            git.commit().setMessage("commit 1").call();
            Files.writeString(tempDir.resolve("file2.txt"), "test");
            git.add().addFilepattern("file2.txt").call();
            final ObjectId commit2Id = git.commit().setMessage("commit 2").call().getId();
            assertThat(new GitCommitGetter().getGitCommitHash(tempDir), equalTo(commit2Id.name()));
        }
    }
}