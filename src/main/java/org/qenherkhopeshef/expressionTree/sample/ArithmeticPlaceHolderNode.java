package org.qenherkhopeshef.expressionTree.sample;

import org.qenherkhopeshef.expressionTree.AbstractPlaceHolderNode;
import org.qenherkhopeshef.expressionTree.ExpressionTreeModel;

public class ArithmeticPlaceHolderNode extends
		AbstractPlaceHolderNode<ArithmeticGrammar, ArithmeticNodeVisitor>
		implements ArithmeticNode {

	public ArithmeticPlaceHolderNode(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> model) {
		super(model);
	}

	@Override
	public void accept(ArithmeticNodeVisitor visitor) {
		visitor.visitPlaceHolder(this);
	}

}
