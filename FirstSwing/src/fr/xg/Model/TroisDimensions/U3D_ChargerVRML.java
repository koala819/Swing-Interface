package fr.xg.Model.TroisDimensions;

import java.io.File;
import java.io.Reader;

import javax.media.j3d.BranchGroup;
import javax.swing.JFileChooser;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import com.sun.j3d.loaders.Scene;

public class U3D_ChargerVRML {


	public BranchGroup ouvreVRML(String avatar) {
		VrmlLoader loader = new VrmlLoader();
		Scene scene = null; //scene VRML à charger
		try   
		{   scene = loader.load(avatar);}
		catch (Exception e)  {
			System.out.println("Erreur de chargement du fichier :: " + e);
			System.exit(1);
		}
		BranchGroup sceneGroup = scene.getSceneGroup();
		sceneGroup.setCapability(BranchGroup.ALLOW_DETACH);
		sceneGroup.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
		return sceneGroup;


	}
}
