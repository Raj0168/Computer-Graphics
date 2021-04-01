import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

public class birb extends Applet implements ActionListener
{
    int lenX, lenY;
    int zoomxy;
    int originX, originY;
    int pad;
    int xc, yc, gxc=0, gyc=0;
    int isgrid, showgear;

    Button zoomin;
    Button zoomout;
    Button down;
    Button up;
    Button left;
    Button right;
    Button tgrid;
    Button gear;

    public void init()
    {
        this.setSize(new Dimension(1366, 768));
        lenY = getHeight();
        lenX = getWidth();
        originX = getWidth()/2;
        originY = getHeight()/2;
        zoomxy= 7;
        pad= 10;
        xc=160;
        yc=70;
        isgrid=1;
        showgear=0;

        //Buttons
        Frame f=new Frame("Buttons");

        up = new Button("Fly");
        up.addActionListener(this);
        add(up);  
        up.setBounds(100,40,80,30); 
        f.add(up);

        down = new Button("Fall");
        down.addActionListener(this);
        add(down);
        down.setBounds(100,100,80,30);
        f.add(down);  

        left = new Button("Left");
        left.addActionListener(this);
        add(left);
        left.setBounds(50,70,80,30);
        f.add(left);  

        right = new Button("Right");
        right.addActionListener(this);
        add(right);
        right.setBounds(160,70,80,30);
        f.add(right);  

        zoomin = new Button("ZOOM In");
        zoomin.addActionListener(this);
        add(zoomin);  
        zoomin.setBounds(50,200,80,30); 
        f.add(zoomin);

        zoomout = new Button("ZOOM Out");
        zoomout.addActionListener(this);
        add(zoomout);
        zoomout.setBounds(160,200,80,30);
        f.add(zoomout);

        tgrid = new Button("Toggle Grid");
        tgrid.addActionListener(this);
        add(tgrid);
        tgrid.setBounds(50,150,80,30);
        f.add(tgrid);

        gear = new Button("Gear");
        gear.addActionListener(this);
        add(gear);
        gear.setBounds(160,150,80,30);
        f.add(gear);

        f.setSize(270,260);  
        f.setLayout(null); 
        f.setVisible(true);
    }
    
    void grid(Graphics g, int dec){
        dec=isgrid;
        if (isgrid==0)
            return;
        
        int h, w;
        g.setColor(Color.green);
                
        for(h=originY; h>=0; h-=pad) {
            g.drawLine(0, h, lenX, h);
        }
                
        for(h=originY; h<=lenY; h+=pad) {
            g.drawLine(0, h, lenX, h);
        }
                
        for(w=originX; w>=0; w-=pad){
            g.drawLine(w, 0, w, lenY);
        }
        
        for(w=originX; w<=lenX; w+=pad){
            g.drawLine(w, 0, w, lenY);
        }
    }

    void axes(Graphics g, int dec)
    {
        dec=isgrid;
        if (isgrid==0)
            return;
        
        g.setColor(Color.black);
        g.drawLine(0, originY, lenX, originY);
        g.drawLine(originX, 0, originX, lenY);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == zoomin) {
            pad+= zoomxy;
            repaint();
        }
        else if(e.getSource() == zoomout) {
            if(pad > zoomxy) {
                pad-= zoomxy;
            }
            repaint();
        }
        else if(e.getSource() == down){
            yc-=7;
            repaint();
        }
        else if(e.getSource() == up){
            yc+=7;
            repaint();
        }
        else if(e.getSource() == left){
            xc-=10;
            repaint();
        }
        else if(e.getSource() == right){
            xc+=10;
            repaint();
        }
        if (e.getSource() == tgrid) {
            if (isgrid==1)
                isgrid=0;
            else if(isgrid==0)
                isgrid=1;
            repaint();
        }
        if (e.getSource() == gear) {
            if (showgear==1)
                showgear=0;
            else if(showgear==0)
                showgear=1;
            repaint();
        }
    }

    public void paint(Graphics g){
        setBackground(Color.white);
        grid(g, isgrid);
        
        Font stringFont= new Font("Inter",1,22);
	    g.setFont(stringFont);
	    g.setColor(Color.black);
	    g.drawString("BIRD",1100,300);
        g.drawString("BIRD",600,800);
        bird b=new bird(g,originX,originY,pad);
        b.drawbird(xc,yc,21,14);

        if(showgear==1)
        {
            gear ge=new gear(g,originX,originY,pad);
            ge.gearball(gxc,gyc,30);
        }
        axes(g, isgrid);
    }
}

