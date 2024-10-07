package furnitures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.function.Predicate;

/** This is the main class of the application. It is the entry point of the program. */
public class Main {

  /**
   * This is the main method of the application. It is the entry point of the program.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Deque<Integer> females = new ArrayDeque<>();
    Deque<Integer> males = new ArrayDeque<>();
    String[] inputMales = scanner.nextLine().split("\\s+");
    String[] inputFemales = scanner.nextLine().split("\\s+");
    scanner.close();
    Predicate<Integer> isPositive = v -> v > 0;
    Arrays.stream(inputMales)
        .mapToInt(Integer::valueOf)
        .boxed()
        .filter(isPositive)
        .forEach(males::push);
    Arrays.stream(inputFemales)
        .mapToInt(Integer::valueOf)
        .boxed()
        .filter(isPositive)
        .forEach(females::offer);
    int matchesCount = 0;
    while (!females.isEmpty() && !males.isEmpty()) {
      int currentMale = males.peek();
      int currentFemale = females.peek();
      if (currentMale % 25 == 0) {
        males.pop();
        if (!males.isEmpty()) {
          males.pop();
        }
        continue;
      }
      if (currentFemale % 25 == 0) {
        females.poll();
        if (!females.isEmpty()) {
          females.poll();
        }
        continue;
      }
      if (currentMale == currentFemale) {
        matchesCount++;
        males.pop();
        females.poll();
      } else {
        int temp = currentMale;
        males.pop();
        females.poll();
        males.push(temp - 2);
      }
      males.removeIf(isPositive.negate());
      females.removeIf(isPositive.negate());
    }
    System.out.println("Matches: " + matchesCount);
    if (males.isEmpty()) {
      System.out.println("Males left: none");
    } else {
      System.out.print("Males left: ");
      System.out.println(
          String.join(", ", males.stream().map(String::valueOf).toArray(String[]::new)));
    }

    if (females.isEmpty()) {
      System.out.println("Females left: none");
    } else {
      boolean isFirst = false;
      System.out.print("Females left: ");
      System.out.println(
          String.join(", ", females.stream().map(String::valueOf).toArray(String[]::new)));
    }
  }
}
