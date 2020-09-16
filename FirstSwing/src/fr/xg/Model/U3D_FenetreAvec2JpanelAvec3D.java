/*
 * Explications des fonctions et aide pour utiliser swing
 * trouv�es sur ces sites :
 * https://www.jmdoudoux.fr/java/dej/chap-awt-composants.htm
 * https://bbclone.developpez.com/fr/java/tutoriels/uiswing/gridbaglayout/?page=page_2
 */

package fr.xg.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.ToolTipManager;

import com.sun.j3d.utils.universe.SimpleUniverse;

import fr.xg.J3D.jcanvas3d.JInternalWorld;
import fr.xg.J3D.Cube;
import fr.xg.J3D.Engrenages.MAINGearBox;
import fr.xg.J3D.cg_shader.ObjLoadCg;

public class U3D_FenetreAvec2JpanelAvec3D  {
	//protected static Component frame;
	protected JFrame frame = new JFrame();

	public U3D_FenetreAvec2JpanelAvec3D()
	{
		initComponents();
//        Toolkit kezako = null;		//Le toolkit contient des classes d�crivant les composants graphiques, les polices, les couleurs et les images
//        kezako.getDefaultToolkit();
//        kezako.setDynamicLayout(true);
        
	}
	private void initComponents() {
		GridBagLayout grilleDePositionnement = new GridBagLayout();
		GridBagConstraints indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT = new GridBagConstraints();
		
		/*
		 * DEFINITION JFRAME 
		 */
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
		jPannelZoneGauche.setLayout(grilleDePositionnement); //On applique au JPanel (de gauche) une grille de positionnement afin de pouvoir placer les �l�ments comme on le souhaite
		jPannelDiviseEnDeux.add(jPannelZoneGauche);
		
		/*
		 * ZONE DROITE
		 * ancien code qui affiche un rectangle
		 * TODO � changer pour y inclure la 3D 
		 */
//		U3D_DessineMoiUnRectangle zone_dessin = new U3D_DessineMoiUnRectangle();
//		jPannelDiviseEnDeux.add(zone_dessin);
		JDesktopPane ApplicationMultiDocuments = new JDesktopPane();
		ApplicationMultiDocuments.setBackground(new Color(48, 48, 48));
		ApplicationMultiDocuments.setPreferredSize(new Dimension(300, 300));
		jPannelDiviseEnDeux.setRightComponent(ApplicationMultiDocuments);
		
		/*
		 * BOUTONS
		 * permet l'appel de formes 3D 
		 */
		JButton boutonNumberOne = new JButton();
		boutonNumberOne.setText("Afficher texte qui Tourne");
		boutonNumberOne.setToolTipText("Affiche le texte JCanvas");
		boutonNumberOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalWorld iWorld = new JInternalWorld(false, true, true);
				iWorld.setSize(256, 256);
				iWorld.setLocation(50, 50);
		        iWorld.setResizable(true);
		        ApplicationMultiDocuments.add(iWorld);
		        iWorld.setVisible(true);
			}
		});
		jPannelZoneGauche.add(boutonNumberOne, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		
		JButton boutonNumberTwo = new JButton();
		boutonNumberTwo.setText("Afficher Cube qui tourne");
		boutonNumberTwo.setToolTipText("Affiche un joli cube qui tourne tout le temps");
		boutonNumberTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Quand on cr�e un nouvelle application dans une JFrame, l'�cran scintille quand on la d�place/re-dimensionne,
				 * il se peut aussi que l'�cran devient blanc. Il existe une fa�on simple de corriger le probl�me
				 * Il faut mettre la propri�t� �sun.awt.noerasebackground� � true et d�sactiver le ToolTipManager et le JPopupMenu
				 * --------------------------------------------------------------------------
				 * https://justjava3d.wordpress.com/2017/01/30/java3d-jframe-setup/
				 * http://www.cs.stir.ac.uk/courses/CSC9N6/lectures/3d/3DL4-Example.pdf
				 */
				System.setProperty("sun.awt.noerasebackground", "true");
				JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				ToolTipManager ttm = ToolTipManager.sharedInstance();
				ttm.setLightWeightPopupEnabled(false);
				/*
				 * CREATION CANVAS 3D ET DE L'UNIVERS
				 */
				GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
				Canvas3D canvas = new Canvas3D(config);
				SimpleUniverse univers = new SimpleUniverse(canvas);
				BranchGroup group = new BranchGroup();
				Cube cubeToune = new Cube();
				//canvas.add(cubeToune);
				frame.add("Center", cubeToune);
		        
				
		        
			}
		});
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;	//permet de mettre le bouton en-dessous
		jPannelZoneGauche.add(boutonNumberTwo, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		
		
		
		//OBLIGATOIRE de mettre � la fin pour afficher le rendu
		frame.setVisible(true);
				
	}
}
