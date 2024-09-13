package com.awtpi314;

public class Sorter {
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
  
  public static void main(String[] args) {}
}
