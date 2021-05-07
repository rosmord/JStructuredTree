package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;


public abstract class AbstractRootNode<G extends ExpressionGrammar<G,V>,V> extends CompositeNode<G,V> {

	public AbstractRootNode(ExpressionTreeModel<G,V> model) {
		super(model);
	}
	
	@Override
	public String getText() {
		return "";
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public JPopupMenu getPopup() {
		return null;
	}
	
}
