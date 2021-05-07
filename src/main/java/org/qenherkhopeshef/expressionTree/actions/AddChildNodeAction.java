package org.qenherkhopeshef.expressionTree.actions;



import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.qenherkhopeshef.expressionTree.CompositeNode;
import org.qenherkhopeshef.expressionTree.ExpressionGrammar;
import org.qenherkhopeshef.expressionTree.ExpressionNode;


@SuppressWarnings("serial")
public class AddChildNodeAction<G extends ExpressionGrammar<G,V>,V> extends AbstractAction {
	
	private CompositeNode<G, V> container;
	private ExpressionNode<G, V> newNode;

	public AddChildNodeAction(String title, CompositeNode<G,V> container, ExpressionNode<G,V> newNode) {
		super(title);
		this.container = container;
		this.newNode = newNode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		container.addChild(newNode);
	}

}
