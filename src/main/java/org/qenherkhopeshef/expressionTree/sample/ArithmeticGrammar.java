package org.qenherkhopeshef.expressionTree.sample;

import javax.swing.JPopupMenu;

import org.qenherkhopeshef.expressionTree.CompositeNode;
import org.qenherkhopeshef.expressionTree.ExpressionGrammar;
import org.qenherkhopeshef.expressionTree.ExpressionNode;
import org.qenherkhopeshef.expressionTree.ExpressionTreeModel;
import org.qenherkhopeshef.expressionTree.actions.AddSlibingNodeAction;
import org.qenherkhopeshef.expressionTree.actions.RemoveNodeAction;
import org.qenherkhopeshef.expressionTree.actions.ReplaceNodeAction;
import org.qenherkhopeshef.expressionTree.utils.LabelRepository;

public class ArithmeticGrammar implements
		ExpressionGrammar<ArithmeticGrammar, ArithmeticNodeVisitor> {

	@Override
	public JPopupMenu buildPopup(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> treeModel,
			CompositeNode<ArithmeticGrammar, ArithmeticNodeVisitor> parent,
			ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> child) {
		PopupBuilder builder = new PopupBuilder(parent, treeModel);
		child.accept(builder);
		return builder.built;
	}

	@Override
	public CompositeNode<ArithmeticGrammar, ArithmeticNodeVisitor> createRootNode(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> model) {
		return new ArithmeticRootNode(model);
	}

	@Override
	public ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> createPlaceHolderNode(
			ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> model) {
		return new ArithmeticPlaceHolderNode(model);
	}

	/**
	 * Popup menu creation. TODO : provide the generic part of this code in some
	 * way.
	 * 
	 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
	 */

	private class PopupBuilder implements ArithmeticNodeVisitor {
		JPopupMenu built;
		private ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> treeModel;
		CompositeNode<ArithmeticGrammar, ArithmeticNodeVisitor> parent;

		public PopupBuilder(
				CompositeNode<ArithmeticGrammar, ArithmeticNodeVisitor> parent,
				ExpressionTreeModel<ArithmeticGrammar, ArithmeticNodeVisitor> treeModel) {
			this.treeModel = treeModel;
			this.built = new JPopupMenu();
			this.parent = parent;
		}

		@Override
		/**
		 * Possibilities for numbers : replacement by an operation.
		 */
		public void visitNumber(NumberNode node) {
			replaceByOpMenu(node);
			topElementsMenu(node);
		}

		/**
		 * Optional menu for direct children of root.
		 * <p>
		 * This is not very natural for arithmetic expressions, but reasonable
		 * in many other cases. We suppose that the root element corresponds to
		 * an implicit operator (for regular expressions, it would be "sequence"
		 * ; for conditions, it might be "AND" (or "OR"), etc... Here we suppose
		 * an implicit "+".
		 * <p>
		 * Those elements can be removed.
		 * <p>
		 * New elements can also be added at the same level (before or after the
		 * direct child element). *
		 * 
		 * @param node
		 */
		private void topElementsMenu(
				ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> node) {
			if (node.getParent() == treeModel.getRoot()) {
				ArithmeticPlaceHolderNode newNode = new ArithmeticPlaceHolderNode(
						treeModel);

				AddSlibingNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor> addBeforeAction, addAfterAction;
				addBeforeAction= new AddSlibingNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor>(
						LabelRepository.getLabel("Add_new_Element_before"), treeModel.getRoot(), node, newNode, AddSlibingNodeAction.InsertPosition.BEFORE
						);
				addAfterAction= new AddSlibingNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor>(
						LabelRepository.getLabel("Add_new_Element_after"), treeModel.getRoot(), node, newNode, AddSlibingNodeAction.InsertPosition.AFTER
						);

				built.addSeparator();
				built.add(addBeforeAction);
				built.add(addAfterAction);
				RemoveNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor> removeAction = new RemoveNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor>(
						node.getParent(), node);
				built.addSeparator();
				built.add(removeAction);
			}
		}

		/**
		 * @param node
		 */
		private void replaceByOpMenu(
				ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> node) {
			char operators[] = { '+', '-', '*', '/' };
			for (char c : operators) {
				OperationNode newNode = new OperationNode(treeModel, c);
				newNode.addChild(new ArithmeticPlaceHolderNode(treeModel));
				newNode.addChild(new ArithmeticPlaceHolderNode(treeModel));
				String title = "" + c;
				built.add(new ReplaceNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor>(
						title, parent, node, newNode));
			}
		}

		@Override
		/**
		 * Possibilities for operation: replace by a number. 
		 */
		public void visitOperation(OperationNode operation) {
			replaceByNumberMenu(operation);
			topElementsMenu(operation);
		}

		/**
		 * @param operation
		 */
		private void replaceByNumberMenu(
				ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> operation) {
			NumberNode newNode = new NumberNode(treeModel);
			built.add(new ReplaceNodeAction<ArithmeticGrammar, ArithmeticNodeVisitor>(
					"0-9", parent, operation, newNode));
		}

		@Override
		/**
		 * Possibilities : replace by a number or an operation.
		 */
		public void visitPlaceHolder(ArithmeticPlaceHolderNode node) {
			replaceByNumberMenu(node);
			built.addSeparator();
			replaceByOpMenu(node);
			topElementsMenu(node);
		}

		@Override
		/**
		 * Possibilities : add a number or an operation.
		 */
		public void visitRootNode(ArithmeticRootNode node) {
			// Do nothing here ? as there is normally always a visible root in
			// the operation ?		
		}
	}
}
