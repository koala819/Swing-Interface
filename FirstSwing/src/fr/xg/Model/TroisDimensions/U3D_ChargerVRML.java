package fr.xg.Model.TroisDimensions;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class U3D_ChargerVRML
{
	public static BranchGroup ouvreVRML(JCanvas3D canvas3D, String avatar, SimpleUniverse universe)
	{
		VrmlLoader loader = new VrmlLoader();
		Scene sceneVRML = null; //scene VRML à charger
		try   
		{
			sceneVRML = loader.load(avatar);
		}
		catch (Exception e)
		{
			System.out.println("Erreur de chargement du fichier :: " + e);
			System.exit(1);
		}		

		BranchGroup sceneGroup = sceneVRML.getSceneGroup();
		sceneGroup.setCapability(BranchGroup.ALLOW_DETACH);
		sceneGroup.setCapability(BranchGroup.ALLOW_BOUNDS_READ);

		BranchGroup racinePourRotation = new BranchGroup();
		TransformGroup groupeRotation = new TransformGroup();
		groupeRotation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		racinePourRotation.addChild(groupeRotation);
		groupeRotation.addChild(sceneGroup);		

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 500.0);

		MouseRotate mr = new MouseRotate(canvas3D, groupeRotation);
		mr.setSchedulingBounds(bounds);
		mr.setSchedulingInterval(1);
		sceneGroup.addChild(mr);
		//positionneLeZoom(sceneGroup, universe);

		return racinePourRotation;
	}
	
	private static void positionneLeZoom(BranchGroup sceneGroup, SimpleUniverse universe)
	{
		BoundingSphere sceneBounds = (BoundingSphere)sceneGroup.getBounds();
		double radius = sceneBounds.getRadius();
		Point3d center = new Point3d();
		sceneBounds.getCenter(center);
		
		//now move the viewpoint back so we can see the whole scene
		Vector3d temp = new Vector3d(center);
		View view = (universe.getViewer()).getView();
		temp.z += 1.4*radius/Math.tan(view.getFieldOfView()/2.0);
		
		//and finally set that wiepoint into the viewinf transform
		Transform3D viewTransform = new Transform3D();
		viewTransform.set(temp);
		ViewingPlatform  viewingPlatform = universe.getViewingPlatform();
		TransformGroup vpTransGroup = viewingPlatform.getViewPlatformTransform();
		vpTransGroup.setTransform(viewTransform);
	}
}
