package alg.bintree;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinTreeTest {

    private static <A extends Comparable<A>> Comparator<A> defaultComparator() {
	return new Comparator<A>() {
	    @Override
	    public int compare(A a, A b) {
		return a.compareTo(b);
	    }
	};
    }

    @Test
    public void testInsertion() {
	final BinTree<Integer> tree = BinTree.leaf(5);
	final Comparator<Integer> intCmp = defaultComparator();
	tree.insert(1, intCmp);
	tree.insert(7, intCmp);
	tree.insert(6, intCmp);
	tree.insert(9, intCmp);

	final List<Integer> elems = new LinkedList<Integer>();
	final BinTree.Callback<Integer> cb = new BinTree.Callback<Integer>() {
	    @Override
	    public void on(Integer a) {
		elems.add(a);
	    }
	};
	tree.traverse(BinTree.TraversalOrder.INORDER, cb);

	assertArrayEquals(new Integer[] {1, 5, 6, 7, 9}, elems.toArray(new Integer[] {}));

	tree.delete(1, intCmp);

	elems.clear();
	tree.traverse(BinTree.TraversalOrder.INORDER, cb);
	assertArrayEquals(new Integer[] {5, 6, 7, 9}, elems.toArray(new Integer[] {}));

	tree.delete(7, intCmp);

	elems.clear();
	tree.traverse(BinTree.TraversalOrder.INORDER, cb);
	assertArrayEquals(new Integer[] {5, 6, 9}, elems.toArray(new Integer[] {}));

    }
}