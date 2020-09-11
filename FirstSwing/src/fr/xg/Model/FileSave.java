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
import java.util.Scanner;

import javax.swing.JFileChooser;
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
		FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
		FileNameExtensionFilter filterBin = new FileNameExtensionFilter("Binary Files (*.bin)", "bin");
		select.addChoosableFileFilter(filterTxt);
		select.addChoosableFileFilter(filterBin);
		select.setDialogTitle("Spécifier le fichier à sauvegarder");
		select.addChoosableFileFilter(select.getAcceptAllFileFilter());
		//select.setFileFilter(filter);
		int res = select.showSaveDialog(DoublePanel.getFrame());
		if (res == JFileChooser.APPROVE_OPTION)  {
			File fileToSave = select.getSelectedFile();
			BufferedWriter writer = null;
			//Verfication de l'extension du fichier
			 String filename = fileToSave.getPath();
			 System.out.println("extension ::" + fileToSave.getAbsolutePath());
			if (filename.endsWith(".txt")) {
                System.out.println("file text selected");
            }
			//OK
			try {
				fileToSave = new File(fileToSave.toString() + ".txt");
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
