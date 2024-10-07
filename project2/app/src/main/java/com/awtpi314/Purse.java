package com.awtpi314;

public class Purse {
  private int solSize;
  private int denominations;
  private int[] solution = new int[denominations];

  public Purse(int[] solution, int denominations) {
    this.denominations = denominations;
    this.solution = solution;
  }

  public int getSolSize() {
    return this.solSize;
  }

  public void setSolution(int[] solution) {
    this.solution = solution;
    for (Integer i : solution) {
      this.solSize += solution[i];
    }
  }

  public int[] getSolution() {
    return this.solution;
  }

  public void incSolution(int denominationIndex) {
    this.solution[denominationIndex] = this.solution[denominationIndex]++;
  }
}
