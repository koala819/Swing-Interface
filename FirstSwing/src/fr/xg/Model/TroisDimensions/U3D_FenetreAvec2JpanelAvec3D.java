/*
 * Explications des fonctions et aide pour utiliser swing
 * trouvées sur ces sites :
 * https://www.jmdoudoux.fr/java/dej/chap-awt-composants.htm
 * https://bbclone.developpez.com/fr/java/tutoriels/uiswing/gridbaglayout/?page=page_2
 */

package fr.xg.Model.TroisDimensions;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class U3D_FenetreAvec2JpanelAvec3D  {
	//protected static Component frame;
	private JFrame frame = new JFrame();
	private GridBagLayout grilleDePositionnement = new GridBagLayout();
	private GridBagConstraints indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT = new GridBagConstraints();
	public U3D_FenetreAvec2JpanelAvec3D()
	{



		/*
		 * DEFINITION JFRAME 
		 */
		frame.setTitle("Ma première animation 3D");
		frame.setSize(600, 400);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * JSplitPane()
		 * Fenêtre divisée en deux zones
		 * avec un diviseur dans le sens verticale
		 */
		JSplitPane jPannelDiviseEnDeux = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jPannelDiviseEnDeux.setDividerLocation(300); //Definit l'emplacement du diviseur
		jPannelDiviseEnDeux.setDividerSize(8); //Défini la taille du diviseur
		jPannelDiviseEnDeux.setContinuousLayout(true); //doit être vraie pour que les composants enfants soient continuellement réaffiche et disposés pendant l'intervention de l'utilisateur.
		jPannelDiviseEnDeux.setOneTouchExpandable(true); //doit être vraie pour que JSplitPane fournisse un widget d'interface utilisateur sur le diviseur afin de développer/réduire rapidement le diviseur.
		frame.getContentPane().add(jPannelDiviseEnDeux);

		/*
		 * ZONE GAUCHE
		 * boutons pour appeler une forme 3D
		 */
		JPanel jPannelZoneGauche= new JPanel();
		jPannelZoneGauche.setBackground(new Color(187, 210, 225));
		jPannelZoneGauche.setLayout(grilleDePositionnement); //On applique au JPanel (de gauche) une grille de positionnement pour placer les éléments comme on le souhaite
		jPannelDiviseEnDeux.add(jPannelZoneGauche);

		/*
		 * ZONE DROITE
		 * pour afficher la 3D
		 */
		
		GraphicsConfigTemplate3D configurationGraphiqueDeLaTemplate3D = new GraphicsConfigTemplate3D();
		JCanvas3D jCanvas3D = new JCanvas3D(configurationGraphiqueDeLaTemplate3D);
		jPannelDiviseEnDeux.add(jCanvas3D);
		jCanvas3D.setSize(2,2); // taille non prise en compte, mais utile pour la définition de l'objet SimpleUniverse
		SimpleUniverse universe = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());
		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.getViewer().getView().setMinimumFrameCycleTime(30);

		/*TODO : pour comprendre les éléments de la scène
		 * fonction à écrire pour sortir les chlidren, 
		 * qu'est-ce qu'une scène
		 * de quoi est composé un arbre en 3D
		 */
		//        traceScene(scene);



		/*
		 * BOUTONS
		 * permet l'appel de formes 3D 
		 */
		JButton boutonNumberOne = new JButton();
		boutonNumberOne.setText("Afficher texte qui Tourne");
		boutonNumberOne.setToolTipText("Affiche le texte JCanvas");
		jPannelZoneGauche.add(boutonNumberOne, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BranchGroup scene = U3D_Forme3D_Texte3D.createSceneGraph(jCanvas3D, false, true);
				universe.addBranchGraph(scene);				
			}
		});

		JButton boutonNumberTwo = new JButton();
		boutonNumberTwo.setText("Afficher Cube qui tourne");
		boutonNumberTwo.setToolTipText("Affiche un joli cube qui tourne tout le temps");
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;	//permet de mettre le bouton en-dessous
		jPannelZoneGauche.add(boutonNumberTwo, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BranchGroup scene = U3D_Forme3D_CUBE.createSceneGraph();
				universe.addBranchGraph(scene);
			}
		});		
		
		JButton boutonNumberThree = new JButton();
		boutonNumberThree.setText("Effacer la scène");
		boutonNumberThree.setToolTipText("Pour tout enlever !");
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;	//permet de mettre le bouton en-dessous
		jPannelZoneGauche.add(boutonNumberThree, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("je ne sers à rien");
			}
		});
		
		//OBLIGATOIRE de mettre à la fin pour afficher le rendu
		frame.setVisible(true);        
	}
}