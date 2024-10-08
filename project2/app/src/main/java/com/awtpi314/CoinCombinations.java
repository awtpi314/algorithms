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
      AnswerTable.getInstance().clear();
      int target = sc.nextInt();

      System.out.printf("%d cents = ", target);
      // /* Memoized Recursive */
      // CoinPurseMemoized purse = new CoinPurseMemoized(target, denominations);

      // /* Non-Memoized Recursive */
      // CoinPurseNonMemoized purse = new CoinPurseNonMemoized(target, denominations);

      // for (int j = 1; j <= target; j++) {
      // new CoinPurseMemoized(j, denominations);
      // }
      // System.out.println(Arrays.toString(AnswerTable.getInstance().get(target).getCoins()));

      /* Non-Memoized Bottom Up Construction */
      CoinPurseMemoized purse = new CoinPurseMemoized(0, denominations);
      for (int j = 1; j <= target; j++) {
        purse = new CoinPurseMemoized(j, denominations);
      }

      boolean first = true;
      for (int j = purse.getCoins().length - 1; j >= 0; j--) {
        if (purse.getCoins()[j] == 0) {
          continue;
        }
        if (!first) {
          System.out.print(" ");
        } else {
          first = false;
        }
        System.out.printf("%d:%d", denominations[j], purse.getCoins()[j]);
      }
      System.out.println();

    }

    sc.close();
  }
}
