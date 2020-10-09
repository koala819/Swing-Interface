package fr.xg.Model.TroisDimensions.OrbitBehavior;

import java.awt.AWTEvent;
import java.awt.BorderLayout;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Point3d;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Cube3DavecJCanvas3D {

	//JCanvas3D jCanvas3D = new JCanvas3D();
	JCanvas3D jCanvas3D = new JCanvas3D(new GraphicsConfigTemplate3D()) {
        {
            this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | 
                              AWTEvent.MOUSE_MOTION_EVENT_MASK |
                              AWTEvent.MOUSE_WHEEL_EVENT_MASK);
        }
    };

	public Cube3DavecJCanvas3D() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Behavior with JCanvas3D");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Etape 3 :
		// Creation du Canvas 3D
		
			jCanvas3D.setSize(2,2);
			frame.add(jCanvas3D, BorderLayout.CENTER);

			// Etape 4 :
			// Creation d'un objet SimpleUniverse
			SimpleUniverse simpleU = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());

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
			//		//Active le behaviour au clic souris
			//		MouseRotate behaviour = new MouseRotate(jCanvas3D, objSpin);
			//		parent.addChild(behaviour);
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
			Cube3DavecJCanvas3D go = new Cube3DavecJCanvas3D();
		}
	}