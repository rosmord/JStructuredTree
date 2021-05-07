package org.qenherkhopeshef.expressionTree;


import java.awt.Component;

import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Simple renderer for tree cells.
 * TODO consider making this class non public.
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 */
@SuppressWarnings("serial")
public class ExpressionRenderer extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (value instanceof ExpressionNode<?,?>) {
			ExpressionNode<?,?> node = (ExpressionNode<?,?>) value;
			setText(node.getText());
			setIcon(node.getIcon());
		}
		//setBorder(new LineBorder(Color.BLUE));
		setBorder(new EmptyBorder(3,3,3,3));
		return this;
	}

	
}
