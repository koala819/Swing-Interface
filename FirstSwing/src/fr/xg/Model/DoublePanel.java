package fr.xg.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class DoublePanel
{
	protected static Component frame;

	public DoublePanel()
	{
		//Cr�ation barre de menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu);

		//Cr�ation sous-menu
		JMenuItem menuItem = new JMenuItem("Enregistrer sous...");
		menu.add(menuItem);

		//Listener sur le sous-menu
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChoose.FileExplorer();
				FileChoose.Check();
			}
		});

		//Cr�ation de la fen�tre 
		JFrame frame = new JFrame();
		frame.setTitle("Bienvenue");
		frame.setSize(400, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);

		/*Contenu de la fen�tre avec deux zones
		 * zone gauche :: zone_outil (JCheckBox & JRadioButton)
		 * zone droite :: zone_dessin
		 * */
		JPanel zone_outil= new JPanel();
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    frame.getContentPane().add(splitPane);
	    zone_outil.setBackground(Color.ORANGE);
	    zone_outil.setLayout(new GridLayout(3, 1));
	    splitPane.add(zone_outil);
	    
	    //Cr�ation JCheckBox
	    JCheckBox logging = new JCheckBox("Enable logging");
	    JCheckBox sound = new JCheckBox("Increase sound");
	    JCheckBox photo = new JCheckBox("Take photo");
	    Box boxVerticale = Box.createVerticalBox();
	    boxVerticale.add(logging);
	    boxVerticale.add(sound);
	    boxVerticale.add(photo);
	    zone_outil.add(boxVerticale);
	    
	    //Cr�ation JRadioButton
	    JLabel lblMessage = new JLabel("How much coffee do you want ?");
	    JRadioButton j1 = new JRadioButton("One");
	    JRadioButton j2 = new JRadioButton("Two");
	    JRadioButton j3 = new JRadioButton("Three");
	    zone_outil.add(lblMessage);
	    //ButtonGroup :: pour ne s�lectionner qu'un bouton radio
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
	    Tst0DessinonsPnl zone_dessin = new Tst0DessinonsPnl();
	    Tst0DessinonsPnl.toto();
	    
	    splitPane.add(zone_dessin);
//	    zone_dessin.setBackground(Color.DARK_GRAY);
//	    zone_dessin.add(new DrawRect());
//	    zone_dessin.add(frame, new Draw(), 0);
//	    zone_dessin.add(new DrawRect());
	    
	    //OBLIGATOIRE de mettre � la fin pour afficher le rendu
	    frame.setVisible(true);
		


	}

}