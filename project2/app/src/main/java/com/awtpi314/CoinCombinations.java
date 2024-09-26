package com.awtpi314;

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinations {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("How many denominations are in your currency?");
    int numDenominations = sc.nextInt();

    int[] denominations = new int[numDenominations];
    for (int i = 0; i < numDenominations; i++) {
      denominations[i] = sc.nextInt();
    }

    // System.out.println("Enter the money values and enter -1 to quit");
    // int moneyValue = sc.nextInt();

    // while (moneyValue != -1) {

    // }

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
