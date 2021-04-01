import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class drawellipse extends Applet implements ActionListener{

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

        zoomout = new Button("zoom out (-)");
        zoomin = new Button("zoom in (+)");

        add(zoomin);
        add(zoomout);
        zoomin.addActionListener(this);
        zoomout.addActionListener(this);
        //addKeyListener(this);
    }

    
    public void paint(Graphics g){
        
        setBackground(Color.white);
        grid(g);
        
        ellipse base = new ellipse(0,0,40,33);
        base.drawoutline(g);
        axes(g);
    }
    
    class ellipse
    {
        public int xc,yc,a,b;

        ellipse(int x1, int y1, int a1, int b1)
        {
            xc=x1;
            yc=y1;
            a=a1;
            b=b1;
        }

        public void drawoutline(Graphics g)
        {
            int x, y;
            double p;
            
            x=0;
            y=b;
            p=(b*b)-(a*a*b)+((1/4)*a*a);
            
            for (int j=yc;j<yc+b;j++)
            {
                for (int i=xc;i<xc+a;i++)
                {
                    int temp=(i*i*b*b+a*a*j*j)-(a*a*b*b);
                    if (temp<0)
                    {
                        point(g,xc+i,yc+j,Color.cyan);
                        point(g,xc+i,yc-j,Color.cyan);
                        point(g,xc-i,yc+j,Color.cyan);
                        point(g,xc-i,yc-j,Color.cyan);
                    }
                }
            }

            do
            {
                point(g,xc+x,yc+y,Color.blue);
                point(g,xc+x,yc-y,Color.blue);
                point(g,xc-x,yc+y,Color.blue);
                point(g,xc-x,yc-y,Color.blue);
                if(p<0)
                {
                    x=x+1;
                    p=p+2*b*b*x+b*b;
                }
                else
                {
                    x=x+1;
                    y=y-1;
                    p=p+2*b*b*x-2*a*a*y+b*b;
                }
            }
            while(2*b*b*x<2*a*a*y);
            
            p=(b*b*(x+0.5)*(x+0.5))+((y-1)*(y-1)*a*a-(a*a*b*b));
            
            do
            {
                point(g,xc+x,yc+y,Color.blue);
                point(g,xc+x,yc-y,Color.blue);
                point(g,xc-x,yc+y,Color.blue);
                point(g,xc-x,yc-y,Color.blue);
                if(p>0)
                {
                    y=y-1;
                    p=p-2*a*a*y+a*a;
                }
                else
                {
                    x=x+1;
                    y=y-1;
                    p=p-2*a*a*y+2*b*b*x+a*a;
                }
            }
            while(y!=-1);

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
    void axes(Graphics g)
    {
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

