package fr.xg.Model.TroisDimensions;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;

public class U3D_Forme3D_MonMouseCUBE3D {

	static BranchGroup createSceneGraph(JCanvas3D jCanvas3D)
	{
		BranchGroup objBranchGroup = new BranchGroup();

		// Creation du cube de gauche
		Transform3D leftTranslation = new Transform3D();
		leftTranslation.setTranslation(new Vector3f(-0.6f, 0.0f, -0.6f));
		TransformGroup leftTranslationTG = new TransformGroup(leftTranslation);
		leftTranslationTG.addChild(new ColorCube(0.1));
		objBranchGroup.addChild(leftTranslationTG);

		// Creation du cube du centre
		Transform3D centerTranslation = new Transform3D();
		centerTranslation.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		centerTranslation.rotX(Math.PI/3.0d);
		TransformGroup centerTranslationTG = new TransformGroup(centerTranslation);
		centerTranslationTG.addChild(new ColorCube(0.1));
		objBranchGroup.addChild(centerTranslationTG);

		// Creation du cube de droite
		Transform3D rightTranslation = new Transform3D();
		rightTranslation.setTranslation(new Vector3f(0.6f, 0.0f, -0.6f));
		TransformGroup rightTranslationTG = new TransformGroup(rightTranslation);
		rightTranslationTG.addChild(new ColorCube(0.1));
		objBranchGroup.addChild(rightTranslationTG);

		// Définit zone d'infuence où au delà de laquelle la rotation n'agit plus
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);

		// Attacher comportement MouseRotate afin de faire pivoter le texte avec le bouton gauche de la souris
		rightTranslationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Donne la permission au TransformGroup d'être modifié
		MouseRotate mr = new MouseRotate(jCanvas3D, rightTranslationTG);
		mr.setSchedulingBounds(bounds);
		mr.setSchedulingInterval(1);
		objBranchGroup.addChild(mr);

		// Creation comportement zoom a la souris : avec le click molette
		centerTranslationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Donne la permission au TransformGroup d'être modifié
		// avec le click molette
		MouseZoom zoom = new MouseZoom(centerTranslationTG);
		zoom.setSchedulingBounds(bounds);
		objBranchGroup.addChild(zoom);
		// en tournant la molette
		MouseWheelZoom zoom2 = new MouseWheelZoom(centerTranslationTG);
		zoom2.setSchedulingBounds(bounds);
		objBranchGroup.addChild(zoom2);
		
		// Attacher comportement MouseTranslate afin de faire glisser le texte avec le bouton droit de la souris
		leftTranslationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);  // Donne la permission au TransformGroup d'être modifié
		MouseTranslate translate = new MouseTranslate(leftTranslationTG);
		translate.setSchedulingBounds(bounds);
		objBranchGroup.addChild(translate);

		objBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		objBranchGroup.compile();
		return objBranchGroup;
	}

}
