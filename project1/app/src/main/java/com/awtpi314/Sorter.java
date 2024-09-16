package com.awtpi314;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class Sorter {

  private static final int[] ARRAY_LENGTHS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      21, 22, 23, 24, 25, 50, 75,
      100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 2000, 3000,
      4000, 5000, 6000, 7000, 8000, 9000, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000,
      200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000 };
  int[][][] numbers = new int[3][ARRAY_LENGTHS.length][];

  public enum PivotType {
    RANDOM, MEDIAN, MEDIAN_OF_3, FIRST, LAST
  }

  public enum PartitionType {
    SINGLE, DUAL
  }

  public enum SortType {
    INSERTION, QUICK
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

  private void swap(int[] unsorted, int i, int j) {
    int temp = unsorted[i];
    unsorted[i] = unsorted[j];
    unsorted[j] = temp;
  }

  private int singlePartitionRandom(int[] unsorted, int first, int last) {
    int location = (int) (Math.random() * (last - first) + first);
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionMedian(int[] unsorted, int first, int last) {
    int location = (first + last) / 2;
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

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

  private int singlePartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    swap(unsorted, first, last);
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int singlePartitionLast(int[] unsorted, int first, int last) {
    int pivot = unsorted[last];
    return singlePointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionRandom(int[] unsorted, int first, int last) {
    int location = (int) (Math.random() * (last - first) + first);
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

  private int dualPartitionMedian(int[] unsorted, int first, int last) {
    int location = (first + last) / 2;
    int pivot = unsorted[location];
    swap(unsorted, location, last);
    return dualPointerPartition(unsorted, first, last, pivot);
  }

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

  private int dualPartitionFirst(int[] unsorted, int first, int last) {
    int pivot = unsorted[first];
    swap(unsorted, first, last);
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
        swap(unsorted, lower, upper);
      }
    }

    swap(unsorted, lower, last);

    return lower;
  }

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

    SortType sort = SortType.QUICK;
    PartitionType part = PartitionType.DUAL;
    PivotType pivot = PivotType.MEDIAN_OF_3;

    for (int i = 0; i < 3; i++) {
      String arrayType = i == 0 ? "Random" : i == 1 ? "Sorted" : "Reversed";
      File file;
      if (sort == SortType.QUICK) {
        new File(String.format("output/%s/%s/%s/%s", sort.name(), arrayType, part.name(), pivot)).mkdirs();
        file = new File(
            String.format("output/%s/%s/%s/%s/%d.csv", sort.name(), arrayType, part.name(), pivot, System.nanoTime()));
      } else {
        new File(String.format("output/%s/%s", sort.name(), arrayType)).mkdirs();
        file = new File(String.format("output/%s/%s/%d.csv", sort.name(), arrayType, System.nanoTime()));
      }
      try {
        FileWriter writer = new FileWriter(file);
        for (int j = 0; j < ARRAY_LENGTHS.length; j++) {
          writer.write(String.format("%d,", ARRAY_LENGTHS[j]));
          for (int k = 0; k < 10; k++) {
            int[] current = Arrays.copyOf(sorter.numbers[i][j], sorter.numbers[i][j].length);
            long start = System.nanoTime();
            if (sort == SortType.QUICK) {
              if (current.length <= 1000000) {
                sorter.quickSort(current, pivot, part);
              } else {
                continue;
              }
            } else {
              if (current.length <= 200000) {
                sorter.insertionSort(current);
              } else {
                continue;
              }
            }
            long end = System.nanoTime();
            writer.write(String.format("%.6f,", (end - start) / 1000000.0));
            writer.flush();
            assert isSorted(current);
          }
          writer.write("\n");
        }
        writer.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void setUp() {
    for (int i = 0; i < ARRAY_LENGTHS.length; i++) {
      numbers[0][i] = new int[ARRAY_LENGTHS[i]];
      numbers[1][i] = new int[ARRAY_LENGTHS[i]];
      numbers[2][i] = new int[ARRAY_LENGTHS[i]];
      for (int j = 0; j < ARRAY_LENGTHS[i]; j++) {
        numbers[0][i][j] = (int) (Math.random() * 1000000);
        numbers[1][i][j] = j;
        numbers[2][i][j] = ARRAY_LENGTHS[i] - j;
      }
    }
  }
}
