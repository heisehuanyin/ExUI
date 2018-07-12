package ws.exui.uiwin;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;

import ws.exui.ExManager;
import ws.exui.event.I_UIExEvent;
import ws.exui.uibase.BridgeGraphicsPort;
import ws.exui.uibase.I_GraphicsPort;
import ws.exui.uibase.I_Size;
import ws.exui.uibase.WPoint;
import ws.exui.uibase.WSize;
import ws.exui.uiview.I_View;

public class ExWindow extends ExSplitPane implements I_Window {
	private Frame window = new CustomWindow(this);
	private I_Size basicOutline = new WSize(400,300);
	private ExManager ctl = null;
	private I_GraphicsPort gport;
	
	public ExWindow(String string, I_Size miniSize, ExManager ctl) {
		super(ExSplitPane.VERTICAL);
		this.ctl = ctl;
		if(miniSize != null)
			this.basicOutline = miniSize;
		
		
		this.window.setTitle(string);
		this.window.setSize((int)basicOutline.getWidth(), (int)basicOutline.getHeight());
		this.window.pack();
		this.gport = new BridgeGraphicsPort(this, window);
		
		I_Size vSize = new WSize(basicOutline.getWidth() -window.getInsets().left -window.getInsets().right,
				basicOutline.getHeight() -window.getInsets().top -window.getInsets().bottom);
		this.size_SetBasicSize(vSize);
		this.point_SetOriginPoint(new WPoint(window.getInsets().left, window.getInsets().top));
	}
	@Override
	public void setTitle(String title) {
		this.window.setTitle(title);
	}
	
	@Override
	public void setSize(I_Size size) {
		if(size.getWidth() > basicOutline.getWidth() &&
				size.getHeight() > basicOutline.getHeight()) {
			this.window.setSize((int)size.getWidth(), (int)size.getHeight());
			
			
			I_Size vSize = new WSize(window.getWidth() -window.getInsets().left -window.getInsets().right,
					window.getHeight() -window.getInsets().top -window.getInsets().bottom);
			this.size_SetVisibleSize(vSize);
		}
	}
	
	@Override
	public void __display() {
		this.window.addWindowListener((CustomWindow)this.window);
		this.window.setVisible(true);
	}
	//WView============================================================

	@Override
	public void __paintItSelf(I_GraphicsPort port) {
		Rectangle2D.Double shape = new Rectangle2D.Double(0, 0,
				this.size_GetVisibleSize().getWidth(), this.size_GetVisibleSize().getHeight());
		port.fillShape(shape, null);
	}
	/**
	 * 派送UI事件*/
	@Override
	public void event_DispatchUIEvent(I_UIExEvent e) {
		ctl.event_AcceptEvent(e);
	}
	@Override
	public I_GraphicsPort gport_getGraphicsPort() {
		return this.gport;
	}

}









































































//隔绝与Frame的联系，防止Swing污染代码
//////////////////////////===================================================================

class CustomWindow extends Frame implements WindowListener, ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExWindow f = null;
	//private Image offScreenImg;
	
	public CustomWindow(ExWindow f) {
		this.f = f;
		this.addComponentListener(this);
	}
	
	@Override
	public void paint(Graphics a) {
		Graphics2D g = (Graphics2D) a;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.paintOneByOne(f);
	}
	
	private void paintOneByOne(I_View v) {
		I_GraphicsPort g = v.gport_getGraphicsPort();
		v.__paintItSelf(g);
		
		for(int i=0;i<v.view_GetChildCount();++i) {
			I_View x = v.view_GetChildAtIndex(i);
			paintOneByOne(x);
		}
	}
	
	
	
	
	

	@Override
	public void componentResized(ComponentEvent e) {
		I_Size vSize = new WSize(this.getWidth() -this.getInsets().left -this.getInsets().right,
				this.getHeight() - this.getInsets().top - this.getInsets().bottom + 6);
		if(vSize.getWidth() < this.f.size_GetBasicSize().getWidth()) {
		}
		if(vSize.getHeight() < this.f.size_GetBasicSize().getHeight()) {}
		f.size_SetVisibleSize(vSize);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
