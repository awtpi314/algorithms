package com.awtpi314;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

/**
 * The GalacticBreakup class represents a program that determines the number of
 * months a galaxy spends disconnected after a series of monarchies break off.
 */
public class GalacticBreakup {
  private static int numSets = 0;
  private static int disconnectedMonths;
  private static DisjointNode[][][] galaxy;
  private static Deque<ArrayList<DisjointNode>> monarchies = new ArrayDeque<>();

  /**
   * Find the adjacent nodes to the given node that are not in the same set.
   * 
   * @param adjacentTo the node to find adjacent nodes to
   * @return an ArrayList of disconnected adjacent nodes
   */
  private static ArrayList<DisjointNode> findAdjacent(DisjointNode adjacentTo) {
    // We need a non-null node
    if (adjacentTo == null) {
      return null;
    }

    // Find the representative of the current node
    DisjointNode rep = adjacentTo.findRepresentative();

    // Extract the coordinates to make the code cleaner
    Location loc = adjacentTo.getLocation();
    int x = loc.getX();
    int y = loc.getY();
    int z = loc.getZ();

    // Empty list of adjacent nodes
    ArrayList<DisjointNode> adjacencies = new ArrayList<DisjointNode>();

    // For each if statements, we check that we are not out of bounds and that the
    // adjacent node is not in the same set
    if (x - 1 >= 0) {
      DisjointNode adjacent = galaxy[x - 1][y][z];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }
    if (x + 1 < galaxy.length) {
      DisjointNode adjacent = galaxy[x + 1][y][z];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }
    if (y - 1 >= 0) {
      DisjointNode adjacent = galaxy[x][y - 1][z];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }
    if (y + 1 < galaxy[0].length) {
      DisjointNode adjacent = galaxy[x][y + 1][z];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }
    if (z - 1 >= 0) {
      DisjointNode adjacent = galaxy[x][y][z - 1];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }
    if (z + 1 < galaxy[0][0].length) {
      DisjointNode adjacent = galaxy[x][y][z + 1];
      if (adjacent != null && adjacent.findRepresentative() != rep) {
        adjacencies.add(adjacent);
      }
    }

    return adjacencies;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Parse the size of the galaxy and the number of dominions breaking off
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();
    int l = sc.nextInt();

    galaxy = new DisjointNode[n][m][k];

    // Read in the dominions and store them in a stack
    for (int i = 0; i < l; i++) {
      int dominions = sc.nextInt();
      ArrayList<DisjointNode> monarchy = new ArrayList<DisjointNode>();
      // Construct the monarchies from the input numbers
      for (int j = 0; j < dominions; j++) {
        Location loc = new Location(sc.nextInt(), n, m, k);
        monarchy.add(DisjointNode.makeSet(loc));
      }
      monarchies.push(monarchy);
    }

    sc.close();

    // Add the monarchies by domionion to the galaxy
    while (!monarchies.isEmpty()) {
      ArrayList<DisjointNode> monarchy = monarchies.pop();
      for (var dominion : monarchy) {
        // Add the dominion to the galaxy
        Location loc = dominion.getLocation();
        galaxy[loc.getX()][loc.getY()][loc.getZ()] = dominion;
        // We have a new set
        numSets++;

        // Find adjacent nodes and union them if they are not in the same set
        ArrayList<DisjointNode> adjacencies = findAdjacent(dominion);
        for (var adjacent : adjacencies) {
          if (!adjacent.findRepresentative().equals(dominion.findRepresentative())) {
            // Keep track of how many sets we have for the disconnected test
            numSets--;
            dominion.union(adjacent);
          }
        }
      }
      // If we have more than one set, we are disconnected this month
      if (numSets > 1) {
        disconnectedMonths++;
      }
    }

    System.out.println(disconnectedMonths);
  }
}
