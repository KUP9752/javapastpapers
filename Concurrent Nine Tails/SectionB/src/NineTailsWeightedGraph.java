/**
 * You must implement the <code>constructGraph</code> and
 * <code>constructMinimumSpanningTree</code> methods.
 */

public class NineTailsWeightedGraph {

  private final static int NUM_CONFIGURATIONS = 512;
  private final static int TERMINAL_CONFIGURATION_INDEX = 512;
  private ListInterface<PriorityQueueInterface<WeightedEdge>> configurations;
  private MinimumSpanningTree mst;

  /**
   * Constructs the weighted graph for the nine tails problem, and constructs
   * the minimum spanning tree starting from the target configuration.
   */
  public NineTailsWeightedGraph() {
    constructGraph();
    constructMinimumSpanningTree();
  }

  /**
   * <strong>Implement this method for Question 3</strong>
   */
  private void constructGraph() {
    configurations = new ListArrayBased<>();
    for (int i = 1; i <= NUM_CONFIGURATIONS; i++) {
      configurations.add(i, new PriorityQueue<>());
    }
    for (int i = 1; i <= NUM_CONFIGURATIONS; i++) {
      PriorityQueueInterface<WeightedEdge> edges = generateParents(i);
      while (!edges.isEmpty()) {
        WeightedEdge edge = edges.peek();
        configurations.get(edge.child).add(edge);
        edges.remove();
      }
    }
  }

  /**
   * Returns a copy of the entire weighted graph
   */
  private ListInterface<PriorityQueueInterface<WeightedEdge>> getConfigurationsCopy() {
    ListInterface<PriorityQueueInterface<WeightedEdge>> copy
        = new ListArrayBased<>();
    for (int i = 1; i <= NUM_CONFIGURATIONS; i++) {
      copy.add(i, configurations.get(i).clone());
    }
    return copy;
  }

  /**
   * Returns a priority queue of weighted edges that correspond to the legal
   * moves whose child is the configuration with index equal to the given index
   *
   * @param index the configuration index of a child configuration
   */
  private PriorityQueueInterface<WeightedEdge> generateParents(int index) {
    PriorityQueueInterface<WeightedEdge> parents = new PriorityQueue<>();
    char[] conf = indexToConfiguration(index);
    for (int pos = 0; pos < 9; pos++) {
      if (conf[pos] == 'H') {
        FlipResult res = flipConfiguration(index, pos);
        WeightedEdge edge = new WeightedEdge(index, res.newIndex, res.numFlips);
        parents.add(edge);
      }
    }
    return parents;
  }

  // **** helper functions ******

  /**
   * Returns the wrapper object flipResult of the configuration generated by
   * applying a legal move to the given configuration index at the given
   * position.
   *
   * flipResult includes the index of the generated configuration and the number
   * of flips
   *
   * @param confIndex index of the configuration to where a legal move is
   * applied
   * @param position position in the string of Hs and Ts where the legal move is
   * applied NB: position is 0-based
   */
  private FlipResult flipConfiguration(int confIndex, int position) {
    char[] conf = indexToConfiguration(confIndex);
    int row = position / 3;
    int column = position % 3;
    int count = 0;
    if (flipACell(conf, row, column)) {
      count++;
    }
    if (flipACell(conf, row - 1, column)) {
      count++;
    }
    if (flipACell(conf, row + 1, column)) {
      count++;
    }
    if (flipACell(conf, row, column - 1)) {
      count++;
    }
    if (flipACell(conf, row, column + 1)) {
      count++;
    }
    return new FlipResult(configurationToIndex(conf), count);
  }

