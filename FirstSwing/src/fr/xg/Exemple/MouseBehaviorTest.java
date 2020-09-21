package fr.xg.Exemple;

//Etape 1 :
//Importation des packages Java 2
import java.applet.Applet;
import java.awt.*;

//Etape 2 :
//Importation des packages Java 3D
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;

public class MouseBehaviorTest extends Applet {

public MouseBehaviorTest() {
 this.setLayout(new BorderLayout());

 // Etape 3 :
 // Creation du Canvas 3D
 Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

 this.add(canvas3D, BorderLayout.CENTER);

 // Etape 4 :
 // Creation d'un objet SimpleUniverse
 SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

 // Etape 5 :
 // Positionnement du point d'observation pour avoir une vue correcte de la
 // scene 3D
 simpleU.getViewingPlatform().setNominalViewingTransform();

 // Etape 6 :
 // Creation de la scene 3D qui contient tous les objets 3D que l'on veut visualiser
 BranchGroup scene = createSceneGraph();

 // Etape 7 :
 // Compilation de la scene 3D
 scene.compile();

 // Etape 8:
 // Attachement de la scene 3D a l'objet SimpleUniverse
 simpleU.addBranchGraph(scene);
}

/**
* Creation de la scene 3D qui contient tous les objets 3D
* @return scene 3D
*/
public BranchGroup createSceneGraph() {
 // Creation de l'objet parent qui contiendra tous les autres objets 3D
 BranchGroup parent = new BranchGroup();

 // Creation du groupe de transformation sur lequel vont s'appliquer les
 // comportements
 TransformGroup mouseTransform = new TransformGroup();

 // Le groupe de transformation sera modifie par le comportement de la
 // souris
 mouseTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
 mouseTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

 // Creation comportement rotation a la souris
 MouseRotate rotate = new MouseRotate(mouseTransform);
 rotate.setSchedulingBounds(new BoundingSphere());
 parent.addChild(rotate);

 // Creation comportement deplacement a la souris
 MouseTranslate translate = new MouseTranslate(mouseTransform);
 translate.setSchedulingBounds(new BoundingSphere());
 parent.addChild(translate);

 // Creation comportement zoom a la souris
 MouseZoom zoom = new MouseZoom(mouseTransform);
 zoom.setSchedulingBounds(new BoundingSphere());
 parent.addChild(zoom);

 // Creation du cube
 ColorCube cube = new ColorCube(0.3);

 // Ajout de cube au graphe de la scene
 mouseTransform.addChild(cube);
 parent.addChild(mouseTransform);

 return parent;
}

/**
* Etape 9 :
* Methode main() nous permettant d'utiliser cette classe comme une applet
* ou une application.
* @param args arguments de la ligne de commande
*/
public static void main(String[] args) {
 Frame frame = new MainFrame(new MouseBehaviorTest(), 256, 256);
}
}
