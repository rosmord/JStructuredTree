package org.qenherkhopeshef.expressionTree.sample;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.JComboBox;

import org.qenherkhopeshef.expressionTree.AbstractNodeEditorInterface;


public class OperationNodeEditor extends
		AbstractNodeEditorInterface<OperationNode, ArithmeticGrammar, ArithmeticNodeVisitor> {

	private JComboBox comboBox = new JComboBox();

	public OperationNodeEditor(OperationNode node) {
		super(node);
		Character[] chars = { Character.valueOf('+'), Character.valueOf('-'),
				Character.valueOf('*'), Character.valueOf('/') };
		comboBox = new JComboBox(chars);
		Character op = node.getOperator();
		comboBox.setSelectedItem(op);
		comboBox.addActionListener(EventHandler.create(ActionListener.class,
				this, "stopEditing"));
	}
	
	@Override
	protected void doCustomInit() {
	}

	@Override
	protected void processEditionResult() {
		Character sel= (Character) comboBox.getSelectedItem();
		node.setOperator(sel);
	}
	
	@Override
	public Component getAsComponent() {
		return comboBox;
	}

}
