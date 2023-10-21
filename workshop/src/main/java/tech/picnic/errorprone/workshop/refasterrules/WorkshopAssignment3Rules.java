package tech.picnic.errorprone.workshop.refasterrules;

import com.google.common.base.Preconditions;

/** Refaster rules for the third assignment of the workshop. */
@SuppressWarnings("UnusedTypeParameter" /* Ignore this for demo purposes. */)
final class WorkshopAssignment3Rules {
  private WorkshopAssignment3Rules() {}

  /** Prefer {@link Preconditions#checkArgument(boolean)} over more verbose alternatives. */
  static final class CheckArgumentWithoutMessage<T> {
    // XXX: Implement the Refaster rule to get the test green.
  }

  /** Prefer {@link Preconditions#checkArgument(boolean, Object)} over more verbose alternatives. */
  static final class CheckArgumentWithMessage<T> {
    // XXX: Implement the Refaster rule to get the test green.
  }
}
