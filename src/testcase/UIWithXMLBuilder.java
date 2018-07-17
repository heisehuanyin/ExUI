package testcase;

import exui.ExManager;
import exui.uibuilder.XmlWindowBuilder;

public class UIWithXMLBuilder {
	public static void main(String[] args) {
		ExManager vmr = new ExManager();
		
		XmlWindowBuilder x = new XmlWindowBuilder();
		x.loadFile("./Description.xml");

		vmr.openWindow(x.getWindow());
		new Thread(vmr);
	}

}
