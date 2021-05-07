package org.qenherkhopeshef.expressionTree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * A tree for editing searches.
 * 
 * @author rosmord
 * 
 */
@SuppressWarnings("serial")
public class JExpressionTree<G extends ExpressionGrammar<G,V>,V> extends JTree {

	// private ExpressionRenderer expressionRenderer;

	/**
	 * Create an incomplete tree, with an empty model.
	 * To complete it, a call to {@link #setExpressionGrammar(ExpressionGrammar) is needed.
	 */
	public JExpressionTree() {
		this(null);
	}
	
	public JExpressionTree(G grammar) {
		if (grammar == null)
			setModel(new EmptyModel<G,V>());
		else
			setExpressionGrammar(grammar);

		// The renderer, which asks each node to render itself.
		setCellRenderer(new ExpressionRenderer());

		// The editor, which does also ask each node to send its default
		// renderer.
		setCellEditor(new ExpressionTreeEditor<G,V>());

		setRootVisible(true);
		setRowHeight(0); // Not fixed = 0

		// doesn't work with the mac Look and Feel
		putClientProperty("JTree.lineStyle", "Angled");
		setEditable(true);
		addMouseListener(new PopupListener());
	}

	
	@Override
	public boolean isPathEditable(TreePath path) {
		if (path.getLastPathComponent() instanceof ExpressionNode<?,?>) {
			ExpressionNode<?,?> node = (ExpressionNode<?,?>) path
					.getLastPathComponent();
			return node.isEditable();
		} else {
			return false;
		}
	}

	private class PopupListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				TreePath path = getPathForLocation(e.getX(), e.getY());
				expandPath(path);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			processPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			processPopup(e);
		}

		/**
		 * @param e
		 */
		private void processPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				// Find the node
				int x = e.getX();
				int y = e.getY();
				// Ask for its popup
				TreePath path = getPathForLocation(x, y);
				if (path == null)
					return;
				ExpressionNode<?,?> node = (ExpressionNode<?,?>) path
						.getLastPathComponent();
				if (node.getPopup() != null) {
					node.getPopup().show(JExpressionTree.this, x, y);
					expandAll();

				}
			}
		}
	}

	// Expansion of the whole path

	public void expandAll() {
		for (int i = 0; i < getRowCount(); i++) {
			expandRow(i);
		}
	}

	@Override
	// Create a specific tailored model listener.
	// The main goal is to keep the tree expanded at all time
	protected TreeModelListener createTreeModelListener() {
		return new ExpressionTreeModelListener();
	}

	// Handler for tree events.
	// Same as the one for regular trees, except that the tree is always
	// expanded.
	protected class ExpressionTreeModelListener extends JTree.TreeModelHandler {
		@Override
		public void treeStructureChanged(TreeModelEvent e) {
			// Update the tree as usual.
			super.treeStructureChanged(e);
			// The whole structure should be displayed.
			// A "cleaner" solution would be to send more precise messages from
			// the model,
			// and only update what needs to be updated.
			//
			// Note also that we use invokelater because directly calling
			// expandAll
			// interfers with the usual update process, and has no result.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					expandAll();
				}
			});
		}
	}

	public void setExpressionGrammar(G grammar) {
		setModel(new ExpressionTreeModelImpl<G,V>(grammar));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ExpressionTreeModel<G,V> getModel() {
		return (ExpressionTreeModel<G,V>) super.getModel();
	}

	public void clear() {
		getModel().clear();
	}
}
