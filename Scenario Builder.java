import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class scenariomenu extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    int lenX, lenY;
    int zoomxy;
    int originX, originY;
    int pad;
    int xc, yc, gxc=0, gyc=0;
    int isgrid, showbird, showtree;
    int flap=1;
    int angle, scale;
    double rad;
    int flockno=1;
    int forestno=1;

    Button zoomin, zoomout;
    Button up, down, right, left;
    Button tgrid, reset, duck, leaf;
    Button incsize, decsize, rotleft, rotright;
    Button flock, forest;

    public void init()
    {
        super.init();
        setSize(400,400);
        addMouseListener(this);
        addMouseMotionListener(this);

        this.setSize(new Dimension(1366, 768));
        lenY = getHeight();
        lenX = getWidth();
        originX = getWidth()/2;
        originY = getHeight()/2;
        zoomxy= 7;
        pad= 10;
        xc=0;
        yc=0;
        isgrid=1;
        showbird=0;
        scale=1;
        angle=0;

        up = new Button("Up");
        up.addActionListener(this);
        add(up); 

        down = new Button("Down");
        down.addActionListener(this);
        add(down);

        left = new Button("Left");
        left.addActionListener(this);
        add(left);

        right = new Button("Right");
        right.addActionListener(this);
        add(right);

        zoomin = new Button("ZOOM In");
        zoomin.addActionListener(this);
        add(zoomin);

        zoomout = new Button("ZOOM Out");
        zoomout.addActionListener(this);
        add(zoomout);

        tgrid = new Button("Grid");
        tgrid.addActionListener(this);
        add(tgrid);

        reset = new Button("Reset");
        reset.addActionListener(this);
        add(reset);

        leaf = new Button("Tree");
        leaf.addActionListener(this);
        add(leaf);

        duck = new Button("Bird");
        duck.addActionListener(this);
        add(duck);

        incsize = new Button("Inc");
        incsize.addActionListener(this);
        add(incsize);

        decsize = new Button("Dec");
        decsize.addActionListener(this);
        add(decsize);

        rotleft=new Button("Left");
        rotleft.addActionListener(this);
        add(rotleft);

        rotright=new Button("Right");
        rotright.addActionListener(this);
        add(rotright);

        flock=new Button("Flock");
        flock.addActionListener(this);
        add(flock);

        forest=new Button("Forest");
        forest.addActionListener(this);
        add(forest);
    }

    int xclick, yclick;
    int xhover, yhover;

    String msg="";
    String scalefac="";
    String clickDesc;
    int x,y;
 
    @Override
    public void mouseDragged(MouseEvent e) {
        x=e.getX();y=e.getY();
        msg="X="+x+"Y="+y;
        scalefac="Scale Factor"+scale;
    repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        repaint();
        msg="X="+x+"Y="+y;
        scalefac="Scale Factor"+scale;
    }
    @Override
    public void mouseClicked(MouseEvent ec) {
        xclick=ec.getX();
        yclick=ec.getY();
        xclick=(xclick-originX+(pad/2))/pad;
        yclick=(originY-yclick-(pad/2))/pad;
    }
    
    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

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
            if (flap==1)
                flap=0;
            else if(flap==0)
                flap=1;
            yclick-=7;
            repaint();
        }
        else if(e.getSource() == up){
            if (flap==1)
                flap=0;
            else if(flap==0)
                flap=1;
            yclick+=7;
            repaint();
        }
        else if(e.getSource() == left){
            if (flap==1)
                flap=0;
            else if(flap==0)
                flap=1;
            xclick-=10;
            repaint();
        }
        else if(e.getSource() == right){
            if (flap==1)
                flap=0;
            else if(flap==0)
                flap=1;
            xclick+=10;
            repaint();
        }
        if (e.getSource() == tgrid) {
            if (isgrid==1)
                isgrid=0;
            else if(isgrid==0)
                isgrid=1;
            repaint();
        }
        if (e.getSource() == duck) {
            if (showbird==1)
                showbird=0;
            else if(showbird==0)
                showbird=1;
            repaint();
        }
        if (e.getSource() == leaf) {
            if (showtree==1)
                showtree=0;
            else if(showtree==0)
                showtree=1;
            repaint();
        }
        if (e.getSource() == incsize) {
            scale+=1;
            repaint();
        }
        if (e.getSource() == decsize) {
            scale-=1;
            repaint();
        }
        if(e.getSource()==rotleft) {
            angle-=30;
            repaint();
        }
        if(e.getSource()==rotright) {
            angle+=30;
            repaint();
        }

        if(e.getSource()==flock) {
            if(flockno==1)
                flockno=2;
            else if(flockno==2)
                flockno=3;
            else if(flockno==3)
                flockno=1;
            repaint();
        }
        if(e.getSource()==forest) {
            if(forestno==1)
                forestno=2;
            else if(forestno==2)
                forestno=3;
            else if(forestno==3)
                forestno=1;
            repaint();
        }
        if(e.getSource()==reset) {
            scale=1;
            showtree=0;
            showbird=0;
            isgrid=1;
            flap=1;
            xclick=-10;
            yclick=0;
            angle=0;
            flockno=1;
            forestno=1;
            repaint();
        }
    }

    public void paint(Graphics g){

        setBackground(Color.white);
        grid(g, isgrid);
        axes(g, isgrid);

        Font reference= new Font("Monospace Regular",3,17);
        g.setFont(reference);
        g.setColor(Color.blue);
        
        showStatus(msg);
        showStatus(scalefac);
        g.fillOval(x, y, 10, 10);
        g.drawString("JA: "+x+", "+y, 20,20);
        x=(x-originX+(pad/2))/pad;
        y=(originY-y-(pad/2))/pad;
        g.drawString("X: "+x+" Y:"+y, 20,40);
        g.drawString("SCALE FACTOR: "+scale, 170,20);
        g.drawString("ANGLE: "+angle, 170,40);

        this.duck.setLocation(1000,200);
        this.duck.setSize(50,40);
        
        this.leaf.setLocation(1160,200);
        this.leaf.setSize(50,40);

        
        double rad=Math.toRadians(angle);
        
        if(showtree==1)
        {
            tree t=new tree(g,originX,originY,pad);
            if(forestno==1) {
                t.oak(xclick,xclick+20,yclick,yclick-6,scale,rad);
            }
            else if(forestno==2) {
                t.oak(xclick,xclick+20,yclick,yclick-6,scale,rad);
                t.oak(xclick,xclick+20,yclick-25,yclick-6-25,scale,rad);
                t.oak(xclick,xclick+20,yclick-50,yclick-6-50,scale,rad);
            }
            else if(forestno==3) {
                int x=0, val=0;
                while(x<12)
                {
                    t.oak(xclick+val,xclick+val+20,yclick,yclick-6,scale,rad);
                    t.oak(xclick+val,xclick+val+20,yclick-25,yclick-6-25,scale,rad);
                    t.oak(xclick+val,xclick+val+20,yclick-50,yclick-6-50,scale,rad);
                    val+=20;
                    x++;
                }
            }
        }

        if(showbird==1)
        {
            bird b=new bird(g,originX,originY,pad);
            int notflap;
            if(flap==1)
                notflap=0;
            else
                notflap=1;
            if(flockno==1) {
                b.drawbird(xclick,yclick,21,14,scale,flap,rad);
            }
            else if (flockno==2) {
                b.drawbird(xclick,yclick,21,14,scale,notflap,rad);
                b.drawbird(xclick+60,yclick+40,21,14,scale,flap,rad);
                b.drawbird(xclick+60,yclick-40,21,14,scale,notflap,rad);
            }
            else if (flockno==3) {
                b.drawbird(xclick,yclick,21,14,scale,notflap,rad);
                b.drawbird(xclick+60,yclick+40,21,14,scale,notflap,rad);
                b.drawbird(xclick+60,yclick-40,21,14,scale,flap,rad);

                b.drawbird(xclick+120,yclick,21,14,scale,flap,rad);
                b.drawbird(xclick+120,yclick+80,21,14,scale,notflap,rad);
                b.drawbird(xclick+120,yclick-80,21,14,scale,flap,rad);
            }
        }

        Color blueeee=new Color(0, 51, 153);
        g.setColor(blueeee);
        g.fillRect(850,0,500,672);
        g.setColor(Color.black);
        g.fillRect(850,0,5,672);
        g.fillRect(1345,0,5,672);
        Color blueee=new Color(102, 0, 102);
        g.setColor(blueee);
        g.fillRect(738,540,170,105);

        Font stringFont= new Font("Monospace Regular",4,21);
	    g.setFont(stringFont);
        g.setColor(Color.white);
        g.drawString("SCENARIO BUILDER",940,30);
        bird bd=new bird(g,originX,originY,pad);
        bd.drawbird(110,80,21,14,1,1,0);
        tree td=new tree(g,originX,originY,pad);
        td.oak(150,170,70,64,1,0);
        
        this.up.setLocation(800,550);
        this.up.setSize(40,30);
        this.down.setLocation(800,610);
        this.down.setSize(40,30);
        this.left.setLocation(750,580);
        this.left.setSize(40,30);
        this.right.setLocation(850,580);
        this.right.setSize(40,30);

        g.drawString("SIZE",1080,330);
        this.incsize.setLocation(1000,300);
        this.incsize.setSize(50,40);
        this.decsize.setLocation(1160,300);
        this.decsize.setSize(50,40);

        g.drawString("ROTATE",1060,430);
        this.rotleft.setLocation(1000,400);
        this.rotleft.setSize(50,40);
        this.rotright.setLocation(1160,400);
        this.rotright.setSize(50,40);

        g.drawString("COPY",1060,530);
        this.flock.setLocation(1000,500);
        this.flock.setSize(50,40);
        this.forest.setLocation(1160,500);
        this.forest.setSize(50,40);

        this.reset.setLocation(1000,600);
        this.reset.setSize(50,40);
        this.tgrid.setLocation(1160,600);
        this.tgrid.setSize(50,40);
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
    void drawbird(int x,int y,int a,int b, int scale,int flap, double rad)
    {
        int x2[]={scale*(x+13),scale*(x+45),scale*(x+30)};
        int y2[]={scale*(y-2),scale*(y-2),scale*(y-8)};
        polygon tail=new polygon(gp,oX,oY,u);
        tail.draw(gp,x2,y2,3,rad);
        tail.fill4this(scale*(x+30),scale*(y-5),Color.orange,rad);
        
        ellipse e=new ellipse(gp,oX,oY,u);
        e.draw_ellipse(scale*x,scale*y,a*scale,b*scale,rad);
        e.getpoints(scale*x,scale*y,a*scale,b*scale);
        e.fill4this(scale*(x+3),scale*(y+3),rad);

        //wing up
        if(flap==1)
        {
            semiellipse se1=new semiellipse(gp,oX,oY,u);
            se1.draw_semiellipse(scale*x,scale*(y+10),14*scale,11*scale,rad);
            se1.getpoints(scale*x,scale*(y+10),14*scale,11*scale);
            se1.fill4this(scale*x,scale*(y+10),rad);
        }
        //wing down
        if(flap==0)
        {
            semiellipserev se2=new semiellipserev(gp,oX,oY,u);
            se2.draw_semiellipserev(scale*x,scale*(y+10),14*scale,11*scale,rad);
            se2.getpoints(scale*x,scale*(y+10),14*scale,11*scale);
            se2.fill4this(scale*x,scale*(y+10),rad);
        }
        
        circle c=new circle(gp,oX,oY,u);
        c.draw_circle((x-24)*scale,(y+15)*scale,10*scale,rad);
        c.fill4this((x-24)*scale,scale*(y+15),Color.yellow,rad);
        
        circle c2=new circle(gp,oX,oY,u);
        c2.draw_circle((x-27)*scale,(y+17)*scale,1*scale,rad);
        c2.fill4this((x-27)*scale,scale*(y+17),Color.white,rad);
        
        int x4[]={scale*(x-46),scale*(x-34),scale*(x-34)};
        int y4[]={scale*(y+10),scale*(y+18),scale*(y+12)};
        polygon peak2=new polygon(gp,oX,oY,u);
        peak2.draw(gp,x4,y4,3,rad);
        peak2.fill4this(scale*(x-37),scale*(y+15),Color.orange,rad);

        int x3[]={scale*(x-47),scale*(x-34),scale*(x-34)};
        int y3[]={scale*(y+15),scale*(y+18),scale*(y+12)};
        polygon peak1=new polygon(gp,oX,oY,u);
        peak1.draw(gp,x3,y3,3,rad);
        peak1.fill4this(scale*(x-37),scale*(y+15),Color.red,rad);
    }
}

