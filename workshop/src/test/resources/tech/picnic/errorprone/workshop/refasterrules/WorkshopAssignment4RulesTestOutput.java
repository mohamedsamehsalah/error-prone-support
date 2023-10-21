package tech.picnic.errorprone.workshop.refasterrules;

import com.google.common.collect.ImmutableSet;
import java.math.RoundingMode;
import java.util.Objects;
import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment4RulesTest implements RefasterRuleCollectionTestCase {
  @Override
  public ImmutableSet<Object> elidedTypesAndStaticImports() {
    return ImmutableSet.of(Objects.class);
  }

  ImmutableSet<Boolean> testPrimitiveOrReferenceEqualityEnum() {
    return ImmutableSet.of(
        "foo".equals("bar"),
        Integer.valueOf(1).equals(2),
        RoundingMode.UP == RoundingMode.DOWN,
        RoundingMode.UP == RoundingMode.DOWN,
        RoundingMode.UP != RoundingMode.DOWN,
        RoundingMode.UP != RoundingMode.DOWN);
  }
}
