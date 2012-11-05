package alg.graph;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class GraphTest {

  private static final double DELTA = 0.001;

  @Test
  public void testShortest1() {

    final Graph g = new Graph();
    g.addUniEdge(1, 2, 2.0);
    g.addUniEdge(2, 3, 1.0);

    final Map<Integer, Double> dist = g.shortestPaths(1);

    assertEquals(3.0, dist.get(3), DELTA); 
    assertEquals(2.0, dist.get(2), DELTA);

    g.addEdge(1, 3, 3.5);
    final Map<Integer, Double> dist2 = g.shortestPaths(1);
    assertEquals(3.0, dist2.get(3), DELTA);
    
    g.addEdge(1, 3, 2.5);
    final Map<Integer, Double> dist3 = g.shortestPaths(1);
    assertEquals(2.5, dist3.get(3), DELTA);

    final Map<Integer, Double> dist4 = g.shortestPaths(3);
    assertEquals(3.0, dist4.get(1), DELTA);
  }

  @Test
  public void testShortest2() {
  }

}
