package tech.picnic.errorprone.bugpatterns;

import com.google.errorprone.BugCheckerRefactoringTestHelper;
import com.google.errorprone.BugCheckerRefactoringTestHelper.TestMode;
import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

public final class JUnitMethodDeclarationCheckTest {
  private final CompilationTestHelper compilationTestHelper =
      CompilationTestHelper.newInstance(JUnitMethodDeclarationCheck.class, getClass());
  private final BugCheckerRefactoringTestHelper refactoringTestHelper =
      BugCheckerRefactoringTestHelper.newInstance(JUnitMethodDeclarationCheck.class, getClass());

  @Test
  void identification() {
    compilationTestHelper
        .addSourceLines(
            "A.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "import org.junit.jupiter.api.Test;",
            "import org.junit.jupiter.params.ParameterizedTest;",
            "",
            "class A {",
            "  @BeforeAll void beforeAll1() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeAll public void beforeAll2() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeAll protected void beforeAll3() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeAll private void beforeAll4() {}",
            "",
            "  @BeforeEach void setUp5() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeEach public void setUp6() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeEach protected void setUp7() {}",
            "  // BUG: Diagnostic contains:",
            "  @BeforeEach private void setUp8() {}",
            "",
            "  @AfterEach void tearDown1() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterEach public void tearDown2() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterEach protected void tearDown3() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterEach private void tearDown4() {}",
            "",
            "  @AfterAll void afterAll5() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterAll public void afterAll6() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterAll protected void afterAll7() {}",
            "  // BUG: Diagnostic contains:",
            "  @AfterAll private void afterAll8() {}",
            "",
            "  @Test void method1() {}",
            "  // BUG: Diagnostic contains:",
            "  @Test void testMethod2() {}",
            "  // BUG: Diagnostic contains:",
            "  @Test public void method3() {}",
            "  // BUG: Diagnostic contains:",
            "  @Test protected void method4() {}",
            "  // BUG: Diagnostic contains:",
            "  @Test private void method5() {}",
            "",
            "  @ParameterizedTest void method6() {}",
            "  // BUG: Diagnostic contains:",
            "  @ParameterizedTest void testMethod7() {}",
            "  // BUG: Diagnostic contains:",
            "  @ParameterizedTest public void method8() {}",
            "  // BUG: Diagnostic contains:",
            "  @ParameterizedTest protected void method9() {}",
            "  // BUG: Diagnostic contains:",
            "  @ParameterizedTest private void method10() {}",
            "",
            "  @BeforeEach @BeforeAll @AfterEach @AfterAll void testNonTestMethod1() {}",
            "  public void testNonTestMethod2() {}",
            "  protected void testNonTestMethod3() {}",
            "  private void testNonTestMethod4() {}",
            "  @Test void test5() {}",
            "}")
        .addSourceLines(
            "B.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "import org.junit.jupiter.api.Test;",
            "import org.junit.jupiter.params.ParameterizedTest;",
            "",
            "class B extends A {",
            "  @Override @BeforeAll void beforeAll1() {}",
            "  @Override @BeforeAll public void beforeAll2() {}",
            "  @Override @BeforeAll protected void beforeAll3() {}",
            "",
            "  @Override @BeforeEach void setUp5() {}",
            "  @Override @BeforeEach public void setUp6() {}",
            "  @Override @BeforeEach protected void setUp7() {}",
            "",
            "  @Override @AfterEach void tearDown1() {}",
            "  @Override @AfterEach public void tearDown2() {}",
            "  @Override @AfterEach protected void tearDown3() {}",
            "",
            "  @Override @AfterAll void afterAll5() {}",
            "  @Override @AfterAll public void afterAll6() {}",
            "  @Override @AfterAll protected void afterAll7() {}",
            "",
            "  @Override @Test void method1() {}",
            "  @Override @Test void testMethod2() {}",
            "  @Override @Test public void method3() {}",
            "  @Override @Test protected void method4() {}",
            "",
            "  @Override @ParameterizedTest void method6() {}",
            "  @Override @ParameterizedTest void testMethod7() {}",
            "  @Override @ParameterizedTest public void method8() {}",
            "  @Override @ParameterizedTest protected void method9() {}",
            "",
            "  @Override @BeforeEach @BeforeAll @AfterEach @AfterAll void testNonTestMethod1() {}",
            "  @Override public void testNonTestMethod2() {}",
            "  @Override protected void testNonTestMethod3() {}",
            "  @Override @Test void test5() {}",
            "}")
        .doTest();
  }

  @Test
  void replacement() {
    refactoringTestHelper
        .addInputLines(
            "in/A.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "import org.junit.jupiter.api.RepeatedTest;",
            "import org.junit.jupiter.api.Test;",
            "import org.junit.jupiter.params.ParameterizedTest;",
            "",
            "class A {",
            "  @BeforeAll public void setUp1() {}",
            "  @BeforeEach protected void setUp2() {}",
            "  @AfterEach private void setUp3() {}",
            "  @AfterAll private void setUp4() {}",
            "",
            "  @Test void testFoo() {}",
            "  @ParameterizedTest void testBar() {}",
            "",
            "  @Test public void baz() {}",
            "  @RepeatedTest(2) private void qux() {}",
            "  @ParameterizedTest protected void quux() {}",
            "}")
        .addOutputLines(
            "out/A.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "import org.junit.jupiter.api.RepeatedTest;",
            "import org.junit.jupiter.api.Test;",
            "import org.junit.jupiter.params.ParameterizedTest;",
            "",
            "class A {",
            "  @BeforeAll void beforeAll() {}",
            "  @BeforeEach void setUp() {}",
            "  @AfterEach void tearDown() {}",
            "  @AfterAll void afterAll() {}",
            "",
            "  @Test void foo() {}",
            "  @ParameterizedTest void bar() {}",
            "",
            "  @Test void baz() {}",
            "  @RepeatedTest(2) void qux() {}",
            "  @ParameterizedTest void quux() {}",
            "}")
        .doTest(TestMode.TEXT_MATCH);
  }

  @Test
  void replaceSetupAndTeardownMethods() {
    refactoringTestHelper
        .addInputLines(
            "in/A.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "",
            "class A {",
            "  @BeforeAll public void setUp1() {}",
            "  @BeforeEach protected void setUp2() {}",
            "  @AfterEach private void setUp3() {}",
            "  @AfterAll private void setUp4() {}",
            "}")
        .addOutputLines(
            "out/A.java",
            "import org.junit.jupiter.api.AfterAll;",
            "import org.junit.jupiter.api.AfterEach;",
            "import org.junit.jupiter.api.BeforeAll;",
            "import org.junit.jupiter.api.BeforeEach;",
            "",
            "class A {",
            "  @BeforeAll void beforeAll() {}",
            "  @BeforeEach void setUp() {}",
            "  @AfterEach void tearDown() {}",
            "  @AfterAll void afterAll() {}",
            "}")
        .doTest(TestMode.TEXT_MATCH);
  }
}
