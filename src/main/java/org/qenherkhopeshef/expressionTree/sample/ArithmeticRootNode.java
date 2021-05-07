package org.qenherkhopeshef.expressionTree.sample;

import org.qenherkhopeshef.expressionTree.AbstractRootNode;
import org.qenherkhopeshef.expressionTree.ExpressionTreeModel;

public class ArithmeticRootNode extends AbstractRootNode<ArithmeticGrammar,ArithmeticNodeVisitor> implements ArithmeticNode{

	public ArithmeticRootNode(ExpressionTreeModel<ArithmeticGrammar,ArithmeticNodeVisitor> model) {
		super(model);
	}

	@Override
	public void accept(ArithmeticNodeVisitor visitor) {
		visitor.visitRootNode(this); 
	}

}
