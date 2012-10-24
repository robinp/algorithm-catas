package alg.sort;

public class HeapSortTest extends SortTest {

  @Override
  Sort sort() {
    return new HeapSort();
  }

}
