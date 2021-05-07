package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;

/**
 * A node made of other nodes in an expression.
 * @author rosmord
 * @param <G>
 * @param <V> 
 */
public abstract class CompositeNode<G extends ExpressionGrammar<G,V>,V> extends ExpressionNode<G,V> {
	
	boolean unary = false; // This node has only one child ?

	public CompositeNode(ExpressionTreeModel<G,V> model) {
		super(model);
	}

	/**
	 * Create a composite node, which might be unary (limited to one child).
	 * 
	 * @param unary
	 */
	public CompositeNode(ExpressionTreeModel<G,V> model, boolean unary) {
		super(model);
		this.unary = unary;
	}

	public void addChild(ExpressionNode<G,V> child) {
		if (unary && getNumberOfChildren() >= 1)
			children.set(0, child);
		else
			children.add(child);
		child.parent= this;
		model.fireChange();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	public void clear() {
		children.clear();
	}
	
	@Override
	public JPopupMenu getPopup() {
		JPopupMenu result = getModel().buildPopup(parent, this);
		result.addSeparator();
		//new PopupBuilder(getParent(), this).fillPopup(result);
		return result;
	}

	public void addAfter(ExpressionNode<G,V> element,
			ExpressionNode<G,V> newElement) {
		int i= children.indexOf(element);
		children.add(i+1, newElement);
		newElement.parent= this;
		model.fireChange();
	}
	
	public void addBefore(ExpressionNode<G,V> element,
			ExpressionNode<G,V> newElement) {
		int i= children.indexOf(element);
		children.add(i, newElement);
		newElement.parent= this;
		model.fireChange();
	}
	
	public void replace(ExpressionNode<G,V> toReplace, ExpressionNode<G,V> replacement) {
		int i= children.indexOf(toReplace);
		if (i != -1) {
			children.set(i, replacement);
			replacement.parent= this;
		}
		else
			System.err.println(java.util.ResourceBundle.getBundle("grammaticalBase/search/searchLabels").getString("Invalid_replacement_")+ toReplace + " -> " + replacement);
	}
	
}