class bird
{
    Graphics gp;
    int u,oX,oY;
    public bird(Graphics g,int originX,int originY,int unit)
    {
        gp=g;
        oX=originX;
        oY=originY;
        u=unit;
    }
    void drawbird(int x,int y,int a,int b)
    {
        int x2[]={x+13,x+45,x+30};
        int y2[]={y-2,y-2,y-8};
        polygon tail=new polygon(gp,oX,oY,u);
        tail.draw(gp,x2,y2,3);
        tail.fill4this(x+30,y-5,Color.orange);
        
        ellipse e=new ellipse(gp,oX,oY,u);
        e.draw_ellipse(x,y,a,b);
        e.getpoints(x,y,a,b);
        e.fill4this(x,y);

        semiellipse se=new semiellipse(gp,oX,oY,u);
        se.draw_semiellipse(x,y+10,14,11);
        se.getpoints(x,y+10,14,11);
        se.fill4this(x,y+10);
        
        circle c=new circle(gp,oX,oY,u);
        c.draw_circle(x-24,y+15,10);
        c.fill4this(x-24,y+15,Color.yellow);
        
        gp.setColor(Color.black);
        point eye=new point();
        eye.drawpoint(gp,oX,oY,u,x-27,y+18);

        int x4[]={x-46,x-34,x-34};
        int y4[]={y+10,y+18,y+12};
        polygon peak2=new polygon(gp,oX,oY,u);
        peak2.draw(gp,x4,y4,3);
        peak2.fill4this(x-37,y+15,Color.orange);

        int x3[]={x-47,x-34,x-34};
        int y3[]={y+15,y+18,y+12};
        polygon peak1=new polygon(gp,oX,oY,u);
        peak1.draw(gp,x3,y3,3);
        peak1.fill4this(x-37,y+15,Color.red);
    }
}

class gear
{
    Graphics gp;
    int u, oX, oY;
    public gear(Graphics g, int originX, int originY, int unit)
    {
        gp=g;
        oX=originX;
        oY=originY;
        u=unit;
    }

    void gearball(int x, int y, int r)
    {
        Color grey=new Color(179,179,179);


        //I HAD TO MAKE EVERY SEPERATE POLYGON
        //DUE TO SOME REASON THE FUNCTION WAS NOT WORKING

        int x5[]={x+16,x+24,x+32,x+24};
        int y5[]={y+24,y+32,y+24,y+16};
        polygon as5=new polygon(gp,oX,oY,u);
        as5.draw(gp,x5,y5,4);
        as5.fill4this(x+24,y+30,grey);

        int x6[]={x-16,x-24,x-32,x-24};
        int y6[]={y+24,y+32,y+24,y+16};
        polygon as6=new polygon(gp,oX,oY,u);
        as6.draw(gp,x6,y6,4);
        as6.fill4this(x-24,y+30,grey);

        int x7[]={x+16,x+24,x+32,x+24};
        int y7[]={y-24,y-32,y-24,y-16};
        polygon as7=new polygon(gp,oX,oY,u);
        as7.draw(gp,x7,y7,4);
        as7.fill4this(x+24,y-30,grey);

        int x8[]={x-16,x-24,x-32,x-24};
        int y8[]={y-24,y-32,y-24,y-16};
        polygon as8=new polygon(gp,oX,oY,u);
        as8.draw(gp,x8,y8,4);
        as8.fill4this(x-24,y-30,grey);

        circle body=new circle(gp, oX,oY,u);
        body.draw_circle(x,y,r);
        body.fill4this(x,y,grey);

        circle centre=new circle(gp, oX,oY,u);
        centre.draw_circle(x,y,r-13);
        centre.fill4this(x,y,Color.green);

        int x1[]={x-6,x-6,x+6,x+6};
        int y1[]={y+30,y+40,y+40,y+30};
        polygon as1=new polygon(gp,oX,oY,u);
        as1.draw(gp,x1,y1,4);
        as1.fill4this(x,y+33,grey);

        int x2[]={x-6,x-6,x+6,x+6};
        int y2[]={y-30,y-40,y-40,y-30};
        polygon as2=new polygon(gp,oX,oY,u);
        as2.draw(gp,x2,y2,4);
        as2.fill4this(x,y-33,grey);

        int x3[]={x-30,x-30,x-40,x-40};
        int y3[]={y+6,y-6,y-6,y+6};
        polygon as3=new polygon(gp,oX,oY,u);
        as3.draw(gp,x3,y3,4);
        as3.fill4this(x-33,y,grey);

        int x4[]={x+30,x+30,x+40,x+40};
        int y4[]={y-6,y+6,y+6,y-6};
        polygon as4=new polygon(gp,oX,oY,u);
        as4.draw(gp,x4,y4,4);
        as4.fill4this(x+33,y,grey);
      }
}


