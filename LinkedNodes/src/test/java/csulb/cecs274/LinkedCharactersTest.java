package csulb.cecs274;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for LinkedCharacters class
 * @author Christian Lam christian.lam@student.csulb.edu
 * @author Gideon Essel gideon.essel@student.csulb.edu
 * @author Alvaro Monge alvaro.monge@csulb.edu
 */
public class LinkedCharactersTest {
   @Test
   public void testInsertEmptyList() {
      LinkedCharacters text = new LinkedCharacters("");
      text.insert('0');
      assertEquals("0|", text.toString());
      text.insert('1');
      assertEquals("01|", text.toString());
   }

   @Test
   public void testInsertStart() {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.insert('0');
      assertEquals("0|Java", text.toString());
   }

   @Test
   public void testInsertMiddle() {
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
   public void testInsertLast() {
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
   public void testAddEnd()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.next();
      text.next();
      text.next();
      text.add('1');
      assertEquals("Java1|", text.toString());
      text.add('0');
      assertEquals("Java10|", text.toString());

   }

   @Test
   public void testAddEmpty()
   {
      LinkedCharacters text = new LinkedCharacters("");
      text.add('2');
      assertEquals("2|", text.toString());
   }

   @Test
   public void removeCharacterFront()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.remove();
      assertEquals("|ava", text.toString());
   }

   @Test
   public void removeCharacterMid()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.next();
      text.remove();
      assertEquals("Ja|a", text.toString());
   }
   @Test
   public void removeCharacterEnd()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.next();
      text.next();
      text.remove();
      assertEquals("Jav|", text.toString());
   }

   @Test
   public void removeCharacters()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      text.next();
      text.remove();
      assertEquals("J|va", text.toString());
      text.next();
      text.remove();
      assertEquals("Jv|", text.toString());

   }



   @Test
   public void reverseCharacters()
   {
      LinkedCharacters text = new LinkedCharacters("Go Beach");
      text.next();
      text.next();
      text.next();
      text.reverse();
      assertEquals("hca|eB oG", text.toString());
      text.reverse();
      assertEquals("Go |Beach", text.toString());
      text.next();
      text.reverse();
      assertEquals("hcae|B oG",text.toString());
      text.reverse();
      assertEquals("Go B|each",text.toString());

   }

   @Test
   public void clearCharacters()
   {
      LinkedCharacters text = new LinkedCharacters("Java");
      assertEquals("Java", text.clear());
   }
}

