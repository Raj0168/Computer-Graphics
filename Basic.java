import java.applet.*;
import java.awt.*;

public class ass extends Applet
{

    public void paint(Graphics g)
    {

    	int x[]={250,450,450,250};
	int y[]={250,250,350,350};
	g.drawPolygon(x,y,4);
	Color col0=new Color(255, 255, 0);
	g.setColor(col0);
	g.fillPolygon(x,y,4);
	
	x=new int[]{250,325,450};
	y=new int[]{250,100,250};
	g.drawPolygon(x,y,3);
	Color col2=new Color(255, 100, 139);
	g.setColor(col2);
	g.fillPolygon(x,y,3);
	
	x=new int[]{0,1500,1500,0};
	y=new int[]{350,350,700,700};
	g.drawPolygon(x,y,4);
	Color col5=new Color(102, 153, 0);
	g.setColor(col5);
	g.fillPolygon(x,y,4);

	
	x=new int[]{325,325,375,375};
	y=new int[]{350,280,280,350};
	g.drawPolygon(x,y,4);
	Color col1=new Color(204, 102, 0);
	g.setColor(col1);
	g.fillPolygon(x,y,4);
	
	x=new int[]{325,650,750,450};
	y=new int[]{100,100,250,350};
	g.drawPolygon(x,y,4);
	Color col3=new Color(128, 0, 0);
	g.setColor(col3);
	g.fillPolygon(x,y,4);
	
	x=new int[]{400,750,750,400};
	y=new int[]{250,250,350,350};
	g.drawPolygon(x,y,4);
	Color col4=new Color(255,150,50);
	g.setColor(col4);
	g.fillPolygon(x,y,4);
	
	x=new int[]{100,145,145,100};
	y=new int[]{350,350,225,225};
	g.drawPolygon(x,y,4);
	Color col6=new Color(153,102,0);
	g.setColor(col6);
	g.fillPolygon(x,y,4);


	g.drawOval(50,115,0,0);
	g.setColor(Color.green);
	g.fillOval(50,115,150,150);
	
	g.setColor(Color.green);
	g.fillOval(40,115,50,50);
	
	
	g.setColor(Color.green);
	g.fillOval(58,115,50,50);

	g.setColor(Color.green);
	g.fillOval(50,125,50,50);

	g.setColor(Color.green);
	g.fillOval(40,90,50,50);
	

	g.setColor(Color.green);
	g.fillOval(30,190,50,50);

	g.setColor(Color.green);
	g.fillOval(20,180,50,50);

	g.setColor(Color.green);
	g.fillOval(64,180,50,50);

	g.setColor(Color.green);
	g.fillOval(180,111,50,50);

	g.setColor(Color.green);
	g.fillOval(175,119,50,50);

	g.setColor(Color.green);
	g.fillOval(121,185,50,50);

	g.setColor(Color.green);
	g.fillOval(180,170,50,50);

	g.setColor(Color.green);
	g.fillOval(138,160,50,50);

	g.setColor(Color.green);
	g.fillOval(169,180,50,50);
	
	

	g.drawOval(1000,100,150,100);
	g.setColor(Color.blue);
	g.fillOval(1000,100,150,100);
	
	
	x=new int[]{1075,1150,1175,1250,1300,1300,1175,1150,1075};
	y=new int[]{100,100,140,148,110,150,160,200,200};
	g.drawPolygon(x,y,9);
	Color col7=new Color(0,0,102);
	g.setColor(col7);
	g.fillPolygon(x,y,9);

	x=new int[]{1090,1090,1090,1100,1110,1120,1120,1120};
	y=new int[]{100,90,90,80,80,90,90,100};
	g.drawPolygon(x,y,8);
	g.setColor(Color.black);
	g.fillPolygon(x,y,8);


	x=new int[]{1105,950,950};
	y=new int[]{80,90,86};
	g.drawPolygon(x,y,3);
	g.setColor(Color.black);
	g.fillPolygon(x,y,3);

	x=new int[]{1105,1250,1250};
	y=new int[]{80,90,84};
	g.drawPolygon(x,y,3);
	g.setColor(Color.black);
	g.fillPolygon(x,y,3);	

	x=new int[]{1000,1000,970};
	y=new int[]{300,330,315};
	g.drawPolygon(x,y,3);
	g.setColor(Color.black);
	g.fillPolygon(x,y,3);
	
	g.drawOval(940,300,50,25);
	g.setColor(Color.black);
	g.fillOval(940,300,50,25);
	
	Font stringFont= new Font("Comic Sans MS",1,32);
	g.setFont(stringFont);
	g.setColor(Color.black);
	g.drawString("not to scale",1000,500);
	
	
    }
}

