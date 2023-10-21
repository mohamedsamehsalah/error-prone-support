package tech.picnic.errorprone.workshop.bugpatterns;

import com.google.errorprone.BugCheckerRefactoringTestHelper;
import com.google.errorprone.BugCheckerRefactoringTestHelper.TestMode;
import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

final class JUnitTestMethodDeclarationTest {
  @Disabled("Enable this to validate part 1.")
  @Test
  void identificationIllegalModifiers() {
    CompilationTestHelper.newInstance(JUnitTestMethodDeclaration.class, getClass())
        .addSourceLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  void method1() {}",
            "",
            "  @Test",
            "  // BUG: Diagnostic contains:",
            "  public void method2() {}",
            "",
            "  @Test",
            "  // BUG: Diagnostic contains:",
            "  protected void method3() {}",
            "",
            "  @Test",
            "  // BUG: Diagnostic contains:",
            "  private void method4() {}",
            "}")
        .doTest();
  }

  @Disabled("Enable this to validate part 1.")
  @Test
  void replacementIllegalModifiers() {
    BugCheckerRefactoringTestHelper.newInstance(JUnitTestMethodDeclaration.class, getClass())
        .addInputLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  void foo() {}",
            "",
            "  @Test",
            "  public void bar() {}",
            "",
            "  @Test",
            "  protected void baz() {}",
            "",
            "  @Test",
            "  private void qux() {}",
            "}")
        .addOutputLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  void foo() {}",
            "",
            "  @Test",
            "  void bar() {}",
            "",
            "  @Test",
            "  void baz() {}",
            "",
            "  @Test",
            "  void qux() {}",
            "}")
        .doTest(TestMode.TEXT_MATCH);
  }

  @Disabled("Enable this to validate part 2.")
  @Test
  void identificationMethodRename() {
    CompilationTestHelper.newInstance(JUnitTestMethodDeclaration.class, getClass())
        .addSourceLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "class A {",
            "  @Test",
            "  void test() {}",
            "",
            "  @Test",
            "  void method1() {}",
            "",
            "  @Test",
            "  // BUG: Diagnostic contains:",
            "  void testMethod2() {}",
            "",
            "  public void testNonTestMethod2() {}",
            "",
            "  protected void testNonTestMethod3() {}",
            "",
            "  private void testNonTestMethod4() {}",
            "}")
        .doTest();
  }

  @Disabled("Enable this to validate part 2.")
  @Test
  void replacementMethodRenames() {
    BugCheckerRefactoringTestHelper.newInstance(JUnitTestMethodDeclaration.class, getClass())
        .addInputLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  void testFoo() {}",
            "",
            "  @Test",
            "  void baz() {}",
            "}")
        .addOutputLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  void foo() {}",
            "",
            "  @Test",
            "  void baz() {}",
            "}")
        .doTest(TestMode.TEXT_MATCH);
  }

  @Disabled("Enable this for part 3.")
  @Test
  void identificationIgnoreEdgeCases() {
    CompilationTestHelper.newInstance(JUnitTestMethodDeclaration.class, getClass())
        .addSourceLines(
            "A.java",
            "import org.junit.jupiter.api.Test;",
            "",
            "class A {",
            "  @Test",
            "  public void testToString() {}",
            "",
            "  @Test",
            "  public void testOverload() {}",
            "",
            "  void overload() {}",
            "",
            "  @Test",
            "  private void testClass() {}",
            "",
            "  @Test",
            "  private void testTrue() {}",
            "}")
        .doTest();
  }
}
