package exui.uibuilder;

import java.awt.Color;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exui.binding.I_Object4BindingCommon;
import exui.uibase.WEmptyTransform;
import exui.uiview.I_View;
import exui.uiwin.ExWindow;
import exui.uiwin.I_Window;

public class XmlWindowBuilder implements I_WindowBuilder {
	private XmlViewBuilder btool = new XmlViewBuilder();
	private ExWindow window = null;
	private Document wholeDoc = null;

	public XmlWindowBuilder() {
	}

	public XmlWindowBuilder(String xmlPath) {
		super();
		loadFile(xmlPath);
	}

	public XmlWindowBuilder loadFile(String xmlPath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 创建DocumentBuilder对象
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			// 通过documentBuilder对象 的parser方法加载books。xml文件到当前项目下
			wholeDoc = db.parse("books.xml");
			Element doc = wholeDoc.getDocumentElement();
			Node resource = doc.getElementsByTagName("Resource").item(0);
			this.parse4Resource((Element) resource);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// =========================================
		return this;
	}

	@Override
	public I_Window getWindow() {
		return this.window;
	}

	/**
	 * 将资源集合节点传入，解析并建立资源
	 */
	public void parse4Resource(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node one = children.item(i);
			String tag = one.getNodeName();
			String key = ((Element) one).getAttribute("id");
			switch (tag) {
			case "Color": {
				Element t = (Element) one;
				int r = Integer.parseInt(t.getAttribute("r"));
				int g = Integer.parseInt(t.getAttribute("g"));
				int b = Integer.parseInt(t.getAttribute("b"));
				int a = Integer.parseInt(t.getAttribute("a"));
				this.window.registerResource(key, new Color(r, g, b, a));
			}
				break;
			case "WEmptyTransform": {
				this.window.registerResource(key, new WEmptyTransform());
			}
				break;
			}
		}
	}
}

class XmlViewBuilder implements I_ViewBuilder<I_Object4BindingCommon> {
	private Element window;
	
	private I_View initViewShape(Element elm) {
		I_View x = null ;
		this.window = elm;
		String tag = elm.getNodeName();
		String key = null;
		return x;
	}

	@Override
	public I_View generateViewUnitAs(I_Object4BindingCommon d_unit) {
		// TODO Auto-generated method stub
		return null;
	}

}