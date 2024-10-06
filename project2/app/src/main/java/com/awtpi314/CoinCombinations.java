package com.awtpi314;

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinations {
  private static Purse bottomUpSolve(int[] denominations, int target) {
    Purse[] coinCombos = new Purse[target];

    for (int i = 1; i <= target; i++) {
      coinCombos[i] = getDenominations(coinCombos, denominations, i);
    }

    return coinCombos[target];
  }

  private static Purse noMemRecurse(int[] denominations, int target) {
   Purse[] ans = new Purse[denominations.length];
   if (target == 0) {
    int[] sol = new int[denominations.length];
    for (int i : sol) {
      sol[i] = 0;
    }
  
    Purse newPurse = new Purse(sol, denominations.length);
     
     return newPurse;
   }

   for (int i : denominations) {
    if (target - denominations[i] >= 0) {
      ans[i] = noMemRecurse(denominations, target - denominations[i]);
    }
   }
   //Set current Purse to have the best sol
   //return best sol of ans
    //ans is an array Purses

   Purse bestAns = ans[0];
   int i = 0;
   for (Purse Purse : ans) {
    i++;
    if(bestAns.getSolSize() > Purse.getSolSize()) {}
      bestAns = Purse;
    }
    return bestAns;
   }

  

  private static int[] memRecurse(int[] denominations, int target) {
    return new int[0];
  }

  private static Purse getDenominations(Purse[] table, int[] denominations, int target) {
    int[] bestSol = table[target - denominations[0]].getSolution();
    int bestSolSize = table[target - denominations[0]].getSolSize() + 1;

    for (int i = 1; i < denominations.length; i++) {
      if (target - denominations[i] >= 0) {
        if (bestSolSize < table[target - denominations[i]].getSolSize() + 1) {
          bestSol = table[target - denominations[i]].getSolution();
        }
      }
    }
    Purse newPurse = new Purse(bestSol, denominations.length);
    return newPurse;
  }

  private static void getDenominations(int[] denominations, int target) {

  }

  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);

    // System.out.println("How many denominations are in your currency?");
    // int numDenominations = sc.nextInt();

    // int[] denominations = new int[numDenominations];
    // for (int i = 0; i < numDenominations; i++) {
    //   denominations[i] = sc.nextInt();
    // }

    // sc.close();

    // int max = 300;

    // int[] coins = new int[numDenominations];

    // for (int i = 1; i <= max; i++) {
    //   System.out.println(i);
    //   for (int j = numDenominations - 1; j >= 0; j--) {
    //     int current = i;
    //     for (int k = j; k >= 0; k--) {
    //       if (current / denominations[k] > 0) {
    //         coins[k] = current / denominations[k];
    //       }
    //       current %= denominations[k];
    //     }
    //     System.out.println(Arrays.toString(coins));
    //     Arrays.fill(coins, 0);
    //   }
    // }

    AnswerTable table = AnswerTable.getInstance();
    int[] denominations = new int[] {1, 2, 7};
    table.setDenominations(denominations);
    CoinPurseMemoized purse = new CoinPurseMemoized(2, denominations);
    System.out.println(Arrays.toString(purse.getCoins()));
  }
}
