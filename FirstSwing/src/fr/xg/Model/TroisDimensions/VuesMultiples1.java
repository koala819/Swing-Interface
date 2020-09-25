//http://deven3d.free.fr/java3d/chap09.htm

package fr.xg.Model.TroisDimensions;

// Etape 1 : importation des packages Java 2
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.vecmath.Vector3f;

// Etape 2 : importation des packages Java 3D
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;

public class VuesMultiples1 extends Applet {

	public VuesMultiples1() {

		this.setLayout(new GridLayout(2, 2));

		// Etape 3 : création d'un univers et d'un objet Locale (va contenir la scene 3D ainsi que les 4 cameras)
		VirtualUniverse universe = new VirtualUniverse();
		Locale locale = new Locale(universe);

		// Etape 4 : creation et placement des 4 vues
		
		// Vue de face perpendiculaire a Z placee a Z = 3 metres
		Vue vueFace = new Vue();
		TransformGroup vpTG = vueFace.getViewPlatformTransformGroup();
		Transform3D transform = new Transform3D();
		transform.setTranslation(new Vector3f(0f, 0f, 3f));
		vpTG.setTransform(transform);

		// Vue de droite perpendiculaire a X placee a X = 3 metres
		Vue vueDroite = new Vue();
		vpTG = vueDroite.getViewPlatformTransformGroup();
		transform = new Transform3D();
		transform.setTranslation(new Vector3f(3f, 0f, 0f));
		Transform3D transform2 = new Transform3D();
		transform2.rotY(Math.PI / 2.0);
		transform.mul(transform2);
		vpTG.setTransform(transform);

		// Vue de gauche perpendiculaire a X placee a X = -3 metres
		Vue vueGauche = new Vue();
		vpTG = vueGauche.getViewPlatformTransformGroup();
		transform = new Transform3D();
		transform.setTranslation(new Vector3f(-3f, 0f, 0f));
		transform2 = new Transform3D();
		transform2.rotY(-Math.PI / 2.0);
		transform.mul(transform2);
		vpTG.setTransform(transform);

		// Vue de haut perpendiculaire a Y placee a Y = 3 metres
		Vue vueHaut = new Vue();
		vpTG = vueHaut.getViewPlatformTransformGroup();
		transform = new Transform3D();
		transform.setTranslation(new Vector3f(0f, 3f, 0f));
		transform2 = new Transform3D();
		transform2.rotX(-Math.PI / 2.0);
		transform.mul(transform2);
		vpTG.setTransform(transform);

		// Etape 5 : attachement des 4 vues a l'objet locale
		locale.addBranchGraph(vueFace.getParent());
		locale.addBranchGraph(vueDroite.getParent());
		locale.addBranchGraph(vueGauche.getParent());
		locale.addBranchGraph(vueHaut.getParent());

		// Etape 6 : creation de la scene 3D qui contient tous les objets 3D que l'on veut visualiser
		BranchGroup scene = createSceneGraph();

		// Etape 7 : compilation de la scene 3D
		scene.compile();

		// Etape 8 : attachement de la scene 3D a l'objet locale
		locale.addBranchGraph(scene);

		// Etape 9 : ajout de chaque canvas 3D (correspondant a chaque vue) a la frame principale de notre application/applet
		
		// Vue de haut
		JPanel panelHaut = new JPanel();
		panelHaut.setLayout(new BorderLayout());
		TitledBorder borderHaut = new TitledBorder(new EtchedBorder(),
				"Vue de haut");
		panelHaut.setBorder(borderHaut);
		panelHaut.add(vueHaut.getCanvas3D(), BorderLayout.CENTER);

		// Vue de droite
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new BorderLayout());
		TitledBorder borderDroite = new TitledBorder(new EtchedBorder(),
				"Vue de droite");
		panelDroite.setBorder(borderDroite);
		panelDroite.add(vueDroite.getCanvas3D(), BorderLayout.CENTER);

