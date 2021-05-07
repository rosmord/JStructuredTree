package org.qenherkhopeshef.expressionTree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.qenherkhopeshef.expressionTree.CompositeNode;
import org.qenherkhopeshef.expressionTree.ExpressionGrammar;
import org.qenherkhopeshef.expressionTree.ExpressionNode;

@SuppressWarnings("serial")
public class ReplaceNodeAction<G extends ExpressionGrammar<G, V>, V> extends AbstractAction {
	
	private CompositeNode<G,V> parent;
	private ExpressionNode<G, V> toReplace, replacement;

	public ReplaceNodeAction(String title, CompositeNode<G,V> parent,
			ExpressionNode<G, V> toReplace, ExpressionNode<G, V> replacement) {
		super(title);
		this.parent= parent;
		this.toReplace= toReplace;
		this.replacement= replacement;
	}

	public void actionPerformed(ActionEvent e) {
		parent.getModel().replace(parent, toReplace, replacement);
	}

}
