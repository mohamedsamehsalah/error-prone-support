# Error Prone Workshop

The slides of the workshop:

[EPS Workshop JFall][eps-workshop-jfall]

## Initial setup of the workshop

1. Start with forking `PicnicSupermarket/error-prone-support` repository on
   GitHub. Make sure to unselect `Copy the master branch only`.
2. Set the `workshop` branch as the `default` branch in the fork. Go to
   `Settings -> General -> Default Branch` for this.
3. Go to the `Actions` tab of your repository and click the green `Enable`
   button to allow running of workflows.
4. Clone the repository locally.
5. Make sure to run a `mvn clean install` in the root of this repository.
6. Open your code editor and familiarize yourself with the project structure.

If you are a macOS user, please run the following commands:
* `brew install grep`
* `brew install gsed`

Our testing framework relies on `grep` and `sed`. 

To verify that the test setup works, please run this command:

```sh
./integration-tests/checkstyle-10.12.4.sh
```

The script should end with the following text:
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:49 min
[INFO] Finished at: 2023-10-31T19:56:46+01:00
[INFO] ------------------------------------------------------------------------
Inspecting changes...
Inspecting emitted warnings...
```

Now the project is ready for the rest of the workshop.

Important to know is that for every commit and pull request a GitHub Action
will be triggered to run the integration test. After a failure, there is an
option to download artifacts that contain information on the changes that are
introduced by the new rules.


## Part 1: Writing Refaster rules

During this part of the workshop we will implement multiple Refaster rules.

Go to the `workshop` module and open the
`tech.picnic.errorprone.workshop.refasterrules` package. There you can find one
example and 5 different exercises to do.
Make sure to check out the `WorkshopRefasterRulesTest.java` class where you can
enable tests. Per assignment there is a test in this class that one can enable
(by dropping the `@Disabled` annotation) which runs the test. The assignment is
to implement or improve the Refaster rule such that the build succeeds.

Tips:
* The `XXX:` comments explains what needs to happen.
* See the associated test cases of the Refaster rule by looking for the name of the Refaster rule
  prefixed with `test`. For example, the `WorkshopAssignment0Rules.java` rule collection has a Refaster rule named `ExampleStringIsEmpty`.
  In the `WorkshopAssignment0RulesTestInput.java` and `WorkshopAssignment0RulesTestOutput.java` file there is 
  a `testExampleStringIsEmpty` method that shows the input and output to test the Refaster rule.

### Validating changes with the integration tests

After finishing the 5 assignments, commit and push the result. This will trigger the integration test
which will run for about 10 minutes. When the build finished, inspect the uploaded artifacts
and look at the file containing `diff-of-diffs` in the name. It shows the impact of the rules that you
wrote when they are applied on Checkstyle!

If you want to test this locally, run the following commands:

```sh
mvn clean install -DskipTests -Dverification.skip
./integration-tests/checkstyle-10.12.4.sh
```


## Part 2: Writing Error Prone checks

During this part of the workshop we will implement multiple Error Prone BugCheckers.
The assignments are split in different parts. Make sure to get the associated tests green. 
Drop the `@Disabled` annotation to run the test. Every `BugChecker` has an associated test class
which has the same class name but suffixed with `Test`. 
Go to the 

A recommended order of solving the assignments (but not required):
1. `DeleteEmptyMethod`
2. `JUnitMethodDeclaration`

Some utility classes that you can use:
* ASTHelpers - contains many common operations on the AST.
* Types - for doing comparisons between Java types.
* SuggestedFixes - contains helper methods for creating fixes of BugPatterns.


### Validating changes with the integration tests

After finishing the 2 assignments, commit and push the result. This will trigger the integration test
which will run for about 10 minutes. When the build finished, inspect the uploaded artifacts
and look at the file containing `diff-of-diffs` in the name. It shows the impact of the rules that you
wrote when they are applied on Checkstyle!

If you want to test this locally, run the following commands:

```sh
mvn clean install -DskipTests -Dverification.skip
./integration-tests/checkstyle-10.12.4.sh
```


## Part 3: Bring your own rule!

_In case you don't have a rule, feel free to continue with the previous
exercises._

This part of the workshop is about implementing your own idea!

You can use this chance to automatically:
* fix code style issues,
* enforce common coding guidelines,
* rewrite bug patterns,
* or make your codebase more consistent.

If you have an idea, feel free to mention this during the workshop and get some
ideas on how to implement it!

[eps-workshop-jfall]: https://drive.google.com/file/d/14UiIZNJxpiFSnHNjXVAyvWex2WWXQ8Ln/view
