package org.qenherkhopeshef.expressionTree.sample;


import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import org.qenherkhopeshef.expressionTree.AbstractNodeEditorInterface;

public class NumberNodeEditor extends AbstractNodeEditorInterface<NumberNode, ArithmeticGrammar, ArithmeticNodeVisitor>{
	
	JFormattedTextField textField= new JFormattedTextField(NumberFormat.getInstance());
	
	public NumberNodeEditor(NumberNode node) {
		super(node);
		textField.setColumns(10);
		textField.setValue(node.getNumber());
		textField.addActionListener(EventHandler.create(ActionListener.class, this, "stopEditing"));
	}

	@Override
	public Component getAsComponent() {
		return textField;
	}

	@Override
	protected void processEditionResult() {
		Number value= (Number) textField.getValue();
		node.setNumber(value.doubleValue());
	}
	

}
