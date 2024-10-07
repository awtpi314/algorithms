package com.awtpi314;

import java.util.Arrays;

public class CoinPurseNonMemoized {
  private int[] denominations;
  private int[] coins;
  private int value;

  private static int countCoins(int[] coins) {
    int count = 0;
    for (int i : coins) {
      count += i;
    }
    return count;
  }

  private static int[] solve(int value, int[] denominations) {
    int[] bestPurse = new int[denominations.length];
    int bestCount = Integer.MAX_VALUE;

    if (value == 0) {
      return bestPurse;
    }

    for (int i = 0; i < denominations.length; i++) {
      int newValue = value - denominations[i];
      if (newValue >= 0) {
        CoinPurseNonMemoized purse = new CoinPurseNonMemoized(newValue, denominations);
        purse.coins[i]++;
        if (countCoins(purse.coins) < bestCount) {
          bestPurse = Arrays.copyOf(purse.coins, purse.coins.length);
          bestCount = countCoins(purse.coins);
        }
      } else {
        break;
      }
    }

    return bestPurse;
  }

  public CoinPurseNonMemoized(int value, int[] denominations) {
    this.denominations = denominations;
    this.coins = solve(value, denominations);
    this.value = value;
  }

  public CoinPurseNonMemoized(CoinPurseNonMemoized purse) {
    this.denominations = purse.denominations;
    this.coins = Arrays.copyOf(purse.coins, purse.coins.length);
    this.value = purse.value;
  }

  public int[] getCoins() {
    return Arrays.copyOf(this.coins, this.coins.length);
  }
}
