package fr.xg.Model.TroisDimensions.Tempo;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class ResetTransform3D
{
	private static BranchGroup scene = null;
	static TransformGroup leftTranslationTG;
	static BranchGroup createSceneGraph(JCanvas3D jCanvas3D)
	{
		BranchGroup objBranchGroup = new BranchGroup();

		// Creation d'un, cube de gauche
		Transform3D leftTranslation = new Transform3D();
		leftTranslation.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		leftTranslationTG = new TransformGroup(leftTranslation);
		leftTranslationTG.addChild(new ColorCube(0.1));
		objBranchGroup.addChild(leftTranslationTG);

		// Définit zone d'infuence où au delà de laquelle la rotation n'agit plus
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);

		// Attacher comportement MouseTranslate afin de faire glisser le texte avec le bouton droit de la souris
		leftTranslationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);  // Donne la permission au TransformGroup d'être modifié
		MouseTranslate translate = new MouseTranslate(jCanvas3D, leftTranslationTG);  //clic droit
		MouseRotate mr = new MouseRotate(leftTranslationTG);
		mr.setSchedulingBounds(bounds);
		translate.setSchedulingBounds(bounds);
		objBranchGroup.addChild(translate);
		objBranchGroup.addChild(mr);

		objBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		objBranchGroup.compile();
		return objBranchGroup;
	}
	public static void main(String[] args) {
		
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
		boutonNumberOne.setText("tournez les carrés");
		boutonNumberOne.setToolTipText("tourne tourne");
		jPannelZoneGauche.add(boutonNumberOne, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		boutonNumberOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (scene == null)
				{
					scene = ResetTransform3D.createSceneGraph(jCanvas3D);
					universe.addBranchGraph(scene);
				}
								
			}
		});
		JButton resetButton = new JButton();
		resetButton.setText("Reset");
		resetButton.setToolTipText("1 pour les controler tous");
		indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT.gridx=0;
		jPannelZoneGauche.add(resetButton, indicationsDePositionnementEtDeDimensionAObjetGRIDBAGLAYOUT);
		resetButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.out.println("clic");
		    leftTranslationTG.setTransform( new Transform3D());
		}
		});
		//OBLIGATOIRE de mettre à la fin pour afficher le rendu
				frame.setVisible(true);
	}
}

