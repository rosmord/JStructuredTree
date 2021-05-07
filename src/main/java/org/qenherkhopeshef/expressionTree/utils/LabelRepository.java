package org.qenherkhopeshef.expressionTree.utils;

public class LabelRepository {

	public static String getLabel(String code) {
		return java.util.ResourceBundle.getBundle(
				"org.qenherkhopeshef.expressionTree.labels").getString(code);
	}
}
