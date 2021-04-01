import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class makegrid extends Applet implements ActionListener{

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
		zoomX = zoomY = 8;
		padX = padY = 15;

        zoomin = new Button("zoom in (+)");
		zoomout = new Button("zoom out (-)");

		add(zoomin);
		add(zoomout);
		zoomin.addActionListener(this);
		zoomout.addActionListener(this);
		//addKeyListener(this);
    }

	public void paint(Graphics g){
		
		setBackground(Color.white);
		grid(g);
		
		Font stringFont= new Font("Inter",1,22);
		g.setFont(stringFont);
		g.setColor(Color.red);
		g.drawString("Red- DDA",75,100);
		g.setColor(Color.blue);
		g.drawString("Blue- Bresenham",75,130);
		g.setColor(Color.black);
		g.drawString("Black- Midpoint",75,160);
		dda(g, -6, -9, 7, 9);
		dda(g, -20, 17, -8, 0);
		bresenham(g, -22, -2, -8, 8);
		bresenham(g, 5, 0, 20, 12);
		dda(g, 15, 0, 30, 12);
		midPoint(g, 30, 0, 45, 12);
		midPoint(g, 0, 0, 8, 5);
		midPoint(g, -22, -10, -8, 0);
		dda(g, 15, 0, 25, -18);
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

	void midPoint(Graphics g, int x1, int y1, int x2, int y2) 
	{ 
  		int dx=x2-x1; 
    	int dy=y2-y1; 
  
    	int d=dy-(dx/2); 
    	int x=x1, y=y1; 
		Color c1 = new Color(0,0,0);
		point(g, x, y, c1);
    	while (x<x2) 
    	{ 
        	x++; 
        	if (d<0) 
            	d=d+dy; 
        	else
        	{ 
            	d+=(dy-dx); 
            	y++;
        	}
			point(g, x, y, c1);
    	} 
	} 

	void bresenham(Graphics g, int x1, int y1, int x2, int y2)
    {
    	int x, y;
    	int dx,dy,p;
    	dx=(x2-x1);
		dy=(y2-y1);
		int m=(dy/dx);
		x=x1;
		y=y1;
		p=2*dy-dx;
		Color c1 = new Color(0,0,255);
		point(g, x, y, c1);
		{
			for(int i=0; i<dx;i++)
	    	{
				x++;
	  		  	if(p<0)
	  		  	{
					point(g, x, y, c1);
	  		  		p=p+(2*dy);
	  		  	}
	  		  	else
	  		  	{
	  		  		point(g, x, y, c1);
					p=p+(2*dy-2*dx);
					y++;
				}
			}
		}
	}

	void dda(Graphics g, float x1, float y1, float x2, float y2)
	{
	    float dx, dy;
	    float step;
		float xinc, yinc;
		int x, y;

	    dx=(float)(x2-x1);
	    dy=(float)(y2-y1);
		if(Math.abs(dx)>Math.abs(dy)){
			step=Math.abs(dx);
		}
	    else{
			step=Math.abs(dy);
		}

	    xinc=dx/step;
		yinc=dy/step;
		Color c1 = new Color(255,0,0);
	    for(int i=0; i<=step; i++)
	    {
			x=(int)x1;
			y=(int)y1;
			point(g, x, y, (c1));
	        x1+=xinc;
	        y1+=yinc;
	    }
	}

	void grid(Graphics g){
		int h, w;

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
		
		g.setColor(Color.black);
    	g.drawLine(0, originY, lenX, originY);
    	g.drawLine(originX, 0, originX, lenY);
    }
	
	void point(Graphics g, int x, int y, Color c1) {
		x = windowX(x);
		y = windowY(y);
		int h,w;
		h = heightT(1);
		w = widthT(1);
		g.setColor(c1);
		g.fillRect(x, y, w, h);
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
