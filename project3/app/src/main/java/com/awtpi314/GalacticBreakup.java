package com.awtpi314;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GalacticBreakup {
  private static int numSets = 0;
  private static int disconneectedMonths;
  private static DisjointNode[][][] galaxy;
  private static Stack<ArrayList<DisjointNode>> monarchies = new Stack<>();

  private static ArrayList<DisjointNode> findAdjacent(DisjointNode adjacentTo) {
    if (adjacentTo == null) {
      return null;
    }

    DisjointNode parent = adjacentTo.findRepresentative();

    Location loc = adjacentTo.getLocation();
    int x = loc.getX();
    int y = loc.getY();
    int z = loc.getZ();

    ArrayList<DisjointNode> adjacencies = new ArrayList<DisjointNode>();

    if (x - 1 > 0) {
      DisjointNode adjacent = galaxy[x - 1][y][z];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }
    if (x + 1 < galaxy.length) {
      DisjointNode adjacent = galaxy[x + 1][y][z];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }
    if (y - 1 > 0) {
      DisjointNode adjacent = galaxy[x][y - 1][z];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }
    if (y + 1 < galaxy[0].length) {
      DisjointNode adjacent = galaxy[x][y + 1][z];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }
    if (z - 1 > 0) {
      DisjointNode adjacent = galaxy[x][y][z - 1];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }
    if (z + 1 < galaxy[0][0].length) {
      DisjointNode adjacent = galaxy[x][y][z + 1];
      if (adjacent != null && adjacent.findRepresentative() != parent) {
        adjacencies.add(adjacent);
      }
    }

    return adjacencies;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n, m, k, l;
    n = sc.nextInt();
    m = sc.nextInt();
    k = sc.nextInt();
    l = sc.nextInt();

    galaxy = new DisjointNode[n][m][k];

    for (int i = 0; i < l; i++) {
      int dominions = sc.nextInt();
      ArrayList<DisjointNode> monarchy = new ArrayList<DisjointNode>();
      for (int j = 0; j < dominions; j++) {
        Location loc = new Location(sc.nextInt(), n, m, k);
        monarchy.add(DisjointNode.makeSet(loc));
      }
      monarchies.push(monarchy);
    }

    sc.close();

    for (var monarchy : monarchies) {
      for (var dominion : monarchy) {
        Location loc = dominion.getLocation();
        galaxy[loc.getX()][loc.getY()][loc.getZ()] = dominion;
        numSets++;

        ArrayList<DisjointNode> adjacencies = findAdjacent(dominion);
        for (var adjacent : adjacencies) {
          if (!adjacent.findRepresentative().equals(dominion.findRepresentative())) {
            numSets--;
            dominion.union(adjacent);
          }
        }
      }
      if (numSets > 1) {
        disconneectedMonths++;
      }
    }

    System.out.println(disconneectedMonths);
  }
}
