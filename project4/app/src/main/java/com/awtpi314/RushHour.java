package com.awtpi314;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class RushHour {
  public static void main(String[] args) {
    HashMap<BoardState, BoardState> visited = new HashMap<>();
    ArrayDeque<BoardState> stateQueue = new ArrayDeque<>();
    Scanner scanner = new Scanner(System.in);

    int numOfCars = Integer.parseInt(scanner.nextLine());

    String[][] originalBoard = new String[6][6];
    for (int i = 0; i < numOfCars; i++) {
      int carLength = scanner.nextLine().equals("car") ? 2 : 3;
      String carName = scanner.nextLine();
      String carDir = scanner.nextLine();
      int row = Integer.parseInt(scanner.nextLine()) - 1;
      int col = Integer.parseInt(scanner.nextLine()) - 1;
      for (int j = 0; j < carLength; j++) {
        originalBoard[row][col] = carName;
        if (carDir.equals("h")) {
          col++;
        } else {
          row++;
        }
      }
    }

    scanner.close();

    BoardState start = new BoardState(originalBoard);
    visited.put(start, start);

    stateQueue.add(start);

    while (!stateQueue.isEmpty()) {
      BoardState current = stateQueue.poll();
      if (current == null) {
        System.out.println("No solution found.");
        break;
      }

      if (current.isSolved()) {
        walkBack(visited, current, 0);
        break;
      }

      for (BoardState next : current.getNextMoves()) {
        if (!visited.containsKey(next)) { 
          visited.put(next, current);
          stateQueue.add(next);
        }
      }
    }
  }

  private static void walkBack(HashMap<BoardState, BoardState> visited, BoardState current, int depth) {
    if (current != null) {
      walkBack(visited, visited.get(current), depth + 1);
      System.out.println(current.getLastMove());
    } else {
      System.out.println(depth + " moves:");
    }
  }
}