class tree
{
    Graphics g;
    int u, oX, oY;
    public tree(Graphics gp, int originX, int originY, int unit)
    {
        g=gp;
        oX=originX;
        oY=originY;
        u=unit;
    }
    void oak(int a, int b, int c, int d, int scale,double rad)
    {
    
        Color mud=new Color(184,80,80);
        Color brown=new Color(76,36,20);
        Color dgreen=new Color(11,102,35);
        Color lgreen=new Color(46,139,87);

        int x1[]={a*scale, b*scale, b*scale, a*scale};
        int y1[]={(c+14)*scale, (c+14)*scale, (d-7)*scale, (d-7)*scale};
        polygon base=new polygon(g,oX,oY,u);
        base.draw(g,x1,y1,4,0);
        base.fill4this(scale*(a+1),scale*(d+1),mud,0);
        
        int x2[]={scale*(a+7), scale*(a+7), scale*(a+13), scale*(a+13)};
        int y2[]={scale*c, scale*(c+20), scale*(c+20), scale*c};
        polygon trunk=new polygon(g,oX,oY,u);
        trunk.draw(g,x2,y2,4,rad);
        trunk.fill4this(scale*(a+10),scale*(c+3),brown,rad);

        int x3[]={scale*(a-4), scale*(a+10), scale*(b+4)};
        int y3[]={scale*(c+20), scale*(c+35), scale*(c+20)};
        polygon lv1=new polygon(g,oX,oY,u);
        lv1.draw(g,x3,y3,3,rad);
        lv1.fill4this(scale*(a+10),scale*(c+22),dgreen,rad);
        
        int x4[]={scale*(a-2), scale*(a+10), scale*(b+2)};
        int y4[]={scale*(c+28),scale*(c+43),scale*(c+28)};
        polygon lv2=new polygon(g,oX,oY,u);
        lv2.draw(g,x4,y4,3,rad);
        lv2.fill4this(scale*(a+6),scale*(c+30),dgreen,rad);

        int x5[]={scale*(a), scale*(a+10), scale*(b)};
        int y5[]={scale*(c+35),scale*(c+45),scale*(c+35)};
        polygon lv5=new polygon(g,oX,oY,u);
        lv5.draw(g,x5,y5,3,rad);
        lv5.fill4this(scale*(a+6),scale*(c+40),lgreen,rad);
        
        
    }
}

