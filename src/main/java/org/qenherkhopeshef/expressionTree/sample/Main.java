package org.qenherkhopeshef.expressionTree.sample;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.qenherkhopeshef.expressionTree.ExpressionNode;
import org.qenherkhopeshef.expressionTree.JExpressionTree;

// We used MigLayout, which is nice, but we don't want to introduce a dependence there just for that reason.
//import net.miginfocom.swing.MigLayout;

/**
 * Demonstration program for the use of the expression tree.
 * <ul>
 * <li>This example shows how to create an expression system
 * <li>How to provide editors for the expressions
 * <li>How to navigate through the edited expression in order to extract the
 * relevant information (usually creating an ADT)
 * </ul>
 * 
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
	private JButton doButton = new JButton("do");
	private JButton clearButton = new JButton("clear");
	private JExpressionTree<ArithmeticGrammar, ArithmeticNodeVisitor> tree;
	private JTextField resultField = new JTextField(20);

	public Main() {
		doButton.addActionListener(EventHandler.create(ActionListener.class,
				this, "compute"));
		clearButton.addActionListener(EventHandler.create(ActionListener.class,
				this, "clear"));
		resultField.setEditable(false);
		tree = new JExpressionTree<ArithmeticGrammar, ArithmeticNodeVisitor>();
		tree.setExpressionGrammar(new ArithmeticGrammar());
		JScrollPane treePane= new JScrollPane(tree);
		treePane.setPreferredSize(new Dimension(320, 500));
		// Layout
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.insets= new Insets(20,2, 2, 2);
		c.fill= GridBagConstraints.BOTH;
		c.weightx=1;
		c.weighty=1;
		c.gridwidth=2;
		add(treePane,c);
		c= new GridBagConstraints();
		c.gridy=1;
		add(doButton,c);
		c.gridx=1;
		add(clearButton,c);
		c.gridy=2;
		c.gridx=0;
		c.gridwidth=2;
		c.fill= GridBagConstraints.HORIZONTAL;
		c.weightx=1;
		c.insets= new Insets(2, 2, 20, 2);
		add(resultField,c);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

	public void compute() {
		try {
			Computer computer = new Computer();
			tree.getModel().getRoot().accept(computer);
			resultField.setText(""+computer.result);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"You must complete the expression.");
		}
	}

	public void clear() {
		tree.clear();
	}

	private static class Computer implements ArithmeticNodeVisitor {

		double result=0;

		@Override
		public void visitNumber(NumberNode node) {
			result= node.getNumber();
		}

		@Override
		public void visitOperation(OperationNode operation) {
			operation.getChild(0).accept(this);
			double val1= result;
			operation.getChild(1).accept(this);
			double val2= result;
			switch (operation.getOperator()) {
			case '+':
				result= val1+val2;				
				break;
			case '*':
				result= val1*val2;				
				break;
			case '-':
				result= val1-val2;				
				break;
			case '/':
				result= val1/val2;				
				break;
			default:
				throw new RuntimeException("Uknown operator");				
			}
		}

		@Override
		public void visitPlaceHolder(ArithmeticPlaceHolderNode node) {
			throw new RuntimeException("You can't compute this expression.");
		}

		@Override
		public void visitRootNode(ArithmeticRootNode node) {
			double total= 0;
			List<ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor>> children = node.getChildren();
			for (ExpressionNode<ArithmeticGrammar, ArithmeticNodeVisitor> child: children) {
				child.accept(this);
				total+= result;
			}
			result= total;
		}

	}
}
