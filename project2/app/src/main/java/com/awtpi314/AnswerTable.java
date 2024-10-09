package com.awtpi314;

import java.util.ArrayList;

public class AnswerTable {
  private ArrayList<CoinPurse> table;
  private int[] denominations;
  private static AnswerTable instance;

  private AnswerTable() {
    table = new ArrayList<CoinPurse>();
  }

  public static AnswerTable getInstance() {
    if (instance == null) {
      instance = new AnswerTable();
    }
    return instance;
  }

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

  public CoinPurse get(int value) throws RuntimeException {
    if (value < 0 || value > table.size()) {
      throw new RuntimeException("Table must contain the passed value");
    }
    if (value == 0) {
      return new CoinPurse(0, denominations, true);
    }
    return new CoinPurse(table.get(value - 1));
  }

  public boolean exists(int value) {
    return (value == 0) || (value > 0 && value <= table.size() && table.get(value - 1) != null);
  }

  public int[] getDenominations() {
    return this.denominations;
  }

  public void setDenominations(int[] denominations) {
    this.denominations = denominations;
  }

  public void clear() {
    table.clear();
  }
}