class point
{
    public void drawpoint(Graphics g,int originX,int originY,int unit,int x2,int y2, double rad)
    {
        int x, y;
        double x1, y1;
        
        x1=(double)(x2);
        y1=(double)(y2);
        
        x=(int)(x1*Math.cos(rad)+y1*Math.sin(rad));
        y=(int)(y1*Math.cos(rad)-x1*Math.sin(rad));
        x = originX+(x*unit)-(unit/2);
        y = originY-(y*unit)-(unit/2);
        int h,w;
        h = unit*2;
        w = unit*2;
        //g.fillOval(x, y, w, h);
        g.fillRect(x, y, w, h);

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
    public void draw(Graphics g,int[] x,int[] y,int len,double rad)
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
                drawlineMidPoint(x[i],y[i],x[i+1],y[i+1],rad);
            }
            else
            {
                drawlineMidPoint(x[i],y[i],x[0],y[0],rad);
            }
        }
    }
    void drawlineMidPoint(int x1, int y1, int x2, int y2, double rad)
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
            p1.drawpoint(gp,oX,oY,u,x1,y1,rad);
            point_x.add(x1);
            point_y.add(y1);
            p1.drawpoint(gp,oX,oY,u,x2,y2,rad);
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
                        p1.drawpoint(gp,oX,oY,u,xe,ye,rad);
                        point_x.add(xe);
                        point_y.add(ye);
                        p -= Math.abs(m);
                    }
                    else{
                        ye += iy;
                        p1.drawpoint(gp,oX,oY,u,xe,ye,rad);
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
                        p1.drawpoint(gp,oX,oY,u,xe,ye,rad);
                        point_x.add(xe);
                        point_y.add(ye);
                        p += 1-Math.abs(m);
                    }
                    else{
                        
                        p1.drawpoint(gp,oX,oY,u,xe,ye,rad);
                        point_x.add(xe);
                        point_y.add(ye);
                        p += 1;
                    }
                }
            }
    }
    void fill4this(int x,int y,Color c,double rad)
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
        pf.drawpoint(gp,oX,oY,u,x,y,rad);
        fill4this(x-1,y,c,rad);
        fill4this(x+1,y,c,rad);
        fill4this(x,y+1,c,rad);
        fill4this(x,y-1,c,rad);
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
    public void draw_circle(int x,int y,int r,double rad)
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
        p1.drawpoint(gp,originX,originY,unit,x,y+r,rad);
        p1.drawpoint(gp,originX,originY,unit,x+r,y,rad);
        p1.drawpoint(gp,originX,originY,unit,x,y-r,rad);
        p1.drawpoint(gp,originX,originY,unit,x-r,y,rad);
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
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1,rad);
            point p4=new point();
            p4.drawpoint(gp,originX,originY,unit,x+x1,y-y1,rad);
            point p5=new point();
            p5.drawpoint(gp,originX,originY,unit,x-x1,y-y1,rad);
            point p6=new point();
            p6.drawpoint(gp,originX,originY,unit,x-x1,y+y1,rad);
            point p7=new point();
            p7.drawpoint(gp,originX,originY,unit,x+y1,y+x1,rad);
            point p8=new point();
            p8.drawpoint(gp,originX,originY,unit,x+y1,y-x1,rad);
            point p9=new point();
            p9.drawpoint(gp,originX,originY,unit,x-y1,y-x1,rad);
            point p10=new point();
            p10.drawpoint(gp,originX,originY,unit,x-y1,y+x1,rad);
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
    
    void fill4this(int x,int y,Color c,double rad)
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
        pf.drawpoint(gp,originX,originY,unit,x,y,rad);
        fill4this(x-1,y,c,rad);
        fill4this(x+1,y,c,rad);
        fill4this(x,y+1,c,rad);
        fill4this(x,y-1,c,rad);
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
    void draw_ellipse(int x,int y,int a,int b, double rad)
    {
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        do
        {
            point p1=new point();
            p1.drawpoint(gp,originX,originY,unit,x+x1,y+y1,rad);
            p1.drawpoint(gp,originX,originY,unit,x+x1,y-y1,rad);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y+y1,rad);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y-y1,rad);
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
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1,rad);
            p2.drawpoint(gp,originX,originY,unit,x+x1,y-y1,rad);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y+y1,rad);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y-y1,rad);
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
    void fill4this(int x,int y,double rad)
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
        pf.drawpoint(gp,originX,originY,unit,x,y,rad);
        fill4this(x-1,y,rad);
        fill4this(x+1,y,rad);
        fill4this(x,y+1,rad);
        fill4this(x,y-1,rad);
    }
    public void fill_ellipse(int x,int y,int a,int b,double rad)
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
                    p12.drawpoint(gp,originX,originY,unit,x+i,y+j,rad);
                    p12.drawpoint(gp,originX,originY,unit,x+i,y-j,rad);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y+j,rad);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y-j,rad);
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
    void draw_semiellipse(int x,int y,int a,int b,double rad)
    {
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        do
        {
            point p1=new point();
            p1.drawpoint(gp,originX,originY,unit,x+x1,y+y1,rad);
            //p1.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y+y1,rad);
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
            p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1,rad);
            //p2.drawpoint(gp,originX,originY,unit,x+x1,y-y1);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y+y1,rad);
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
    void fill4this(int x,int y,double rad)
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
        pf.drawpoint(gp,originX,originY,unit,x,y,rad);
        fill4this(x-1,y,rad);
        fill4this(x+1,y,rad);
        fill4this(x,y+1,rad);
        //fill4this(x,y-1);
    }
    public void fill_ellipse(int x,int y,int a,int b,double rad)
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
                    p12.drawpoint(gp,originX,originY,unit,x+i,y+j,rad);
                    //p12.drawpoint(gp,originX,originY,unit,x+i,y-j);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y+j,rad);
                    //p12.drawpoint(gp,originX,originY,unit,x-i,y-j);
                }
            }
        }
    }
}

