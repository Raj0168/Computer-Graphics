import java.applet.Applet; 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;

public class diwali extends Applet implements ActionListener {

	static int f = 10,n=9,mx,my;
	static int flag_shape = 10,clip = 0,unclip = 0;
	static int imageclick = 0,oclick=0,ti=0,bi=0,button = 0,fly = 0,speed=500;
	static int flag_grid_draw = 0,qtr = 0;
	static double frac = 0.69;
	static double scalex = 1,scaley=1,degree=0,scale=1;
	
	static int INSIDE = 0, LEFT = 1, RIGHT = 2, BOTTOM = 4, TOP = 8;
	
	static int cx_max = 20, cy_max = 20, cx_min = -20, cy_min = -20; 
	
	Button button1 = new Button("Zoom IN");
	Button button2 = new Button("Zoom OUT");
	Button button3 = new Button("Sutherland Cohen");
	Button button4 = new Button("Sutherland Hodge");
	
	public void init()
    {
        setSize(800,800);
        add(button1);
        button1.addActionListener(this);
        add(button2);
        button2.addActionListener(this);
        add(button3);
        button3.addActionListener(this);
        add(button4);
        button4.addActionListener(this);
    }
	
	

    class point 
    {
    	void plotpoint(Graphics g,int x1,int y1)
    	{
    		int r = (int)(frac*f);
    		g.fillRect(f*x1-r, f*y1-r, 2*r, 2*r);
    	}
    	void plotpoint(Graphics g,int x1,int y1,double rad)
    	{
    		double x2=x1,y2=y1; 
    		int x = (int)(x2*Math.cos(rad)+y2*Math.sin(rad));
    		int y = (int)(y2*Math.cos(rad)-x2*Math.sin(rad));
    		int r = (int)(frac*f);
    		g.fillRect(f*x-r, f*y-r, 2*r, 2*r);
    		
    		
    	}
    	void plotpoint(Graphics g,int x1,int y1,double rad,int ox1,int oy1)
    	{
    		double x2=x1-ox1,y2=y1-oy1; 
    		int x = (int)(x2*Math.cos(rad)+y2*Math.sin(rad))+ox1;
    		int y = (int)(y2*Math.cos(rad)-x2*Math.sin(rad))+oy1;
    		int r = (int)(frac*f);
    		g.fillRect(f*x-r, f*y-r, 2*r, 2*r);
    		
    		
    	}
    	void fillpoint(Graphics g,int x1,int y1)
    	{
    		int r = (int)(frac*f);
    		g.fillRect(f*x1-r, f*y1-r, 2*r, 2*r);
    	}
    	void fillpoint(Graphics g,int x1,int y1,double rad)
    	{
    		double x2=x1,y2=y1; 
    		int x = (int)(x2*Math.cos(rad)+y2*Math.sin(rad));
    		int y = (int)(y2*Math.cos(rad)-x2*Math.sin(rad));
    		int r = (int)(frac*f);
    		g.fillRect(f*x-r, f*y-r, 2*r, 2*r);
    	}
    	void fillpoint(Graphics g,int x1,int y1,double rad,int ox1,int oy1)
    	{
    		double x2=x1-ox1,y2=y1-oy1; 
    		int x = (int)(x2*Math.cos(rad)+y2*Math.sin(rad))+ox1;
    		int y = (int)(y2*Math.cos(rad)-x2*Math.sin(rad))+oy1;
    		int r = (int)(frac*f);
    		g.fillRect(f*x-r, f*y-r, 2*r, 2*r);
    	}
    }
    
    class line extends point{
    	
    	public int x1,y1,x2,y2,size;
    	public Vector<Integer> p_x = new Vector<>();
    	public Vector<Integer> p_y = new Vector<>();
    	
    	line(int sx, int sy, int ex, int ey)
    	{
    		x1=sx;y1=sy;x2=ex;y2=ey;
    		getpoints();
    		size = p_x.size();
    	}
    	
    	line(){}
    	
    	public void getpoints()
    	{
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
			
    		if(Math.abs(m)<1)
    		{
    			double p = 0.5 - Math.abs(m);
    			while(xe != x2)
    			{	
    				xe += ix;
    				if(p>=0)
    				{
    					p_x.add(xe);p_y.add(ye);
    					p -= Math.abs(m);
    				}
    				else{
    					ye += iy;
    					p_x.add(xe);p_y.add(ye);
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
    					p_x.add(xe);p_y.add(ye);
    					p += 1-Math.abs(m);
    				}
    				else {
    					
    					p_x.add(xe);p_y.add(ye);
    					p += 1;
    				}
    			}
    		}
    	}
    	
