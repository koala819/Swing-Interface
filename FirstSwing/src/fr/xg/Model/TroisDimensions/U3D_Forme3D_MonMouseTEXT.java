package fr.xg.Model.TroisDimensions;

import java.awt.Font;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;

public class U3D_Forme3D_MonMouseTEXT {
	static BranchGroup createSceneGraph(JCanvas3D jCanvas3D)
	{
		BranchGroup objRoot = new BranchGroup();
		TransformGroup objetTransformGroup = new TransformGroup();

		//Le groupe de transformation sera modifie par le comportement de la souris
		objetTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		objetTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRoot.addChild(objetTransformGroup);

		// Create a simple Shape3D node; add it to the scene graph.
		Font3D f3d = new Font3D(new Font("dialog", Font.PLAIN, 1), new FontExtrusion());
		Text3D text = new Text3D(f3d, "JCanvas3D");
		Shape3D sh = new Shape3D();
		sh.setGeometry(text);
		objetTransformGroup.addChild( sh );

		// Définit zone d'infuence où au delà de laquelle la rotation n'agit plus
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);

		// Attacher comportement MouseRotate afin de faire pivoter le texte avec le bouton gauche de la souris
		/*MouseRotate mr = new MouseRotate(jCanvas3D, objetTransformGroup);
		mr.setSchedulingBounds(bounds);
		mr.setSchedulingInterval(1);
		objRoot.addChild(mr);*/
	

		// Attacher comportement MouseTranslate afin de faire glisser le texte avec le bouton droit de la souris
		/*MouseTranslate translate = new MouseTranslate(objetTransformGroup);
		translate.setSchedulingBounds(new BoundingSphere());
		objRoot.addChild(translate);*/

		// Creation comportement zoom a la souris
		/*MouseZoom zoom = new MouseZoom(objetTransformGroup);
		zoom.setSchedulingBounds(new BoundingSphere());
		objRoot.addChild(zoom);*/

		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		objRoot.compile();
		return objRoot;
	}
}
