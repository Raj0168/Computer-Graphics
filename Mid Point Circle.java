import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class drawcircle extends Applet implements ActionListener{

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
    
    public void paint(Graphics g){
        
        setBackground(Color.white);
        grid(g);
        
        circle base = new circle(0,0,35);
        base.drawoutline(g);
        axes(g);
    }
    
    class circle
    {
    	public int x1,y1,r1;
    	
    	circle(int xc, int yc, int r)
    	{
    		x1 = xc;
    		y1 = yc;
    		r1 = r;
    	}
    	
    	public void drawoutline(Graphics g)
    	{
    		int x, y, p;
    		x=0;
    		y=r1;
    		
    		p=(5/4)-r1;
    		
            for (int j=y1;j<y1+r1;j++)
            {
                for (int i=x1;i<x1+r1;i++)
                {
                    int temp=i*i+j*j-r1*r1;
                    if (temp<0)
                    {
                        point(g,x1+i,y1+j,Color.cyan);
                        point(g,x1+i,y1-j,Color.cyan);
                        point(g,x1-i,y1+j,Color.cyan);
                        point(g,x1-i,y1-j,Color.cyan);
                    }
                }
            }

    		for(x=0; x<=y; x++)
    		{
    		    point(g, x1+x, y1-y,Color.blue);
    		    
    		    if(p<0)
    		    {
    		        y=y;
    		        p=(p+2*x+1);
    		    }
    		    else
    		    {
    		        y=y-1;
    		        p=(p+2*x+1-2*y);
    		    }
    		      
    		    point(g,x1+x,y1-y,Color.blue);
    		    point(g,x1-x,y1-y,Color.blue);
    		    point(g,x1+x,y1+y,Color.blue);
    		    point(g,x1-x,y1+y,Color.blue);
    		    point(g,x1+y,y1-x,Color.blue);
    		    point(g,x1-y,y1-x,Color.blue);
    		    point(g,x1+y,y1+x,Color.blue);
    		    point(g,x1-y,y1+x,Color.blue);
    		}
            
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
    }
    void axes(Graphics g){    
        g.setColor(Color.black);
        g.drawLine(0, originY, lenX, originY);
        g.drawLine(originX, 0, originX, lenY);
    }
    
    void point(Graphics g, int x, int y, Color c1) {
        //Color c1=new Color(200,90,70);
        x = windowX(x);
        y = windowY(y);
        int h,w;
        h = heightT(1);
        w = widthT(1);
        g.setColor(c1);
        g.fillRect(x, y, w, h);
    }
    
    int windowX(int x){
        return originX + (x*padX) - (padX/2);
    }
    int windowY(int y){
        return originY - y*padY - (padY/2);
    }
    int heightT(int h){
        int y = padY;
        return h*y;
    }
    int widthT(int w){
        int x = padX;
        return w*x;
    }
}

