package fr.xg.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

public class Window
{
	protected static Component frame;

	public static void Window() {
		//Création barre de menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu);

		//Création sous-menu
		JMenuItem menuItem = new JMenuItem("Enregistrer sous...");
		menu.add(menuItem);

		//Listener sur le sous-menu
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChoose.FileExplorer();
				FileChoose.Check();
			}
		});

		//Création de la fenêtre 
		JFrame frame = new JFrame();
		frame.setTitle("Bienvenue");
		frame.setSize(400, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		



		/*Contenu de la fenêtre avec deux zones
		 * zone gauche :: zone_outil (JCheckBox & JRadioButton)
		 * zone droite :: zone_dessin
		 * */
		JPanel zone_outil= new JPanel();
	    JPanel zone_dessin = new JPanel();
	    Container container = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    frame.getContentPane().add(container);
	    zone_outil.setBackground(Color.ORANGE);
	    zone_outil.setLayout(new GridLayout(3, 1));
	    zone_dessin.setBackground(Color.DARK_GRAY);
	    container.add(zone_outil);
	    
	    //Création JCheckBox
	    JCheckBox logging = new JCheckBox("Enable logging");
	    JCheckBox sound = new JCheckBox("Increase sound");
	    JCheckBox photo = new JCheckBox("Take photo");
	    Box boxVerticale = Box.createVerticalBox();
	    boxVerticale.add(logging);
	    boxVerticale.add(sound);
	    boxVerticale.add(photo);
	    zone_outil.add(boxVerticale);
	    
	    //Création JRadioButton
	    JLabel lblMessage = new JLabel("How much coffee do you want ?");
	    JRadioButton j1 = new JRadioButton("One");
	    JRadioButton j2 = new JRadioButton("Two");
	    JRadioButton j3 = new JRadioButton("Three");
	    zone_outil.add(lblMessage);
	    //ButtonGroup :: pour ne sélectionner qu'un bouton radio
	    ButtonGroup G1 = new ButtonGroup();
	    Box boxHorizontal = Box.createHorizontalBox();
	    G1.add(j1);
	    G1.add(j2);
	    G1.add(j3);
	    boxHorizontal.add(j1);
	    boxHorizontal.add(j2);
	    boxHorizontal.add(j3);
	    zone_outil.add(boxHorizontal);
	    
	    //DESSINONS 
	    container.add(zone_dessin);
//	    zone_dessin.add(new DrawRect());
	    zone_dessin.add(zone_dessin, new Draw());
	    
	    //OBLIGATOIRE de mettre à la fin pour afficher le rendu
	    frame.setVisible(true);
		


	}
}
