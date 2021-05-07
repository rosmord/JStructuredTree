package org.qenherkhopeshef.expressionTree.sample;

import org.qenherkhopeshef.expressionTree.ExpressionNode;
import org.qenherkhopeshef.expressionTree.ExpressionNodeEditorInterface;
import org.qenherkhopeshef.expressionTree.ExpressionTreeModel;

public class NumberNode extends ExpressionNode<ArithmeticGrammar,ArithmeticNodeVisitor> implements ArithmeticNode {

	private double number = 0.0;

	public NumberNode(ExpressionTreeModel<ArithmeticGrammar,ArithmeticNodeVisitor> model) {
		super(model);
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public double getNumber() {
		return number;
	}

	@Override
	public String getText() {
		return ""+ number;
	}
	
	@Override
	public boolean isEditable() {
		return true;
	}
	
	@Override
	public void accept(ArithmeticNodeVisitor visitor) {
		visitor.visitNumber(this);
	}

	@Override
	public ExpressionNodeEditorInterface buildEditor() {
		return new NumberNodeEditor(this);
	}
}
