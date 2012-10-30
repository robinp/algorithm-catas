package alg.bintree;

import java.util.Comparator;

public class BinTree<A> {

    public static enum TraversalOrder {
	INORDER,
	    PREORDER,
	    POSTORDER;
    }
    
    public static interface Callback<A> {
	void on(A a);
    }


    public static <A> BinTree<A> node(A data, BinTree<A> left, BinTree<A> right) {
	return new BinTree<A>(data, left, right);
    }

    public static <A> BinTree<A> leaf(A data) {
	return node(data, null, null);
    }

    private A data;

    private BinTree<A> left;
    private BinTree<A> right;

    public BinTree(A data, BinTree<A> left, BinTree<A> right) {
	this.data = data;
	this.left = left;
	this.right = right;
    }

    // Traversal

    public void traverse(TraversalOrder order, Callback<A> callback) {
	if (TraversalOrder.INORDER == order) {
	    if (left != null) left.traverse(order, callback);
	    callback.on(data);
	    if (right != null) right.traverse(order, callback);
	}
	else throw new IllegalStateException("TODO implement");
    }

    // Insertion

    public void insert(A elem, Comparator<A> cmp) {
	if (cmp.compare(elem, data) <= 0) insertLeft(elem, cmp);
	else insertRight(elem, cmp);
    }

    private void insertLeft(A elem, Comparator<A> cmp) {
	if (left == null) {
	    left = leaf(elem);
	}
	else {
	    left.insert(elem, cmp); 
	}
    }

    private void insertRight(A elem, Comparator<A> cmp) {
	if (right == null) {
	    right = leaf(elem);
	} else {
	    right.insert(elem, cmp);
	}
    }

}