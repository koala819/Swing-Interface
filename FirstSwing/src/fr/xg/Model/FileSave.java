package fr.xg.Model;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSave  {

	/*
	 * tuto explication vidéo 
	 * https://www.youtube.com/watch?v=GO829h_Cc-w
	 * TODO : rajouter options :: Filtre sur les fichiers à afficher 
	 */
	public void Explore()
	{
		JFileChooser select = new JFileChooser("");
		select.setDialogTitle("Spécifier le fichier à sauvegarder");
		FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
		FileNameExtensionFilter filterBin = new FileNameExtensionFilter("Binary Files (*.bin)", "bin");
		select.addChoosableFileFilter(filterTxt);
		select.addChoosableFileFilter(filterBin);
		int res = select.showSaveDialog(DoublePanel.getFrame());
		if (res == JFileChooser.APPROVE_OPTION)  {
			File fileToSave = select.getSelectedFile();
			BufferedWriter writer = null;

			/*
			 * Verification de l'extension du fichier
			 * fonction ne marche que si c'est l'utilisateur qui écrit l'extension
			 */
//			String nomDeLextensionDuFichier = fileToSave.toString();
//			int lastIndexOf = nomDeLextensionDuFichier.lastIndexOf(".");
//			if (lastIndexOf == -1) { nomDeLextensionDuFichier = ""; // empty extension
//			}else {nomDeLextensionDuFichier = nomDeLextensionDuFichier.substring(lastIndexOf);}
//			System.out.println("extension ::" + nomDeLextensionDuFichier);
			//	FIN de la vérification de l'extension du fichier
			
			/*
			 * Verification de l'extension du fichier
			 * fonction détecte le choix de l'utilisateur dans la liste créée ci-dessus
			 * filterTxt :: Text Files (*.txt)", "txt"
			 * filterBin :: Binary Files (*.bin)", "bin"
			 */
			FileFilter choixUtilisateurExtension = select.getFileFilter();
			if (choixUtilisateurExtension == filterTxt) {
				System.out.println("File Text DeTeCtEd");
				fileToSave = new File(fileToSave.toString() + ".txt");
			} if (choixUtilisateurExtension == filterBin) {
				System.out.println("File Binary DeTeCtEd");
				fileToSave = new File(fileToSave.toString() + ".bin");
			}else {
				System.out.println("BaD");
			}
			//OK


			try {
				writer = new BufferedWriter(new FileWriter(fileToSave));
				writer.write(String.valueOf(Math.PI));
				writer.write("\nHello world!");
				writer.close();
				FileReader fileReader = new FileReader(fileToSave);
				//writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileToSave), "UTF-8"));

				//lecture du fichier enregistré
				//				
				//				Scanner myReader = new Scanner(fileReader);
				//				while (myReader.hasNextLine()) {
				//					String data = myReader.nextLine();
				//					System.out.println(data);
				//				}
				//fin de la lecture du fichier enregistré

				fileReader.close();
				//OK
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				System.out.println("File size in bytes " + fileToSave.length());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}
}
