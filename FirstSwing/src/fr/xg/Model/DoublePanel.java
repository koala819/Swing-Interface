package fr.xg.Model;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.RepaintManager;

public class DoublePanel
{
	protected static Component frame;

	public DoublePanel()
	{
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
				//FileChoose.FileExplorer();
				//FileChoose.Check();
				FileSave fS = new FileSave();
				fS.Explore();
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
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		frame.getContentPane().add(splitPane);
		zone_outil.setBackground(Color.ORANGE);
		zone_outil.setLayout(new GridLayout(3, 1));
		splitPane.add(zone_outil);

		//DESSINONS 
		Tst0DessinonsPnl zone_dessin = new Tst0DessinonsPnl();
		splitPane.add(zone_dessin);

		//Création JCheckBox
		Box boxVerticale = Box.createVerticalBox();
		/* la couleur de fond du dessin à droite devient jaune
		 * En reclickant dessus, la couleur de fond redevient dark_grey.
		 */
		JCheckBox couleurDeFond = new JCheckBox("Couleur de fond");
		boxVerticale.add(couleurDeFond);
		couleurDeFond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (couleurDeFond.isSelected()) {
					zone_dessin.setBackground(Color.YELLOW);
				} else {
					zone_dessin.setBackground(Color.DARK_GRAY);
				}

			}
		});

		/* la couleur de fond du rectangle dessiné à droite devient vert
		 * En reclickant dessus, la couleur de fond redevient orange
		 */
		JCheckBox couleurDuRectangle = new JCheckBox("Couleur du rectangle");
		boxVerticale.add(couleurDuRectangle);
		couleurDuRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (couleurDuRectangle.isSelected()) {
//					Tst0DessinonsPnl toto = new Tst0DessinonsPnl();
//					toto.setCouleur1(Color.GREEN);
					zone_dessin.setCouleur1(Color.GREEN);
					
					
				} else {
					zone_dessin.setCouleur1(Color.ORANGE);
				}

			}
		});
		
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



		//OBLIGATOIRE de mettre à la fin pour afficher le rendu
		frame.setVisible(true);



	}

	public static Component getFrame() {
		return frame;
	}

}
