import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class fill extends Applet implements ActionListener{

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
        
        fig t=new fig(g,originX,originY,padX);
        t.boundary();
    }
    
class polygon
{
    private int sides;
    Graphics gp;
    int oX;
    int oY;
    int unit;
    public polygon(Graphics g,int originX,int originY,int u)
    {
        gp=g;
        oX=originX;
        oY=originY;
        unit=u;
    }

    public void draw(Graphics g,int[] x,int[] y,int len)
    {
        g.setColor(Color.gray);
        for(int i=0;i<len;i++)
        {
            if(i!=len-1)
            {
                dda a=new dda();
                a.drawdda(g,oX,oY,unit,x[i],y[i],x[i+1],y[i+1]);
            }
            else
            {
                dda b=new dda();
                b.drawdda(g,oX,oY,unit,x[i],y[i],x[0],y[0]);
            }
        }
    }
    
}

class fig
{
    Graphics fill;
    int originX;
    int originY;
    int unit1;
    public fig(Graphics g,int oX,int oY,int unit)
    {
        fill=g;
        originX=oX;
        originY=oY;
        unit1=unit;
    }
    
    public void boundary(){
        int x1[]={-12,12,12,-12};
        int y1[]={-6,-6,-14,-14};
        polygon p1=new polygon(fill,originX,originY,unit1);
        p1.draw(fill,x1,y1,4);
        int x2[]={-3,3,3,-3};
        int y2[]={2,2,-8,-8};
        polygon p2=new polygon(fill ,originX,originY,unit1);
        p2.draw(fill,x2,y2,4);
        int x3[]={-8,0,8};
        int y3[]={1,9,1};
        polygon p3=new polygon(fill ,originX,originY,unit1);
        p3.draw(fill,x3,y3,3);
        int x4[]={-8,0,8};
        int y4[]={7,15,7};
        polygon p4=new polygon(fill ,originX,originY,unit1);
        p4.draw(fill,x4,y4,3);
        int x5[]={-8,0,8};
        int y5[]={13,21,13};
        polygon p5=new polygon(fill ,originX,originY,unit1);
        p5.draw(fill,x5,y5,3);
    }
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