package csulb.cecs274;

import java.util.NoSuchElementException;

/**
 * Represents a sequence of characters that can be mutated.
 * @author Gideon Essel gideon.essel@student.csulb.edu
 * @author Christian Lam christian.lam@student.csulb.edu
 * @author Alvaro Monge alvaro.monge@csulb.edu
 */
public class LinkedCharacters {
   public static final Character CURSOR = '|';
   private class Node{
      Character character;
      Node next;
      public Node(Character c, Node link){
         this.character = c;
         this.next = link;
      }
   }

   private Node first;
   private Node last;
   private Node cursor;
   private Node previous;  // previous to Node at cursor
   private int size;

   /**
    * Creates a linked list of Character objects, one from each of the characters in the text.
    * @param text The characters to create the linked list from
    */
   public LinkedCharacters(String text) {
      if (text != null && ! text.isEmpty()) {
         char[] characters = text.toCharArray();
         this.first = new Node(characters[0], null);
         Node current = first;
         for (int i=1; i < text.length(); i++) {
            current.next = new Node(characters[i], null);
            current = current.next;
            size++;
         }
         last = current;

         cursor = this.first;
      }

      // otherwise, all Node instance variables remain null, their default value
   }

    /**
     * Creates an empty linked list of Character objects
     */
   public LinkedCharacters()
   {
      first = null;
      previous = null;
      cursor = null;
      previous = null;
   }

   /**
    * Insert a character at the current position of the cursor.
    * @param c the character to be inserted
    */
   public void insert(Character c) {
      // Creates a new Node to the character linked with the cursor
      Node nodeToBeInserted = new Node(c, cursor);
      // If the string is empty
      if(first == last)
      {
         first = nodeToBeInserted;
         previous = nodeToBeInserted;
         last = cursor;
         size ++;
      }
      // if cursor is in the front
      else if(cursor == first)
      {
         first = nodeToBeInserted;
         size ++;
      }
      // if the previous is also the last
      else if (previous == last)
      {
         previous.next = nodeToBeInserted;
         previous= nodeToBeInserted;
         last = nodeToBeInserted;
         size ++;
      }
      // insert in the middle
      else
      {
         previous.next = nodeToBeInserted;
         size ++;
      }


   }
   /**
    * Adds a character at the current position of the cursor.
    * @param c the character to be inserted
    */
   public void add(Character c)
   {
      // Creates new node to add
      Node currentNode = new Node(c,cursor);
      // Case if empty
      if(first == last)
      {
         first = currentNode;
         previous = currentNode;
         last = cursor;
         size ++;
      }
      else
       {
         previous.next = currentNode;
         previous = currentNode;
         size++;
       }

   }

    /**
     *Removes a character at the position of the cursor
     */
   public void remove()
   {
      // remove for case where cursor is at beginning
      if( cursor == first )
      {
         first = first.next;
         cursor = first;
         size--;
      }
      // remove for case where cursor is at the end
      else if ( cursor == last)
      {
         cursor.next = last;
         previous.next = null;
         size--;
      }
      // Removes for case of the middle.
      else
      {
         cursor = cursor.next;
         previous.next = cursor;
         size--;
      }
   }


    /**
     * Calls the recursive helper method() to reverse the list
     * with cursor being in the same position.
     */
   public void reverse() {

      // First number is the current node and the Second number is where the cursor is at
      int[] progressLog = {0,-1};
      if (first != null)
      {

         // Reverse the linked list

         last = reverse(first,progressLog);

      }
   }

   /**
    * Reverse the list starting at node, using a pair of integers in progressLog to keep
    * track of the progress the method makes in reversing the list.
    * @param node the node at which to start at
    * @param progressLog pair of integers to keep track of postions in the list
    * @return the reversed list of nodes
    */
   private Node reverse(Node node, int[] progressLog) {
      // Case of cursor being null
      if ( cursor == null )
      {
         progressLog[1] = -1;
      }
      // Case of cursor on node
      if ( cursor == node)
      {
         progressLog[1] = progressLog[0];
      }
      // If next node is null
      if( node.next == null)
      {
         first = node;
         return node;
      }
      else
       {
          // stores the next node into a new node
          Node followingPosition = node.next;
          // Counter increments each time we move to another node
          progressLog[0]++;
          // unlinks node to next node
          node.next = null;
          // stores the rest of nodes into new node
          Node others = reverse(followingPosition, progressLog);
          progressLog[0]--;
          if(size - progressLog[1] == progressLog[0])
          {
             cursor = node;
          }
          // references each node to each other
          followingPosition.next = node;
          return others;
       }
   }

    /**
     *Clears the Character objects in the list, leaving the list empty
     * @return String objects of Characters in the list
     */
   public String clear()
   {
      // Creates empty string
      String convertedNode = "";
      // creates node to iterate
      Node nodeIterator = first;
      // Loops through and converts node to character
      while(nodeIterator != null)
      {
         convertedNode += nodeIterator.character;
         nodeIterator = nodeIterator.next;
      }
      first = previous;
      size = 0;
      return convertedNode;
   }

   /**
    *
    * @return the size of the reference
    */
   public int getSize()
   {

      return this.size;
   }

   /**
    * @return true if there's a next character after the cursor
    */
   public boolean hasNext() {
      return cursor != null;
   }

   /**
    * Position the cursor at the first Character in the sequence
    */
   public void first() {
      cursor = first;
      previous = null;
   }

   /**
    * Move the cursor forward from its current position
    * @return the Character that was bypassed when the cursor was moved
    * @throws NoSuchElementException when the cursor is past the last character as there's no next
    */
   public Character next() throws NoSuchElementException {
      if (cursor == null) {
         throw new NoSuchElementException();
      }

      Character result = cursor.character;
      previous = cursor;
      cursor = cursor.next;
      return result;
   }

   /**
    * Overwrites the character at the current position with the character provided.
    * @param c the character that replaces the current one at the cursor's position in the sequence
    * @throws NoSuchElementException
    */
   public void set(Character c) throws NoSuchElementException {
      if (cursor == null)
         throw new NoSuchElementException();

      cursor.character = c;
   }

   /**
    * Provides a String representation of the sequence and the position of the cursor.
    * The cursor is represented by a vertical bar | and is shown immediately before
    * the character where the cursor is positioned.
    * @return the String representation, e.g.: "Go| Beach"
    */
   public String toString() {
      String result = "";
      Node nodeIterator = first;
      boolean isCursorInserted = false;
      while ( nodeIterator != null) {
         if ( nodeIterator == cursor) {
            result += CURSOR;
            isCursorInserted = true;
         }

         result +=  nodeIterator.character;
         nodeIterator = nodeIterator.next;
      }

      if ( (first == null) || (first != null && !isCursorInserted) )
         result += CURSOR;

      return result;
   }
}
