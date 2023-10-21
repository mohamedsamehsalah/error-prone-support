package tech.picnic.errorprone.workshop.bugpatterns;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;
import static com.google.errorprone.BugPattern.StandardTags.SIMPLIFICATION;
import static com.google.errorprone.matchers.ChildMultiMatcher.MatchType.AT_LEAST_ONE;
import static com.google.errorprone.matchers.Matchers.annotations;
import static com.google.errorprone.matchers.Matchers.anyOf;
import static com.google.errorprone.matchers.Matchers.isType;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;

/** A {@link BugChecker} that flags empty methods that seemingly can simply be deleted. */
@AutoService(BugChecker.class)
@BugPattern(
    summary = "Empty method can likely be deleted",
    severity = WARNING,
    tags = SIMPLIFICATION)
@SuppressWarnings("UnusedVariable" /* This check is yet to be implemented as part of the demo */)
public final class DeleteEmptyMethod extends BugChecker implements MethodTreeMatcher {
  private static final long serialVersionUID = 1L;
  private static final Matcher<Tree> PERMITTED_ANNOTATION =
      annotations(AT_LEAST_ONE, anyOf(isType("java.lang.Override")));

  /** Instantiates a new {@link DeleteEmptyMethod} instance. */
  public DeleteEmptyMethod() {}

  @Override
  public Description matchMethod(MethodTree tree, VisitorState state) {
    // XXX: Part 1: Ensure that we only delete methods that contain no statements.
    // XXX: Part 2: Don't delete methods that are annotated with `@Override`.

    return Description.NO_MATCH;
  }
}
