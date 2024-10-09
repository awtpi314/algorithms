package com.awtpi314;

import java.util.ArrayList;

/**
 * <h3>AnswerTable</h3>
 * 
 * This class is a singleton that holds the memoized answers for coin purses
 */
public class AnswerTable {
  private ArrayList<CoinPurse> table;
  private int[] denominations;
  private static AnswerTable instance;

  /**
   * Private default constructor
   */
  private AnswerTable() {
    table = new ArrayList<CoinPurse>();
  }

  /**
   * Get the instance of the AnswerTable
   * 
   * @return the instance of the AnswerTable
   */
  public static AnswerTable getInstance() {
    if (instance == null) {
      instance = new AnswerTable();
    }
    return instance;
  }

  /**
   * Set the value of the table at the passed value
   * 
   * @param value the value to set
   * @param purse the coin purse to set
   * @throws RuntimeException if the value is less than zero
   */
  public void set(int value, CoinPurse purse) throws RuntimeException {
    if (table.size() <= value) {
      table.ensureCapacity(value + 1);
      for (int i = table.size(); i <= value; i++) {
        table.add(null);
      }
    }
    if (value == 0) {
      return;
    }
    if (value < 0) {
      throw new RuntimeException("Value must be larger than zero");
    }
    table.add(value - 1, new CoinPurse(purse));
  }

  /**
   * Get the coin purse at the passed value
   * 
   * @param value the value to get
   * @return the coin purse at the passed value
   * @throws RuntimeException if the value is less than zero or greater than the
   *                          size of the table
   */
  public CoinPurse get(int value) throws RuntimeException {
    if (value < 0 || value > table.size()) {
      throw new RuntimeException("Table must contain the passed value");
    }
    if (value == 0) {
      return new CoinPurse(0, denominations, true);
    }
    return new CoinPurse(table.get(value - 1));
  }

  /**
   * Check if the value exists in the table
   * 
   * @param value the value to check
   * @return true if the value exists in the table, false otherwise
   */
  public boolean exists(int value) {
    return (value == 0) || (value > 0 && value <= table.size() && table.get(value - 1) != null);
  }

  /**
   * Get the denominations of the money system
   * 
   * @return the denominations of the money system
   */
  public int[] getDenominations() {
    return this.denominations;
  }

  /**
   * Set the denominations of the money system
   * 
   * @param denominations the denominations of the money system
   */
  public void setDenominations(int[] denominations) {
    this.denominations = denominations;
  }

  /**
   * Clear the table
   */
  public void clear() {
    table.clear();
  }
}
