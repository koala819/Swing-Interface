/*
 * Explications des fonctions et aide pour utiliser swing
 * trouvées sur ces sites :
 * https://www.jmdoudoux.fr/java/dej/chap-awt-composants.htm
 * https://bbclone.developpez.com/fr/java/tutoriels/uiswing/gridbaglayout/?page=page_2
 */

package fr.xg.Model.TroisDimensions;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class U3D_FenetreAvec2JpanelAvec3D
{
	private BranchGroup scene = null;
	private SimpleUniverse universe = null;
//	private Transform3D transfo3DInitiale = new Transform3D();
//	private ViewingPlatform viewingPlatform;
//	private TransformGroup viewPlatformTransform;
	private JCanvas3D jCanvas3D = new JCanvas3D(new GraphicsConfigTemplate3D()) {
        {
            this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | 
                              AWTEvent.MOUSE_MOTION_EVENT_MASK |
                              AWTEvent.MOUSE_WHEEL_EVENT_MASK);
        }
    };
	public U3D_FenetreAvec2JpanelAvec3D()
	{
		/* DEFINITION JFRAME */
		JFrame frame = new JFrame();
		frame.setTitle("Animations 3D");
		frame.setSize(600, 400);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* JSplitPane() : fenêtre divisée en deux zones (avec un diviseur dans le sens verticale) */
		JSplitPane jPannelDiviseEnDeux = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jPannelDiviseEnDeux.setDividerLocation(300); //Definit l'emplacement du diviseur
		jPannelDiviseEnDeux.setDividerSize(8); //Défini la taille du diviseur
		jPannelDiviseEnDeux.setContinuousLayout(true); //doit être vraie pour que les composants enfants soient continuellement réaffiche et disposés pendant l'intervention de l'utilisateur.
		jPannelDiviseEnDeux.setOneTouchExpandable(true); //doit être vraie pour que JSplitPane fournisse un widget d'interface utilisateur sur le diviseur afin de développer/réduire rapidement le diviseur.
		frame.getContentPane().add(jPannelDiviseEnDeux);

		/* ZONE GAUCHE : boutons pour appeler une forme 3D */
		JPanel jPannelZoneGauche= new JPanel();
		jPannelZoneGauche.setBackground(new Color(187, 210, 225));
		jPannelZoneGauche.setLayout(new GridBagLayout()); //On applique au JPanel (de gauche) une grille de positionnement pour placer les éléments comme on le souhaite
		jPannelDiviseEnDeux.add(jPannelZoneGauche);

		GraphicsConfigTemplate3D configurationGraphiqueDeLaTemplate3D = new GraphicsConfigTemplate3D();
		jPannelDiviseEnDeux.add(jCanvas3D);
		jCanvas3D.setSize(1,1); // obligatoire de definir une taille au jCanvas
		universe = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());
		universe.getViewingPlatform().setNominalViewingTransform(); // recule un peu la ViewPlatform pour que les objets de la scène puissent être vus

		/* OrbitBehavior : pour definir interaction avec souris 
		 * lié avec déclaration Jcanvas3D au début de la classe
		 * private JCanvas3D jCanvas3D = new JCanvas3D(new GraphicsConfigTemplate3D()) {
		        {
		            this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | 
		                              AWTEvent.MOUSE_MOTION_EVENT_MASK |
		                              AWTEvent.MOUSE_WHEEL_EVENT_MASK);
		        }
		    };
		 */
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
		OrbitBehavior orbit = new OrbitBehavior();
		orbit.setSchedulingBounds(bounds);
		ViewingPlatform vp = universe.getViewingPlatform();
		
		vp.setViewPlatformBehavior(orbit);

		/* BOUTONS : pour appeller les formes 3D */
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
		//		boutonNumberFive.setText("Trois cubes qui bougent chacun avec la souris");
		boutonNumberFive.setText("Reset Vue");
		boutonNumberFive.setToolTipText("des petits cubes toujours des petits cubes");
		jPannelZoneGauche.add(boutonNumberFive, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene != null)
				{
				 	
//					ViewingPlatform vueDeLunivers = universe.getViewingPlatform();
//					Viewer vueDeLunivers2 = universe.getViewer(); 
//					View toto = vueDeLunivers2.getView();
//					System.out.println(toto.getViewPolicy());
//
//					TransformGroup directionTG = vueDeLunivers.getViewPlatformTransform();
//					Transform3D changePointDeVue = new Transform3D();
//					changePointDeVue.lookAt(  new  Point3d(1.0, 10.0, 1.0)		//eye
//											, new  Point3d(0.0, 0.0, 0.0)		//center
//											, new Vector3d(0.0, 0.0, 1.0) );	//up
//					changePointDeVue.invert();
//					directionTG.setTransform(changePointDeVue);
					
//					universe.getViewingPlatform().setNominalViewingTransform();
//					System.out.println("Returns the ViewPlatformBehavior :: " + universe.getViewingPlatform().getViewPlatformTransform());
					
					// On regle la profondeur du champ de vision a 1000 metres (au lieu de 10 metres par defaut)
				    View view = universe.getViewer().getView();
				    view.setBackClipDistance(1000);
				    universe.getViewingPlatform().setNominalViewingTransform();
				}

				//				scene = U3D_Forme3D_MonMouseCUBE3D.createSceneGraph(jCanvas3D);
				//				universe.addBranchGraph(scene);				
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


