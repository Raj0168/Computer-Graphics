import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class grid extends Applet implements ActionListener{

	int lenX, lenY;
	int zoomX, zoomY;
    int padX, padY;
    int originX, originY;
	
    Button zoomin;
    Button zoomout;

    public void init()
    {
        this.setSize(new Dimension(1366, 768));
        lenY = getHeight();
        lenX = getWidth();
        originX = getWidth()/2;
        originY = getHeight()/2;
        zoomX = zoomY = 5;
        padX = padY = 15;

        zoomin = new Button("zoom in (+)");
		zoomout = new Button("zoom out (-)");

		add(zoomin);
		add(zoomout);
		zoomin.addActionListener(this);
		zoomout.addActionListener(this);
		//addKeyListener(this);
    }
/*
	public void keyPressed( KeyEvent e2) { }
	public void keyReleased( KeyEvent e2) { }
	public void keypressed(keyEvent e2)
	{}
	public void actionPerformed(ActionEvent e2)
	{
		char c=e2.getKeyChar();
		if(c!=keyEvent.CHAR_UNDEFINED)
		{
			s+=c;
			repaint();
			e2.consume();
		}
	}

   public void paint( Graphics g ) {
      g.setColor( Color.gray );
      g.drawLine( originX, originY, originX, originY-10 );
      g.drawLine( originX, originY, originX+10, originY);
      g.setColor( Color.blue );
      g.drawString( s, originX, originY );
   }
*/
	public void paint(Graphics g){
		
		setBackground(Color.white);
		grid(g);
        g.setColor(Color.black);
        point(g, 4, 6);
		point(g, -5, 6);
		point(g, -4, -5);
		point(g, 3, -5);

        g.setColor(Color.magenta);
        drawLine(g, -3, -6, 6, 0);
		
		rectangle(g, -1, -1, 2, 2, Color.red);
		rectangle(g, -8, -13, 15, 2, Color.blue);

    }

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == zoomin) {
			if(padX <= originX){
			padX+= zoomX;
			}
			if(padY <= originY){
			padY+= zoomY;
			}
			repaint();
		}
		else if(e.getSource() == zoomout) {
			if(padX > zoomX) {
				padX-= zoomX;
			}
			if(padY > zoomY) {
				padY-= zoomY;
			}
			repaint();
		}
	}
	
	void grid(Graphics g){
		int h, w;

		Color axescolor=new Color(90,58,255);
		g.setColor(axescolor);
    	g.drawLine(0, originY, lenX, originY);
    	g.drawLine(originX, 0, originX, lenY);

		g.setColor(Color.green);
				
		for(h=originY; h>=0; h-=padY) {
			g.drawLine(0, h, lenX, h);
		}
				
		for(h=originY; h<=lenY; h+=padY) {
			g.drawLine(0, h, lenX, h);
		}
				
		for(w=originX; w>=0; w-=padX){
			g.drawLine(w, 0, w, lenY);
		}
		
		for(w=originX; w<=lenX; w+=padX){
			g.drawLine(w, 0, w, lenY);
		}
    }
	
	void point(Graphics g, int x, int y) {
		x = windowX(x);
		y = windowY(y);
		int h,w;
		h = heightT(1);
		w = widthT(1);
		g.fillRect(x, y, w, h);
	}

    void drawLine(Graphics g, int x, int y, int len, int orien) {
		if(orien==0) {
			for(int i=0; i<len; i++) {
				point(g,x+i,y);
			}
		}
		else{
			for(int i=0; i<len; i++) {
				point(g,x,y+i);
			}
		}
    }

    void rectangle(Graphics g, int x, int y, int wd, int ht, Color c) {
		g.setColor(c);
		for(int i=x; i<=x+wd; i++) {
			for(int j=y; j<=y+ht; j++)
				point(g,i,j);
		}
    }
    
    int windowX(int x){
        return originX + (x*padX) - (padX/4);
    }
    int windowY(int y){
        return originY - y*padY - (padY/4);
    }
    int heightT(int h){
        int y = padY/2;
        return h*y;
    }
    int widthT(int w){
        int x = padX/2;
        return w*x;
	}
} 
