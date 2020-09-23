package fr.xg.Model.TroisDimensions;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;

public class U3D_Forme3D_MonMouseCUBE3D_PointOfView
{
	static BranchGroup createSceneGraph(JCanvas3D jCanvas3D)
	{
		BranchGroup objBranchGroup = new BranchGroup();

		TransformGroup rootTg = new TransformGroup();
		objBranchGroup.addChild(rootTg);
		//pointeurSouris(jCanvas3D, rootTg);
		
		
		// Creation du cube de gauche
		Transform3D objLeftTransForm3D = new Transform3D();
		objLeftTransForm3D.setTranslation(new Vector3f(-0.6f, 0.0f, -0.6f));
		TransformGroup objTransformGroup = new TransformGroup(objLeftTransForm3D);
		objTransformGroup.addChild(new ColorCube(0.1));
		
		//pointeurSouris(jCanvas3D, objTransformGroup);
		rootTg.addChild(objTransformGroup);

		// Creation du cube du centre
		Transform3D centerTranslation = new Transform3D();
		centerTranslation.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		centerTranslation.rotX(Math.PI/3.0d);
		objTransformGroup = new TransformGroup(centerTranslation);
		objTransformGroup.addChild(new ColorCube(0.1));
		//pointeurSouris(jCanvas3D, objTransformGroup);
		rootTg.addChild(objTransformGroup);

		// Creation du cube de droite
		Transform3D rightTranslation = new Transform3D();
		rightTranslation.setTranslation(new Vector3f(0.6f, 0.0f, -0.6f));
		objTransformGroup = new TransformGroup(rightTranslation);
		objTransformGroup.addChild(new ColorCube(0.1));
		//pointeurSouris(jCanvas3D, objTransformGroup);
		rootTg.addChild(objTransformGroup);		
		
		objBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		objBranchGroup.compile();
		return objBranchGroup;
	}

	
	private static void pointeurSouris(JCanvas3D jCanvas3D, TransformGroup objTransformGroup)
	{
		// Définit zone d'infuence où au delà de laquelle la rotation n'agit plus
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 1000);
		objTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Rotation a la souris
		MouseRotate rotateBehavior = new MouseRotate(jCanvas3D, objTransformGroup);
		rotateBehavior.setSchedulingBounds(bounds);
		objTransformGroup.addChild(rotateBehavior);

		// Translation
		MouseTranslate translateBehavior = new MouseTranslate(jCanvas3D, objTransformGroup);
		translateBehavior.setSchedulingBounds(bounds);
		objTransformGroup.addChild(translateBehavior);

		// Zoom Molette
		MouseWheelZoom wheelZoomBehavior = new MouseWheelZoom(jCanvas3D, objTransformGroup);
		wheelZoomBehavior.setSchedulingBounds(bounds);
		objTransformGroup.addChild(wheelZoomBehavior);

		// Zoom Souris
		MouseZoom zoomBehavior = new MouseZoom(jCanvas3D, objTransformGroup);
		zoomBehavior.setSchedulingBounds(bounds);
		objTransformGroup.addChild(zoomBehavior);
	}
}
