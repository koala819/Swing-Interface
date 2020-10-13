package fr.xg.Model.ParcoursArbre;

import java.util.Enumeration;

import javax.media.j3d.Group;
import javax.media.j3d.Link;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;

public class AlaRechercheDeLaRacinePerdue
{
	public static void chercheTrouveEtAffiche(Enumeration enumerationDesEnfantsDeLaBranchGroupSCENE) {
		while (enumerationDesEnfantsDeLaBranchGroupSCENE.hasMoreElements())
		{
			Object elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE = enumerationDesEnfantsDeLaBranchGroupSCENE.nextElement();
			System.out.println("\nELEMENT: " + elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE);
			System.out.println(  "CLASSE : " + elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE.getClass().getSimpleName());
			
			if (elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE instanceof Link) {
				Link link = (Link) elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE;
				System.out.println(link.getBounds());
			}
			if (elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE instanceof TransformGroup) {
				Enumeration enumerationDesElementsDuTransformGroup = ((TransformGroup) elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE).getAllChildren();
				LesElementsDuTransformGroup.faitLaListeDesElements(enumerationDesElementsDuTransformGroup);
			}
//			if (elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE instanceof TransformGroup) {
//				Enumeration children2 = ((TransformGroup) elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE).getAllChildren();	
//				System.out.println("\n--------------------------------------------------------------------");
//				System.out.println("DECOUVERTE DES ELEMENTS DE TransformGroup");
//				System.out.println("--------------------------------------------------------------------");
//				while (children2.hasMoreElements())
//				{
//					Object element2 = children2.nextElement();
//					System.out.println("\nELEMENT: " + element2);
//					System.out.println(  "CLASSE : " + element2.getClass().getSimpleName());
//					if (element2 instanceof Shape3D) {
//						Enumeration children3 = ((Shape3D) element2).getAllGeometries();
//						System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//						System.out.println("Enumeration des composants géométriques");
//						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//						while (children3.hasMoreElements())
//						{
//							Object element3 = children3.nextElement();
//							System.out.println("\nELEMENT: " + element3);
//							System.out.println(  "CLASSE : " + element3.getClass().getSimpleName());
//						}/*FIN while (children3.hasMoreElements())*/
//						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//						System.out.println("FIN DE LA DECOUVERTE DES ELEMENTS DE TransformGroup");
//						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
//					}/*FIN if (element2 instanceof Shape3D)*/
//
//
//
//				}/*FIN while (children2.hasMoreElements())*/
//				System.out.println("--------------------------------------------------------------------");
//				System.out.println("FIN DE LA DECOUVERTE DES ELEMENTS DE TransformGroup");
//				System.out.println("--------------------------------------------------------------------\n");
//
//			}/*FIN if (element instanceof TransformGroup)*/
//
//			/*
//			 * VERIF 2 : Shape3D
//			 */
//			if (elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE instanceof Shape3D) {
//				Enumeration children3 = ((Shape3D) elementDeLEnumerationDesEnfantsDeLaBranchGroupSCENE).getAllGeometries();
//				System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//				System.out.println("Enumeration des composants géométriques");
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//				while (children3.hasMoreElements())
//				{
//					Object element3 = children3.nextElement();
//					System.out.println("\nELEMENT: " + element3);
//					System.out.println(  "CLASSE : " + element3.getClass().getSimpleName());
//
//					/*Recuperation des coordonées des triangle*/
//					//							if (element3 instanceof TriangleArray)
//					//							{
//					//								TriangleArray tableauDeTriangles = (TriangleArray) element3;
//					//								int nbPoints = tableauDeTriangles.getVertexCount();
//					//
//					//								System.out.println("nbPoints :: " + nbPoints);
//					//								
//					//								for (int i=0; i<nbPoints; i++)
//					//								{
//					//									double[] coordinates = new double[3];
//					//									tableauDeTriangles.getCoordinates(i, coordinates);
//					//									
//					//
//					//									System.out.println("Point " + i + " :: X=" + coordinates[0] + " Y=" + coordinates[1] + " Z=" + coordinates[2]);
//					//								}
//					//							}/*FIN if (element2 instanceof TriangleArray)*/
//
//
//				}/*FIN while (children3.hasMoreElements())*/
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//				System.out.println("FIN DE Enumeration des composants géométriques");
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
//			}/*FIN if (element2 instanceof Shape3D)*/


		}/*FIN while (enumerationDesEnfantsDeLaBranchGroupSCENE.hasMoreElements()*/
	}

}
