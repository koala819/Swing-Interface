import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class U3dMain
{

	
	public U3dMain()
	{
		JFrame frame = new JFrame();
		frame.setTitle("My First Menu");
		frame.setSize(400, 300);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Enregistrer sous...");
		menu.add(menuItem);		

		frame.setJMenuBar(menuBar);
		
		frame.setVisible(true);
		
		Container container = frame.getContentPane();
		
		
//		CardLayout cardL = new CardLayout();
//		JPanel content = new JPanel();
//				//**********************************************
//				//ajout menu
//				menuBar.add(menu);
//				//ajout listener sur le sous menu
//				menu.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						cardL.next(content);
//					}
//				});
//				//ajout de notre sous menu
//				menu.add(menuItem);
//				//ajout du menu bar
//				setJMenuBar(menuBar);
//				
//				//création pan1
//				JPanel pan1 = new JPanel();
//				pan1.setBackground(Color.DARK_GRAY);
//				//définition du Layout
//				content.setLayout(cardL);
//				//ajout panneau pan1
//				content.add(pan1);
//				//ajout des panneaux boutonPane et content à notre fenêtre
//				frame.getContentPane().add(content, BorderLayout.CENTER);
//				this.setVisible(true);*/
	}

	public static void main(String[] args)
	{
		new U3dMain();
	}
}
