package alg.graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Graph {

  private static final <A, B> Map<A, B> newMap() {
    return new HashMap<A, B>();
  }

  private final Map<Integer, Map<Integer, Double>> edges;

  public Graph() {
    edges = newMap();
  }

  public void addEdge(int i, int j, double w) {
    if (!edges.containsKey(i)) {
      edges.put(i, Graph.<Integer, Double>newMap());
    }
    edges.get(i).put(j, w);
  }

  public void addUniEdge(int i, int j, double w) {
    addEdge(i, j, w);
    addEdge(j, i, w);
  }

  public Map<Integer, Double> shortestPaths(int fromNode) {
    final Set<Integer> finished = new HashSet<Integer>();
    final Map<Integer, Double> dist = newMap();

    finish(fromNode, 0.0, dist, finished);

    Integer minDistNode = 999;
    while (minDistNode != null) {
      minDistNode = null;
      for (Map.Entry<Integer, Double> nodeDist : dist.entrySet()) {
        final int node = nodeDist.getKey();
        final double d = nodeDist.getValue();
        if (!finished.contains(node)) {
          if (minDistNode == null || dist.get(minDistNode) > d) {
            minDistNode = node;
          } 
        }
      }
      if (minDistNode != null) finish(minDistNode, dist.get(minDistNode), dist, finished);
    }
    return dist;
  }

  private void finish(int i, double d, Map<Integer, Double> dist, Set<Integer> finished) {
    finished.add(i);
    dist.put(i, d);
    updateDists(i, dist);
  }

  private void updateDists(int i, Map<Integer, Double> dist) {
    final double iDist = dist.get(i);
    for (Map.Entry<Integer, Double> neighW : edges.get(i).entrySet()) {
      final double newDist = iDist + neighW.getValue();
      final int neigh = neighW.getKey();
      if (!dist.containsKey(neigh)) dist.put(neigh, newDist);
      else if (newDist < dist.get(neigh)) dist.put(neigh, newDist);
    }
  }
}