/*
				BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
				OrbitBehavior orbit = new OrbitBehavior();
				orbit.setSchedulingBounds(bounds);
				ViewingPlatform vp = universe.getViewingPlatform();
				vp.setViewPlatformBehavior(orbit);*/


				universe.addBranchGraph(scene);

			}
		});

		JButton boutonNumberSeven = new JButton();
		boutonNumberSeven.setText("Mon premier arbre");
		boutonNumberSeven.setToolTipText("recupère les objets");
		jPannelZoneGauche.add(boutonNumberSeven, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene == null)
				{
					System.out.println("!!!ERRREUR--scene vide--ERREUR!!!");
					return;
				}
				Enumeration children = scene.getAllChildren();	

				while (children.hasMoreElements())
				{
					Object element = children.nextElement();
					//					System.out.println("\nELEMENT: " + element);
					//					System.out.println(  "CLASSE : " + element.getClass().getSimpleName());
					if (element instanceof TransformGroup) {
						Enumeration children2 = ((TransformGroup) element).getAllChildren();	
						System.out.println("\n--------------------------------------------------------------------");
						System.out.println("DECOUVERTE DES ELEMENTS DE TransformGroup");
						System.out.println("--------------------------------------------------------------------");
						while (children2.hasMoreElements())
						{
							Object element2 = children2.nextElement();
							System.out.println("\nELEMENT: " + element2);
							System.out.println(  "CLASSE : " + element2.getClass().getSimpleName());
							if (element2 instanceof Shape3D) {
								Enumeration children3 = ((Shape3D) element2).getAllGeometries();
								System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
								System.out.println("Enumeration des composants géométriques");
								System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
								while (children3.hasMoreElements())
								{
									Object element3 = children3.nextElement();
									System.out.println("\nELEMENT: " + element3);
									System.out.println(  "CLASSE : " + element3.getClass().getSimpleName());
								}/*FIN while (children3.hasMoreElements())*/
								System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
								System.out.println("FIN DE LA DECOUVERTE DES ELEMENTS DE TransformGroup");
								System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
							}/*FIN if (element2 instanceof Shape3D)*/



						}/*FIN while (children2.hasMoreElements())*/
						System.out.println("--------------------------------------------------------------------");
						System.out.println("FIN DE LA DECOUVERTE DES ELEMENTS DE TransformGroup");
						System.out.println("--------------------------------------------------------------------\n");

					}/*FIN if (element instanceof TransformGroup)*/

					/*
					 * VERIF 2 : Shape3D
					 */
					if (element instanceof Shape3D) {
						Enumeration children3 = ((Shape3D) element).getAllGeometries();
						System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						System.out.println("Enumeration des composants géométriques");
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						while (children3.hasMoreElements())
						{
							Object element3 = children3.nextElement();
							System.out.println("\nELEMENT: " + element3);
							System.out.println(  "CLASSE : " + element3.getClass().getSimpleName());

							/*Recuperation des coordonées des triangle*/
							//							if (element3 instanceof TriangleArray)
							//							{
							//								TriangleArray tableauDeTriangles = (TriangleArray) element3;
							//								int nbPoints = tableauDeTriangles.getVertexCount();
							//
							//								System.out.println("nbPoints :: " + nbPoints);
							//								
							//								for (int i=0; i<nbPoints; i++)
							//								{
							//									double[] coordinates = new double[3];
							//									tableauDeTriangles.getCoordinates(i, coordinates);
							//									
							//
							//									System.out.println("Point " + i + " :: X=" + coordinates[0] + " Y=" + coordinates[1] + " Z=" + coordinates[2]);
							//								}
							//							}/*FIN if (element2 instanceof TriangleArray)*/


						}/*FIN while (children3.hasMoreElements())*/
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						System.out.println("FIN DE Enumeration des composants géométriques");
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
					}/*FIN if (element2 instanceof Shape3D)*/


				}/*FIN while (children.hasMoreElements())*/			
			}/*FIN public void actionPerformed(ActionEvent e)*/
		});/* FIN boutonNumberSeven.addActionListener(new ActionListener()*/

		//OBLIGATOIRE de mettre à la fin pour afficher le rendu
		frame.setVisible(true);        
	}
	
}


