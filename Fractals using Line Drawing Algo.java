import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class twig extends Applet implements ActionListener{

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
		g.setColor(Color.black);
		g.drawString("DDA Line Drawing Algo",400,60);
		g.drawString("Fixed Parameters",75,100);
		g.drawString("Random Parameter",800,100);
        
        dda(g, -50, 1, -50, -30);
        twigfix(g, -50, 1, 45, 20, 5);
        twigfix(g, -50, 1, -45, 20, 5);
        //dda(g, -40, 1, -40, -30);
        twigrand(g, 40, -25, 7, 2, 0);
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

    void twigfix(Graphics g, float x, float y, float angle, int len, int depth)
    {
        if(depth==0)
            return;
        
        int x1=(int)(len*Math.sin(Math.toRadians(angle)));
        int y1=(int)(len*Math.cos(Math.toRadians(angle)));
        
        dda(g, x, y, x+x1, y+y1);
            
        twigfix(g, x+x1, y+y1, angle-45, (int)(len/2), depth-1);
        twigfix(g, x+x1, y+y1, angle+45, (int)(len/2), depth-1);
    }
    
    void twigrand(Graphics g, float x, float y, int depth, int elec, int temp)
    {
        if(depth==0)
            return;
            
        double l=Math.random()*20;
        int len=(int)(l);
        double a=Math.random()*35;
        int angle=(int)(a);
        
        if(elec==0)
            angle=temp-angle;
        else if(elec==1)
            angle=temp+angle;
        
        int x1=(int)(len*Math.sin(Math.toRadians(angle)));
        int y1=(int)(len*Math.cos(Math.toRadians(angle)));
        
        dda(g, x, y, x+x1, y+y1);

        twigrand(g, x+x1, y+y1, depth-1, 0, angle);
        twigrand(g, x+x1, y+y1, depth-1, 1, angle);
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
