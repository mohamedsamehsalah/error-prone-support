package tech.picnic.errorprone.workshop.refasterrules;

import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment0RulesTest implements RefasterRuleCollectionTestCase {
  boolean testExampleStringIsEmpty() {
    return "foo".length() == 0;
  }
}
