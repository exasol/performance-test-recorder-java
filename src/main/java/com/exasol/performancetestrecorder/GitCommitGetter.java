package com.exasol.performancetestrecorder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.revwalk.RevCommit;

import com.exasol.errorreporting.ExaError;

/**
 * This class gets the last commit hash of a git repository.
 */
class GitCommitGetter {

    /**
     * Get the last commit hash of a git repository.
     * 
     * @param projectDirectory directory of the git repository
     * @return commit hash
     */
    String getGitCommitHash(final Path projectDirectory) {
        try (final Git git = Git.open(projectDirectory.toFile())) {
            final Stream<RevCommit> commits = StreamSupport.stream(git.log().call().spliterator(), false);
            final Optional<RevCommit> firstCommit = commits.findFirst();
            return firstCommit.map(AnyObjectId::getName).orElse("no-commits");
        } catch (final IOException | GitAPIException exception) {
            throw new IllegalStateException(ExaError.messageBuilder("E-PTRJ-3")
                    .message("Failed to get the hash of the last git-commit.").toString(), exception);
        }
    }
}
