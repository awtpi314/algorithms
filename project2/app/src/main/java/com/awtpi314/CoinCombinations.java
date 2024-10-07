package com.awtpi314;

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinations {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int numDenominations = sc.nextInt();

    int[] denominations = new int[numDenominations];
    for (int i = 0; i < numDenominations; i++) {
      denominations[i] = sc.nextInt();
    }
    Arrays.sort(denominations);
    AnswerTable.getInstance().setDenominations(denominations);

    int numProblems = sc.nextInt();

    for (int i = 0; i < numProblems; i++) {
      int target = sc.nextInt();
      
      CoinPurseMemoized purse = new CoinPurseMemoized(target, denominations);
      System.out.printf("%d cents = ", target);
      for (int j = purse.getCoins().length - 1; j >= 0; j--) {
        if (purse.getCoins()[j] == 0) {
          continue;
        }
        System.out.printf("%d:%d ", denominations[j], purse.getCoins()[j]);
      }
      System.out.println();

      // CoinPurseNonMemoized purse2 = new CoinPurseNonMemoized(target, denominations);
      // System.out.println(Arrays.toString(purse2.getCoins()));

      // AnswerTable.getInstance().clear();
      // for (int j = 1; j <= target; j++) {
      //   new CoinPurseMemoized(j, denominations);
      // }
      // System.out.println(Arrays.toString(AnswerTable.getInstance().get(target).getCoins()));
    }

    sc.close();
  }
}