    	public void plot(Graphics g,Color C)
    	{
    		plotpoint(g,x1,y1);
    		plotpoint(g,x2,y2);
    		int a,b;
    		for(int i=0;i<size;i++)
    		{
    			a=p_x.get(i);b=p_y.get(i);
    			plotpoint(g,a,b);
    		}
    	}
    	
    	void plot_line_parallel_to_axis(Graphics g2d,int x1,int y1,int x2,int y2)
    	{
    		for(int i=x1;i<=x2;i++)
    		{
    			for(int j=y1;j<=y2;j++)
    			{
    				plotpoint(g2d,i,j);
    			}
    		}
    	}
    	
    	void drawlineDDA(Graphics g2d,int x1, int y1, int x2, int y2)
    	{
    		int dx=(x2-x1),dy=(y2-y1), steps;
    		if(Math.abs(dx)>=Math.abs(dy))
    		{
    			steps=Math.abs(dx);
    			double ix = (double)(dx)/(double)(steps);
    			double iy = (double)(dy)/(double)(steps);
    			double x=x1,y=y1;
    			double xa = Math.floor(x), ya = Math.floor(y);
    			while(xa != x2)
    			{
    				plotpoint(g2d,(int)xa,(int)ya);
    				x+=ix;
    				y+=iy;
    				xa = Math.floor(x); ya = Math.floor(y);
    				System.out.println(x+" "+y);
    				System.out.println(xa+" "+ya);
    			}
    			plotpoint(g2d,(int)xa,(int)ya);
    			plotpoint(g2d,x2,y2);
    		}
    		else
    		{
    			steps = Math.abs(dy);
    			double ix = (double)(dx)/(double)(steps);
    			double iy = (double)(dy)/(double)(steps);
    			double x=x1,y=y1;
    			double xa = Math.floor(x), ya = Math.floor(y);
    			while(ya != y2)
    			{
    				plotpoint(g2d,(int)xa,(int)ya);
    				x+=ix;
    				y+=iy;
    				xa = Math.floor(x); ya = Math.floor(y);
    				System.out.println(x+" "+y);
    				System.out.println(xa+" "+ya);
    			}
    			plotpoint(g2d,(int)xa,(int)ya);
    			plotpoint(g2d,x2,y2);
    		}
    	}
    	
    	void drawlineBresenham(Graphics g2d,int x1,int y1, int x2,int y2)
    	{
    		double dx=(x2-x1),dy=(y2-y1);
    		double m = (double)(dy)/(double)(dx);
    		int x=x1,y=y1;
    		if(Math.abs(m)<1)
    		{
    			double p = 2*Math.abs(dy) - Math.abs(dx);
    			while(x!=x2)
    			{
    				plotpoint(g2d,x,y);
    				if(p<0)
    				{
    					if(dx>0)
    					{
    						x++;
    					}
    					if(dx<0)
    					{
    						x--;
    					}
    					p += 2*Math.abs(dy);
    				}
    				else
    				{
    					if(dx>0)
    					{
    						x++;
    					}
    					if(dx<0)
    					{
    						x--;
    					}
    					if(dy>0)
    					{
    						y++;
    					}
    					if(dy<0)
    					{
    						y--;
    					}
    					p += 2*Math.abs(dy) - 2*Math.abs(dx);
    				}
    			}
    			plotpoint(g2d,x,y);
    			plotpoint(g2d,x2,y2);
    		}
    		else 
    		{
    			double p = 2*Math.abs(dx) - Math.abs(dy);
    			while(y!=y2)
    			{
    				System.out.println((int)Math.round(x)+" "+(int)Math.round(y));
    				plotpoint(g2d,(int)Math.round(x),(int)Math.round(y));
    				if(p<0)
    				{
    					if(dy>0)
    					{
    						y++;
    					}
    					if(dy<0)
    					{
    						y--;
    					}
    					p += 2*Math.abs(dx);
    				}
    				else
    				{
    					if(dx>0)
    					{
    						x++;
    					}
    					if(dx<0)
    					{
    						x--;
    					}
    					if(dy>0)
    					{
    						y++;
    					}
    					if(dy<0)
    					{
    						y--;
    					}
    					p += 2*Math.abs(dx) - 2*Math.abs(dy);
    				}
    			}
    			plotpoint(g2d,(int)Math.round(x),(int)Math.round(y));
    			plotpoint(g2d,x2,y2);
    		}
    	}
    	
