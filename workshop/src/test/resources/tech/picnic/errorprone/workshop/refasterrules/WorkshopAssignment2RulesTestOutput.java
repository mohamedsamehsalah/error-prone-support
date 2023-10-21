package tech.picnic.errorprone.workshop.refasterrules;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.List;
import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class WorkshopAssignment2RulesTest implements RefasterRuleCollectionTestCase {
  @Override
  public ImmutableSet<Object> elidedTypesAndStaticImports() {
    return ImmutableSet.of(Collections.class);
  }

  ImmutableSet<List<Integer>> testImmutableListOfOne() {
    return ImmutableSet.of(ImmutableList.of(1), ImmutableList.of(2));
  }
}
