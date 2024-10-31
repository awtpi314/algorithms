package com.awtpi314;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import javax.management.Query;

class RushHour {
  private static int vehicles;
  private static ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
  private static Map<String, String> vehicleMap;
  private static String[][] board = new String[6][6];
  private static HashMap boardStates = new HashMap<String[][], String>();
  private static Queue<String[][]> moves = new ArrayDeque<String[][]>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    vehicles = sc.nextInt();
    for (int i = 0; i < vehicles; i++) {

      int length = sc.nextLine().equals("car") ? 2 : 3;
      String name = sc.nextLine();
      String heading = sc.nextLine();
      int row = sc.nextInt();
      int col = sc.nextInt();

      // Vehicle vehicle = new Vehicle(name, heading, row, col, length);
      // vehicleList.add(vehicle);
    }

    // populate array and create heading map
    for (Vehicle vehicle : vehicleList) {
      int h = vehicle.getHeading() == "h" ? 1 : 0;
      int l = h == 0 ? 1 : 0;

      for (int i = 0; i < vehicle.getSize(); i++) {
        board[vehicle.getX() + i * h][vehicle.getY() + i * l] = vehicle.getName();
      }

      vehicleMap.put(vehicle.getName(), vehicle.getHeading());
    }

    // breadth first search
    // PROBLEM changing the vehicleList breaks all other cases
    while (true) {

      // configure vehicleList from board

      // find move
      for (Vehicle vehicle : vehicleList) {
        int h = vehicle.getHeading() == "h" ? 1 : 0;
        int l = h == 0 ? 1 : 0;

        if (board[vehicle.getX() + h][vehicle.getY() + l] == null) {
          // valid move 1
          String[][] nboard = board;
          nboard[vehicle.getX() + h][vehicle.getY() + l] = vehicle.getName();
          nboard[vehicle.getX() - h * vehicle.getSize()][vehicle.getY() - l * vehicle.getSize()] = null;
          if (!boardStates.containsKey(nboard))
            moves.add(nboard);
        }

        if (board[vehicle.getX() - h * vehicle.getSize()][vehicle.getY() - l * vehicle.getSize()] == null) {
          // valid move 2
          String[][] nboard = board;
          nboard[vehicle.getX() - h * vehicle.getSize()][vehicle.getY() - l * vehicle.getSize()] = vehicle.getName();
          nboard[vehicle.getX() + h][vehicle.getY() + l] = null;
          if (!boardStates.containsKey(nboard))
            moves.add(nboard);
        }

      }
      // checkMove for uniqueness and validity
      // if nboard == previos bourd ... dont do move

      // Enqueue move

      // no mor moves
      // deque and repeat

    }
  }
}