    	void drawlineMidPoint(Graphics g2d,int x1, int y1, int x2, int y2)
    	{
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
    		plotpoint(g2d,x1,y1);
    		plotpoint(g2d,x2,y2);
    		if(Math.abs(m)<1)
    		{
    			double p = 0.5 - Math.abs(m);
    			while(xe != x2)
    			{	
    				xe += ix;
    				if(p>0)
    				{
    					plotpoint(g2d,xe,ye);
    					p -= Math.abs(m);
    				}
    				else{
    					ye += iy;
    					plotpoint(g2d,xe,ye);
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
    					plotpoint(g2d,xe,ye);
    					p += 1-Math.abs(m);
    				}
    				else{
    					
    					plotpoint(g2d,xe,ye);
    					p += 1;
    				}
    			}
    		}
    	}
    }
    
    class polygon extends line
    {
    	public int vert_x[];
    	public int vert_y[];
    	public int size;
    	public int y_min,y_max,x_min,x_max,del_x,del_y;
    	public int grid[][];
    	public line L[];
    	
    	polygon(){}
    	
    	polygon(int arrx[], int arry[])
    	{
    		vert_x = arrx.clone();
    		vert_y = arry.clone();
    		size = arrx.length;
    		
    		L = new line[size];
    		
    		y_min = arry[0];x_min = arrx[0];
    		y_max = arry[0];x_max = arrx[0];

    		for(int i=0;i<size;i++)
    		{
    			if(y_min>arry[i])
    			{
    				y_min = arry[i];
    			}
    			if(y_max<arry[i])
    			{
    				y_max = arry[i];
    			}
    			if(x_min>arrx[i])
    			{
    				x_min = arrx[i];
    			}
    			if(x_max<arrx[i])
    			{
    				x_max = arrx[i];
    			}
    		}
    		del_x = 1+x_max-x_min;
    		del_y = 1+y_max-y_min;
    		
    		grid = new int[del_x][del_y];
    		
    		for(int i=0;i<size;i++)
    		{
    			L[i] = new line(vert_x[i%size],vert_y[i%size],vert_x[(i+1)%size],vert_y[(i+1)%size]);
    			
    			line l=L[i];
    			
    			if((vert_y[(i+1)%size]<=vert_y[i] && vert_y[(i-1+size)%size]<=vert_y[i]) || (vert_y[(i+1)%size]>=vert_y[i] && vert_y[(i-1+size)%size]>=vert_y[i]))
    			{
    				grid[arrx[i]-x_min][arry[i]-y_min]=2;
    			}
    			else
    			{
    				grid[vert_x[i]-x_min][vert_y[i]-y_min]=1;
    			}
    			
    			for(int j=0;j<l.size;j++)
    			{
    				if(grid[l.p_x.get(j)-x_min][l.p_y.get(j)-y_min] != 2)
    				grid[l.p_x.get(j)-x_min][l.p_y.get(j)-y_min] = 1;
    			}
    		}
    		gridfill();
    	}
    	
    	public void draw(Graphics g,Color C)
    	{
    		g.setColor(C);
    		for(int i=0;i<size;i++)
    		{	
    			L[i].plot(g, C);
    		}
    	}
    	public void gridfill()
    	{
    		for(int i=0;i<del_y;i++)
    		{
    			int Lseek = 0,Rseek = 0; 
    			for(int j=0;j<del_x;j++)
    			{
    				if(grid[j][i]==1 && grid[(j+1)%del_x][i]==0)
    				{
    					Lseek = j+1;
    					break;
    				}
    			}
    			for(int j=del_x-2;j>=0;j--)
    			{
    				if(grid[j][i]==0 && grid[(j+1)%del_x][i]==1)
    				{
    					Rseek = j;
    					break;
    				}
    			}
    			if(Lseek==0)
    			{
    				Lseek = del_x;
    			}
    			if(Rseek == del_x)
    			{
    				Rseek = 0;
    			}
    			for(int j=Lseek;j<=Rseek;j++)
    			{
    				if(grid[j][i]==0)
    				{
    					grid[j][i]=3;
    				}
    			}
    		}
    	}
    	
    	public void fill(Graphics g,Color C) 
    	{
    		g.setColor(C);
    		for(int i=0;i<del_x;i++)
    			for(int j=0;j<del_y;j++)
    				if(grid[i][j]==3)
    						fillpoint(g,i+x_min,j+y_min);
    	}
    }
    

