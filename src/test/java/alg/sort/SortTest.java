package alg.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class SortTest {

  abstract Sort sort();

  @Test
  public void testSortingComplex() {
    equalsSorted(
      new int[] {1, 1, 2, 3, 4, 5, 7},
      new int[] {5, 4, 3, 1, 2, 1, 7});
  }

  @Test
  public void testSortingEmpty() {
    equalsSorted(
      new int[] {},
      new int[] {});
  }
 
  @Test
  public void testSortingSingle() {
    equalsSorted(
      new int[] {1},
      new int[] {1});
  }

  @Test
  public void testSortingTwo() {
    equalsSorted(
      new int[] {1, 2},
      new int[] {2, 1});
  }

  @Test
  public void testSortingRepeated() {
    equalsSorted(
      new int[] {1, 1, 1, 2, 2, 2},
      new int[] {1, 2, 1, 2, 2, 1});
  }

  private void equalsSorted(int[] expected, int[] actual) {
    sort().sort(actual, 0, actual.length - 1);
    assertArrayEquals(expected, actual);
  }

}
