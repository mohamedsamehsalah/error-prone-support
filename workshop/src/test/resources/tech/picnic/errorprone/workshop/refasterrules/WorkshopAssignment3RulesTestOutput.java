package tech.picnic.errorprone.workshop.refasterrules;

import static com.google.common.base.Preconditions.checkArgument;

import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment3RulesTest implements RefasterRuleCollectionTestCase {
  void testCheckArgumentWithoutMessage() {
    checkArgument("foo".isEmpty());
  }

  void testCheckArgumentWithMessage() {
    checkArgument("foo".isEmpty(), "The string is empty");
  }
}
