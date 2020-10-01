package fr.xg.Model.TroisDimensions.Tempo;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class UpdateViewerGeometryJ3D {
	/*lookAt (eye, center, up)
	 * eye :  	l'emplacement de l'œil
	 * center : un point du monde virtuel où l'œil regarde
	 * up : 	un vecteur haut spécifiant la direction ascendante du tronc
	 */
	
	
	public UpdateViewerGeometryJ3D(SimpleUniverse universe, TransformGroup viewingTransformGroup,Point3d eye, Point3d center, Vector3d up)  {
		Transform3D viewingTransform = new Transform3D();
		viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
		viewingTransform.lookAt(eye, center, up);
		viewingTransform.invert();
		viewingTransformGroup.setTransform(viewingTransform);}
}
