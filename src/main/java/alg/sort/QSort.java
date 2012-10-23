package alg.sort;

class QSort implements Sort {

  @Override
  public void sort(int[] as, int b, int e) {
    if (b >= e) return;
    final int ref = as[e];
    int i = b;
    int j = b;
    while (i <= e) {
      if (as[i] <= ref) swap(as, i++, j++);
      else i++;
    }
    sort(as, b, j - 2);
    sort(as, j, e);
  }

  private void swap(int[] as, int i, int j) {
    final int temp = as[i];
    as[i] = as[j];
    as[j] = temp;
  }
}
