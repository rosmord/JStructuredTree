package org.qenherkhopeshef.expressionTree.sample;

import org.qenherkhopeshef.expressionTree.CompositeNode;
import org.qenherkhopeshef.expressionTree.ExpressionTreeModel;

public class OperationNode extends
		CompositeNode<ArithmeticGrammar, ArithmeticNodeVisitor> implements
		ArithmeticNode {

	private char operator = '+';

	public OperationNode(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> model) {
		super(model);
	}

	public OperationNode(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> model,
			char operator) {
		super(model);
		this.operator = operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public char getOperator() {
		return operator;
	}

	@Override
	public String getText() {
		return "" + operator;
	}

	@Override
	public void accept(ArithmeticNodeVisitor visitor) {
		visitor.visitOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * grammaticalBase.search.ui.expressionModel.ExpressionNode#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * grammaticalBase.search.ui.expressionModel.ExpressionNode#buildEditor()
	 */
	@Override
	public OperationNodeEditor buildEditor() {
		return new OperationNodeEditor(this);
	}

}
