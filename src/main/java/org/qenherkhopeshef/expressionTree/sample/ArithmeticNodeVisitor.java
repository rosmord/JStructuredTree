package org.qenherkhopeshef.expressionTree.sample;


public interface ArithmeticNodeVisitor {
	void visitNumber(NumberNode node);
	void visitOperation(OperationNode operation);
	void visitPlaceHolder(ArithmeticPlaceHolderNode node);
	void visitRootNode(ArithmeticRootNode node);
}
