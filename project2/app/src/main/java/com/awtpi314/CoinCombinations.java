package com.awtpi314;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinations {
  private static String csvFile = "output.csv";
  private static PrintWriter writer;

  private static void printSolutionConsole(int target, int[] denominations, CoinPurse purse, long time) {

    System.out.printf("%d cents = ", target);
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
    System.out.printf(" %.5f seconds\n", (double) (time / 1000000000.0));
  }

  public static void printSolutionCSV(int target, int[] denominations, CoinPurse purse, long time) {

    writer.printf("%d cents,", target);
    boolean first = true;
    for (int j = purse.getCoins().length - 1; j >= 0; j--) {
      if (purse.getCoins()[j] == 0) {
        continue;
      }
      if (!first) {
        writer.printf(" ");
      } else {
        first = false;
      }
      writer.printf("%d:%d", denominations[j], purse.getCoins()[j]);
    }
    writer.printf(",%.5f seconds\n", (double) (time / 1000000000.0));
  }

  private static void testVals(int target, int[] denominations) {
    writer.append("Denominations : ");
    for (int i : denominations) {
      writer.printf("%d ", i);
    }
    for (int i = 1; i <= target; i++) {
      long start = System.nanoTime();
      CoinPurse purse = new CoinPurse(i, denominations, true);
      long end = System.nanoTime();
      AnswerTable.getInstance().clear();

      printSolutionCSV(i, denominations, purse, end - start);

      /* Non-Memoized Recursive */

      start = System.nanoTime();
      purse = new CoinPurse(i, denominations, false);
      end = System.nanoTime();

      printSolutionCSV(i, denominations, purse, end - start);

      /* Non-Memoized Bottom Up Construction */

      start = System.nanoTime();
      bottumUp(i, denominations);
      AnswerTable.getInstance().clear();
      end = System.nanoTime();

      printSolutionCSV(i, denominations, purse, end - start);
      System.out.println(i);
    }
  }

  private static CoinPurse bottumUp(int target, int[] denominations) {
    CoinPurse purse = new CoinPurse(0, denominations, true);
    for (int j = 1; j <= target; j++) {
      purse = new CoinPurse(j, denominations, true);
    }

    return purse;
  }

  public static void main(String[] args) {
    try {
      writer = new PrintWriter(csvFile);
    } catch (Exception e) {
      // TODO: handle exception
    }
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
      testVals(target, denominations);
      // // /* Memoized Recursive */

      // long start = System.nanoTime();
      // CoinPurse purse = new CoinPurse(target, denominations, true);
      // long end = System.nanoTime();
      // AnswerTable.getInstance().clear();

      // System.out.println("Memoized Recursive");
      // printSolutionCSV(target, denominations, purse, end - start);

      // // /* Non-Memoized Recursive */

      // start = System.nanoTime();
      // purse = new CoinPurse(target, denominations, false);
      // end = System.nanoTime();

      // System.out.println("Non-Memoized Recursive");
      // printSolutionCSV(target, denominations, purse, end - start);

      // /* Non-Memoized Bottom Up Construction */

      // start = System.nanoTime();
      // bottumUp(target, denominations);
      // AnswerTable.getInstance().clear();
      // end = System.nanoTime();

      // System.out.println("Bottom Up Construction");
      // printSolutionCSV(target, denominations, purse, end - start);
    }
    sc.close();
    writer.close();
  }
}
