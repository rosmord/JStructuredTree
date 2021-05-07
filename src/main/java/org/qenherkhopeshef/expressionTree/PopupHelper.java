package org.qenherkhopeshef.expressionTree;


import javax.swing.JPopupMenu;

import org.qenherkhopeshef.expressionTree.actions.AddSlibingNodeAction;
import org.qenherkhopeshef.expressionTree.actions.RemoveNodeAction;

/**
 * Helper class for building popup menus.
 * 
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 * 
 */
public class PopupHelper<G extends ExpressionGrammar<G, V>, V> {

	JPopupMenu menu;
	private G grammar;
	private ExpressionTreeModel<G, V> treeModel;

	public PopupHelper(G grammar, ExpressionTreeModel<G, V> treeModel,
			JPopupMenu menu) {
		super();
		this.grammar = grammar;
		this.menu = menu;
		this.treeModel = treeModel;
	}

	/**
	 * Adds "add after" and "add before" entries.
	 * <p>
	 * These entries are added only at the top level.
	 * 
	 * @param reference
	 *            the current element.
	 * @param newElement
	 *            a new element which would be added.
	 */
	public void addBeforeAndAfter(ExpressionNode<G, V> reference,
			ExpressionNode<G, V> newElement) {
		CompositeNode<G, V> parent = reference.getParent();
		menu.add(new AddSlibingNodeAction<G, V>(LabelRepository
				.getLabel("Add_new_Element_after"), parent, reference,
				newElement, AddSlibingNodeAction.InsertPosition.AFTER));
		menu.add(new AddSlibingNodeAction<G, V>(LabelRepository
				.getLabel("Add_new_Element_before"), parent, reference,
				newElement, AddSlibingNodeAction.InsertPosition.BEFORE));
	}

	public void addPlaceHoldersBeforeAndAfter(ExpressionNode<G, V> reference) {
		ExpressionNode<G, V> newNode = grammar.createPlaceHolderNode(treeModel);
		addBeforeAndAfter(reference, newNode);
	}

	public void addRemoveNode(ExpressionNode<G, V> node) {
		RemoveNodeAction<G, V> removeAction = new RemoveNodeAction<G, V>(
				node.getParent(), node);
		menu.add(removeAction);
	}
}
