package com.awtpi314;

public class Location {
  private int x;
  private int y;
  private int z;

  public Location(int number, int n, int m, int k) {
    // NOAH DID THIS PART. 
    this.x = number % n;
    this.y = (number / n) % m;
    this.z = number / (n * m);
  }

  public Location(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }

  public boolean equals(Location other) {
    return this.x == other.getX() && this.y == other.getY() && this.z == other.getZ();
  }

  public boolean adjacent(Location other) {
    return Math.abs(this.x - other.getX()) +
        Math.abs(this.y - other.getY()) +
        Math.abs(this.z - other.getZ()) == 1;
  }
}