		// Vue de gauche
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BorderLayout());
		TitledBorder borderGauche = new TitledBorder(new EtchedBorder(),
				"Vue de gauche");
		panelGauche.setBorder(borderGauche);
		panelGauche.add(vueGauche.getCanvas3D(), BorderLayout.CENTER);

		// Vue de face
		JPanel panelFace = new JPanel();
		panelFace.setLayout(new BorderLayout());
		TitledBorder borderFace = new TitledBorder(new EtchedBorder(),
				"Vue de face");
		panelFace.setBorder(borderFace);
		panelFace.add(vueFace.getCanvas3D(), BorderLayout.CENTER);

		// Ajout des 4 vues a la frame principale
		this.add(panelHaut);
		this.add(panelDroite);
		this.add(panelGauche);
		this.add(panelFace);
	}

	/**
	 * Creation de la scene 3D qui contient tous les objets 3D
	 * @return scene 3D
	 */
	public BranchGroup createSceneGraph() {
		// Creation de l'objet parent qui contiendra tous les autres objets 3D
		BranchGroup parent = new BranchGroup();

		// Creation du groupe de transformation sur lequel vont s'appliquer les comportements
		TransformGroup mouseTransform = new TransformGroup();

		// Le groupe de transformation sera modifie par le comportement de la souris
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

		// Creation du cube et ajout a la scene 3D
		ColorCube cube = new ColorCube(0.3);
		mouseTransform.addChild(cube);

		// Ajout du cube a l'objet racine de la scene 3D
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
		Frame frame = new MainFrame(new VuesMultiples1(), 512, 512);
	}
}

/**
 * Classe permettant de creer un point de vue de la scene 3D
 */
class Vue {
	// Les objets de type PhysicalBody et PhysicalEnvironment doivent etre communs a toutes les instances de la classe Vue. 
	// Ce sont les memes objets que l'on utilisera pour toutes les vues
	protected static final PhysicalBody physBody = new PhysicalBody();
	protected static final PhysicalEnvironment physEnv =
			new PhysicalEnvironment();

	// Objets specifiques a chaque vue
	protected BranchGroup parent = null;
	protected TransformGroup vpTG = null;
	protected ViewPlatform viewPlatform = null;
	protected View view = null;
	protected Canvas3D canvas = null;

	public Vue() {

		// Creation de la configuration graphique
		GraphicsConfigTemplate3D gconfigTemplate = new GraphicsConfigTemplate3D();
		GraphicsConfiguration gconfig =
				GraphicsEnvironment.getLocalGraphicsEnvironment().
				getDefaultScreenDevice().getBestConfiguration(gconfigTemplate);

		// Creation du canvas 3D, de l'objet viewPlatform et de l'objet view
		// associe au canvas et a viewPlatform
		canvas = new Canvas3D(gconfig);
		viewPlatform = new ViewPlatform();
		view = new View();

		// Initialisation de l'objet view
		view.addCanvas3D(canvas);
		view.attachViewPlatform(viewPlatform);
		view.setPhysicalBody(physBody);
		view.setPhysicalEnvironment(physEnv);

		// Creation du groupe de transformation qui permet de modifier la position de la camera
		vpTG = new TransformGroup();
		vpTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		vpTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		vpTG.addChild(viewPlatform);

		// Creation de l'objet parent qui est pere de tous les nodes de la classe Vue
		parent = new BranchGroup();
		parent.setCapability(BranchGroup.ALLOW_DETACH);
		parent.addChild(vpTG);
	}

	/**
	 * Renvoie une reference sur le groupe de transformation associe a la
	 * camera (viewPlatform)
	 * @return reference sur le groupe de transformation associe a la camera
	 * (viewPlatform)
	 */
	public TransformGroup getViewPlatformTransformGroup() {
		return vpTG;
	}

	/**
	 * Renvoie une reference sur l'objet parent de la classe Vue
	 * @return reference sur l'objet parent
	 */
	public BranchGroup getParent() {
		return parent;
	}

	/**
	 * Renvoie une reference sur le canvas 3D
	 * @return reference sur le canvas 3D
	 */
	public Canvas3D getCanvas3D() {
		return canvas;
	}
}