    class rectangle extends line
    {
    	public int x1,y1,w,h;
    	
    	rectangle(int x, int y, int width, int height)
    	{
    		x1 = x;
    		y1 = y;
    		w = width;
    		h = height;
    	}
    	
    	public void drawoutline(Graphics g,Color C)
    	{
    		g.setColor(C);
    		drawlineMidPoint(g,x1,y1,x1+w,y1);
    		drawlineMidPoint(g,x1+w,y1,x1+w,y1+h);
    		drawlineMidPoint(g,x1+w,y1+h,x1,y1+h);
    		drawlineMidPoint(g,x1,y1+h,x1,y1);
    	}
    	public void fill(Graphics g, Color C)
    	{
    		g.setColor(C);
    		for(int i=x1+1;i<x1+w;i++)
    		{
    			for(int j=y1+1;j<y1+h;j++)
    			{
    				fillpoint(g,i,j);
    			}
    		}
    	}
    }
		
	public int computeCode(double x, double y) 
	{ 
	    
	    int code = INSIDE; 
	  
	    if (x < cx_min) 
	        code |= LEFT; 
	    else if (x > cx_max) 
	        code |= RIGHT; 
	    if (y < cy_min) 
	        code |= BOTTOM; 
	    else if (y > cy_max)
	        code |= TOP; 
	  
	    return code; 
	}
	
	public void cohenSutherlandClip (Graphics g,double x1, double y1, double x2, double y2) 
	{ 
		int code1 = computeCode(x1, y1); 
		int code2 = computeCode(x2, y2);
		double x=x1, y=y1;
 
		boolean accept = false;

		while (true) { 
			if ((code1 == 0) && (code2 == 0)) { 
				 
				accept = true; 
				break; 
			} 
			else if ((code1 & code2)!=0) { 
				
				break; 
			} 
			else { 
				 
				int code_out;
				if (code1 != 0) 
					code_out = code1; 
				else
					code_out = code2; 
 
				if ((code_out & TOP)!=0) { 
					x = x1 + (x2 - x1) * (cy_max - y1) / (y2 - y1); 
					y = cy_max;
				} 
				else if ((code_out & BOTTOM)!=0) {
					x = x1 + (x2 - x1) * (cy_min - y1) / (y2 - y1); 
					y = cy_min; 
				} 
				else if ((code_out & RIGHT)!=0) {
					y = y1 + (y2 - y1) * (cx_max - x1) / (x2 - x1); 
					x = cx_max; 
				} 
				else if ((code_out & LEFT)!=0) { 
					y = y1 + (y2 - y1) * (cx_min - x1) / (x2 - x1); 
					x = cx_min; 
				}
				if (code_out == code1) { 
					x1 = x; 
					y1 = y;
					code1 = computeCode(x1, y1); 
				} 
				else { 
					x2 = x; 
					y2 = y; 
					code2 = computeCode(x2, y2); 
				} 
			} 
		} 
		if (accept) {
			line L = new line();
			L.drawlineMidPoint(g,(int)x1,(int)y1,(int)x2,(int)y2);
		} 
	} 
	
	class csclippedpolygon extends line{
		public int vert_x[];
    	public int vert_y[];
    	public int size;
    	public int y_min,y_max,x_min,x_max,del_x,del_y;
    	public int grid[][];
    	public line L[];
    	
    	csclippedpolygon(){}
    	
    	csclippedpolygon(int arrx[], int arry[])
    	{
    		vert_x = arrx.clone();
    		vert_y = arry.clone();
    		size = arrx.length;
    		
    		L = new line[size];
    		
    		y_min = arry[0];x_min = arrx[0];
    		y_max = arry[0];x_max = arrx[0];

    		for(int i=0;i<size;i++)
    		{
    			if(y_min>arry[i])
    			{
    				y_min = arry[i];
    			}
    			if(y_max<arry[i])
    			{
    				y_max = arry[i];
    			}
    			if(x_min>arrx[i])
    			{
    				x_min = arrx[i];
    			}
    			if(x_max<arrx[i])
    			{
    				x_max = arrx[i];
    			}
    		}
    		del_x = 1+x_max-x_min;
    		del_y = 1+y_max-y_min;
    		
    		grid = new int[del_x][del_y];
    		
    		for(int i=0;i<size;i++)
    		{
    			L[i] = new line(vert_x[i%size],vert_y[i%size],vert_x[(i+1)%size],vert_y[(i+1)%size]);
    			
    			line l=L[i];
    			
    			if((vert_y[(i+1)%size]<=vert_y[i] && vert_y[(i-1+size)%size]<=vert_y[i]) || (vert_y[(i+1)%size]>=vert_y[i] && vert_y[(i-1+size)%size]>=vert_y[i]))
    			{
    				grid[arrx[i]-x_min][arry[i]-y_min]=2;
    			}
    			else
    			{
    				grid[vert_x[i]-x_min][vert_y[i]-y_min]=1;
    			}
    			
    			for(int j=0;j<l.size;j++)
    			{
    				if(grid[l.p_x.get(j)-x_min][l.p_y.get(j)-y_min] != 2)
    				grid[l.p_x.get(j)-x_min][l.p_y.get(j)-y_min] = 1;
    			}
    		}
    		gridfill();
    	}
    	
