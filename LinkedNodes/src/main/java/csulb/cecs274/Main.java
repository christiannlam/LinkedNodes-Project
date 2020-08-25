package csulb.cecs274;

import java.util.Scanner;

/**
 * Simple user interactive program to try out LinkedCharacters.
 * @author Alvaro Monge alvaro.monge@csulb.edu
 */
public class Main {

   public static final String INITIAL_CONTENT = "Java";
   public static void main(String[] args) {
      LinkedCharacters text = new LinkedCharacters(INITIAL_CONTENT);
      System.out.println(text);
      boolean done = false;

      Scanner userInput = new Scanner(System.in);
      String commandWithOptions = null;
      String command = null;
      String option = null;

      while (!done && userInput.hasNext()) {
         commandWithOptions = userInput.nextLine().strip();
         int separatorIndex = commandWithOptions.indexOf(' ');
         if (separatorIndex == -1)
            command = commandWithOptions;
         else {
            command = commandWithOptions.substring(0, separatorIndex);
            option = commandWithOptions.substring(separatorIndex + 1).strip();
         }
         switch (command.toLowerCase()) {
            case "f": case "first":
               text.first();
               break;
            case "n":
            case "next":
               if (text.hasNext())
                  text.next();
               else
                  System.out.println("Already at the end, there's no next");
               break;
            case "i":
            case "insert":
               if (option == null || option.isEmpty())
                  text.insert(' ');
               else {
                  for (Character c : option.toCharArray())
                     text.insert(c);
               }
               break;
            case "set":
               if (option != null && !option.isEmpty())
                  text.set(option.charAt(0));
               break;
            case "quit":
               done = true;
               break;
            default:
               System.out.println("Not a command, try again");
         }
         System.out.printf("Text = :%s:\n", text);
      }
   }
}
