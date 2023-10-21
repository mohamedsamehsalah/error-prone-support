package tech.picnic.errorprone.workshop.refasterrules;

import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment1RulesTest implements RefasterRuleCollectionTestCase {
  String testNewStringCharArray() {
    return String.copyValueOf(new char[] {'f', 'o', 'o'});
  }
}
