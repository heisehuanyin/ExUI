package ws.exui.uiwin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import ws.exui.ExManager;
import ws.exui.event.I_UIExEvent;
import ws.exui.uibase.I_GraphicsPort;
import ws.exui.uibase.I_Size;
import ws.exui.uibase.WPoint;
import ws.exui.uibase.WSize;
import ws.exui.uiview.I_View;

public class ExWindow extends ExSplitPane implements I_Window {
	private JFrame window = new JFrame();
	private I_Size basicOutline = new WSize(400,300);
	private ExManager ctl = null;
	
	public ExWindow(String string, I_Size miniSize, ExManager ctl) {
		super(ExSplitPane.VERTICAL);
		this.ctl = ctl;
		if(miniSize != null)
			this.basicOutline = miniSize;
		
		
		this.window.setTitle(string);
		this.window.setSize((int)basicOutline.getWidth(), (int)basicOutline.getHeight());
		this.window.pack();
		
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
			
			
			I_Size vSize = new WSize(basicOutline.getWidth() -window.getInsets().left -window.getInsets().right,
					basicOutline.getHeight() -window.getInsets().top -window.getInsets().bottom);
			this.size_SetVisibleSize(vSize);
		}
	}
	
	@Override
	public void __display() {
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.setVisible(true);
	}
	//WView============================================================

	@Override
	public void __paintItSelf(I_GraphicsPort port) {
		Rectangle shape = new Rectangle((int)this.point_GetOriginPoint().getX(), (int)this.point_GetOriginPoint().getY(),
				(int)this.size_GetVisibleSize().getWidth(), (int)this.size_GetVisibleSize().getHeight());
		port.fillShape(shape, null);
	}
	/**
	 * 派送UI事件*/
	@Override
	public void event_DispatchUIEvent(I_UIExEvent e) {
		ctl.event_AcceptEvent(e);
	}

}

class CustomWindow extends JFrame implements WindowListener, ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExWindow f = null;
	//private Image offScreenImg;
	
	public CustomWindow(ExWindow f) {
		this.f = f;
	}
	
	/*@Override
	public void update(Graphics a) {
		
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		
		if(this.offScreenImg == null) {
			this.offScreenImg = this.createImage((int)screensize.getWidth(), (int)screensize.getHeight());
		}
		
		Graphics gImage = this.offScreenImg.getGraphics();
		
		gImage.clearRect(0, 0, (int)screensize.getWidth(), (int)screensize.getHeight());
		
		this.paint(gImage);
		a.drawImage(offScreenImg, 0, 0, null);
	}
	*/
	
	@Override
	public void paint(Graphics a) {
		this.paintOneByOne(f, (Graphics2D)a);
	}
	
	private void paintOneByOne(I_View v, I_GraphicsPort g) {
		v.__paintItSelf(g);
		
		for(int i=0;i<v.getViewCount();++i) {
			View x = v.getViewAtIndex(i);
			paintOneByOne(x, g);
		}
	}
	
	
	
	
	

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
