package org.qenherkhopeshef.expressionTree.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.qenherkhopeshef.expressionTree.*;

@SuppressWarnings("serial")
public class AddSlibingNodeAction<G extends ExpressionGrammar<G, V>, V> extends
		AbstractAction {

	public static enum InsertPosition {
		BEFORE, AFTER
	}

	private CompositeNode<G, V> parent;
	private ExpressionNode<G, V> referenceNode;
	private ExpressionNode<G, V> newNode;
	private InsertPosition insertPosition;

	public AddSlibingNodeAction(String title, CompositeNode<G, V> parent,
			ExpressionNode<G, V> referenceNode, ExpressionNode<G, V> newNode,
			InsertPosition insertPosition) {
		super(title);
		this.parent = parent;
		this.referenceNode = referenceNode;
		this.insertPosition = insertPosition;
		this.newNode = newNode;
	}

	public void actionPerformed(ActionEvent e) {
		if (insertPosition == InsertPosition.AFTER) { // add after
			parent.addAfter(referenceNode, newNode);
		} else { // add before
			parent.addBefore(referenceNode, newNode);
		}
	}
}
