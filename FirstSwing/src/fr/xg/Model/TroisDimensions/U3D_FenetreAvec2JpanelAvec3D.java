/*
 * Explications des fonctions et aide pour utiliser swing
 * trouv�es sur ces sites :
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

import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.universe.SimpleUniverse;

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
		frame.setTitle("Ma premi�re animation 3D");
		frame.setSize(600, 400);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * JSplitPane()
		 * Fen�tre divis�e en deux zones
		 * avec un diviseur dans le sens verticale
		 */
		JSplitPane jPannelDiviseEnDeux = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jPannelDiviseEnDeux.setDividerLocation(300); //Definit l'emplacement du diviseur
		jPannelDiviseEnDeux.setDividerSize(8); //D�fini la taille du diviseur
		jPannelDiviseEnDeux.setContinuousLayout(true); //doit �tre vraie pour que les composants enfants soient continuellement r�affiche et dispos�s pendant l'intervention de l'utilisateur.
		jPannelDiviseEnDeux.setOneTouchExpandable(true); //doit �tre vraie pour que JSplitPane fournisse un widget d'interface utilisateur sur le diviseur afin de d�velopper/r�duire rapidement le diviseur.
		frame.getContentPane().add(jPannelDiviseEnDeux);

		/*
		 * ZONE GAUCHE
		 * boutons pour appeler une forme 3D
		 */
		JPanel jPannelZoneGauche= new JPanel();
		jPannelZoneGauche.setBackground(new Color(187, 210, 225));
		jPannelZoneGauche.setLayout(new GridBagLayout()); //On applique au JPanel (de gauche) une grille de positionnement pour placer les �l�ments comme on le souhaite
		jPannelDiviseEnDeux.add(jPannelZoneGauche);

		GraphicsConfigTemplate3D configurationGraphiqueDeLaTemplate3D = new GraphicsConfigTemplate3D();
		JCanvas3D jCanvas3D = new JCanvas3D(configurationGraphiqueDeLaTemplate3D);
		jPannelDiviseEnDeux.add(jCanvas3D);
		jCanvas3D.setSize(2,2); // taille non prise en compte, mais utile pour la d�finition de l'objet SimpleUniverse
		SimpleUniverse universe = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());
		universe.getViewingPlatform().setNominalViewingTransform();
		


		/*
		 * BOUTONS
		 * permet l'appel de formes 3D 
		 */
		JButton boutonNumberOne = new JButton();
		GridBagConstraints indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT = new GridBagConstraints();
		boutonNumberOne.setText("Afficher texte qui Tourne");
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
		boutonNumberTwo.setText("Afficher Cube qui tourne");
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
					System.out.println("fichier s�lectionn� :: " + fichierSelectionneVRMLaOuvrir);
					if (scene != null)
					{
						scene.detach();
					}
					scene = U3D_ChargerVRML.ouvreVRML(jCanvas3D, fichierSelectionneVRMLaOuvrir, universe);
				}

			}
		});
		
		JButton boutonNumberFour = new JButton();
		boutonNumberFour.setText("Texte tourne / zoom / panoramique");
		boutonNumberFour.setToolTipText("gestion du texte � la souris");
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

		//OBLIGATOIRE de mettre � la fin pour afficher le rendu
		frame.setVisible(true);        
	}
}
/*TODO : pour comprendre les �l�ments de la sc�ne
 * fonction � �crire pour sortir les chlidren, 
 * qu'est-ce qu'une sc�ne
 * de quoi est compos� un arbre en 3D
 */
//        traceScene(scene);


