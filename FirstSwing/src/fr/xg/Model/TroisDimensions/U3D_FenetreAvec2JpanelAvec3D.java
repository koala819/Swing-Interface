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
import java.io.File;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.vecmath.Point3d;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class U3D_FenetreAvec2JpanelAvec3D
{
	private BranchGroup scene = null;
	private SimpleUniverse universe = null;
	
	public U3D_FenetreAvec2JpanelAvec3D()
	{
		/*
		 * DEFINITION JFRAME 
		 */
		JFrame frame = new JFrame();
		frame.setTitle("Animations 3D");
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
		jPannelZoneGauche.setLayout(new GridBagLayout()); //On applique au JPanel (de gauche) une grille de positionnement pour placer les éléments comme on le souhaite
		jPannelDiviseEnDeux.add(jPannelZoneGauche);

		GraphicsConfigTemplate3D configurationGraphiqueDeLaTemplate3D = new GraphicsConfigTemplate3D();
		JCanvas3D jCanvas3D = new JCanvas3D(configurationGraphiqueDeLaTemplate3D);
		jPannelDiviseEnDeux.add(jCanvas3D);
		jCanvas3D.setSize(2,2); // taille non prise en compte, mais utile pour la définition de l'objet SimpleUniverse
		SimpleUniverse universe = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());
		universe.getViewingPlatform().setNominalViewingTransform(); // recule un peu la ViewPlatform pour que les objets de la scène puissent être vus
		


		/*
		 * BOUTONS
		 * permet l'appel de formes 3D 
		 */
		JButton boutonNumberOne = new JButton();
		GridBagConstraints indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT = new GridBagConstraints();
		boutonNumberOne.setText("Ne faire que tourner le texte");
		boutonNumberOne.setToolTipText("Affiche le texte JCanvas");
		jPannelZoneGauche.add(boutonNumberOne, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
					scene.detach();
				}

				scene = U3D_Forme3D_Texte3D.createSceneGraph(jCanvas3D, true, true);
				universe.addBranchGraph(scene);				
			}
		});

		JButton boutonNumberTwo = new JButton();
		boutonNumberTwo.setText("Cube qui tourne tout le temps");
		boutonNumberTwo.setToolTipText("Affiche un joli cube qui tourne tout le temps");
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;	//permet de mettre le bouton en-dessous
		jPannelZoneGauche.add(boutonNumberTwo, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
					scene.detach();
				}
				scene = U3D_Forme3D_CUBE.createSceneGraph();
				universe.addBranchGraph(scene);
			}
		});		

		JButton boutonNumberThree = new JButton();
		boutonNumberThree.setText("Ouvrir Fichier WRL");
		boutonNumberThree.setToolTipText("charge un fichier vrml pour l'afficher dans le Jpannel de droite");
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;	//permet de mettre le bouton en-dessous
		jPannelZoneGauche.add(boutonNumberThree, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser select=new JFileChooser("");

				int result= select.showOpenDialog(frame);
				if(result==JFileChooser.APPROVE_OPTION)
				{
					File file=select.getSelectedFile();
					String fichierSelectionneVRMLaOuvrir = file.toString();
					System.out.println("fichier sélectionné :: " + fichierSelectionneVRMLaOuvrir);
					if (scene != null)
					{
						scene.detach();
					}
					scene = U3D_ChargerVRML.ouvreVRML(jCanvas3D, fichierSelectionneVRMLaOuvrir, universe);
				}

			}
		});
		
		JButton boutonNumberFour = new JButton();
		boutonNumberFour.setText("Texte tourne + zoom + panoramique");
		boutonNumberFour.setToolTipText("gestion du texte à la souris");
		jPannelZoneGauche.add(boutonNumberFour, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
					scene.detach();
				}

				scene = U3D_Forme3D_MonMouseTEXT.createSceneGraph(jCanvas3D);
				universe.addBranchGraph(scene);				
			}
		});
		
		JButton boutonNumberFive = new JButton();
		boutonNumberFive.setText("Trois cubes qui bougent chacun avec la souris");
		boutonNumberFive.setToolTipText("des petits cubes toujours des petits cubes");
		jPannelZoneGauche.add(boutonNumberFive, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
					scene.detach();
				}

				scene = U3D_Forme3D_MonMouseCUBE3D.createSceneGraph(jCanvas3D);
				universe.addBranchGraph(scene);				
			}
		});
		
		JButton boutonNumberSix = new JButton();
		boutonNumberSix.setText("Changer le point de vue de trois cubes");
		boutonNumberSix.setToolTipText("Un cube un jour des cubes toujours");
		jPannelZoneGauche.add(boutonNumberSix, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
					scene.detach();
				}

				scene = U3D_Forme3D_MonMouseCUBE3D_PointOfView.createSceneGraph(jCanvas3D);
				
////				universe.addBranchGraph(scene);
//				//new
//				Transform3D t3d = new Transform3D();
//				/*lookAt (eye, center, up)
//				 * eye :  	l'emplacement de l'œil
//				 * center : un point du monde virtuel où l'œil regarde
//				 * up : 	un vecteur haut spécifiant la direction ascendante du tronc
//				 */
//				t3d.lookAt(new Point3d(2.0, 3.0, 1.0), new Point3d(0.0, 0.0, 0.0), new Vector3d(0.0, 1.0, 0.0));
//				t3d.invert();
//				universe.getViewingPlatform().getViewPlatformTransform().setTransform(t3d);
			    
				BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
				
				OrbitBehavior orbit = new OrbitBehavior();
			    orbit.setSchedulingBounds(bounds);

			    ViewingPlatform vp = universe.getViewingPlatform();
			    vp.setViewPlatformBehavior(orbit);
				
				universe.addBranchGraph(scene);
				
			}
		});

		//OBLIGATOIRE de mettre à la fin pour afficher le rendu
		frame.setVisible(true);        
	}
}
/*TODO : pour comprendre les éléments de la scène
 * fonction à écrire pour sortir les chlidren, 
 * qu'est-ce qu'une scène
 * de quoi est composé un arbre en 3D
 */
//        traceScene(scene);


