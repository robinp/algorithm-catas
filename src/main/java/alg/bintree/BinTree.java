package alg.bintree;

import java.util.Comparator;

public class BinTree<A> {

    public static enum TraversalOrder {
	INORDER,
	    PREORDER,
	    POSTORDER;
    }

    public static enum Dir {
	L, R;
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

    // Helpers

    public void set(Dir dir, BinTree<A> child) {
	if (dir == Dir.L) left = child;
	else right = child;
    }

    public BinTree<A> get(Dir dir) {
	if (dir == Dir.L) return left;
	else return right;
    }

    // Traversal

    public void traverse(TraversalOrder order, Callback<A> callback) {
	if (TraversalOrder.PREORDER == order) callback.on(data);
	if (left != null) left.traverse(order, callback);
	if (TraversalOrder.INORDER == order) callback.on(data);
	if (right != null) right.traverse(order, callback);
	if (TraversalOrder.POSTORDER == order) callback.on(data);
    }

    // Insertion

    public void insert(A elem, Comparator<A> cmp) {
	if (cmp.compare(elem, data) <= 0) insertLeft(elem, cmp);
	else insertRight(elem, cmp);
    }

    private void insertLeft(A elem, Comparator<A> cmp) {
	if (left == null) left = leaf(elem);
	else left.insert(elem, cmp); 
    }

    private void insertRight(A elem, Comparator<A> cmp) {
	if (right == null) right = leaf(elem);
	else right.insert(elem, cmp);
    }

    // Deletion

    public void delete(A elem, Comparator<A> cmp) {
	deleteAt(null, Dir.L, elem, cmp);
    }

    private void deleteAt(BinTree<A> parent, Dir pdir, A elem, Comparator<A> cmp) {
	final int elemCmpData = cmp.compare(elem, data);
	if (elemCmpData == 0) deleteThis(parent, pdir);
	else if (elemCmpData < 0) left.deleteAt(this, Dir.L, elem, cmp);
	else right.deleteAt(this, Dir.R, elem, cmp);
    }

    private void deleteThis(BinTree<A> parent, Dir pdir) {
	if (left == null && right == null) {
	    if (parent == null) throw new IllegalStateException("can't del parent, sorry");
	    parent.set(pdir, null);
	}
	else if (left != null && right != null) {
	    final BinTree<A> deleted = right.deleteSide(Dir.L, this, Dir.R);
	    data = deleted.data;
	}
	else if (left != null) parent.set(pdir, left);
	else parent.set(pdir, right);
    }

    private BinTree<A> deleteSide(Dir dir, BinTree<A> parent, Dir parentDir) {
	if (get(dir) != null) return get(dir).deleteSide(dir, this, dir);
	else {
	    deleteThis(parent, parentDir);
	    return this;
	}
    }

}