package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * An empty tree, which can't be modified. 
 * Can be used as a default value, while the actual implementation is not ready.
 * @author rosmord
 * @param <G>
 * @param <V> 
 */
public class EmptyModel<G extends ExpressionGrammar<G,V>, V> implements
		ExpressionTreeModel<G, V> {

	private EmptyRoot root= new EmptyRoot(this);

	@Override
	public void addTreeModelListener(TreeModelListener l) {

	}

	@Override
	public Object getChild(Object parent, int index) {
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		return 0;

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return -1;
	}

	@Override
	public CompositeNode<G, V> getRoot() {		
		return root;
	}

	@Override
	public boolean isLeaf(Object obj) {
		return true;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	@Override
	public void replace(CompositeNode<G, V> parent,
			ExpressionNode<G, V> toReplace,
			ExpressionNode<G, V> replacement) {
	}

	@Override
	public void remove(ExpressionNode<G, V> parent,
			ExpressionNode<G, V> element) {

	}

	@Override
	public void clear() {
	}

	@Override
	public void fireChange() {
	}

	@Override
	public void fireNodeChange(ExpressionNode<G, V> node) {
	}

	@Override
	public JPopupMenu buildPopup(CompositeNode<G, V> parent,
			ExpressionNode<G, V> child) {
		return null;
	}
	
	private  class EmptyRoot extends AbstractRootNode<G, V> {

		public EmptyRoot(ExpressionTreeModel<G, V> model) {
			super(model);
		}

		@Override
		public void accept(V nodeVisitor) {
		}
		
	
		@Override
		public String toString() {
			return "...";
		}
	}
	
}
