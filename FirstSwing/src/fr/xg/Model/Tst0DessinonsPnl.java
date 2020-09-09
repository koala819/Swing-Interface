package fr.xg.Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;



class Tst0DessinonsPnl extends JPanel
{
	private static final double pi = 3.141592654;
	
	Color couleur1 = new Color(123, 43, 90);
	
	public Tst0DessinonsPnl()
	{
		super();
		toto();
	}
	
	public static void toto()
	{
		System.out.println("Je suis dans toto!");

		System.out.println("PI:" + pi);
	}
	
	@Override
	public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		
        g.setColor (couleur1);
        g.fillRect (100, 100, 100, 100);
        
        Graphics2D graf2D = (Graphics2D)g;
        
        Rectangle2D.Float rect = new Rectangle2D.Float(100, 10, 200, 50);
       
        graf2D.setColor(Color.RED);
        graf2D.fill(rect);
        
        graf2D.setStroke(new BasicStroke(4));
        graf2D.setColor(Color.BLUE);
        graf2D.draw(rect);

        rect = new Rectangle2D.Float(150, 10, 200, 50);
        graf2D.setStroke(new BasicStroke(8));
        graf2D.setColor(Color.ORANGE);
        graf2D.draw(rect);
     }
}
