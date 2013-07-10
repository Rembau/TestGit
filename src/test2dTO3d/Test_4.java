package test2dTO3d;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_4 extends JFrame{
	private static final long serialVersionUID = 1L;
	Panel_ panel = new Panel_();
	public Test_4()
	{
		this.getContentPane().setLayout(new BorderLayout());
		this.add(panel,BorderLayout.NORTH);
		this.setBounds(100, 100, 800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		System.out.println(Math.atan(300/250));
	}
	class Panel_ extends JPanel
	{
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g)
		{
			
		}
	}
}
