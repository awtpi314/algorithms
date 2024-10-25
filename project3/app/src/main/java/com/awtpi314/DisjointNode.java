package com.awtpi314;

public class DisjointNode {
  private DisjointNode rep;
  private int rank;
  private Location location;

  public DisjointNode(Location location) {
    this.rep = this;
    this.rank = 0;
    this.location = location;
  }

  public static DisjointNode makeSet(Location location) {
    DisjointNode node = new DisjointNode(location);
    return node;
  }

  public DisjointNode findRepresentative() {
    if (this.rep != this) {
      this.rep = this.rep.findRepresentative();
    }
    return this.rep;
  }

  public DisjointNode union(DisjointNode other) {
    DisjointNode leftRoot = this.findRepresentative();
    DisjointNode rightRoot = other.findRepresentative();

    if (leftRoot == rightRoot) {
      return leftRoot;
    }

    if (leftRoot.rank < rightRoot.rank) {
      leftRoot.rep = rightRoot;
      return rightRoot;
    } else if (leftRoot.rank > rightRoot.rank) {
      rightRoot.rep = leftRoot;
      return leftRoot;
    } else {
      rightRoot.rep = leftRoot;
      leftRoot.rank++;
      return leftRoot;
    }
  }

  public Location getLocation() {
    return this.location;
  }

  public boolean equals(DisjointNode other) {
    return this.location.equals(other.getLocation());
  }
}
