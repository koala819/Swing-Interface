package fr.xg.Model.DeuxDimensions;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class U3D_FenetreExplorateurChoixExtension {
	
	public File FaisTonChoix(FileFilter choixUtilisateurExtension, File fileToSave) {
		if (choixUtilisateurExtension.getDescription().equals("Text Files (*.txt)"))
		{
			System.out.println("File Text DeTeCtEd");
			fileToSave = new File(fileToSave.toString() + ".txt");
			return (fileToSave);
		}
		else if (choixUtilisateurExtension.getDescription().equals("Binary Files (*.bin)"))
		{
			System.out.println("File Binary DeTeCtEd");
			fileToSave = new File(fileToSave.toString() + ".bin");
			return (fileToSave);
		}
		else {
			System.out.println("BaD");
			fileToSave = new File(fileToSave.toString() + ".txt");
			return (fileToSave);
		}}
}
/* OLD version
 * Verification de l'extension du fichier
 * fonction ne marche que si c'est l'utilisateur qui écrit l'extension
 */
//	String nomDeLextensionDuFichier = fileToSave.toString();
//	int lastIndexOf = nomDeLextensionDuFichier.lastIndexOf(".");
//	if (lastIndexOf == -1) { nomDeLextensionDuFichier = ""; // empty extension
//	}else {nomDeLextensionDuFichier = nomDeLextensionDuFichier.substring(lastIndexOf);}
//	System.out.println("extension ::" + nomDeLextensionDuFichier);
//	FIN OLD version
/* ETAPE 1 : enregistrement de l'extension du fichier
 * Verification de l'extension du fichier
 * fonction détecte le choix de l'utilisateur dans la liste créée ci-dessus
 * filterTxt :: Text Files (*.txt)", "txt"
 * filterBin :: Binary Files (*.bin)", "bin"
 */
