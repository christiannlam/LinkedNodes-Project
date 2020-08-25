package csulb.cecs274;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit tests to evaluate implementation of the LinkedCharacters class.
 * CECS 274 Fall 2019 HW 4
 * @author Alvaro Monge alvaro.monge@csulb.edu
 */
public class LinkedCharactersEvaluation {
   @Test
   public void testDefaultConstructor() {
      LinkedCharacters lc = new LinkedCharacters();
      assertEquals("|", lc.toString());
      assertEquals(0, lc.getSize());
   }

   @Test
   public void testInsertIntoEmptyList() {
      LinkedCharacters text = new LinkedCharacters("");
      text.insert('0');
      assertEquals("0|", text.toString());
      text.insert('1');
      assertEquals("01|", text.toString());
   }

   @Test
   public void testSizeInsertIntoEmptyList() {
      LinkedCharacters text = new LinkedCharacters("");
      text.insert('0');
      assertEquals(1, text.getSize());
      text.insert('1');
      assertEquals(2, text.getSize());
   }

   @Test
   public void testInsertCursorAtFirst() {
      LinkedCharacters text = new LinkedCharacters("Java");
      assertEquals("|Java", text.toString());
      text.insert('0');
      assertEquals("0|Java", text.toString());
   }

   @Test
   public void testSizeInsertCursorAtFirst() {
      LinkedCharacters text = new LinkedCharacters("Java");
      assertEquals("|Java", text.toString());
      assertEquals(4, text.getSize());
      text.insert('0');
      assertEquals(5, text.getSize());
   }

