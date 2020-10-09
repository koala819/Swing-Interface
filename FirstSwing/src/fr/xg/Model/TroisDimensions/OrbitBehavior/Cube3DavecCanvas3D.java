package fr.xg.Model.TroisDimensions.OrbitBehavior;

import java.awt.BorderLayout;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Cube3DavecCanvas3D {

	public Cube3DavecCanvas3D() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Behavior with Canvas3D");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Etape 3 :
		// Creation du Canvas 3D
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

		frame.add(canvas3D, BorderLayout.CENTER);

		// Etape 4 :
		// Creation d'un objet SimpleUniverse
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

		// Etape 5 :
		// Positionnement du point d'observation pour avoir une vue correcte
		// de la scene 3D
		simpleU.getViewingPlatform().setNominalViewingTransform();

		// Etape 6 :
		// Creation de la scene 3D qui contient tous les objets 3D que l'on
		// veut visualiser
		BranchGroup scene = createSceneGraph();

		// Etape 7 :
		// Compilation de la scene 3D
		scene.compile();

		
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
		OrbitBehavior orbit = new OrbitBehavior();
	    orbit.setSchedulingBounds(bounds);
	    ViewingPlatform vp = simpleU.getViewingPlatform();
	    vp.setViewPlatformBehavior(orbit);
		
		
		
		// Etape 8:
		// Attachement de la scene 3D a l'objet SimpleUniverse
		simpleU.addBranchGraph(scene);
		
		frame.setVisible(true);
	}

	/**
	 * Creation de la scene 3D qui contient tous les objets 3D
	 * @return scene 3D
	 */
	public BranchGroup createSceneGraph() {
		// Creation de l'objet parent qui contiendra tous les autres objets 3D
		BranchGroup parent = new BranchGroup();

		/************ Partie de code concernant l'animation du cube *************/
		/* Elle sera expliquee en details dans les chapitres relatifs aux
    transformations geometriques et aux animations */
		TransformGroup objSpin = new TransformGroup();
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		Alpha rotationAlpha = new Alpha(-1, 4000);
//		RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, objSpin);
//		BoundingSphere bounds = new BoundingSphere();
//		rotator.setSchedulingBounds(bounds);
//		objSpin.addChild(rotator);
		/*************** Fin de la partie relative a l'animation ****************/

		// Construction du cube couleur
		objSpin.addChild(new ColorCube(0.4));
		parent.addChild(objSpin);

		return parent;
	}

	/**
	 * Etape 9 :
	 * Methode main() nous permettant d'utiliser cette classe comme une applet
	 * ou une application.
	 * @param args
	 */
	public static void main(String[] args) {
		Cube3DavecCanvas3D go = new Cube3DavecCanvas3D();
	}
}