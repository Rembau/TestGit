package test2dTO3d;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class Test_2 extends JFrame{
	private static final long serialVersionUID = 1L;
	public Test_2()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D) g;
		Ellipse2D s = new Ellipse2D.Double();
		s.setFrameFromCenter(650.0, 200.0, 600.0, 180.0);
		g2.setColor(Color.RED);
		g2.setPaint(new GradientPaint(new Point2D.Double(600,180),Color.black,
				new Point2D.Double(700,180),Color.white));
		g2.rotate(0.0);
		g2.draw(s);
		g2.fillOval(600, 180, 100, 100);

		g2.rotate(Math.PI/2,600, 180);
		g2.draw(s);
		
		g2.rotate(Math.PI/2,600, 180);
		g2.draw(s);
		
		g2.fillOval(600, 180, 5, 5);
		
	}
	public static void main(String args[])
	{
		new Test_2().repaint();
	}
}
