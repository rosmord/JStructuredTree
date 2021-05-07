package org.qenherkhopeshef.expressionTree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.qenherkhopeshef.expressionTree.CompositeNode;
import org.qenherkhopeshef.expressionTree.ExpressionGrammar;
import org.qenherkhopeshef.expressionTree.ExpressionNode;
import org.qenherkhopeshef.expressionTree.utils.LabelRepository;

@SuppressWarnings("serial")
public class RemoveNodeAction<G extends ExpressionGrammar<G, V>, V> extends AbstractAction{
	
	private CompositeNode<G, V> parent;
	private ExpressionNode<G, V> element;
	
	public  RemoveNodeAction(CompositeNode<G, V> parent,
			ExpressionNode<G, V> element) {
		super(LabelRepository.getLabel("Delete"));
		this.parent= parent;
		this.element= element;
	}
	
	public void actionPerformed(ActionEvent e) {
		parent.getModel().remove(parent, element);
	}
}