    	public void draw(Graphics g,Color C)
    	{
    		g.setColor(C);
    		for(int i=0;i<size;i++)
    		{	
    			cohenSutherlandClip(g, L[i].x1,L[i].y1,L[i].x2,L[i].y2);
    		}
    	}
    	public void gridfill()
    	{
    		for(int i=0;i<del_y;i++)
    		{
    			int Lseek = 0,Rseek = 0; 
    			for(int j=0;j<del_x;j++)
    			{
    				if(grid[j][i]==1 && grid[(j+1)%del_x][i]==0)
    				{
    					Lseek = j+1;
    					break;
    				}
    			}
    			for(int j=del_x-2;j>=0;j--)
    			{
    				if(grid[j][i]==0 && grid[(j+1)%del_x][i]==1)
    				{
    					Rseek = j;
    					break;
    				}
    			}
    			if(Lseek==0)
    			{
    				Lseek = del_x;
    			}
    			if(Rseek == del_x)
    			{
    				Rseek = 0;
    			}
    			for(int j=Lseek;j<=Rseek;j++)
    			{
    				if(grid[j][i]==0)
    				{
    					grid[j][i]=3;
    				}
    			}
    		}
    	}
    	
    	public void fill(Graphics g,Color C) 
    	{
    		g.setColor(C);
    		for(int i=0;i<del_x;i++)
    			for(int j=0;j<del_y;j++)
    				if(grid[i][j]==3)
    					if((i+x_min)>=cx_min && (i+x_min)<=cx_max && (j+y_min)>=cy_min && (j+y_min)<=cy_max)
    						fillpoint(g,i+x_min,j+y_min);
    	}
	}
	
	class shclippedpolygon extends polygon{
		
		int vxs[] = new int[20],vys[] = new int[20], poly_size=4;
		
