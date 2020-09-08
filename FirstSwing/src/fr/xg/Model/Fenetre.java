package fr.xg.Model;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Fenetre
{
	protected static Component frame;

	public static void Fenetre() {
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
		frame.setSize(400, 300);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		



		/*Contenu de la fenêtre avec deux zones
		 * zone gauche :: zone_outil (checkbox & radio boutons)
		 * zone droite :: zone_dessin
		 * */
		JPanel zone_outil= new JPanel();
	    JPanel zone_dessin = new JPanel();
	    Container container = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    zone_outil.setBackground(Color.ORANGE);
	    zone_dessin.setBackground(Color.DARK_GRAY);
	    container.add(zone_outil);
	    container.add(zone_dessin);
	    frame.getContentPane().add(container);
	    
	    
	    //OBLIGATOIRE de mettre à la fin pour afficher le rendu
	    frame.setVisible(true);
		


	}
}
