package fr.xg.Model;

import java.io.File;

import javax.swing.JFileChooser;

public class FileChoose  {
	
	/*
	 * tuto explication vidéo 
	 * https://www.youtube.com/watch?v=GO829h_Cc-w
	 * TODO : rajouter options :: Filtre sur les fichiers à afficher 
	 */
	public static void FileExplorer() {
		JFileChooser select = new JFileChooser("");
		select.setMultiSelectionEnabled(true);
		int res = select.showDialog(Window.frame, "Analyser");
		if (res == JFileChooser.APPROVE_OPTION) {
			File[] fs = select.getSelectedFiles();
			System.out.println("Nom des fichiers sélectionnées :");
			for (File f: fs) {
				System.out.println(f.getName());
				
			}
		} else {
			System.out.println("Analyse annulée ...");
		}
		
	}
    
    /*TEST POUR VERIFIER APPEL FONCTION
     * QUAND FONCTION FileChoose() OK 
     * FONCTION Check() A SUPPRIMER
     */
	public static void Check() {
		System.out.println("t'as cliqué !!!");
	}
}