class point
{
    public void drawpoint(Graphics g,int originX,int originY,int unit,int x,int y)
    {
        x = originX+(x*unit)-(unit/2);
        y = originY-(y*unit)-(unit/2);
        int h,w;
        h = unit*2;
        w = unit*2;
        g.fillOval(x, y, w, h);
        //g.fillRect(x, y, w, h);
    }
}

class polygon
{
    public char[][] points;
    public Vector<Integer> point_x=new Vector<>();
    public Vector<Integer> point_y=new Vector<>();
    public int rangeX,rangeY;
    public int Xmin,Ymin,Xmax,Ymax;
    Graphics gp;
    int oX;
    int oY;
    int u;
    public polygon(Graphics g,int originX,int originY,int unit)
    {
        gp=g;
        oX=originX;
        oY=originY;
        u=unit;
    }
    public void draw(Graphics g,int[] x,int[] y,int len)
    {
        Xmin=x[0];
        for(int j=0;j<len;j++){
            if(x[j]<Xmin){
                Xmin=x[j];
            }
        }
        Ymin=y[0];
        for(int k=0;k<len;k++){
            if(y[k]<Ymin){
                Ymin=y[k];
            }
        }
        Xmax=x[0];
        for(int l=0;l<len;l++){
            if(x[l]>Xmax){
                Xmax=x[l];
            }
        }
        Ymax=y[0];
        for(int m=0;m<len;m++){
            if(y[m]>Ymax){
                Ymax=y[m];
            }
        }
        g.setColor(Color.black);
        for(int i=0;i<len;i++)
        {
            if(i!=len-1)
            {
                drawlineMidPoint(x[i],y[i],x[i+1],y[i+1]);
            }
            else
            {
                drawlineMidPoint(x[i],y[i],x[0],y[0]);
            }
        }
    }
    void drawlineMidPoint(int x1, int y1, int x2, int y2)
    {
            rangeX=Xmax-Xmin+1;
            rangeY=Ymax-Ymin+1;
            points=new char[rangeX][rangeY];
            for(int i=0;i<rangeX;i++){
                for(int j=0;j<rangeY;j++){
                    points[i][j]=0;
                }
            }
            double dx=(x2-x1),dy=(y2-y1);
            double m = (double)(dy)/(double)(dx);
            int ix=0,iy=0;
            if(dx>0)
            {
                ix = 1;
            }
            if(dx<0)
            {
                ix = -1;
            }
            if(dy>0)
            {
                iy = 1;
            }
            if(dy<0)
            {
                iy = -1;
            }
            int xe=x1,ye=y1;
            point p1=new point();
            p1.drawpoint(gp,oX,oY,u,x1,y1);
            point_x.add(x1);
            point_y.add(y1);
            p1.drawpoint(gp,oX,oY,u,x2,y2);
            point_x.add(x2);
            point_y.add(y2);
            if(Math.abs(m)<1)
            {
                double p = 0.5 - Math.abs(m);
                while(xe != x2)
                {   
                    xe += ix;
                    if(p>0)
                    {
                        p1.drawpoint(gp,oX,oY,u,xe,ye);
                        point_x.add(xe);
                        point_y.add(ye);
                        p -= Math.abs(m);
                    }
                    else{
                        ye += iy;
                        p1.drawpoint(gp,oX,oY,u,xe,ye);
                        point_x.add(xe);
                        point_y.add(ye);
                        p += 1-Math.abs(m);
                    }
                }
            }
            else
            {
                double p = 1 - Math.abs(m*0.5);
                while(ye != y2)
                {   
                    ye += iy;
                    if(p>=0)
                    {
                        xe += ix;
                        p1.drawpoint(gp,oX,oY,u,xe,ye);
                        point_x.add(xe);
                        point_y.add(ye);
                        p += 1-Math.abs(m);
                    }
                    else{
                        
                        p1.drawpoint(gp,oX,oY,u,xe,ye);
                        point_x.add(xe);
                        point_y.add(ye);
                        p += 1;
                    }
                }
            }
    }
    void fill4this(int x,int y,Color c)
    {
        int a,b,l,m,j,k;
        int len=point_x.size();  
        j=x-Xmin;
        k=y-Ymin;
        for (int i=0;i<len;i++)
        {
            a=point_x.get(i);
            b=point_y.get(i);
            if((a==x && b==y) || points[j][k]==1)
            {
                return;
            }
        }
        points[j][k]=1;
        gp.setColor(c);
        point pf=new point();
        pf.drawpoint(gp,oX,oY,u,x,y);
        fill4this(x-1,y,c);
        fill4this(x+1,y,c);
        fill4this(x,y+1,c);
        fill4this(x,y-1,c);
    }
}

