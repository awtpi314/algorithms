package com.awtpi314;

/**
 * The Location class represents a point in a 3D space with x, y, and z
 * coordinates.
 */
public class Location {
  private int x;
  private int y;
  private int z;

  /**
   * Constructor for a Location.
   * 
   * @param number the number to convert to x, y, and z coordinates
   * @param n      the number of locations in the x direction
   * @param m      the number of locations in the y direction
   * @param k      the number of locations in the z direction
   */
  public Location(int number, int n, int m, int k) {
    // NOAH DID THIS PART.
    this.x = number % n;
    this.y = (number / n) % m;
    this.z = number / (n * m);
  }

  /**
   * Constructor for a Location.
   * 
   * @param x the x coordinate of the location
   * @param y the y coordinate of the location
   * @param z the z coordinate of the location
   */
  public Location(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Returns the x coordinate of this location.
   * 
   * @return the x coordinate of this location
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of this location.
   * 
   * @return the y coordinate of this location
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns the z coordinate of this location.
   * 
   * @return the z coordinate of this location
   */
  public int getZ() {
    return this.z;
  }

  /**
   * Checks for equality with another Location.
   * 
   * @param other the other Location to compare to
   * @return whether this Location is equal to the other Location
   */
  public boolean equals(Location other) {
    return this.x == other.getX() && this.y == other.getY() && this.z == other.getZ();
  }

  /**
   * Checks if this Location is adjacent to another Location.
   * 
   * @param other the other Location to check adjacency with
   * @return whether this Location is adjacent to the other Location
   */
  public boolean adjacent(Location other) {
    return Math.abs(this.x - other.getX()) +
        Math.abs(this.y - other.getY()) +
        Math.abs(this.z - other.getZ()) == 1;
  }
}
