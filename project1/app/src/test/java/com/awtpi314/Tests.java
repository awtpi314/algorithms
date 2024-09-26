package com.awtpi314;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Tests {
  public int[][] num = new int[6][];
  
  @BeforeAll
  public void generateLists() {
  
    for (int j = 1; j < num.length; j++) {
      num[j] = new int[(int)Math.pow(10, j)];
      for (int i = 0; i < (int)Math.pow(10, j); i++) {
        num[j][i] = (int) (Math.random() * 1000000);
      }
    }    
  }

  @Test
  public void testInsertionSort() {
    Project1 inSort = new Project1();
    for (int i = 0; i < 5; i++) {
        long start = System.nanoTime();
        inSort.insertionSort(num[i]);
        long end = System.nanoTime();
        System.out.println("Insertion Sort for " + num[i].length + " elements took " + (end - start) / 1000000000.0 + " seconds");
    }
  
  }

  @Test
  public void testQuickSort_One_Point() {
    Project1 quick = new Project1();
    
  }

  @Test
  public void testQuickSort_Two_Point() {
    Project1 quick = new Project1();
    
  }
}
