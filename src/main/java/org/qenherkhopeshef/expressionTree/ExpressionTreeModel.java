package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * The representation of an tree-like expression, as edited in a {@link JExpressionTree}.
 * <p> This interface is an extension of the TreeModel interface of Swing. That is, it's usable 
 * with a JTree (which explains the use of the Object class as argument of some methods).
 * <p> Actual implementation for this interface will usually be {@link ExpressionTreeModelImpl}. An constant 
 * empty implementation is also available as {@link EmptyModel}.
 * <p> The expression tree model is able to manipulate nodes, 
 * as defined by the Expression Grammar given as argument.
 * @param <G>
 * @param <V> 
 * @see ExpressionGrammar
 */
public interface ExpressionTreeModel<G extends ExpressionGrammar<G, V>, V>
		extends TreeModel {

    @Override
	void addTreeModelListener(TreeModelListener l);

    @Override
	Object getChild(Object parent, int index);

    @Override
	int getChildCount(Object parent);

    @Override
	int getIndexOfChild(Object parent, Object child);

    @Override
	CompositeNode<G, V>  getRoot();

    @Override
	boolean isLeaf(Object obj);

    @Override
	void removeTreeModelListener(TreeModelListener l);

    @Override
	void valueForPathChanged(TreePath path, Object newValue);

    /**
     * Replace a node by another one in a parent.
     * @param parent
     * @param toReplace
     * @param replacement 
     */
	void replace(CompositeNode<G, V> parent, ExpressionNode<G, V> toReplace,
			ExpressionNode<G, V> replacement);

    /**
     * Remove a node in a parent.
     */
	void remove(ExpressionNode<G, V> parent, ExpressionNode<G, V> element);

    /**
     * Clear the tree, leaving only the root node intact.
     */
	void clear();

	public void fireChange();

	void fireNodeChange(ExpressionNode<G, V> node);

    /**
     * Build a popup menu for a given node.
     * <p> The popup menu will typically contain two parts:
     * <ul>
     * <li> menu entries which act on the current node, as replacement, adding other nodes
     * before and/or after it...
     * <li> menu entries which act on the node children, if any (adding a child of a given type, etc...)
     * <li> usually, the specific data associated with most nodes is edited 
     * by a specific editor, which is a completely different story.
     * </ul>
     * @param parent the parent of the node.
     * @param child the node we want to display a menu for.
     * @return 
     */
	JPopupMenu buildPopup(CompositeNode<G, V> parent, ExpressionNode<G, V> child);

}