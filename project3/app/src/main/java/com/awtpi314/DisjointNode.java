/**
 * The DisjointNode class represents a node in a disjoint-set data structure,
 * also known as a union-find data structure. This structure is used to keep
 * track of a set of elements partitioned into a number of disjoint (non-overlapping)
 * subsets.
 * 
 * Each DisjointNode contains a reference to its representative node (rep), a rank
 * used for union, and a location representing where this node is located in the galaxy.
 */
package com.awtpi314;

public class DisjointNode {
  private DisjointNode rep;
  private int rank;
  private Location location;

  /**
   * Constructor for a DisjointNode. We should call the makeSet method instead of
   * this constructor.
   * 
   * @param location where this node is located in the galaxy
   */
  private DisjointNode(Location location) {
    this.rep = this;
    this.rank = 0;
    this.location = location;
  }

  /**
   * Creates a new DisjointNode with the given location and returns it.
   * 
   * @param location where this node is located in the galaxy
   * @return the new DisjointNode
   */
  public static DisjointNode makeSet(Location location) {
    DisjointNode node = new DisjointNode(location);
    return node;
  }

  /**
   * Finds the representative node of this node and returns it. We use path
   * compression to optimize the future find operations.
   * 
   * @return the representative node of this node
   */
  public DisjointNode findRepresentative() {
    if (this.rep != this) {
      this.rep = this.rep.findRepresentative();
    }
    return this.rep;
  }

  /**
   * Unions this node with the given node and returns the representative node of
   * the resulting set. We use rank to optimize the time-complexity of the union
   * operation.
   * 
   * @param other the node to union with
   * @return the representative node of the resulting set
   */
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

  /**
   * Returns the location of this node in the galaxy.
   * 
   * @return the location of this node
   */
  public Location getLocation() {
    return this.location;
  }

  /**
   * Returns whether this node is equal to the given node.
   * 
   * @param other the node to compare to
   * @return true if the nodes are equal, false otherwise
   */
  public boolean equals(DisjointNode other) {
    return this.location.equals(other.getLocation());
  }
}
