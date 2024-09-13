package com.awtpi314;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.awtpi314.Sorter.PartitionType;
import com.awtpi314.Sorter.PivotType;
import com.awtpi314.Sorter.SortType;

public class Tests {
  private static final int[] ARRAY_LENGTHS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      21, 22, 23, 24, 25, 50, 75,
      100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 2000, 3000,
      4000, 5000, 6000, 7000, 8000, 9000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000,
      200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000 };
  int[][] random = new int[ARRAY_LENGTHS.length][];
  int[][] sorted = new int[ARRAY_LENGTHS.length][];
  int[][] reversed = new int[ARRAY_LENGTHS.length][];

  @BeforeEach
  public void setUp() {
    for (int i = 0; i < ARRAY_LENGTHS.length; i++) {
      random[i] = new int[ARRAY_LENGTHS[i]];
      sorted[i] = new int[ARRAY_LENGTHS[i]];
      reversed[i] = new int[ARRAY_LENGTHS[i]];
      for (int j = 0; j < ARRAY_LENGTHS[i]; j++) {
        random[i][j] = (int) (Math.random() * 1000000);
        sorted[i][j] = j;
        reversed[i][j] = ARRAY_LENGTHS[i] - j;
      }
    }
  }

  @Test
  public void testInsertionSort() {
    System.out.println("Starting Random");

    TestRunner randomInsertion = new TestRunner(Arrays.copyOfRange(random, 0, 64), "random", SortType.INSERTION,
        PartitionType.SINGLE, PivotType.RANDOM);
    Thread[] randomThreads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      randomThreads[i] = new Thread(randomInsertion);
      randomThreads[i].start();
    }

    try {
      for (int i = 0; i < 10; i++) {
        randomThreads[i].join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Random done");
    System.out.println("Starting Sorted");

    TestRunner sortedInsertion = new TestRunner(Arrays.copyOfRange(sorted, 0, 64), "sorted", SortType.INSERTION,
        PartitionType.SINGLE, PivotType.RANDOM);
    Thread[] sortedThreads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      sortedThreads[i] = new Thread(sortedInsertion);
      sortedThreads[i].start();
    }

    try {
      for (int i = 0; i < 10; i++) {
        sortedThreads[i].join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Sorted done");
    System.out.println("Starting Reversed");

    TestRunner reversedInsertion = new TestRunner(Arrays.copyOfRange(reversed, 0, 64), "reversed", SortType.INSERTION,
        PartitionType.SINGLE, PivotType.RANDOM);
    Thread[] reversedThreads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      reversedThreads[i] = new Thread(reversedInsertion);
      reversedThreads[i].start();
    }

    try {
      for (int i = 0; i < 10; i++) {
        reversedThreads[i].join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Reversed done");

    // Sorter sorter = new Sorter();
    // System.out.println("Array Length,Random,Sorted,Reversed");
    // for (int i = 0; ARRAY_LENGTHS[i] <= 200000; i++) {
    // System.out.printf("%d,", ARRAY_LENGTHS[i]);

    // long start = System.nanoTime();
    // sorter.insertionSort(random[i]);
    // long end = System.nanoTime();
    // System.out.printf("%.9f,", (end - start) / 1000000000.0);

    // start = System.nanoTime();
    // sorter.insertionSort(sorted[i]);
    // end = System.nanoTime();
    // System.out.printf("%.9f,", (end - start) / 1000000000.0);

    // start = System.nanoTime();
    // sorter.insertionSort(reversed[i]);
    // end = System.nanoTime();
    // System.out.printf("%.9f\n", (end - start) / 1000000000.0);

    // assert isSorted(random[i]);
    // assert isSorted(sorted[i]);
    // assert isSorted(reversed[i]);
    // }
  }
}
