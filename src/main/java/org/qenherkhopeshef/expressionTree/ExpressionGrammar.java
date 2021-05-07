package org.qenherkhopeshef.expressionTree;

import javax.swing.JPopupMenu;

/**
 * A particular expression grammar (searches, filters, etc...).
 * <p> An expression grammar defines the structure an {@link ExpressionTreeModel} can have.
 * Those are GUI elements, which will typically be translated afterwards to model elements. 
 * We use this class as a tool to build ui for searches expressions.
 * 
 * <p>
 * As all elements manipulated by the grammar are parameterized by it, the
 * implementations of expression grammar will have themselves as parameters (G).
 * <p>
 * The second argument of the generic interface is a Visitor class, which implicitly 
 * defines the list of all node types which can occur in the ExpressionGrammar.
 * <p> The {@link #createRootNode(org.qenherkhopeshef.expressionTree.ExpressionTreeModel) } 
 * method will create a specific root node for a given ExpressionTreeModel.
 * <p> The other methods are utility methods, which can be used in the user interface.
 * 
 * <p> Example definition: 
 * <pre>
 * public class CorpusFilterGrammar implements
		ExpressionGrammar&lt;CorpusFilterGrammar, CorpusNodeVisitor> {...}
 * </pre>
 * <p>
 * A grammar can be a singleton, as all its elements are sharable. Now, it's
 * probably not very interesting to do so.
 * 
 * <p>
 *   
 * </p>
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 * @param <G> the implementation class itself (as is done when implementing Comparable, for instance).
 * @param <V> the visitor interface for the node class.
 * 
 */
public interface ExpressionGrammar<G extends ExpressionGrammar<G, V>, V> {
	
	/**
	 * Build the menu used to add and change items.
     * @param treeModel the tree model we work on.
	 * @param parent the parent of the item the menu applies to (important for replacements)
	 * @param child the item we want to create a popup menu for.
	 * @return
	 */
	public JPopupMenu buildPopup(ExpressionTreeModel<G,V> treeModel, CompositeNode<G, V> parent,
			ExpressionNode<G, V> child);

	/**
	 * Create the root node for the model.
	 * <p> This will usually be a subclass of {@link AbstractRootNode} 
	 * @param treeModel
	 * @return the root node for the model.
	 */
	CompositeNode<G, V> createRootNode(ExpressionTreeModel<G, V> treeModel);

	/**
	 * Create a "place holder" node for the model.
	 * <p> When editing the model, place holders are inserted when an element is needed in the tree, 
	 * but the type of the element is not known in advance.
	 * <p> Usually, the object returned will be from a class which extends {@link AbstractPlaceHolderNode}
	 * @param treeModel
	 * @return
	 */
	ExpressionNode<G, V> createPlaceHolderNode(
			ExpressionTreeModel<G, V> treeModel);
}
