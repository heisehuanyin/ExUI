package exui.uibuilder;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exui.binding.ExBindingBridge;
import exui.binding.I_Object4BindingCommon;
import exui.uibase.I_Margin;
import exui.uibase.I_Size;
import exui.uibase.WEmptyTransform;
import exui.uibase.WMargin;
import exui.uibase.WSize;
import exui.uiview.ExSplitPane;
import exui.uiview.I_View;
import exui.uiwin.ExWindow;
import exui.uiwin.I_Window;

public class XmlWindowBuilder implements I_WindowBuilder {
	private Document wholeDoc = null;
	private ExWindow window = null;
	private Map<String, Object> resCon = new HashMap<>();

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
			wholeDoc = db.parse(xmlPath);
			Element wnode = wholeDoc.getDocumentElement();

			this.window = (ExWindow) new XmlViewBuilder(wnode).generateViewUnitAs(null);

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
}

class XmlViewBuilder implements I_ViewBuilder<I_Object4BindingCommon> {
	private Element viewNode;

	public XmlViewBuilder(Element elm) {
		this.viewNode = elm;
	}

	@Override
	public I_View generateViewUnitAs(I_View view2Res) {
		String tag = this.viewNode.getNodeName();
		I_View rtn = null;
		switch (tag) {
		case "ExWindow": {
			rtn = this.generateWindow(viewNode);
			view2Res = rtn;
		}
			break;
		case "ExSplitPane": {
			String o = this.viewNode.getAttribute("orientend");
			if (o == null || o.equals("h"))
				rtn = new ExSplitPane(ExSplitPane.HORIZONTAL);
			else
				rtn = new ExSplitPane(ExSplitPane.VERTICAL);
		}
			break;
		case "Resource":
			return null;
		}
		rtn.__view_SetParentView(view2Res);
		///////////////////////////
		// adjust
		String adjust = this.viewNode.getAttribute("adjustAuto");
		if (adjust != null && !adjust.equals("")) {
			boolean width = this.parse4args(adjust, 0).equals("false") ? false : true;
			boolean height = this.parse4args(adjust, 1).equals("false") ? false : true;
			rtn.adjust_SetAutoAdjust(width, height);
		}

		// margin
		String margin = this.viewNode.getAttribute("margin");
		if (margin != null && (!margin.equals(""))) {
			I_Margin mrn = new WMargin(0, 0, 0, 0);
			if (margin.startsWith("@")) {
				mrn = (I_Margin) view2Res.window_getOwner().getResource(margin.replace("@", ""));
			} else {
				int l = Integer.parseInt(this.parse4args(margin, 0));
				int r = Integer.parseInt(this.parse4args(margin, 1));
				int t = Integer.parseInt(this.parse4args(margin, 2));
				int b = Integer.parseInt(this.parse4args(margin, 3));

				mrn = new WMargin(l, r, t, b);
			}
			rtn.margin_SetMargin(mrn);
		}
		// color
		String color = this.viewNode.getAttribute("color");
		if (color != null && !color.equals("")) {
			Color x = null;
			if (color.startsWith("@")) {
				rtn.color_SetColor((Color) view2Res.window_getOwner().getResource(color.replace("@", "")));
			}
		}
		// basicsize
		String bsize = this.viewNode.getAttribute("basicSize");
		if (bsize != null && !bsize.equals("")) {
			I_Size bs = null;
			if (bsize.startsWith("{")) {
				int w = Integer.parseInt(this.parse4args(bsize, 0));
				int h = Integer.parseInt(this.parse4args(bsize, 1));
				bs = new WSize(w, h);
			}
			rtn.size_SetBasicSize(bs);
		}
		// binding
		String _binding = this.viewNode.getAttribute("binding");
		if (!_binding.equals("")) {
			if (_binding.startsWith("@")) {
				if (_binding.equals("@__binding")) {
					I_View one = rtn;
					while(!(one instanceof I_Window)) {
						one = one.__view_GetParentView();
						if(one.get_CorrespondingObject()!=null) {
							I_Object4BindingCommon y = one.get_CorrespondingObject();
							if(y!=null) {
								new ExBindingBridge(ExBindingBridge.POINT_2_POINT).Binding(y, rtn);
							}
						}
					}
				} else {
					I_View x = (I_View) view2Res.window_getOwner().getResource(_binding);
					I_Object4BindingCommon y = x.get_CorrespondingObject();
					if(y!=null) {
						new ExBindingBridge(ExBindingBridge.POINT_2_POINT).Binding(y, rtn);
					}
				}

			} else {
				view2Res.window_getOwner().registerResource(_binding, rtn);
			}
		}

		/////////////////////////
		String binding = this.viewNode.getAttribute("binding");
		NodeList list = this.viewNode.getChildNodes();
		int num = 0;
		for (int i = 0; i < list.getLength(); ++i) {
			if (list.item(i) instanceof Element) {
				++num;

				XmlViewBuilder xb = new XmlViewBuilder((Element) list.item(i));
				rtn.view_setUIBuilder(xb);
				I_View xv = xb.generateViewUnitAs(rtn);

				if (xv != null) {
					rtn.view_InsertViewAtIndex(xv, rtn.view_GetChildCount());
				}
			}
		}

		if (binding != null && (!binding.equals("")) && num > 1) {
			System.out.println("ERROR：视图节点内含子视图数量超过1，如果进行绑定行为，只有最后一个子视图自动增殖！！");
		}

		return rtn;
	}

	private I_Window generateWindow(Element win) {
		ExWindow window = new ExWindow("Null", null);
		Element res = (Element) win.getElementsByTagName("Resource").item(0);
		this.parse4Resource(window, res);

		String title = win.getAttribute("title");
		window.setTitle(title);

		String miniSizeStr = win.getAttribute("miniSize");
		miniSizeStr = miniSizeStr.replace("{", "").replace("}", "");
		String[] S_A = miniSizeStr.split(",");
		window.setMiniSize(new WSize(Integer.parseInt(S_A[0]), Integer.parseInt(S_A[1])));

		String sizeStr = win.getAttribute("size");
		sizeStr = sizeStr.replace("{", "").replace("}", "");
		String[] S2_A = sizeStr.split(",");
		window.setSize(new WSize(Integer.parseInt(S2_A[0]), Integer.parseInt(S2_A[1])));

		return window;
	}

	/**
	 * 将资源集合节点传入，解析并建立资源
	 */
	private void parse4Resource(ExWindow w, Element e) {
		NodeList children = e.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node one = children.item(i);
			String tag = one.getNodeName();
			if (!(one instanceof Element))
				continue;
			String key = ((Element) one).getAttribute("binding");
			switch (tag) {
			case "Color": {
				Element t = (Element) one;
				int r = Integer.parseInt(t.getAttribute("r"));
				int g = Integer.parseInt(t.getAttribute("g"));
				int b = Integer.parseInt(t.getAttribute("b"));
				int a = Integer.parseInt(t.getAttribute("a"));
				w.registerResource(key, new Color(r, g, b, a));
			}
				break;
			case "WEmptyTransform": {
				w.registerResource(key, new WEmptyTransform());
			}
				break;
			}
		}
	}

	/**
	 * 解析组合参数："{xx,xx,xx,xx}"
	 * 
	 * @param str
	 *            字符串属性参数
	 * @param i
	 *            参数序号
	 * @return 参数字符串
	 */
	private String parse4args(String str, int i) {
		str = str.replace("{", "").replace("}", "").replace(" ", "");
		String[] S2_A = str.split(",");
		return S2_A[i];
	}
}