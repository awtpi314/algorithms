package com.awtpi314;

import java.util.Arrays;

/**
 * <h3>CoinPurse</h3>
 * 
 * This class solves a coin purse in the constructor using denominations and a
 * target value.
 */
public class CoinPurse {
  private int[] denominations;
  private int[] coins;
  private int value;
  private boolean typeMemoized;

  /**
   * Helper method to count coins in an array.
   * 
   * @param coins array of coins to count
   * @return count of coins
   */
  private static int countCoins(int[] coins) {
    int count = 0;
    for (int i : coins) {
      count += i;
    }
    return count;
  }

  /**
   * Helper method to solve the coin purse problem.
   * 
   * Takes a value and denominations and returns the array of coins to solve this
   * purse.
   * 
   * @param value         target value
   * @param denominations array of denominations
   * @param typeMemoized  true if memoized, false if not
   * @return array of coins
   */
  private static int[] solve(int value, int[] denominations, boolean typeMemoized) {
    CoinPurse bestPurse = new CoinPurse(denominations, typeMemoized);
    int bestCount = Integer.MAX_VALUE;

    // Base case
    if (value == 0) {
      return new int[denominations.length];
    }

    // Loop through the denominations solving the purse for each one.
    for (int i = 0; i < denominations.length; i++) {
      int newValue = value - denominations[i];
      if (newValue >= 0) {
        // We can take off the current denomination
        CoinPurse purse = null;
        if (typeMemoized) {
          // If we are using memoization, check if we have already calculated this value.
          AnswerTable table = AnswerTable.getInstance();
          if (table.exists(newValue)) {
            purse = table.get(newValue);
          }
        }
        if (purse == null) {
          purse = new CoinPurse(newValue, denominations, typeMemoized);
        }
        purse.coins[i]++;
        // Check if we have found a new best purse
        if (countCoins(purse.coins) < bestCount) {
          bestPurse = purse;
          bestCount = countCoins(purse.coins);
        }
      } else {
        break;
      }
    }
    if (typeMemoized)
      AnswerTable.getInstance().set(value, bestPurse);

    return bestPurse.getCoins();
  }

  /**
   * Full constructor for CoinPurse.
   * 
   * @param value         target value
   * @param denominations array of denominations
   * @param typeMemoized  true if memoized, false if not
   */
  public CoinPurse(int value, int[] denominations, boolean typeMemoized) {
    this.denominations = denominations;
    this.coins = solve(value, denominations, typeMemoized);
    this.value = value;
    this.typeMemoized = typeMemoized;
  }

  /**
   * Copy constructor for CoinPurse.
   * 
   * @param purse the purse to copy
   */
  public CoinPurse(CoinPurse purse) {
    this.denominations = purse.denominations;
    this.coins = Arrays.copyOf(purse.coins, purse.coins.length);
    this.value = purse.value;
    this.typeMemoized = purse.typeMemoized;
  }

  /**
   * Limited constructor for CoinPurse.
   * 
   * @param denominations array of denominations
   * @param typeMemoized  true if memoized, false if not
   */
  public CoinPurse(int[] denominations, boolean typeMemoized) {
    this.denominations = denominations;
    this.coins = new int[denominations.length];
    this.value = 0;
    this.typeMemoized = typeMemoized;
  }

  /**
   * Get the coin purse array
   * 
   * @return the coin purse array
   */
  public int[] getCoins() {
    return Arrays.copyOf(this.coins, this.coins.length);
  }
}
