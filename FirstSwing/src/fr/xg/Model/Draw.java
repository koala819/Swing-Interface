package fr.xg.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw {
	JFrame frame = new JFrame();
	
	public Draw ()
	 {
		frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());
		frame.setResizable (false);
		frame.setContentPane (new JPanel()
        {
            public void paint (Graphics g)
            {
                g.setColor (Color.RED);
                g.fillRect (100, 100, 100, 100);
             }
         }
    );
		frame.setVisible (true);
}
	public static void main(String[] args) {
		new Draw();
	}
}
