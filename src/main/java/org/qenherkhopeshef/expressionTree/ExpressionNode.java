package org.qenherkhopeshef.expressionTree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPopupMenu;

/**
 * Base class for representing expression tree nodes.
 * 
 * <p> Nodes belong to and respect a given {@link ExpressionGrammar}. See this interface documentation 
 * for an overview of the system. 
 * <p> Basically, nodes belong to a tree whose structure (types of nodes, root...) 
 * is determined by the expression grammar.
 * @see ExpressionGrammar
 * @author rosmord
 */
public abstract class ExpressionNode<G extends ExpressionGrammar<G,V>, V> {
	
	protected ExpressionTreeModel<G,V> model= null;
	protected CompositeNode<G,V> parent= null;
	
	// Not always used.
	protected ArrayList<ExpressionNode<G, V>> children= new ArrayList<ExpressionNode<G, V>>();
	
	public ExpressionNode(ExpressionTreeModel<G,V> model) {
		super();
		this.model = model;
	}
	
	public String getText() {
		return "";
	}

	public Icon getIcon() {
		return null;
	}

	public boolean isEditable() {
		return false;
	}

	/**
	 * Build an editor suitable for this object, and initialize it.
	 * (in case of re-use, try caching the objects).
	 * @return
	 */
	public ExpressionNodeEditorInterface buildEditor() {
		return null;
	}
	
	
	public ExpressionNode<G,V> getChild(int i) {
		return children.get(i);
	}
	
	public int getNumberOfChildren() {
		return children.size();		
	}

	public int indexOfChild(ExpressionNode<G,V> child) {
		return children.indexOf(child);
	}

	public boolean isLeaf() {
		return true;
	}

	/**
	 * Build a popup menu corresponding to this node.
	 * @return
	 */
	public JPopupMenu getPopup() {
		if (parent != null) {
			return model.buildPopup(parent, this);
		} else
			return null;
	}

	public List<ExpressionNode<G,V>> getChildren() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * Return the model which owns this node.
	 * @return
	 */
	public ExpressionTreeModel<G,V> getModel() {
		return model;
	}
	
	public CompositeNode<G,V> getParent() {
		return parent;
	}

	void removeChild(ExpressionNode<G,V> element) {
		children.remove(element);
		// model.fireChange(); Called by model ?
	}
	
	public abstract void accept(V nodeVisitor);
}
