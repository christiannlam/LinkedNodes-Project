# HW 3 Grading

## Score: 70 out of 100 points

## Feedback from code review

### contributions.txt
> Christian Lam - 50
> Gideon Essel - 50

### JavaDoc Comments: 15 out of 15 points
* Inaccurate comment of public method `reverse()`. The comment needs to describe the purpose of the method, not how it does its work. Someone should be able to read it to determine if it's a method they want to use. The comment should not describe how it wordk ("Calls the recursive helper method")

### Code quality: 20 out of 35 points

#### Update size instance variable
* Incorrect update of size in `LinkedCharacters(String)`. The loop starts at index 1 because index 0 is done prior to start of the loop, thus, the update of size does not take into account element at index 0 and is therefore off by 1.

#### LinkedCharacters()
* There are 5 instance variables but only 4 of them are assigned values. Fortunately, the default value used by Java is appropriate in this case.

#### insert()
* Repetitive code: increment of size

#### add()
* Repetitive code: increment of size
* **Incorrect**, several errors:
  * Incorrect to have the new node link to the node referenced by cursor. The cursor might be anywhere in the list, yet, the method needs to add the node as the last one. Instead, the method adds the node wherever the cursor happens to be, working similar to insert.
  * Incorrect condition for "Case if empty". A list is empty when first is null, not when `first == last`. 
  * when cursor is past the end, then previous must be last. Since this method introduces a new node, then, previous needs to be that new node since cursor must remain past the end.


#### remove()
* Repetitive code: decrement of size
* **Incorrect**, several errors:
  * when list is empty, all instance variables are null and an attempt to call `remove()` will result in `NullPointerException` at line 132.
  * when cursor is last, there has to be a new last node, but the method never updates last. 

#### reverse()
* **Incorrect** in the case where the cursor is at the first element. In this case, the code never finds where the cursor is and thus it incorrectly remains unchanged.
* Lines 180-183 are unnecessary as `progressLog[1]` is initially -1 due to line 160.


#### clear()
* **Incorrect**, when a list is cleared, it needs to be empty and an empty list is one where all instance variables of type Node are null and size is 0 -- as was established by `LinkedCharacters()` at line 53.
* Why set first to be previous? Why would this instruction be needed in clearing the list?

#### Complete set of unit tests
* **Insufficient tests**. More tests would have revealed the problems identified here. For example, no unit test attempts to call `remove()` on an empty list.
* No test for the size

## Running unit tests: 35 out of 50 points
* Testing never ended.
* Had to manually narrow down source of the problem
* This test **testRemoveCursorAtLastThenAdd()** revealed the problem, specifically the **remove() followed by add() made the list circular**, so the toString() method will run infinitely.
* Disabling (skipping) 1 test
```
-------------------------------------------------------------------------------
Test set: csulb.cecs274.LinkedCharactersEvaluation
-------------------------------------------------------------------------------
Tests run: 33, Failures: 11, Errors: 3, Skipped: 1, Time elapsed: 0.179 s <<< FAILURE! - in csulb.cecs274.LinkedCharactersEvaluation
testAddCursorAtMiddle  Time elapsed: 0.022 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <J|ava0> but was: <J0|ava>
	at csulb.cecs274.LinkedCharactersEvaluation.testAddCursorAtMiddle(LinkedCharactersEvaluation.java:106)

testReverseListOf2CursortAtFirst  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <|yx> but was: <y|x>
	at csulb.cecs274.LinkedCharactersEvaluation.testReverseListOf2CursortAtFirst(LinkedCharactersEvaluation.java:265)

testSizeRemoveCursorAtLastThenAdd  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <3> but was: <2>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeRemoveCursorAtLastThenAdd(LinkedCharactersEvaluation.java:221)

testRemoveFromEmptyList  Time elapsed: 0.004 s  <<< ERROR!
java.lang.NullPointerException
	at csulb.cecs274.LinkedCharactersEvaluation.testRemoveFromEmptyList(LinkedCharactersEvaluation.java:154)

testSizeRemoveTheOnlyCharacter  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <1> but was: <0>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeRemoveTheOnlyCharacter(LinkedCharactersEvaluation.java:176)

testSizeAddCursorPastEnd  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <5> but was: <4>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeAddCursorPastEnd(LinkedCharactersEvaluation.java:148)

testPiazzaPost120Example  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <8> but was: <7>
	at csulb.cecs274.LinkedCharactersEvaluation.testPiazzaPost120Example(LinkedCharactersEvaluation.java:349)

testClearListOfSix  Time elapsed: 0.001 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <|> but was: <3|456>
	at csulb.cecs274.LinkedCharactersEvaluation.testClearListOfSix(LinkedCharactersEvaluation.java:335)

testRemoveCursorPastEnd  Time elapsed: 0.001 s  <<< ERROR!
java.lang.NullPointerException
	at csulb.cecs274.LinkedCharactersEvaluation.testRemoveCursorPastEnd(LinkedCharactersEvaluation.java:236)

testSizeAddCursorAtMiddle  Time elapsed: 0.001 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <6> but was: <5>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeAddCursorAtMiddle(LinkedCharactersEvaluation.java:122)

testSizeInsertCursorAtFirst  Time elapsed: 0.003 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <4> but was: <3>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeInsertCursorAtFirst(LinkedCharactersEvaluation.java:54)

testSizeRemoveFromEmptyList  Time elapsed: 0.001 s  <<< ERROR!
java.lang.NullPointerException
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeRemoveFromEmptyList(LinkedCharactersEvaluation.java:169)

testSizeRemoveCursorAtMiddle  Time elapsed: 0.001 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <9> but was: <8>
	at csulb.cecs274.LinkedCharactersEvaluation.testSizeRemoveCursorAtMiddle(LinkedCharactersEvaluation.java:195)

testAddIntoEmptyList  Time elapsed: 0.002 s  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <|A> but was: <A|>
	at csulb.cecs274.LinkedCharactersEvaluation.testAddIntoEmptyList(LinkedCharactersEvaluation.java:89)
```
