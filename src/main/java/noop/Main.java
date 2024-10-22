package noop;

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

    // Read dimensions
    String[] dimensions = scanner.nextLine().split(", ");
    int rows = Integer.parseInt(dimensions[0]);
    int cols = Integer.parseInt(dimensions[1]);

    // Read the map
    char[][] map = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
      map[i] = scanner.nextLine().toCharArray();
    }

    // Read commands
    String[] commands = scanner.nextLine().split("\\s+");

    // Initialize variables
    int timeLeft = 16;
    int ctRow = -1, ctCol = -1;
    int bombRow = -1, bombCol = -1;

    // Find initial positions of counter-terrorist and bomb
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (map[r][c] == 'C') {
          ctRow = r;
          ctCol = c;
        } else if (map[r][c] == 'B') {
          bombRow = r;
          bombCol = c;
        }
      }
    }

    // Process commands
    for (String command : commands) {
      if (timeLeft <= 0) {
        System.out.println("Terrorists win!");
        System.out.println("Bomb was not defused successfully!");
        printMap(map, ctRow, ctCol);
        return;
      }

      if (command.equals("defuse")) {
        if (ctRow == bombRow && ctCol == bombCol) {
          if (timeLeft >= 4) {
            timeLeft -= 4;
            map[bombRow][bombCol] = 'D';
            System.out.println("Counter-terrorist wins!");
            System.out.println("Bomb has been defused: " + timeLeft + " second/s remaining.");
            printMap(map, ctRow, ctCol);
            return;
          } else {
            System.out.println("Terrorists win!");
            System.out.println("Bomb was not defused successfully!");
            printMap(map, ctRow, ctCol);
            return;
          }
        } else {
          timeLeft -= 2;
        }
      } else {
        int newRow = ctRow, newCol = ctCol;
        switch (command) {
          case "up":
            newRow = ctRow > 0 ? ctRow - 1 : ctRow;
            break;
          case "down":
            newRow = ctRow < rows - 1 ? ctRow + 1 : ctRow;
            break;
          case "left":
            newCol = ctCol > 0 ? ctCol - 1 : ctCol;
            break;
          case "right":
            newCol = ctCol < cols - 1 ? ctCol + 1 : ctCol;
            break;
        }

        if (newRow != ctRow || newCol != ctCol) {
          timeLeft--;
          ctRow = newRow;
          ctCol = newCol;
          if (map[ctRow][ctCol] == 'T') {
            map[ctRow][ctCol] = '*';
            System.out.println("Terrorists win!");
            printMap(map, ctRow, ctCol);
            return;
          }
        }
      }
    }

    System.out.println("Terrorists win!");
    System.out.println("Bomb was not defused successfully!");
    printMap(map, ctRow, ctCol);
  }

  private static void printMap(char[][] map, int ctRow, int ctCol) {
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (r == ctRow && c == ctCol) {
          System.out.print('C');
        } else {
          System.out.print(map[r][c]);
        }
      }
      System.out.println();
    }
  }
}
