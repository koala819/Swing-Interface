package fr.xg.Model.TroisDimensions;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.ColorCube;

public class U3D_Forme3D_CUBE {
	static BranchGroup createSceneGraph()
	{
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();

		// Create the TransformGroup node and initialize it to the
		// identity. Enable the TRANSFORM_WRITE capability so that
		// our behavior code can modify it at run time. Add it to
		// the root of the subgraph.
		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRoot.addChild(objTrans);

		// Create a simple Shape3D node; add it to the scene graph.
		objTrans.addChild(new ColorCube(0.4));

		// Create a new Behavior object that will perform the
		// desired operation on the specified transform and add
		// it into the scene graph.
		Transform3D yAxis = new Transform3D();
		Alpha rotationAlpha = new Alpha(-1, 4000);

		RotationInterpolator rotator =
				new RotationInterpolator(rotationAlpha, objTrans, yAxis,
						0.0f, (float) Math.PI*2.0f);
		BoundingSphere bounds =
				new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		rotator.setSchedulingBounds(bounds);
		objRoot.addChild(rotator);

		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		
		// Have Java 3D perform optimizations on this scene graph.
		objRoot.compile();

		return objRoot;
	}
}
