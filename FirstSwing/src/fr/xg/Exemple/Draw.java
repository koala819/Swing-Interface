package fr.xg.Exemple;

import java.awt.Toolkit;

import javax.swing.JFrame;

import fr.xg.Model.DeuxDimensions.U3D_DessineMoiUnRectangle;

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
