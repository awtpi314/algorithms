package com.awtpi314;

import java.util.ArrayList;

public class Node {
    private int solSize;
    private int denominations;
    private int[] solution = new int[denominations];   
    
    public Node(int[]solution, int denominations) {
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
}
