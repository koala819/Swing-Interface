package fr.xg.Exemple;

import java.awt.Toolkit;
import fr.xg.Model.U3D_DessineMoiUnRectangle;
import javax.swing.JFrame;

public class Draw
{	
	public Draw ()
	{
		JFrame frame = new JFrame();		
		frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());
		frame.setResizable (false);
		frame.setContentPane (new U3D_DessineMoiUnRectangle());
		frame.setVisible (true);
	}
	
	public static void main(String[] args)
	{
		new Draw();
	}
}
