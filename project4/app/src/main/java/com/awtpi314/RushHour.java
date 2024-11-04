package com.awtpi314;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

class RushHour {
  private static int walkBack(HashMap<String, String> visitedStates, HashMap<String, BoardState> states, String currentState) {
    int steps = 0;
    while (currentState != null) {
      steps++;
      states.get(currentState).printBoard();
      System.out.println();

      currentState = visitedStates.get(currentState);
    }
    return steps;
  }

  public static void main(String[] args) {
    VehicleNode[][] board = new VehicleNode[6][6];
    HashMap<String, String> visitedStates = new HashMap<>(); 
    HashMap<String, BoardState> states = new HashMap<>();
    
    Scanner sc = new Scanner(System.in);

    int numVehicles = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < numVehicles; i++) {
      int length = sc.nextLine().equals("car") ? 2 : 3;
      String carName = sc.nextLine();
      boolean isVertical = sc.nextLine().equals("v");
      int row = Integer.parseInt(sc.nextLine()) - 1;
      int col = Integer.parseInt(sc.nextLine()) - 1;
      for (int j = 0; j < length; j++) {
        board[row][col] = new VehicleNode(isVertical, carName);
        if (isVertical) {
          row++;
        } else {
          col++;
        }
      }
    }

    sc.close();

    BoardState initialState = new BoardState(board);
    visitedStates.put(initialState.getHash(), null);
    states.put(initialState.getHash(), initialState);
    System.out.println("Initial state:");
    initialState.printBoard();
    System.out.println();

    ArrayDeque<BoardState> queue = new ArrayDeque<>();
    queue.add(initialState);

    while (!queue.isEmpty()) {
      BoardState currentState = queue.poll();
      if (currentState == null) {
        System.out.println("No solution found.");
        break;
      }

      if (currentState.isSolved()) {
        System.out.println("Solved! in " + walkBack(visitedStates, states, currentState.getHash()) + " steps.");
        currentState.printBoard();
        break;
      }

      for (BoardState nextState : currentState.getNextStates()) {
        if (!visitedStates.containsKey(nextState.getHash())) {
          visitedStates.put(nextState.getHash(), currentState.getHash());
          queue.add(nextState);
        }
      }
    }
  }
}