package fr.xg.Model.DeuxDimensions;
import java.io.BufferedWriter;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class U3D_FenetreExplorateur  {
	/*
	 * tuto explication vidéo 
	 * https://www.youtube.com/watch?v=GO829h_Cc-w
	 */
	public void Explore()
	{
		JFileChooser select = new JFileChooser("");
		select.setDialogTitle("Spécifier le fichier à sauvegarder");
		FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
		FileNameExtensionFilter filterBin = new FileNameExtensionFilter("Binary Files (*.bin)", "bin");
		select.addChoosableFileFilter(filterTxt);
		select.addChoosableFileFilter(filterBin);
		int res = select.showSaveDialog(U3D_FenetreAvec2Jpanel.getFrame());
		if (res == JFileChooser.APPROVE_OPTION)  {
			File fileToSave = select.getSelectedFile();
			BufferedWriter writer = null;
			FileFilter choixUtilisateurExtension = select.getFileFilter();

			/* ETAPE 1 
			 * Définition et enregistrement du type d'extension 
			 * si utilisateur laisse par défaut ce sera *.txt
			 * création du fichier fileToSave
			 */
			U3D_FenetreExplorateurChoixExtension fece = new U3D_FenetreExplorateurChoixExtension();
			fileToSave = fece.FaisTonChoix(choixUtilisateurExtension, fileToSave);

			/*
			 * ETAPE 2
			 * Enregistrement d'un contenu dans le fichier créé
			 * On vérifie si l'extension *.bin est choisi 
			 * pour effectuer la conversion en binaire
			 * Sinon on enregistre du texte en *.txt 
			 */
			U3D_FenetreExplorateurSauvegardeFichier fesf = new U3D_FenetreExplorateurSauvegardeFichier();
			if (choixUtilisateurExtension.getDescription().equals("Binary Files (*.bin)"))
			{
				fesf.enregistreLeFichierBIN(fileToSave);
			} else {
				fesf.enregistreLeFichierTXT(fileToSave);
			}
			/*
			 * ETAPE 3
			 * Lecture du contenu du fichier créé
			 * On vérifie si l'extension *.bin est choisi 
			 * pour lire le binaire
			 * Sinon lecture *.txt
			 */
			U3D_FenetreExplorateurLectureFichier felf = new U3D_FenetreExplorateurLectureFichier();
			if (choixUtilisateurExtension.getDescription().equals("Binary Files (*.bin)"))
			{
				felf.lireLeFichierBIN(fileToSave);
			} else {
				felf.lireLeFichierTXT(fileToSave);
			}
			/*
			 * ETAPE 4
			 * check emplacement & taille
			 */
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			System.out.println("File size in bytes " + fileToSave.length());
		}


	}

}
