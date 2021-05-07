package org.qenherkhopeshef.expressionTree;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPopupMenu;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * generic editable expression represented as a graphical tree.
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 */
public class ExpressionTreeModelImpl<G extends ExpressionGrammar<G,V>,V> implements ExpressionTreeModel<G,V> {

	private CompositeNode<G,V> abstractRootNode;

	/**
	 * We use a vector, 'cause it's synchronized ?
	 */
	private Vector<TreeModelListener> treeModelListeners = new java.util.Vector<TreeModelListener>();
	private G expressionGrammar;
	
	public ExpressionTreeModelImpl(G expressionGrammar) {
		abstractRootNode = expressionGrammar.createRootNode(this);
		abstractRootNode.addChild(expressionGrammar.createPlaceHolderNode(this));
		this.expressionGrammar= expressionGrammar;
	}

	public void fireChange() {
		TreeModelEvent event = new TreeModelEvent(this,
				new Object[] { abstractRootNode });
		for (TreeModelListener listener : treeModelListeners) {
			listener.treeStructureChanged(event);
		}
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#getChild(java.lang.Object, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object getChild(Object parent, int index) {
		if (parent instanceof ExpressionNode) {
			ExpressionNode<G,V> node = (ExpressionNode<G,V>) parent;
			return node.getChild(index);
		} else
			return null;
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#getChildCount(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int getChildCount(Object parent) {
		return ((ExpressionNode<G,V>) parent).getNumberOfChildren();
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int getIndexOfChild(Object parent, Object child) {
		ExpressionNode<G,V> node = (ExpressionNode<G,V>) parent;
		return node.indexOfChild((ExpressionNode<G,V>)child);
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#getRoot()
	 */
	@Override
	public CompositeNode<G, V>  getRoot() {
		return abstractRootNode;
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#isLeaf(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean isLeaf(Object obj) {
		ExpressionNode<G,V> node = (ExpressionNode<G,V>) obj;
		return node.isLeaf();
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		//System.out.println("hello there " + newValue + path);
	}

	
	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#replace(grammaticalBase.search.ui.expressionModel.CompositeNode, grammaticalBase.search.ui.expressionModel.ExpressionNode, grammaticalBase.search.ui.expressionModel.ExpressionNode)
	 */
	@Override
	public void replace(CompositeNode<G,V> parent, ExpressionNode<G,V> toReplace,
			ExpressionNode<G,V> replacement) {
		parent.replace(toReplace, replacement);
		fireChange();
	}


	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#remove(grammaticalBase.search.ui.expressionModel.ExpressionNode, grammaticalBase.search.ui.expressionModel.ExpressionNode)
	 */
	@Override
	public void remove(ExpressionNode<G,V> parent, ExpressionNode<G,V> element) {
		// We should maybe inhibit firechange at that stage, as
		// the following method should throw it (it doesn't yet).
		parent.removeChild(element);
		if (parent == abstractRootNode && parent.getNumberOfChildren() == 0)
			abstractRootNode.addChild(expressionGrammar.createPlaceHolderNode(this));
		fireChange();
	}
	
	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#clear()
	 */
	@Override
	public void clear() {
		abstractRootNode.children.clear();
		abstractRootNode.addChild(expressionGrammar.createPlaceHolderNode(this));
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#fireNodeChange(grammaticalBase.search.ui.expressionModel.ExpressionNode)
	 */
	@Override
	public void fireNodeChange(ExpressionNode<G,V> node) {
		ArrayList<ExpressionNode<G,V>> path = new ArrayList<ExpressionNode<G,V>>();
		ExpressionNode<G,V> n = (ExpressionNode<G,V>) node;
		while (n != null) {
			path.add(0, n);
			n = n.parent;
		}
		TreeModelEvent e = new TreeModelEvent(this, path.toArray());
		for (TreeModelListener l : treeModelListeners) {
			l.treeNodesChanged(e);
		}
	}

	/* (non-Javadoc)
	 * @see grammaticalBase.search.ui.expressionModel.ExpressionTreeModel#buildPopup(grammaticalBase.search.ui.expressionModel.ExpressionNode, grammaticalBase.search.ui.expressionModel.ExpressionNode)
	 */
	@Override
	public JPopupMenu buildPopup(CompositeNode<G,V> parent,
			ExpressionNode<G,V> child) {
		return expressionGrammar.buildPopup(this, parent, child);
	}
	
}
