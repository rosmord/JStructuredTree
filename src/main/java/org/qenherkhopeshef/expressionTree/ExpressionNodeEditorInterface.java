package org.qenherkhopeshef.expressionTree;

import java.awt.Component;

import javax.swing.tree.TreeCellEditor;

/**
 * Interface for a the editor for a specific kind of node.
 * <p> When the editor object is created, it will need to know its node (so, the constructor should probably pass the node).
 * <p> It's the responsability of this object to call stopEditing and to update the node when edition is finished.
 * <p> Normally, you should use the class {@link AbstractNodeEditorInterface} as base class for those objects.
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 */
public interface ExpressionNodeEditorInterface {
	/**
	 * Starts the edition.
	 * @param treeCellEditor
	 */
	void startNodeEdition(TreeCellEditor treeCellEditor);
	
	 Component getAsComponent();
}
