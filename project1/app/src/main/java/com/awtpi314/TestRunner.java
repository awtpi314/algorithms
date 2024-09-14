package com.awtpi314;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import com.awtpi314.Sorter.PartitionType;
import com.awtpi314.Sorter.PivotType;
import com.awtpi314.Sorter.SortType;

public class TestRunner implements Runnable {
  private int[][] toSort;
  String name;
  SortType sortType;
  PartitionType partitionType;
  PivotType pivotType;

  public TestRunner(int[][] toSort, String name, SortType sortType, PartitionType partitionType, PivotType pivotType) {
    this.toSort = toSort;
    this.name = name;
    this.sortType = sortType;
    this.partitionType = partitionType;
    this.pivotType = pivotType;
  }

  public void run() {
    Sorter sorter = new Sorter();
    double times[] = new double[toSort.length];

    for (int i = 0; i < toSort.length; i++) {
      int[] current = Arrays.copyOf(toSort[i], toSort[i].length);
      long start = System.nanoTime();
      if (sortType == SortType.QUICK) {
        sorter.quickSort(current, pivotType, partitionType);
      } else if (sortType == SortType.INSERTION) {
        sorter.insertionSort(current);
      }
      long end = System.nanoTime();
      times[i] = (end - start) / 1000000000.0;

      assert Sorter.isSorted(current);
    }

    File dirMaker = new File(String.format("output/%s/%s", sortType.name(), name));
    dirMaker.mkdirs();
    File file;
    if (sortType == SortType.QUICK) {
      file = new File(String.format("output/%s/%s/%s_%s_%d_%d.csv", sortType.name(), name, partitionType.name(), pivotType,
          toSort.length, System.nanoTime()));
    } else {
      file = new File(String.format("output/%s/%s/%d_%d.csv", sortType.name(), name, toSort.length, System.nanoTime()));
    }

    FileWriter writer;
    try {
      writer = new FileWriter(file);
      for (int i = 0; i < toSort.length; i++) {
        writer.write(String.format("%.9f\n", times[i]));
      }
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
