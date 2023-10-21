package tech.picnic.errorprone.workshop.refasterrules;

import java.util.stream.Stream;
import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment5RulesTest implements RefasterRuleCollectionTestCase {
  boolean testStreamDoAllMatch() {
    return Stream.of("foo").noneMatch(s -> !s.isBlank());
  }
}
