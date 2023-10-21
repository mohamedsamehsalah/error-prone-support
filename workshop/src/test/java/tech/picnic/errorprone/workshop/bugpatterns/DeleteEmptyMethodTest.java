package tech.picnic.errorprone.workshop.bugpatterns;

import com.google.errorprone.BugCheckerRefactoringTestHelper;
import com.google.errorprone.BugCheckerRefactoringTestHelper.TestMode;
import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

final class DeleteEmptyMethodTest {
  @Disabled("Implement the logic in `DeleteEmptyMethod.java`")
  @Test
  void identification() {
    CompilationTestHelper.newInstance(DeleteEmptyMethod.class, getClass())
        .addSourceLines(
            "A.java",
            "class A {",
            "  Object m1() {",
            "    return null;",
            "  }",
            "",
            "  void m2() {",
            "    System.out.println(42);",
            "  }",
            "",
            "  // BUG: Diagnostic contains:",
            "  static void m3() {}",
            "",
            "  interface F {",
            "    void fun();",
            "  }",
            "}")
        .addSourceLines(
            "B.java",
            "final class B implements A.F {",
            "  @Override",
            "  public void fun() {}",
            "",
            "  /** Javadoc. */",
            "  // BUG: Diagnostic contains:",
            "  void m4() {}",
            "}")
        .doTest();
  }

  @Disabled("Implement the logic in `DeleteEmptyMethod.java`")
  @Test
  void replacement() {
    BugCheckerRefactoringTestHelper.newInstance(DeleteEmptyMethod.class, getClass())
        .addInputLines(
            "A.java",
            "final class A {",
            "",
            "  void instanceMethod() {}",
            "",
            "  static void staticMethod() {}",
            "",
            "  static void staticMethodWithComment() {",
            "    System.out.println(42);",
            "  }",
            "}")
        .addOutputLines(
            "A.java",
            "final class A {",
            "",
            "  static void staticMethodWithComment() {",
            "    System.out.println(42);",
            "  }",
            "}")
        .doTest(TestMode.TEXT_MATCH);
  }
}