class semiellipserev{
    public char[][] points;
    public Vector<Integer> point_x=new Vector<>();
    public Vector<Integer> point_y=new Vector<>();
    public int rangeX,rangeY;
    public int Xmin,Ymin;
    Graphics gp;
    int originX;
    int originY;
    int unit;
    public semiellipserev(Graphics g,int oX,int oY,int u)
    {
        gp=g;
        originX=oX;
        originY=oY;
        unit=u;
    }
    void draw_semiellipserev(int x,int y,int a,int b,double rad)
    {
        gp.setColor(Color.black);
        double p;
        p=(b*b)-(a*a*b)+((1/4)*a*a);
        int x1=0;
        int y1=b;
        do
        {
            point p1=new point();
            //p1.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            p1.drawpoint(gp,originX,originY,unit,x+x1,y-y1,rad);
            //p1.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            p1.drawpoint(gp,originX,originY,unit,x-x1,y-y1,rad);
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
            //p2.drawpoint(gp,originX,originY,unit,x+x1,y+y1);
            p2.drawpoint(gp,originX,originY,unit,x+x1,y-y1,rad);
            //p2.drawpoint(gp,originX,originY,unit,x-x1,y+y1);
            p2.drawpoint(gp,originX,originY,unit,x-x1,y-y1,rad);
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
    void fill4this(int x,int y,double rad)
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
        pf.drawpoint(gp,originX,originY,unit,x,y,rad);
        fill4this(x-1,y,rad);
        fill4this(x+1,y,rad);
        //fill4this(x,y+1,rad);
        fill4this(x,y-1,rad);
    }
    public void fill_ellipse(int x,int y,int a,int b,double rad)
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
                    //p12.drawpoint(gp,originX,originY,unit,x+i,y+j);
                    p12.drawpoint(gp,originX,originY,unit,x+i,y-j,rad);
                    //p12.drawpoint(gp,originX,originY,unit,x-i,y+j);
                    p12.drawpoint(gp,originX,originY,unit,x-i,y-j,rad);
                }
            }
        }
    }
}
