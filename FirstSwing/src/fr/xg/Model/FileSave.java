package fr.xg.Model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt, *.bin)", "txt", "bin");
		select.addChoosableFileFilter(filter);
		select.setDialogTitle("Spécifier le fichier à sauvegarder");
		select.addChoosableFileFilter(select.getAcceptAllFileFilter());
		select.setFileFilter(filter);
		int res = select.showSaveDialog(DoublePanel.getFrame());
		if (res == JFileChooser.APPROVE_OPTION)  {
			File fileToSave = select.getSelectedFile();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(fileToSave));
				writer.write("Hello world!");
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					// Close the writer regardless of what happens...
					writer.close();
				} catch (Exception e) {
				}
			}

		}


	}
}
