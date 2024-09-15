package furnitures;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/** This is the main class of the application. It is the entry point of the program. */
public class Main {
  /**
   * This is the main method of the application. It is the entry point of the program.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int start = 0; start < input.length(); ++start) {
      char ch = input.charAt(start);
      if (ch == '(') {
        stack.push(start);
      } else if (ch == ')') {
        var match = input.substring(stack.pop(), start + 1);
        System.out.println(match);
      }
    }
  }
}
