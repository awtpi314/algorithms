package com.awtpi314;

import java.util.Arrays;

public class CoinPurseMemoized {
  private int[] denominations;
  private int[] coins;
  private int value;

  private static int countCoins(int[] coins) {
    int count = 0;
    for (int i : coins) {
      count += coins[i];
    }
    return count;
  }

  private static int[] solve(int value, int[] denominations) {
    AnswerTable table = AnswerTable.getInstance();
    int[] bestPurse = new int[denominations.length];
    int bestCount = Integer.MAX_VALUE;

    if (value == 0) {
      return bestPurse;
    }

    for (int i = 0; i < denominations.length; i++) {
      if (table.exists(value - denominations[i])) {
        CoinPurseMemoized purse = table.get(value - denominations[i]);
        purse.coins[i]++;
        table.set(value, purse);
        if (countCoins(purse.coins) < bestCount) {
          bestPurse = purse.coins;
          bestCount = countCoins(purse.coins);
        }
        return purse.coins;
      } else {
        if (value - denominations[i] <= 0) {
          break;
        }
        CoinPurseMemoized purse = new CoinPurseMemoized(value - denominations[i], denominations);
        purse.coins[i]++;
        table.set(value - denominations[i], purse);
        if (countCoins(purse.coins) < bestCount) {
          bestPurse = purse.coins;
          bestCount = countCoins(purse.coins);
        }
      }
    }
    return bestPurse;
  }

  public CoinPurseMemoized(int value, int[] denominations) {
    this.denominations = denominations;
    this.coins = solve(value, denominations);
    this.value = value;

    AnswerTable table = AnswerTable.getInstance();
    table.set(value, this);
  }

  public CoinPurseMemoized(CoinPurseMemoized purse) {
    this.denominations = purse.denominations;
    this.coins = purse.coins;
    this.value = purse.value;
  }

  public int[] getCoins() {
    return Arrays.copyOf(this.coins, this.coins.length);
  }
}
