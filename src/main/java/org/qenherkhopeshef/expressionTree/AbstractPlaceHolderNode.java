package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;


/**
 * A place holder node for adding new data.
 * @author rosmord
 *
 */
public abstract class AbstractPlaceHolderNode<G extends ExpressionGrammar<G,V>,V> extends ExpressionNode<G,V> {

	public AbstractPlaceHolderNode(ExpressionTreeModel<G,V> model) {
		super(model);
		
	}

	@Override
	public String getText() {
			return "...";
	}
	
	@Override
	public JPopupMenu getPopup() {
		return getParent().getModel().buildPopup(getParent(), this);
	}
	
}