		int x_intersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
		{ 
			int num = (x1*y2 - y1*x2) * (x3-x4) - (x1-x2) * (x3*y4 - y3*x4); 
			int den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4); 
			return num/den; 
		} 
		int y_intersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
		{ 
			int num = (x1*y2 - y1*x2) * (y3-y4) - (y1-y2) * (x3*y4 - y3*x4); 
			int den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4); 
			return num/den; 
		} 
		
		void clip(int poly_pointsx[],int poly_pointsy[], int x1, int y1, int x2, int y2) 
		{ 
			int new_points[][]= new int[20][2]; 
			int new_poly_size = 0;
			for (int i = 0; i<poly_size; i++) 
			{
				int k = (i+1) % poly_size; 
				int ix = poly_pointsx[i], iy = poly_pointsy[i]; 
				int kx = poly_pointsx[k], ky = poly_pointsy[k]; 

				int i_pos = (x2-x1) * (iy-y1) - (y2-y1) * (ix-x1); 

				int k_pos = (x2-x1) * (ky-y1) - (y2-y1) * (kx-x1); 

				if (i_pos <= 0 && k_pos <= 0) 
				{
					new_points[new_poly_size][0] = kx; 
					new_points[new_poly_size][1] = ky; 
					new_poly_size++; 
				}
				else if (i_pos > 0 && k_pos <= 0) 
				{
					int px = x_intersect(x1, y1, x2, y2, ix, iy, kx, ky);
					int py = y_intersect(x1, y1, x2, y2, ix, iy, kx, ky);
					new_points[new_poly_size][0] = px; 
					new_points[new_poly_size][1] = py; 
					new_poly_size++; 
					new_points[new_poly_size][0] = kx; 
					new_points[new_poly_size][1] = ky; 
					new_poly_size++; 
				}
				else if (i_pos <= 0 && k_pos > 0) 
				{ 
					int px = x_intersect(x1, y1, x2, y2, ix, iy, kx, ky);
					int py = y_intersect(x1, y1, x2, y2, ix, iy, kx, ky);
					new_points[new_poly_size][0] = px;
					new_points[new_poly_size][1] = py; 
					new_poly_size++; 
				}
				else
				{} 
			} 

			poly_size = new_poly_size; 
			for (int i = 0; i < poly_size; i++) 
			{ 
				vxs[i] = new_points[i][0]; 
				vys[i] = new_points[i][1]; 
			} 
		} 

		shclippedpolygon(int poly_pointsx[],int poly_pointsy[]) 
		{
			int clipper_points[][]= new int[4][2];
			clipper_points[0][0]=cx_min;clipper_points[0][1]=cy_min;
			clipper_points[1][0]=cx_min;clipper_points[1][1]=cy_max;
			clipper_points[2][0]=cx_max;clipper_points[2][1]=cy_max;
			clipper_points[3][0]=cx_max;clipper_points[3][1]=cy_min;
			
			
			for (int i = 0; i < poly_size; i++) 
			{ 
				vxs[i] = poly_pointsx[i]; 
				vys[i] = poly_pointsy[i]; 
			} 
			
			for (int i=0; i<4; i++) 
			{ 
				int k = (i+1) % 4;
				clip(vxs, vys, clipper_points[i][0], clipper_points[i][1], clipper_points[k][0], clipper_points[k][1]);
			}
		}
		
		public void draw(Graphics g,Color C1,Color C2)
		{
			int vx[] = new int[poly_size];
			int vy[] = new int[poly_size];
			for(int i=0;i<poly_size;i++)
			{
				vx[i] = vxs[i];
				vy[i] = vys[i];
				System.out.println(vxs[i]+" "+vys[i]);
			}
			polygon p = new polygon(vx,vy);
			p.draw(g, C1);
			p.fill(g, C2);
		}
		

		
	}
	
	public void DrawGrid(Graphics2D g2d)
    {
    	if(fly==1)
    	{
    		g2d.setPaint(new Color(0,255,0));
    		g2d.fillRect(-960,-550, 760+960, 1100);
    	}
		
		if(fly==0)
    	{
			g2d.setPaint(new Color(0,255,0));
			int n = (int)(960/(f));
		
			for(int i=-n;i<=n;i++)
			{
				g2d.drawLine(i*f,-n*f,i*f, n*f);
				g2d.drawLine(-n*f,i*f,n*f,i*f);
			}
    	}
		g2d.setPaint(new Color(0,0,0));
		g2d.drawLine(-1000, 0, 1000, 0);
		g2d.drawLine(0, -500, 0, 500);
    }
	
	public void paint(Graphics g)
	{
		
		Font stringFont= new Font("Monospace Regular",1,44);
		g.setFont(stringFont);
		g.setColor(Color.black);
		g.drawString("Sutherland Hodge",600,600);

		setBackground(Color.white);
		int ox,oy;
		ox = (getX()+getWidth())/2;
		oy = (getY()+getHeight())/2;
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(ox, oy);
		g2d.scale(1, -1);
		DrawGrid(g2d);
		rectangle window = new rectangle(cx_min,cy_min,cx_max-cx_min,cy_max-cy_min);
		window.drawoutline(g2d, Color.black);
		g2d.setPaint(Color.orange);

		
		int vx[] = {-22,0,30,0};
		int vy[] = {-7,30,0,-20};
		if(clip == 1)
		{
			if(unclip == 1)
			{
				polygon p3 = new polygon(vx,vy);
				p3.draw(g2d, Color.magenta);
			}
			csclippedpolygon p1 = new csclippedpolygon(vx,vy);
			p1.draw(g2d, Color.red);
			p1.fill(g2d, Color.orange);
		}
		if(clip == 2)
		{
			if(unclip == 1)
			{
				polygon p3 = new polygon(vx,vy);
				p3.draw(g2d, Color.black);
			}
			shclippedpolygon p2 = new shclippedpolygon(vx,vy);
			p2.draw(g2d, Color.yellow, Color.magenta);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			f += 5;
			repaint();
		}
		if (e.getSource() == button2) {
			if(f >= 10){
				f -= 5;
				if(f==5)
					frac = 0.69;
				else
					frac = 0.54;
				repaint();
			}
		}
		
		if (e.getSource() == button3) {
			clip = 1;
			repaint();
		}
		if (e.getSource() == button4) {
			clip = 2;
			repaint();
		}
	}
}
