package com.awtpi314;

import java.util.Scanner;

public class Project2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("How many denominations are in your currency?");
    int numDenominations = sc.nextInt();

    int[] denominations = new int[numDenominations];
    for (int i = 0; i < numDenominations; i++) {
      System.out.println("Enter denomination " + (i + 1) + ":");
      denominations[i] = sc.nextInt();
    }

    System.out.println("Enter the money values and enter -1 to quit");
    int moneyValue = sc.nextInt();

    while (moneyValue != -1) {
      
    }
  }
}
