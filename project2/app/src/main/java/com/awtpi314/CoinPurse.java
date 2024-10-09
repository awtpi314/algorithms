package com.awtpi314;

import java.util.Arrays;

public class CoinPurse {
  private int[] denominations;
  private int[] coins;
  private int value;
  private boolean typeMemoized;

  private static int countCoins(int[] coins) {
    int count = 0;
    for (int i : coins) {
      count += i;
    }
    return count;
  }

  private static int[] solve(int value, int[] denominations, boolean typeMemoized) {
    CoinPurse bestPurse = null;
    int bestCount = Integer.MAX_VALUE;

    if (value == 0) {
      int[] emptyCoins = new int[denominations.length];
      for (int i : emptyCoins) {
        emptyCoins[i] = 0;
      }
      return emptyCoins;
    }

    for (int i = 0; i < denominations.length; i++) {
      int newValue = value - denominations[i];
      if (newValue >= 0) {
        CoinPurse purse = null;
        if (typeMemoized) {
          AnswerTable table = AnswerTable.getInstance();
          if (table.exists(newValue)) {
            purse = table.get(newValue);
          } 
        }
        if (purse == null) purse = new CoinPurse(newValue, denominations, typeMemoized);
        purse.coins[i]++;
        if (countCoins(purse.coins) < bestCount) {
          bestPurse = purse;
          bestCount = countCoins(purse.coins);
        }
      } else {
        break;
      }
    }
    if (typeMemoized) AnswerTable.getInstance().set(value, bestPurse);
    
    return bestPurse.getCoins();
  }

  public CoinPurse(int value, int[] denominations, boolean typeMemoized) {
    this.denominations = denominations;
    this.coins = solve(value, denominations, typeMemoized);
    this.value = value;
    this.typeMemoized = typeMemoized;
  }

  public CoinPurse(CoinPurse purse) {
    this.denominations = purse.denominations;
    this.coins = Arrays.copyOf(purse.coins, purse.coins.length);
    this.value = purse.value;
    this.typeMemoized = purse.typeMemoized;
  }

  public int[] getCoins() {
    return Arrays.copyOf(this.coins, this.coins.length);
  }
}
