package org.qenherkhopeshef.expressionTree;

/**
 * Easy access to international names for the elements of the search interface.
 * TODO : unify the naming of those helper classes.
 * @author Serge Rosmorduc (serge.rosmorduc@qenherkhopeshef.org)
 *
 */
public class LabelRepository {

	public static String getLabel(String code) {
		return java.util.ResourceBundle.getBundle(
				"org.qenherkhopeshef.expressionTree.labels").getString(code);
	}
}
