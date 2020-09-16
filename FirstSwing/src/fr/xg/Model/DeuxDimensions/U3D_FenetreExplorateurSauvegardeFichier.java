package fr.xg.Model.DeuxDimensions;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class U3D_FenetreExplorateurSauvegardeFichier {
	BufferedWriter writer;
	protected void enregistreLeFichierTXT(File fileToSave) {
		try {
			writer = new BufferedWriter(new FileWriter(fileToSave));
			writer.write(String.valueOf(Math.PI));
			writer.write("\nHello world!");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void enregistreLeFichierBIN(File fileToSave) {
		/*
		 * ecriture MASTER
		 * try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
		 *       new FileOutputStream("c:\\test1.bin")))) {
		 */
		try (
				FileOutputStream fichierEnregistrement = new FileOutputStream(fileToSave);
				/*
				 * Buffer est utilisé pour stocker le DataOutputStream
				 * buffer se remplit au fur et à mesure et se vide quand il est plein
				 * utilité du buffer : éviter d'écrire bit par bit pour accroître vitesse execution 
				 */
				BufferedOutputStream bufferEnregistrement = new BufferedOutputStream(fichierEnregistrement);
				//DataOutputStream pour écrire des données au format binaire
				DataOutputStream ecrireDonneesFormatBinaire = new DataOutputStream(bufferEnregistrement);
				){
			//ecrireDonneesFormatBinaire.writeByte((byte) 123);
			ecrireDonneesFormatBinaire.writeDouble((double) Math.PI);
//			ecrireDonneesFormatBinaire.writeUTF("le chat est sage");
//			ecrireDonneesFormatBinaire.writeUTF((String) "Hello World");
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