class circle
{
    public char[][] points;
    public Vector<Integer> point_x=new Vector<>();
    public Vector<Integer> point_y=new Vector<>();
    public int rangeX,rangeY;
    public int Xmin,Ymin;
    Graphics gp;
    int originX;
    int originY;
    int unit;
    public circle(Graphics g,int oX,int oY,int u)
    {
        gp=g;
        originX=oX;
        originY=oY;
        unit=u;
    }
    public void draw_circle(int x,int y,int r)
    {
        int Xmax=x+r;
        Xmin=x-r;
        int Ymax=y+r;
        Ymin=y-r;
        rangeX=Xmax-Xmin+1;
        rangeY=Ymax-Ymin+1;
        points=new char[rangeX][rangeY];
        for(int i=0;i<rangeX;i++)
        {
            for(int j=0;j<rangeY;j++)
            {
                points[i][j]=0;
            }
        }
        float p=5/4-r;
        int x1=0;
        int y1=r;
        gp.setColor(Color.black);
        point p1=new point();
        p1.drawpoint(gp,originX,originY,unit,x,y+r);
        p1.drawpoint(gp,originX,originY,unit,x+r,y);
        p1.drawpoint(gp,originX,originY,unit,x,y-r);
        p1.drawpoint(gp,originX,originY,unit,x-r,y);
        int a1=x+r;
        int a2=x-r;
        int a3=y+r;
        int a4=y-r;
        point_x.add(x);
        point_y.add(a3);
        point_x.add(a1);
        point_y.add(y);
        point_x.add(x);
        point_y.add(a4);
        point_x.add(a2);
        point_y.add(y);
        for (int i=0;i<y1;i++)
        {
            if(p>0)
            {
                x1=x1+1;
                p=p+2*x1+(1-2*y1);
                y1=y1-1;
            }
            else if(p<0)
            {
                x1=x1+1;
                p=p+2*x1+1;
            }
            point p2=new point();
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            point p4=new point();
            p4.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            point p5=new point();
            p5.drawpoint(gp,originX,originY,unit,x-x1,y-y1);
            point p6=new point();
            p6.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            point p7=new point();
            p7.drawpoint(gp,originX,originY,unit,x+y1,y+x1);
            point p8=new point();
            p8.drawpoint(gp,originX,originY,unit,x+y1,y-x1);
            point p9=new point();
            p9.drawpoint(gp,originX,originY,unit,x-y1,y-x1);
            point p10=new point();
            p10.drawpoint(gp,originX,originY,unit,x-y1,y+x1);
            int a5=x+x1;
            int a6=y+y1;
            int a7=y-y1;
            int a8=x-x1;
            int a9=x+y1;
            int a10=y+x1;
            int a11=y-x1;
            int a12=x-y1;
            point_x.add(a5);
            point_y.add(a6);
            point_x.add(a5);
            point_y.add(a7);
            point_x.add(a8);
            point_y.add(a7);
            point_x.add(a8);
            point_y.add(a6);
            point_x.add(a9);
            point_y.add(a10);
            point_x.add(a9);
            point_y.add(a11);
            point_x.add(a12);
            point_y.add(a11);
            point_x.add(a12);
            point_y.add(a10);
        }        
    }
    
    void fill4this(int x,int y,Color c)
    {
        int a,b,l,m,j,k;
        int len=point_x.size();  
        j=x-Xmin;
        k=y-Ymin;
        for (int i=0;i<len;i++)
        {
            a=point_x.get(i);
            b=point_y.get(i);
            if((a==x && b==y) || points[j][k]==1)
            {
                return;
            }
        }
        points[j][k]=1;
        gp.setColor(c);
        point pf=new point();
        pf.drawpoint(gp,originX,originY,unit,x,y);
        fill4this(x-1,y,c);
        fill4this(x+1,y,c);
        fill4this(x,y+1,c);
        fill4this(x,y-1,c);
    }
}

