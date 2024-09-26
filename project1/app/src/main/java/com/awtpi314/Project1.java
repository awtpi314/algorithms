package com.awtpi314;

import java.util.Arrays;

public class Project1 {
  public static final int TRIALS = 10;

  public enum PivotType {
    RANDOM, MEDIAN, MEDIAN_OF_3, FIRST, LAST
  }

  public enum PartitionType {
    SINGLE, DUAL
  }

  public void insertionSort(int[] unsorted) {
    Integer arrayLength = unsorted.length;
    for (Integer i = 1; i < arrayLength; i++) {
      Integer key = unsorted[i];
      Integer j = i - 1;

      while (j >= 0 && unsorted[j] > key) {
        unsorted[j + 1] = unsorted[j];
        j = j - 1;
      }

      unsorted[j + 1] = key;
    }
  }

  private int singlePartitionRandom(int[] unsorted, int first, int last) {
    int pivot = unsorted[(int) (Math.random() * (last - first) + first)];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionMedian(int[] unsorted, int first, int last) {
    int pivot = unsorted[last / 2];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionMedianOf3(int[] unsorted, int first, int last) {
    int pivot = unsorted[(first + last) / 2];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionLast(int[] unsorted, int first, int last) {
    int pivot = unsorted[last];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionRandom(int[] unsorted, int first, int last) {
    int pivot = unsorted[(int) (Math.random() * (last - first) + first)];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionMedian(int[] unsorted, int first, int last) {
    int pivot = unsorted[last / 2];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionMedianOf3(int[] unsorted, int first, int last) {
    int pivot = unsorted[(first + last) / 2];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionLast(int[] unsorted, int first, int last) {
    int pivot = unsorted[last];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPointerPartition(int[] unsorted, int first, int last, int pivot) {
    int lower = first;
    int upper = last - 1;

    while (lower <= upper) {
      while (lower <= upper && unsorted[upper] >= pivot) {
        upper--;
      }
      while (lower <= upper && unsorted[lower] <= pivot) {
        lower++;
      }
      if (lower < upper) {
        int temp = unsorted[lower];
        unsorted[lower] = unsorted[upper];
        unsorted[upper] = temp;
      }
    }

    int temp = unsorted[lower];
    unsorted[lower] = unsorted[last];
    unsorted[last] = temp;

    return lower;
  }

  private int singlePointerPartition(int[] unsorted, int first, int last, int pivot) {
    int lowPointer = first - 1;
    for (int i = first; i < last - 1; i++) {
      if (unsorted[i] < pivot) {
        lowPointer++;
        int temp = unsorted[lowPointer];
        unsorted[lowPointer] = unsorted[i];
        unsorted[i] = temp;
      }
    }
    lowPointer++;
    int temp = unsorted[lowPointer];
    unsorted[lowPointer] = unsorted[last];
    unsorted[last] = temp;
    return lowPointer;
  }

  public void quickSort(int[] unsorted, PivotType pivotType, PartitionType partitionType) {
    quickSort(unsorted, 0, unsorted.length - 1, pivotType, partitionType);
  }

  public void quickSort(int[] unsorted, int first, int last, PivotType pivotType, PartitionType partitionType) {
    if (first < last) {
      int middle = 0;
      switch (pivotType) {
        case FIRST:
          if (partitionType == PartitionType.SINGLE) {
            middle = singlePartitionFirst(unsorted, first, last);
          } else {
            middle = dualPartitionFirst(unsorted, first, last);
          }
          break;

        case LAST:
          if (partitionType == PartitionType.SINGLE) {
            middle = singlePartitionLast(unsorted, first, last);
          } else {
            middle = dualPartitionLast(unsorted, first, last);
          }
          break;

        case RANDOM:
          if (partitionType == PartitionType.SINGLE) {
            middle = singlePartitionRandom(unsorted, first, last);
          } else {
            middle = dualPartitionRandom(unsorted, first, last);
          }
          break;

        case MEDIAN:
          if (partitionType == PartitionType.SINGLE) {
            middle = singlePartitionMedian(unsorted, first, last);
          } else {
            middle = dualPartitionMedian(unsorted, first, last);
          }
          break;

        case MEDIAN_OF_3:
          if (partitionType == PartitionType.SINGLE) {
            middle = singlePartitionMedianOf3(unsorted, first, last);
          } else {
            middle = dualPartitionMedianOf3(unsorted, first, last);
          }
          break;

        default:
          break;
      }

      quickSort(unsorted, first, middle - 1, pivotType, partitionType);
      quickSort(unsorted, middle + 1, last, pivotType, partitionType);
    }
  }

  private boolean isSorted(int[] unsorted) {
    for (int i = 0; i < unsorted.length - 1; i++) {
      if (unsorted[i] > unsorted[i + 1]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 50, 75,
        100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 2000, 3000,
        4000, 5000, 6000, 7000, 8000, 9000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000,
        200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000 };
    int[][] num = new int[values.length][];

    for (int j = 1; j <= num.length; j++) {
      num[j - 1] = new int[values[j - 1]];
      for (int i = 0; i < values[j - 1]; i++) {
        num[j - 1][i] = (int) (Math.random() * 1000000);
        // num[j - 1][i] = values[j - 1] - i;
        // num[j - 1][i] = i;
      }
    }

    System.out.println("Starting Quick Sort");

    Project1 inSort = new Project1();
    for (int i = 0; i < num.length; i++) {
      System.out.printf("%d,", num[i].length);
      for (int j = 0; j < TRIALS; j++) {
        int[] current = Arrays.copyOf(num[i], num[i].length);
        long start = System.nanoTime();
        inSort.quickSort(current, PivotType.LAST, PartitionType.SINGLE);
        long end = System.nanoTime();
        System.out.printf("%.9f,", (end - start) / 1000000000.0);
        if (!inSort.isSorted(current)) {
          System.out.println("Not Sorted");
        }
      }
      System.out.println();
    }
  }
}
