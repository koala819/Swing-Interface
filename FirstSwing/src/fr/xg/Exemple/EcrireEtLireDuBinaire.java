package fr.xg.Exemple;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EcrireEtLireDuBinaire {
	public static void main(String[] args) throws IOException {

		/*
		 * ecriture MASTER
		 * try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
		 *       new FileOutputStream("c:\\test1.bin")))) {
		 */
		try (
				FileOutputStream fichierEnregistrement = new FileOutputStream("c:\\test1.bin");
				/*
				 * Buffer est utilisé pour stocker le DataOutputStream
				 * buffer se remplit au fur et à mesure et se vide quand il est plein
				 * utilité du buffer : éviter d'écrire bit par bit pour accroître vitesse execution 
				 */
				BufferedOutputStream bufferEnregistrement = new BufferedOutputStream(fichierEnregistrement);
				//DataOutputStream pour écrire des données au format binaire
				DataOutputStream ecrireDonneesFormatBinaire = new DataOutputStream(bufferEnregistrement);
				){
			ecrireDonneesFormatBinaire.writeByte((byte) 123);
			ecrireDonneesFormatBinaire.writeDouble((double) Math.PI);
			ecrireDonneesFormatBinaire.writeUTF("le chat est sage");
			ecrireDonneesFormatBinaire.writeUTF((String) "Hello World");
			ecrireDonneesFormatBinaire.close();
			/*
			 * writeShort((short) 1_234);
			 * writeInt(1_234_567);
			 * writeLong(1_234_567_890_123_456L);
			 * writeFloat((float) Math.E);
			 * writeDouble(Math.PI);
			 * writeBoolean(true);
			 * writeChar('€');
			 */
		}

		//LECTURE
		/*
		 * ecriture MASTER
		 * try (DataInputStream in = new DataInputStream(new BufferedInputStream(
		 *       new FileInputStream("c:\\test1.bin")))) {
		 */
		try ( FileInputStream fichierLectureContenu = new FileInputStream("c:\\test1.bin");
				BufferedInputStream bufferLecture = new BufferedInputStream(fichierLectureContenu);
				DataInputStream lectureDonneesDansFichier = new DataInputStream(bufferLecture);				) {
			System.out.println(lectureDonneesDansFichier.readByte());
			System.out.println(lectureDonneesDansFichier.readDouble());
			System.out.println(lectureDonneesDansFichier.readUTF());
			System.out.println(lectureDonneesDansFichier.readUTF());
			lectureDonneesDansFichier.close();}
	}

}