class ellipse{
    public char[][] points;
    public Vector<Integer> point_x=new Vector<>();
    public Vector<Integer> point_y=new Vector<>();
    public int rangeX,rangeY;
    public int Xmin,Ymin;
    Graphics gp;
    int originX;
    int originY;
    int unit;
    public ellipse(Graphics g,int oX,int oY,int u)
    {
        gp=g;
        originX=oX;
        originY=oY;
        unit=u;
    }
    void draw_ellipse(int x,int y,int a,int b)
    {
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        do
        {
            point p1=new point();
            p1.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            p1.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y-y1);
            if(p<0)
            {
                x1=x1+1;
                p=p+2*b*b*x1+b*b;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p+2*b*b*x1-2*a*a*y1+b*b;
            }
        }
        while(2*b*b*x1<2*a*a*y1);
        p=(b*b*(x1+0.5)*(x1+0.5))+((y1-1)*(y1-1)*a*a-(a*a*b*b));
        do
        {
            point p2=new point();
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            p2.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y-y1);
            if(p>0)
            {
                y1=y1-1;
                p=p-2*a*a*y1+a*a;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p-2*a*a*y1+2*b*b*x1+a*a;
            }
        }
        while(y1!=-1);
        
    }
    void getpoints(int x,int y,int a,int b)
    {
        int Xmax=x+a;
        Xmin=x-a;
        int Ymax=y+b;
        Ymin=y-b;
        rangeX=Xmax-Xmin+1;
        rangeY=Ymax-Ymin+1;
        points=new char[rangeX][rangeY];
        for(int i=0;i<rangeX;i++)
        {
            for(int j=0;j<rangeY;j++)
            {
                points[i][j]=0;
            }
        }
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        int a1,b1,c1,d1;
        do
        {
            a1=x+x1;
            b1=y+y1;
            c1=y-y1;
            d1=x-x1;
            point_x.add(a1);
            point_y.add(b1);
            point_x.add(a1);
            point_y.add(c1);
            point_x.add(d1);
            point_y.add(b1);
            point_x.add(d1);
            point_y.add(c1);
            if(p<0)
            {
                x1=x1+1;
                p=p+2*b*b*x1+b*b;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p+2*b*b*x1-2*a*a*y1+b*b;
            }
        }
        while(2*b*b*x1<2*a*a*y1);
        p=(b*b*(x1+0.5)*(x1+0.5))+((y1-1)*(y1-1)*a*a-(a*a*b*b));
        do
        {
            a1=x+x1;
            b1=y+y1;
            c1=y-y1;
            d1=x-x1;
            point_x.add(a1);
            point_y.add(b1);
            point_x.add(a1);
            point_y.add(c1);
            point_x.add(d1);
            point_y.add(b1);
            point_x.add(d1);
            point_y.add(c1);
            if(p>0)
            {
                y1=y1-1;
                p=p-2*a*a*y1+a*a;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p-2*a*a*y1+2*b*b*x1+a*a;
            }
        }
        while(y1!=-1);
    }
    void fill4this(int x,int y)
    {
        int a,b,l,m,j,k;
        int len=point_x.size();  
        j=x-Xmin;
        k=y-Ymin;
        for (int i=0;i<len;i++)
        {
            a=point_x.get(i);
            b=point_y.get(i);
            if((a==x && b==y) || points[j][k]==1)
            {
                return;
            }
        }
        points[j][k]=1;
        gp.setColor(Color.yellow);
        point pf=new point();
        pf.drawpoint(gp,originX,originY,unit,x,y);
        fill4this(x-1,y);
        fill4this(x+1,y);
        fill4this(x,y+1);
        fill4this(x,y-1);
    }
    public void fill_ellipse(int x,int y,int a,int b)
    {
        float t;
        for (int j=0;j<=b;j++)
        {
            for (int i=0;i<=a;i++)
            {
                t=((i*i)/(a*a))+((j*j)/(b*b))-1;
                if (t<0)
                {
                    point p12=new point();
                    p12.drawpoint(gp,originX,originY,unit,x+i,y+j);
                    p12.drawpoint(gp,originX,originY,unit,x+i,y-j);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y+j);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y-j);
                }
            }
        }
    }
}

