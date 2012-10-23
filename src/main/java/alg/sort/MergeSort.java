package alg.sort;

class MergeSort implements Sort {

  @Override
  public void sort(int[] as, int b, int e) {
    final int[] helper = new int[as.length];
    sortUsing(as, helper, b, e);
  }

  void sortUsing(int[] as, int[] merge, int b, int e) {
    if (e - b <= 0) return;
    final int c = b + (e - b) / 2;
    sortUsing(as, merge, b, c);
    sortUsing(as, merge, c + 1, e);
    merge(as, b, c, as, c + 1, e, merge);
    System.arraycopy(merge, 0, as, b, e - b + 1);
  }

  void merge(int[] as, int ab, int ae, int[] bs, int bb, int be, int[] target) {
    int i = ab;
    int j = bb;
    int t = 0;
    while (i <= ae && j <= be) {
      while (i <= ae && as[i] <= bs[j]) target[t++] = as[i++];
      while (j <= be && bs[j] <= as[i]) target[t++] = bs[j++];
    }
    while (i <= ae) target[t++] = as[i++];
    while (j <= be) target[t++] = bs[j++];
  }

}
