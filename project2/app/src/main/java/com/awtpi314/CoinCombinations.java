package com.awtpi314;

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinations {
  private static Node bottomUpSolve(int[] denominations, int target) {
    Node[] coinCombos = new Node[target];

    for (int i = 1; i <= target; i++) {
      coinCombos[i] = getDenominations(coinCombos, denominations, i);
    }

    return coinCombos[target];
  }

  private static Node noMemRecurse(int[] denominations, int target) {
   Node[] ans;
   if (target == 0) {
    int[] sol = new int[denominations.length];
    for (int i : sol) {
      sol[i] = 0;
    }
  
    Node newNode = new Node(sol, denominations.length);
     
     return newNode;
   }

   for (int i : denominations) {
    if (target - denominations[i] >= 0) {
      ans[i] = noMemRecurse(denominations, target - denominations[i]);
    }
   }

   //return best sol of ans
    //ans is an array nodes
   Node bestAns = null;
   for (Node node : ans) {
    if() {}
      bestAns = node;
    }
   }
   return bestAns;
  }

  private static int[] memRecurse(int[] denominations, int target) {
    return;
  }

  private static Node getDenominations(Node[] table, int[] denominations, int target) {
    int[] bestSol = table[target - denominations[0]].getSolution();
    int bestSolSize = table[target - denominations[0]].getSolSize() + 1;

    for (int i = 1; i < denominations.length; i++) {
      if (target - denominations[i] >= 0) {
        if (bestSolSize < table[target - denominations[i]].getSolSize() + 1) {
          bestSol = table[target - denominations[i]].getSolution();
        }
      }
    }
    Node newNode = new Node(bestSol, denominations.length);
    return newNode;
  }

  private static void getDenominations(int[] denominations, int target) {

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("How many denominations are in your currency?");
    int numDenominations = sc.nextInt();

    int[] denominations = new int[numDenominations];
    for (int i = 0; i < numDenominations; i++) {
      denominations[i] = sc.nextInt();
    }

    sc.close();

    int max = 300;

    int[] coins = new int[numDenominations];

    for (int i = 1; i <= max; i++) {
      System.out.println(i);
      for (int j = numDenominations - 1; j >= 0; j--) {
        int current = i;
        for (int k = j; k >= 0; k--) {
          if (current / denominations[k] > 0) {
            coins[k] = current / denominations[k];
          }
          current %= denominations[k];
        }
        System.out.println(Arrays.toString(coins));
        Arrays.fill(coins, 0);
      }
    }
  }
}
