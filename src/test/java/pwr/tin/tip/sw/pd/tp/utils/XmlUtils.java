package pwr.tin.tip.sw.pd.tp.utils;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class XmlUtils {

	public static String changeValue(String messageBody, String value, String xpathExpression) {
		try {
			Document doc = DocumentHelper.parseText(messageBody);
			Node node = doc.selectSingleNode(xpathExpression);
			if (node != null) {
				node.setText(value);
			}
			return doc.asXML();
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String changeValues(String messageBody, String value, String xpathExpression) {
		try {
			Document doc = DocumentHelper.parseText(messageBody);
			List<Node> listOfNodes = doc.selectNodes(xpathExpression);
			if (listOfNodes != null) {
				for (Node node : listOfNodes) {
					node.setText(value);
				}
			}
			return doc.asXML();
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(String messageBody, String xpathExpression) {
		try {
			Node singleNode = DocumentHelper.parseText(messageBody).selectSingleNode(xpathExpression);
			if (singleNode != null) {
				return singleNode.getStringValue();
			}
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
