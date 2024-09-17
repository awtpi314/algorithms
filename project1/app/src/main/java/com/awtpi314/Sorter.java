package com.awtpi314;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Sorter {
  private int[][] tenThosands = new int[1000000 / 10000][];
  private int[][][] hundreds = new int[3][10000 / 100][];
  private int[][] tens = new int[1000 / 10][];

  /**
   * PivotType represents the different pivots available to the quicksort
   * algorithm
   */
  public enum PivotType {
    RANDOM, MEDIAN, MEDIAN_OF_3, FIRST, LAST
  }

  /**
   * PartitionType represents the different partitioning methods available to the
   * quicksort algorithm
   */
  public enum PartitionType {
    SINGLE, DUAL
  }

  /**
   * SortType represents the different sorting algorithms available
   */
  public enum SortType {
    INSERTION, QUICK
  }

  /**
   * Sort an array using insertion sort
   * 
   * @param unsorted the unsorted array to sort
   */
  public void insertionSort(int[] unsorted) {
    Integer arrayLength = unsorted.length;
    // Outer loop for iterating through the array
    for (Integer i = 1; i < arrayLength; i++) {
      Integer key = unsorted[i];
      Integer j = i - 1;

      // Find the location where we should insert into the array
      while (j >= 0 && unsorted[j] > key) {
        unsorted[j + 1] = unsorted[j];
        j = j - 1;
      }

      unsorted[j + 1] = key;
    }
  }

  /**
   * Helper method for swapping two elements in an array
   * 
   * @param array the array to swap elements in
   * @param i     the index of the first element
   * @param j     the index of the second element
   */
  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * Partition the array using a single pointer on a random element
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int singlePartitionRandom(int[] unsorted, int first, int last) {
    int location = (int) (Math.random() * (last - first) + first);
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the median using a single pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int singlePartitionMedian(int[] unsorted, int first, int last) {
    int location = (first + last) / 2;
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the median of the first, middle, and last elements
   * using a single pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int singlePartitionMedianOf3(int[] unsorted, int first, int last) {
    int location;
    int middle = (first + last) / 2;
    if ((first > middle) && (first > last)) {
      location = first;
    } else if ((middle > first) && (middle > last)) {
      location = middle;
    } else {
      location = last;
    }
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the first element using a single pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int singlePartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    swap(unsorted, first, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the last element using a single pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int singlePartitionLast(int[] unsorted, int first, int last) {
    int pivot = unsorted[last];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on a random element using a dual pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int dualPartitionRandom(int[] unsorted, int first, int last) {
    int location = (int) (Math.random() * (last - first) + first);
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the median using a dual pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int dualPartitionMedian(int[] unsorted, int first, int last) {
    int location = (first + last) / 2;
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the median of the first, middle, and last elements
   * using a dual pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int dualPartitionMedianOf3(int[] unsorted, int first, int last) {
    int location;
    int middle = (first + last) / 2;
    if ((first > middle) && (first > last)) {
      location = first;
    } else if ((middle > first) && (middle > last)) {
      location = middle;
    } else {
      location = last;
    }
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the first element using a dual pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int dualPartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    swap(unsorted, first, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array on the last element using a dual pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * 
   * @return the index of the pivot
   */
  private int dualPartitionLast(int[] unsorted, int first, int last) {
    int pivot = unsorted[last];
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  /**
   * Partition the array using two pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * @param pivot    the pivot element
   * 
   * @return the index of the pivot
   */
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
        swap(unsorted, lower, upper);
      }
    }

    swap(unsorted, lower, last);

    return lower;
  }

  /**
   * Partition the array using a single pointer
   * 
   * @param unsorted the unsorted array to partition
   * @param first    the first index of the subarray
   * @param last     the last index of the subarray
   * @param pivot    the pivot element
   * 
   * @return the index of the pivot
   */
  private int singlePointerPartition(int[] unsorted, int first, int last, int pivot) {
    int lowPointer = first - 1;
    for (int ptr = first; ptr < last; ptr++) {
      if (unsorted[ptr] <= pivot) {
        lowPointer++;
        swap(unsorted, lowPointer, ptr);
      }
    }
    lowPointer++;
    swap(unsorted, lowPointer, last);
    return lowPointer;
  }

  /**
   * Sort an array using quicksort with the choice of pivot and partition
   * 
   * @param unsorted      the unsorted array to sort
   * @param pivotType     the type of pivot to use
   * @param partitionType the type of partition to use
   */
  public void quickSort(int[] unsorted, PivotType pivotType, PartitionType partitionType) {
    quickSort(unsorted, 0, unsorted.length - 1, pivotType, partitionType);
  }

  /**
   * Sort a subarray using quicksort with the choice of pivot and partition
   * 
   * @param unsorted      the unsorted array to sort
   * @param first         the first index of the subarray
   * @param last          the last index of the subarray
   * @param pivotType     the type of pivot to use
   * @param partitionType the type of partition to use
   */
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

  /**
   * Helper method to check if an array is sorted
   * 
   * @param array the array to check
   * @return true if the array is sorted, false otherwise
   */
  public static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Sorter sorter = new Sorter();
    sorter.setUp();

    try {
      FileWriter writer = new FileWriter("partitions.csv");
      writer.write(
          "Quicksort Dual Partition\nArray Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");

      for (int i = 0; i < sorter.tenThosands.length; i++) {
        int[] array = sorter.tenThosands[i];
        writer.write(array.length + ",");
        for (int j = 0; j < 10; j++) {
          int[] copy = Arrays.copyOf(array, array.length);
          long startTime = System.nanoTime();
          sorter.quickSort(copy, PivotType.LAST, PartitionType.DUAL);
          long endTime = System.nanoTime();
          writer.write((endTime - startTime) / 1000000.0 + ",");
        }
        writer.write("\n");
      }

      writer.write("\n\n");
      writer.write(
          "Quicksort Single Partition\nArray Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");

      for (int i = 0; i < sorter.tenThosands.length; i++) {
        int[] array = sorter.tenThosands[i];
        writer.write(array.length + ",");
        for (int j = 0; j < 10; j++) {
          int[] copy = Arrays.copyOf(array, array.length);
          long startTime = System.nanoTime();
          sorter.quickSort(copy, PivotType.LAST, PartitionType.SINGLE);
          long endTime = System.nanoTime();
          writer.write((endTime - startTime) / 1000000.0 + ",");
        }
        writer.write("\n");
      }

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished partitions");

    try {
      FileWriter writer = new FileWriter("pivots.csv");

      PivotType[] pivots = {
          PivotType.RANDOM,
          PivotType.RANDOM,
          PivotType.RANDOM,
          PivotType.LAST,
          PivotType.LAST,
          PivotType.LAST,
          PivotType.MEDIAN,
          PivotType.MEDIAN,
          PivotType.MEDIAN
      };
      String[] dataTypes = {
          "Sorted",
          "Reversed",
          "Random",
          "Sorted",
          "Reversed",
          "Random",
          "Sorted",
          "Reversed",
          "Random"
      };

      HashMap<String, String> fileData = new HashMap<>();

      for (int i = 0; i < pivots.length; i++) {
        String key = dataTypes[i] + " Data - Pivot " + pivots[i];
        fileData.put(key,
            "Array Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");
        for (int j = 0; j < sorter.hundreds[i % 3].length; j++) {
          int[] array = sorter.hundreds[i % 3][j];
          fileData.put(key, fileData.get(key) + array.length + ",");
          for (int k = 0; k < 10; k++) {
            int[] copy = Arrays.copyOf(array, array.length);
            long startTime = System.nanoTime();
            sorter.quickSort(copy, pivots[i], PartitionType.DUAL);
            long endTime = System.nanoTime();
            fileData.put(key, fileData.get(key) + (endTime - startTime) / 1000000.0 + ",");
          }
          fileData.put(key, fileData.get(key) + "\n");
        }
      }

      for (var key : fileData.keySet()) {
        writer.write(key + "\n" + fileData.get(key) + "\n\n");
      }

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished pivots");

    try {
      FileWriter writer = new FileWriter("quickVsInsertion.csv");

      String[] dataTypes = {
          "Sorted",
          "Reversed",
          "Random",
          "Sorted",
          "Reversed",
          "Random"
      };

      String[] sortTypes = {
          "Quick",
          "Quick",
          "Quick",
          "Insertion",
          "Insertion",
          "Insertion"
      };

      HashMap<String, String> fileData = new HashMap<>();

      for (int i = 0; i < dataTypes.length; i++) {
        String key = dataTypes[i] + " Data - " + sortTypes[i] + " Sort";
        fileData.put(key,
            "Array Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");
        for (int j = 0; j < sorter.hundreds[i % 3].length; j++) {
          int[] array = sorter.hundreds[i % 3][j];
          fileData.put(key, fileData.get(key) + array.length + ",");
          for (int k = 0; k < 10; k++) {
            int[] copy = Arrays.copyOf(array, array.length);
            long startTime = System.nanoTime();
            if (sortTypes[i].equals("Quick")) {
              sorter.quickSort(copy, PivotType.LAST, PartitionType.DUAL);
            } else {
              sorter.insertionSort(copy);
            }
            long endTime = System.nanoTime();
            fileData.put(key, fileData.get(key) + (endTime - startTime) / 1000000.0 + ",");
          }
          fileData.put(key, fileData.get(key) + "\n");
        }
      }

      for (var key : fileData.keySet()) {
        writer.write(key + "\n" + fileData.get(key) + "\n\n");
      }

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished quick vs insertion");

    try {
      FileWriter writer = new FileWriter("zoomedIn.csv");
      writer.write(
          "Quicksort\nArray Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");

      for (int i = 0; i < sorter.tens.length; i++) {
        int[] array = sorter.tens[i];
        writer.write(array.length + ",");
        for (int k = 0; k < 10; k++) {
          int[] copy = Arrays.copyOf(array, array.length);
          long startTime = System.nanoTime();
          sorter.quickSort(copy, PivotType.LAST, PartitionType.DUAL);
          long endTime = System.nanoTime();
          writer.write((endTime - startTime) / 1000000.0 + ",");
        }
        writer.write("\n");
      }

      writer.write("\n\n");
      writer.write(
          "Insertion Sort\nArray Length,Trial 1,Trial 2,Trial 3,Trial 4,Trial 5,Trial 6,Trial 7,Trial 8,Trial 9,Trial 10\n");

      for (int i = 0; i < sorter.tens.length; i++) {
        int[] array = sorter.tens[i];
        writer.write(array.length + ",");
        for (int k = 0; k < 10; k++) {
          int[] copy = Arrays.copyOf(array, array.length);
          long startTime = System.nanoTime();
          sorter.insertionSort(copy);
          long endTime = System.nanoTime();
          writer.write((endTime - startTime) / 1000000.0 + ",");
        }
        writer.write("\n");
      }

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Set up the arrays for testing
   */
  public void setUp() {
    for (int i = 0; i < tenThosands.length; i++) {
      tenThosands[i] = new int[10000 * (i + 1)];
      for (int j = 1; j <= 10000 * i; j++) {
        tenThosands[i][j - 1] = (int) (Math.random() * 1000000);
      }
    }

    for (int i = 0; i < hundreds[0].length; i++) {
      hundreds[0][i] = new int[100 * (i + 1)];
      hundreds[1][i] = new int[100 * (i + 1)];
      hundreds[2][i] = new int[100 * (i + 1)];
      for (int j = 1; j <= 100 * i; j++) {
        hundreds[0][i][j - 1] = j;
        hundreds[1][i][j - 1] = 100 * i - j;
        hundreds[2][i][j - 1] = (int) (Math.random() * 1000000);
      }
    }

    for (int i = 0; i < tens.length; i++) {
      tens[i] = new int[10 * (i + 1)];
      for (int j = 1; j <= 10 * i; j++) {
        tens[i][j - 1] = (int) (Math.random() * 1000000);
      }
    }
  }
}
