package org.qenherkhopeshef.expressionTree;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTree;
import javax.swing.tree.TreeCellEditor;

/**
 * The editor for the search tree delegate its work to specialized editors.
 * It uses the nodes as editor factories, and then keeps the correct editor object in memory.
 * all specialized initialisation stuff is done through the AbstractNodeEditorInterface
 * @author rosmord
 */
@SuppressWarnings("serial")
public final class ExpressionTreeEditor<G extends ExpressionGrammar<G,V>,V> extends AbstractCellEditor implements TreeCellEditor {
	
	public ExpressionTreeEditor() {
		// DOES NOTHING SPECIAL
	}

	public Object getCellEditorValue() {
		// IT SHOULD NOT WORK ????
		// WELL, IT DOES BECAUSE THE EDITORS FIX THEIR VALUES...
		return null;
	}

	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		if (value instanceof ExpressionNode<?,?>) {
			// Build the editor if it doesn't exist yet
			@SuppressWarnings("unchecked")
			ExpressionNode<G,V> node = (ExpressionNode<G,V>) value;
			ExpressionNodeEditorInterface result = node.buildEditor();
			result.startNodeEdition(this);
			// Return the editor
			return result.getAsComponent();
		} else
			throw new RuntimeException(java.util.ResourceBundle.getBundle("grammaticalBase/search/searchLabels").getString("Unknown_Type_") + value.getClass());
	}	
	
	@Override
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent) {
			MouseEvent mouseEvent= (MouseEvent) e;
			if (mouseEvent.isPopupTrigger())
				return false;
		}
		return super.isCellEditable(e);
	}
}
