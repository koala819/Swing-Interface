package fr.xg.Model.ParcoursArbre;

import java.util.Enumeration;

public class LesElementsDuTransformGroup  {
	public static void faitLaListeDesElements(Enumeration enumerationDesElementsDuTransformGroup) {
		System.out.println("\n--------------------------------------------------------------------");
		System.out.println("DECOUVERTE DES ELEMENTS DE TransformGroup");
		System.out.println("--------------------------------------------------------------------");
		while (enumerationDesElementsDuTransformGroup.hasMoreElements())
		{
			Object elementEnumerationDesElementsDuTransformGroup = enumerationDesElementsDuTransformGroup.nextElement();
			System.out.println("\nELEMENT: " + elementEnumerationDesElementsDuTransformGroup);
			System.out.println(  "CLASSE : " + elementEnumerationDesElementsDuTransformGroup.getClass().getSimpleName());
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("FIN DE LA DECOUVERTE DES ELEMENTS DE TransformGroup");
		System.out.println("--------------------------------------------------------------------\n");
	}
}
