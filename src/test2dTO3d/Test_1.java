package test2dTO3d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 2.5D 多个圆球。
 * @author Rembau
 *
 */
public class Test_1 extends JFrame
{
	private static final long serialVersionUID = 1L;
	Light light = new Light(300,50);
	LinkedList<Circle> cs = new LinkedList<Circle>();
	ActionEvent_ a = new ActionEvent_();
	Panel_ panel_ = new Panel_();
	Color colors[] = {Color.black,Color.blue,Color.red};
	int mark_color=0;
	public Test_1()
	{
		super("D");	
		Circle c;
		c= new Circle(100,100,150,200);
		c.init();
		cs.add(c);
		c= new Circle(100,100,250,200);
		c.init();
		cs.add(c);
		
		c= new Circle(100,100,350,200);
		c.init();
		cs.add(c);
		
		c= new Circle(100,100,450,200);
		c.init();
		cs.add(c);
		c= new Circle(100,100,550,200);
		c.init();
		cs.add(c);
		
		c= new Circle(100,100,250,200);
		c.init();
		cs.add(c);
		
		ActionEvent_ a = new ActionEvent_();
		panel_.addMouseListener(a);
		panel_.addMouseMotionListener(a);
		
		JMenuBar bar =new JMenuBar();
		JMenu operate = new JMenu("操作");
		bar.add(operate);
		
		JMenuItem ys = new JMenuItem("变色");
		ys.addActionListener(a);
		JMenuItem spin = new JMenuItem("2D旋转");
		spin.addActionListener(a);
		
		JMenuItem spr = new JMenuItem("分离");
		spr.addActionListener(a);
		
		JMenuItem com = new JMenuItem("合并");
		com.addActionListener(a);
		
		JMenuItem big = new JMenuItem("放大");
		big.addActionListener(a);
		
		JMenuItem small = new JMenuItem("缩小");
		small.addActionListener(a);
		
		operate.add(ys);
		operate.add(spin);
		operate.add(spr);
		operate.add(com);
		operate.add(big);
		operate.add(small);
		
		JPanel panel = new JPanel();
		panel.add(new JButton("hello"));
		this.getContentPane().setLayout(new BorderLayout());
		this.add(panel,BorderLayout.NORTH);
		this.add(panel_,BorderLayout.CENTER);
		this.setJMenuBar(bar);
		this.setBounds(100, 100, 800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class Panel_ extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g)
		{	
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			for(Circle c:cs){
				paint_(g,c);
				//System.out.println("1");
			}
			g.setColor(Color.red);
			g.fillOval(light.x, light.y, light.w, light.h);
		}
		public void paint_(Graphics g,Circle c)
		{
			g.setColor(Color.gray);
			c.s.handle();
			g.fillOval(c.s.x, c.s.y, c.s.w, c.s.h);
			g.setColor(colors[mark_color%3]);
			g.fillOval(c.x, c.y, c.w, c.h);
			c.l.init_();
			for(int i=250;i>0;i--)
			{
				int j=i;
				if(mark_color==0)
				{
					g.setColor(new Color(255-j,255-j,255-j));
				} 
				g.fillOval(c.l.x, c.l.y, c.l.w, c.l.h);
				c.l.handle(i);
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
			
			//g.drawLine(c.m.x, c.m.y, c.m.x+10, c.m.y);
		}
	}
	public void spin3()
	{
		for(final Circle c:cs)
			new Thread(){
				public void run()
				{
					c.spin3();
				}
			}.start();
	}
	/**
	 * 2d旋转
	 */
	public void spin()
	{
		for(final Circle c:cs)
			new Thread(){
			public void run()
			{
				c.spin();
			}
		}.start();
	}
	public float f1(float y,float z)
	{
		return z*y*y;
	}
	public static void main(String[] args) {
		long i=System.currentTimeMillis();
		//Test_1 t=
		new Test_1();
		long j=System.currentTimeMillis();
		System.out.println(j-i);
	}
	class Circle
	{
		int w_,h_,centerx_,centery_;
		int x,y,w,h,centerx,centery;
		Shadow s ;
		Light_Shadow l ;
		Mark m;
		public Circle(int w,int h,int x,int y)
		{
			this.w_=w;
			this.h_=h;
			this.centerx_=x;
			this.centery_=y;
		}
		public void init(int w,int h)
		{
			this.w_=w;
			this.h_=h;
			init();
		}
		public void init()
		{
			s= new Shadow();
			l=new Light_Shadow();
			m=new Mark();
			x=centerx_-w_/2;
			y=centery_-h_/2;
			w=w_;
			h=h_;
			centerx=centerx_;
			centery=centery_;
		}
		public void handle1(float p)
		{
			x=(int) (centerx_-w_/2+p);
			w=(int) (w_-2*p);
		}
		
		public void spin3()
		{
			while(true)
			{
				this.m.handle();
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * 2d旋转
		 */
		public void spin()
		{
			float x,y=0,z=1;
			float mark=0.5f;
			while(true)
			{
				y=y%26;
				x=f1(y,z);
				handle1(x);
				repaint();
				if(x>w_/2 || x<-1*(w_/2))
				{
					mark=mark*(-1);
				}
				y+=mark;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		class Shadow
		{
			int w_,h_/*=(int) ((1/9.0f)*c.h_)*/,
			centerx_/*=(int) (c.centerx_+(1/9.0f)*c.centerx_)*/,
			centery_/*=c.centery_+c.h_/2*/;
			int x=centerx_-w_/2,y=centery_-h_/2,w=w_,h=h_;
			public Shadow()
			{
				init();
			}
			public void init()
			{
				w_=(int) (Circle.this.w_*Math.sqrt((Circle.this.centery_-light.y)*(Circle.this.centery_-light.y) + (Circle.this.centerx_-light.x)*(Circle.this.centerx_-light.x))/(Circle.this.centery_-light.y));
				h_=(int) ((1/9.0f)*Circle.this.h_);
				centerx_=(int) ((Circle.this.w_*(Circle.this.centerx_-light.x)/(1.0*(Circle.this.centery_-light.y)))/2.0+Circle.this.centerx_);
				centery_=Circle.this.centery_+Circle.this.h_/2;
			}
			public void handle()
			{
				x=centerx_-w_/2;
				y=centery_-h_/2;
				w=w_;
				h=h_;
			}
		}
		class Light_Shadow
		{
			int w_,
			h_,
			centerx_,
			centery_;
			int x=centerx_-w_/2,y=centery_-h_/2,w=w_,h=h_;
			float w__=w_,h__=h_,l=w_/150;  //用浮点数记录偏移
			public Light_Shadow()
			{
				init();
			}
			public void init()
			{
				w_=Circle.this.w_/2-5;
				h_=Circle.this.h_/2-5;
				centerx_=(int) (Circle.this.centerx_-(Circle.this.w_/4.0)*(Circle.this.centerx_-light.x)/(Math.sqrt((Circle.this.centery_-light.y)*(Circle.this.centery_-light.y) + (Circle.this.centerx_-light.x)*(Circle.this.centerx_-light.x))));
				centery_=(int) (Circle.this.centery_-(Circle.this.h_/4.0)*(Circle.this.centery_-light.y)/(Math.sqrt((Circle.this.centery_-light.y)*(Circle.this.centery_-light.y) + (Circle.this.centerx_-light.x)*(Circle.this.centerx_-light.x))));
				//centerx_=(int) (c.centerx_-(7/45.0f)*c.w_);
				//centery_=(int) (c.centery_-(2/9.0f)*c.h_);
			}
			public void init_()
			{
				w__=w_;
				h__=h_;
			}
			public void handle(int i)
			{
				w__=w__-(w_/250f);
				h__=h__-(w_/250f);
				w=(int) w__;
				h=(int) h__;
				x=centerx_-w/2;
				y=centery_-h/2;
			}
		}
		class Mark
		{
			int x=Circle.this.centerx_-Circle.this.w/2,y=Circle.this.centery_;
			public void handle()
			{
				x+=10;
				x=x%(Circle.this.centerx_+Circle.this.w/2);
				if(x<Circle.this.centerx_-Circle.this.w/2)
				{
					x=Circle.this.centerx_-Circle.this.w/2;
				}
			}
		}
	}
	class Light
	{
		public Light(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		int w_=5,h_=5;
		int x=50,y=50,w=w_,h=h_;
	}
	class ActionEvent_ implements ActionListener,MouseListener,MouseMotionListener
	{
		int current=-1;
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("变色"))
			{
				mark_color++;
				repaint(); 
			} else if(e.getActionCommand().equals("2D旋转"))
			{
				new Thread(){
					public void run()
					{
						spin();
					}
				}.start();
			} else if(e.getActionCommand().equals("分离"))
			{
				for(Circle c:cs)
				{
					c.l.centerx_=c.l.centerx_-50;
					c.s.centerx_=c.s.centerx_+50;
				}
				repaint();
			} else if(e.getActionCommand().equals("合并"))
			{
				for(Circle c:cs)
				{
					c.l.centerx_=c.l.centerx_+50;
					c.s.centerx_=c.s.centerx_-50;
				}
				repaint();
			} else if(e.getActionCommand().equals("放大"))
			{
				for(Circle c:cs)
					c.init(c.w_+40, c.h_+40);
			} else if(e.getActionCommand().equals("缩小"))
			{
				for(Circle c:cs)
					c.init(c.w_-40, c.h_-40);
			}
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
			light = new Light(e.getX(),e.getY());
			for(Circle c:cs){
				c.s.init();
				c.l.init();
			}
			repaint();
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			double x = light.x;
            double y = light.y;
            Rectangle2D r = new Rectangle2D.Double(x, y, light.w_, light.h_);
            if (r.contains(p))
            {
               current = 1;
               return;
            }
		}

		public void mouseReleased(MouseEvent e) {
			current=-1;
		}

		public void mouseDragged(MouseEvent e) {
			if(current == -1) return;
			light = new Light(e.getX(),e.getY());
			for(Circle c:cs){
				c.s.init();
				c.l.init();
			}
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			
		}
	}
}
