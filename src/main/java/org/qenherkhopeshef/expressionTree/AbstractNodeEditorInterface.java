package org.qenherkhopeshef.expressionTree;

import java.awt.Component;

import javax.swing.tree.TreeCellEditor;

/**
 * A base class for node editors.
 * 
 * For each type of node, an editor will be created and reused for each edition.
 * The idea is that the graphical component will be reused too.
 * 
 * You should install some system on the component which will call stopEditing()
 * when edition is finished ; for instance, it can be an actionListener.
 * 
 * @author rosmord
 * 
 */
public abstract class AbstractNodeEditorInterface<ND extends ExpressionNode<G, V>, G extends ExpressionGrammar<G, V>, V> implements ExpressionNodeEditorInterface{
	/**
	 * The node being currently edited.
	 */
	protected ND node;

	/**
	 * The Tree cell editor in use.
	 */

	protected TreeCellEditor editor;

	
	public AbstractNodeEditorInterface(ND node) {
		super();
		this.node = node;
	}

	/**
	 * Returns a component view of this specialized editor.
	 * <p>
	 * Precondition: this method is called after startNodeEdition. You can
	 * assume that node and editor are defined.
	 * 
	 * @return
	 */
	public abstract Component getAsComponent();

	/**
	 * Custom initializations, after setting editor and node.
	 * 
	 * @param node
	 */
	protected void doCustomInit() {}

	/**
	 * Called when a node edition is finished.
	 */
	public void stopEditing() {
		editor.stopCellEditing();
		processEditionResult();
		editor = null;
		node.getModel().fireNodeChange(node);
		node = null;
	}

	/**
	 * Method called by stopEditing when edition has stopped. At this stage,
	 * node and editor are still available, and you should probably update the
	 * value of the node using the content of your editing object.
	 */
	protected void processEditionResult() {
	}

	public final void startNodeEdition(TreeCellEditor editor) {
		this.editor = editor;
		doCustomInit();
	}

}