  private boolean flipACell(char[] conf, int row, int col) {
    if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
      conf[row * 3 + col] = conf[row * 3 + col] == 'H' ? 'T' : 'H';
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the configuration of coin's Hs and Ts that corresponds to a given
   * index
   *
   * @param index index of a configuration NB: index is a 1-base integer
   */
  public char[] indexToConfiguration(int index) {
    index--; // make it a 0-based index
    char[] conf = new char[9];
    for (int i = 0; i < 9; i++) {
      int digit = index % 2;
      if (digit == 0) {
        conf[8 - i] = 'H';
      } else {
        conf[8 - i] = 'T';
      }
      index = index / 2;
    }
    return conf;
  }

  /**
   * Returns the configuration index that corresponds to a given configuration
   * of coin's Hs and Ts
   *
   * @param conf configuration of coin's of Hs and Ts NB: result is a 1-base
   * integer
   */
  public int configurationToIndex(char[] conf) {
    int index = 0;
    for (int i = 0; i < 9; i++) {
      if (conf[i] == 'T') {
        index = index * 2 + 1;
      } else {
        index = index * 2;
      }
    }
    index++; // make it back to 1-based index
    return index;
  }

  public void printParentsTest(int index) {
    printConfiguration(index);
    PriorityQueue<WeightedEdge> parents = configurations.get(index).clone();
    System.out.println(parents.getSize() + " parents" + ": ");
    System.out.println("-----------------------------");
    while (!parents.isEmpty()) {
      WeightedEdge edge = parents.peek();
      parents.remove();
      printConfiguration(edge.parent);
      System.out.println("with weight of " + edge.weight + ".");
    }
    System.out.println("-----------------------------");
  }

  /**
   * Print a configuration with index equal to the given index, as a 3x3 matrix
   *
   * @param index index of the configuration of coin's of Hs and Ts to print
   */
  public void printConfiguration(int index) {
    System.out.println("Configuration " + index + ":");
    char[] conf = indexToConfiguration(index);
    for (int pos = 0; pos < 9; pos++) {
      System.out.print(conf[pos]);
      System.out.print((pos + 1) % 3 == 0 ? '\n' : " ");
    }
  }

  /**
   * Print the shortest path from a given configuration, with index equal to the
   * given source, to the target configuration
   *
   * @param source index of a given configuration of coin's of Hs and Ts
   */
  public void printShortestPath(int source) {
    int confIndex = source;
    System.out.println(
        "Shortest path from " + source + " to target configuration ("
            + TERMINAL_CONFIGURATION_INDEX + ") has cost of "
            + mst.costs[source] + ":"
    );
    System.out.println("------------------------------");
    while (confIndex != TERMINAL_CONFIGURATION_INDEX) {
      printConfiguration(confIndex);
      System.out.println("- - - - - - -");
      confIndex = mst.nextMoves[confIndex];
    }
    printConfiguration(TERMINAL_CONFIGURATION_INDEX);
    System.out.println("------------------------------");
  }

  /**
   * <strong>Implement the rest of this method for Question 4</strong>
   */
  private void constructMinimumSpanningTree() {
    ListInterface<Integer> visited = new ListArrayBased<>();
    int[] nextMoves = new int[NUM_CONFIGURATIONS + 1];
    int[] costs = new int[NUM_CONFIGURATIONS + 1];
    for (int i = 1; i <= NUM_CONFIGURATIONS; i++) {
      nextMoves[i] = -1;
      costs[i] = Integer.MAX_VALUE;
    }
    ListInterface<PriorityQueueInterface<WeightedEdge>> confCopy
        = getConfigurationsCopy();
    visited.add(1, TERMINAL_CONFIGURATION_INDEX);
    costs[TERMINAL_CONFIGURATION_INDEX] = 0;
    while (visited.size() < NUM_CONFIGURATIONS) {
      int u = 1;
      int v = 1;
      int c = Integer.MAX_VALUE;
      for (int i = 1; i <= visited.size(); i++) {
        int u0 = visited.get(i);
        PriorityQueueInterface<WeightedEdge> conf = confCopy.get(u0);
        if (!conf.isEmpty()) {
          WeightedEdge edge = conf.peek();
          if (edge != null) {
            int v0 = edge.parent;
            int c0 = edge.weight + costs[u0];
            while (visited.contains(v0) && !conf.isEmpty()) {
              edge = conf.peek();
              if (edge != null) {
                v0 = edge.parent;
                c0 = edge.weight + costs[u0];
                conf.remove();
              }
            }
            if (c0 + costs[u0] < c) {
              u = u0;
              v = v0;
              c = c0;
            }
          }
        }
      }
      visited.add(visited.size() + 1, v);
      costs[v] = c;
      nextMoves[v] = u;
    }
    mst = new MinimumSpanningTree(nextMoves, costs);
  }

  // *** helper classes

  // MinimumSpanningTree
  private class MinimumSpanningTree {

    int[] nextMoves;
    int[] costs;

    public MinimumSpanningTree(int[] ms, int[] cs) {
      nextMoves = ms;
      costs = cs;
    }

  }

  private class FlipResult {

    int newIndex;
    int numFlips;

    FlipResult(int idx, int flips) {
      newIndex = idx;
      numFlips = flips;
    }

  }

  // WeightedEdge
  private class WeightedEdge implements Comparable<WeightedEdge> {

    private int weight;
    private int parent;
    private int child;

    public WeightedEdge(int p, int c, int w) {
      this.parent = p;
      this.child = c;
      this.weight = w;
    }

    public int compareTo(WeightedEdge edge) {
      if (weight < edge.weight) {
        return -1;
      } else if (weight == edge.weight) {
        return 0;
      } else {
        return 1;
      }
    }

    @Override
    public String toString() {
      return "Edge: parent=" + parent + ", child=" + child + ", weight="
          + weight + ".";
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof WeightedEdge)) {
        return false;
      }
      WeightedEdge otherEdge = (WeightedEdge) obj;
      return (otherEdge.parent == this.parent && otherEdge.child == this.child
          && otherEdge.weight == this.weight);
    }

  }

}