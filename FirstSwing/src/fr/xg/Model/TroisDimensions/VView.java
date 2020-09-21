package fr.xg.Model.TroisDimensions;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class VView extends Applet {
	SimpleUniverse universe = null;
	Scene scene = null; //scene VRML à charger

	public VView(String avatar) {
		System.out.println(avatar);
		setLayout(new BorderLayout());
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add("Center",canvas);
		universe = new SimpleUniverse(canvas);
		ViewingPlatform  viewingPlatform = universe.getViewingPlatform();
		TransformGroup vpTransGroup = viewingPlatform.getViewPlatformTransform();
		View view = (universe.getViewer()).getView();
		VrmlLoader loader = new VrmlLoader();
		try   
		{   scene = loader.load(avatar);
		}
		catch (Exception e)  {
			System.out.println("Erreur de chargement du fichier :: " + e);
			System.exit(1);
		}
		BranchGroup sceneGroup = scene.getSceneGroup();
		sceneGroup.setCapability(BranchGroup.ALLOW_DETACH);
		sceneGroup.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
		
		//make the VRML scene live
		universe.addBranchGraph(sceneGroup);
		
		//find the radius and center of the scene's bounding sphere
		BoundingSphere sceneBounds = (BoundingSphere)sceneGroup.getBounds();
		double radius = sceneBounds.getRadius();
		Point3d center = new Point3d();
		sceneBounds.getCenter(center);
		
		//now move the viewpoint back so we can see the whole scene
		Vector3d temp = new Vector3d(center);
		temp.z += 1.4*radius/Math.tan(view.getFieldOfView()/2.0);
		
		//and finally set that wiepoint into the viewinf transform
		Transform3D viewTransform = new Transform3D();
		viewTransform.set(temp);
		vpTransGroup.setTransform(viewTransform);
	}
	public static void main(String[] args) {
		//new VView("C:\\Users\\Xavier\\Documents\\2-Car0.wrl");
		VView titi = new VView("C:\\Users\\Xavier\\Documents\\2-Car0.wrl");
		new MainFrame(titi, 320, 400);
	}
}
