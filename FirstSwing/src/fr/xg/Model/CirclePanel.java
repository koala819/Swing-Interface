package fr.xg.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CirclePanel  extends JFrame {
	 public CirclePanel() {
	        setPreferredSize(new Dimension(100, 100));
	        setBackground(Color.white);
	    }//end constructor

	//================================================ instance variables
    CirclePanel drawing = new CirclePanel();               // Note 1
    
    //======================================================= constructor
    CircleMain() {
        //--- Get content pane, set layout, add components
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        
        content.add(drawing, BorderLayout.CENTER);        // Note 2
        
        this.setTitle("Circles");     
        this.pack();       // finalize the layout
    }//end constructor
    
    //============================================================== main
    public static void main(String[] args) {
        JFrame myWindow = new CircleMain();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
    }//end main
}//endclass CircleMain