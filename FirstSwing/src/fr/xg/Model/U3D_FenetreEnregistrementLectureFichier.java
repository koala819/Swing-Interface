package fr.xg.Model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class U3D_FenetreEnregistrementLectureFichier {
	protected void lireLeFichierTXT(File fileToSave) {
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileToSave);
			Scanner myReader = new Scanner(fileReader);
							while (myReader.hasNextLine()) {
								String data = myReader.nextLine();
								System.out.println(data);
							}
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	protected void lireLeFichierBIN(File fileToSave) {
		/*
		 * ecriture MASTER
		 * try (DataInputStream in = new DataInputStream(new BufferedInputStream(
		 *       new FileInputStream("c:\\test1.bin")))) {
		 */
		try ( FileInputStream fichierLectureContenu = new FileInputStream(fileToSave);
				BufferedInputStream bufferLecture = new BufferedInputStream(fichierLectureContenu);
				DataInputStream lectureDonneesDansFichier = new DataInputStream(bufferLecture);				) {
			System.out.println(lectureDonneesDansFichier.readByte());
			System.out.println(lectureDonneesDansFichier.readDouble());
			System.out.println(lectureDonneesDansFichier.readUTF());
			System.out.println(lectureDonneesDansFichier.readUTF());
			lectureDonneesDansFichier.close();} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