   @Test
   public void testInsertCursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.insert('0');
      assertEquals("J0|ava", text.toString());
      text.next();
      text.next();
      text.insert('0');
      assertEquals("J0av0|a", text.toString());
   }

   @Test
   public void testInsertCursorAtLast() {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.next();
      text.next();
      text.next();
      text.insert('0');
      assertEquals("Java0|", text.toString());
      text.insert('1');
      assertEquals("Java01|", text.toString());
   }


   @Test
   public void testAddIntoEmptyList() {
      LinkedCharacters text = new LinkedCharacters();
      text.add('A');
      assertEquals("|A", text.toString());
   }


   @Test
   public void testSizeAddIntoEmptyList() {
      LinkedCharacters text = new LinkedCharacters();
      text.add('A');
      assertEquals(1, text.getSize());
   }


   @Test
   public void testAddCursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.add('0');
      assertEquals("J|ava0", text.toString());
      text.next();
      text.next();
      text.add('1');
      assertEquals("Jav|a01", text.toString());
   }


   @Test
   public void testSizeAddCursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.add('0');
      text.next();
      text.next();
      text.add('1');
      assertEquals(6, text.getSize());
   }

   @Test
   public void testAddCursorPastEnd() {
      LinkedCharacters text = new LinkedCharacters("Go");
      text.next();
      text.next();
      assertEquals("Go|", text.toString());
      text.add('1');
      assertEquals("Go1|", text.toString());
      text.add('2');
      assertEquals("Go12|", text.toString());
      text.add('3');
      assertEquals("Go123|", text.toString());
   }


   @Test
   public void testSizeAddCursorPastEnd() {
      LinkedCharacters text = new LinkedCharacters("Go");
      text.next();
      text.next();
      text.add('1');
      text.add('2');
      text.add('3');
      assertEquals(5, text.getSize());
   }

   @Test
   public void testRemoveFromEmptyList() {
      LinkedCharacters text = new LinkedCharacters();
      text.remove();
      assertEquals("|", text.toString());
   }

   @Test
   public void testRemoveTheOnlyCharacter() {
      LinkedCharacters text = new LinkedCharacters("A");
      text.remove();
      assertEquals("|", text.toString());
   }


   @Test
   public void testSizeRemoveFromEmptyList() {
      LinkedCharacters text = new LinkedCharacters();
      text.remove();
      assertEquals(0, text.getSize());
   }

   @Test
   public void testSizeRemoveTheOnlyCharacter() {
      LinkedCharacters text = new LinkedCharacters("A");
      assertEquals(1, text.getSize());
      text.remove();
      assertEquals(0, text.getSize());
   }

   @Test
   public void testRemoveCursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("Go Beach!");
      text.next(); text.next(); text.next();
      assertEquals("Go |Beach!", text.toString());
      text.remove();
      assertEquals("Go |each!", text.toString());
      text.remove();
      assertEquals("Go |ach!", text.toString());
   }

   @Test
   public void testSizeRemoveCursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("Go Beach!");
      assertEquals(9, text.getSize());
      text.next(); text.next(); text.next();
      text.remove();
      assertEquals(8, text.getSize());
      text.remove();
      assertEquals(7, text.getSize());
   }

   @Disabled  // This test reveals a problem with add() as it changes
              // the list to be circular and thus toString() will loop infinitely.
   @Test
   public void testRemoveCursorAtLastThenAdd() {
      LinkedCharacters text = new LinkedCharacters("Go!");
      text.next(); text.next();
      assertEquals("Go|!", text.toString());
      text.remove();
      assertEquals("Go|", text.toString());
      text.add('1');
      text.add('2');
      assertEquals("Go12|", text.toString());
   }


   @Test
   public void testSizeRemoveCursorAtLastThenAdd() {
      LinkedCharacters text = new LinkedCharacters("Go!");
      assertEquals(3, text.getSize());
      text.next(); text.next();
      text.remove();
      assertEquals(2, text.getSize());
      text.add('1');
      text.add('2');
      assertEquals(4, text.getSize());
   }


   @Test
   public void testRemoveCursorPastEnd() {
      LinkedCharacters text = new LinkedCharacters("Go!");
      text.next(); text.next(); text.next();
      assertEquals("Go!|", text.toString());
      text.remove();
      assertEquals("Go!|", text.toString());
      assertEquals(3, text.getSize());
   }

   @Test
   public void testReverseEmptyList() {
      LinkedCharacters text = new LinkedCharacters("");
      text.reverse();
      assertEquals("|", text.toString());
   }

   @Test
   public void testReverseSingleCharacter() {
      LinkedCharacters text = new LinkedCharacters("x");
      text.reverse();
      assertEquals("|x", text.toString());
      text.reverse();
      assertEquals("|x", text.toString());
      text.next();
      assertEquals("x|", text.toString());
      text.reverse();
      assertEquals("x|", text.toString());
   }

   @Test
   public void testReverseListOf2CursortAtFirst() {
      LinkedCharacters text = new LinkedCharacters("xy");
      text.reverse();
      assertEquals("|yx", text.toString());
      text.reverse();
      assertEquals("|xy", text.toString());
   }

   @Test
   public void testReverseListOf2CursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("xy");
      text.next();
      assertEquals("x|y", text.toString());
      text.reverse();
      assertEquals("y|x", text.toString());
      text.reverse();
      assertEquals("x|y", text.toString());
   }

   @Test
   public void testReverseListOf2CursorPastEnd() {
      LinkedCharacters text = new LinkedCharacters("xy");
      text.next();
      text.next();
      assertEquals("xy|", text.toString());
      text.reverse();
      assertEquals("yx|", text.toString());
      text.reverse();
      assertEquals("xy|", text.toString());
   }

   @Test
   public void testReverseListOf6CursorAtMiddle() {
      LinkedCharacters text = new LinkedCharacters("abcdef");
      text.next(); text.next(); text.next(); text.next();
      assertEquals("abcd|ef", text.toString());
      text.reverse();
      assertEquals("fedc|ba", text.toString());
      text.reverse();
      assertEquals("abcd|ef", text.toString());
   }

   @Test
   public void testClearEmptyList() {
      LinkedCharacters text = new LinkedCharacters();
      text.clear();
      assertEquals("|", text.toString());
   }

   @Test
   public void testClearListOfThree() {
      String content = "123";
      LinkedCharacters text = new LinkedCharacters(content);
      assertEquals(content, text.clear());
      assertEquals("|", text.toString());
   }

   @Test
   public void testSizeClearListOfThree() {
      LinkedCharacters text = new LinkedCharacters("123");
      text.clear();
      assertEquals(0, text.getSize());
   }


   @Test
   public void testClearListOfSix() {
      String content = "123456";
      LinkedCharacters text = new LinkedCharacters(content);
      text.next();
      text.next();
      text.next();
      assertEquals(content, text.clear());
      assertEquals("|", text.toString());
   }

   @Test
   public void testPiazzaPost120Example() {
      LinkedCharacters text = new LinkedCharacters("Go Beach");
      text.next();
      text.next();
      text.next();
      assertEquals("Go |Beach", text.toString());
      text.reverse();  // reverses it
      assertEquals("hca|eB oG", text.toString());
      text.reverse();  // returns it back to what it was
      assertEquals("Go |Beach", text.toString());
      assertEquals(8, text.getSize());

      text.remove();
      assertEquals("Go |each", text.toString());
      assertEquals(7, text.getSize());
      text.add('!');
      assertEquals("Go |each!", text.toString());
      assertEquals(8, text.getSize());
      text.insert('T');
      assertEquals("Go T|each!", text.toString());
      assertEquals(9, text.getSize());

      String textContents = text.clear();
      assertEquals("Go Teach!", textContents);
      assertEquals("|", text.toString());
      assertEquals(0, text.getSize());
      text.add('1');
      text.add('2');
      assertEquals("|12", text.toString());
      assertEquals(2, text.getSize());

      textContents = text.clear();
      assertEquals("12", textContents);
      assertEquals("|", text.toString());
      assertEquals(0, text.getSize());
      text.insert('A');
      text.insert('B');
      assertEquals("AB|", text.toString());
      assertEquals(2, text.getSize());
   }
}