class semiellipse{
    public char[][] points;
    public Vector<Integer> point_x=new Vector<>();
    public Vector<Integer> point_y=new Vector<>();
    public int rangeX,rangeY;
    public int Xmin,Ymin;
    Graphics gp;
    int originX;
    int originY;
    int unit;
    public semiellipse(Graphics g,int oX,int oY,int u)
    {
        gp=g;
        originX=oX;
        originY=oY;
        unit=u;
    }
    void draw_semiellipse(int x,int y,int a,int b)
    {
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        do
        {
            point p1=new point();
            p1.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            //p1.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            //p1.drawpoint(gp,originX,originY,unit,x-x1,y-y1);
            if(p<0)
            {
                x1=x1+1;
                p=p+2*b*b*x1+b*b;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p+2*b*b*x1-2*a*a*y1+b*b;
            }
        }
        while(2*b*b*x1<2*a*a*y1);
        p=(b*b*(x1+0.5)*(x1+0.5))+((y1-1)*(y1-1)*a*a-(a*a*b*b));
        do
        {
            point p2=new point();
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            //p2.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            //p2.drawpoint(gp,originX,originY,unit,x-x1,y-y1);
            if(p>0)
            {
                y1=y1-1;
                p=p-2*a*a*y1+a*a;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p-2*a*a*y1+2*b*b*x1+a*a;
            }
        }
        while(y1!=-1);
        
    }
    void getpoints(int x,int y,int a,int b)
    {
        int Xmax=x+a;
        Xmin=x-a;
        int Ymax=y+b;
        Ymin=y-b;
        rangeX=Xmax-Xmin+1;
        rangeY=Ymax-Ymin+1;
        points=new char[rangeX][rangeY];
        for(int i=0;i<rangeX;i++)
        {
            for(int j=0;j<rangeY;j++)
            {
                points[i][j]=0;
            }
        }
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        int a1,b1,c1,d1;
        do
        {
            a1=x+x1;
            b1=y+y1;
            c1=y-y1;
            d1=x-x1;
            point_x.add(a1);
            point_y.add(b1);
            point_x.add(a1);
            point_y.add(c1);
            point_x.add(d1);
            point_y.add(b1);
            point_x.add(d1);
            point_y.add(c1);
            if(p<0)
            {
                x1=x1+1;
                p=p+2*b*b*x1+b*b;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p+2*b*b*x1-2*a*a*y1+b*b;
            }
        }
        while(2*b*b*x1<2*a*a*y1);
        p=(b*b*(x1+0.5)*(x1+0.5))+((y1-1)*(y1-1)*a*a-(a*a*b*b));
        do
        {
            a1=x+x1;
            b1=y+y1;
            c1=y-y1;
            d1=x-x1;
            point_x.add(a1);
            point_y.add(b1);
            point_x.add(a1);
            point_y.add(c1);
            point_x.add(d1);
            point_y.add(b1);
            point_x.add(d1);
            point_y.add(c1);
            if(p>0)
            {
                y1=y1-1;
                p=p-2*a*a*y1+a*a;
            }
            else
            {
                x1=x1+1;
                y1=y1-1;
                p=p-2*a*a*y1+2*b*b*x1+a*a;
            }
        }
        while(y1!=-1);
    }
    void fill4this(int x,int y)
    {
        int a,b,l,m,j,k;
        int len=point_x.size();  
        j=x-Xmin;
        k=y-Ymin;
        for (int i=0;i<len;i++)
        {
            a=point_x.get(i);
            b=point_y.get(i);
            if((a==x && b==y) || points[j][k]==1)
            {
                return;
            }
        }
        points[j][k]=1;
        gp.setColor(Color.orange);
        point pf=new point();
        pf.drawpoint(gp,originX,originY,unit,x,y);
        fill4this(x-1,y);
        fill4this(x+1,y);
        fill4this(x,y+1);
        //fill4this(x,y-1);
    }
    public void fill_ellipse(int x,int y,int a,int b)
    {
        float t;
        for (int j=0;j<=b;j++)
        {
            for (int i=0;i<=a;i++)
            {
                t=((i*i)/(a*a))+((j*j)/(b*b))-1;
                if (t<0)
                {
                    point p12=new point();
                    p12.drawpoint(gp,originX,originY,unit,x+i,y+j);
                    //p12.drawpoint(gp,originX,originY,unit,x+i,y-j);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y+j);
                    //p12.drawpoint(gp,originX,originY,unit,x-i,y-j);
                }
            }
        }
    }
}

