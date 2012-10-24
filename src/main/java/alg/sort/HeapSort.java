package alg.sort;

public class HeapSort implements Sort {
  
  // not thread-safe
  private int b;
  private int e;
  private int[] as;


  @Override
  public void sort(int[] as, int b, int e) {
    this.b = b;
    this.e = e;
    this.as = as;
    final int cnt = elemCount();
    for (int i = cnt / 2; i >= 1; i--) {
      heapDown(i);
    }
    // sort in place, building the array from back to end
    for (int i = elemCount(); i > 1; i--) {
      swap(i, 1);
      this.e -= 1; // for heapDown to respect bounds
      heapDown(1);
    }
  }

  private int elemCount() {
    return e - b + 1;
  }

  private int pos(int i) {
    return b + i - 1;
  }

  private int left(int i) {
    return 2*i;
  }

  private int right(int i) {
    return 2*i + 1;
  }

  private boolean valid(int i) {
    return i >= 1 && i <= elemCount();
  }

  private int get(int i) {
    return as[pos(i)];
  }

  private void heapDown(int i) {
    int maxValue = get(i);
    int maxIdx = i;
    if (valid(right(i)) && get(right(i)) > maxValue) {
      maxIdx = right(i);
      maxValue = get(right(i));
    }
    if (valid(left(i)) && get(left(i)) > maxValue) {
      maxIdx = left(i);
      maxValue = get(left(i));
    }
    if (maxIdx == i) return;
    swap(i, maxIdx);
    heapDown(maxIdx);
  }

  private void swap(int i, int j) {
    final int temp = get(i);
    as[pos(i)] = get(j);
    as[pos(j)] = temp;
  }

}
