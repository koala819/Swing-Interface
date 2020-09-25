package fr.xg.Model.ParcoursArbre;

public class Test {
	public static void main(String[] args) {

		// Création des 13 noeuds
		Noeud un = new Noeud("1");
		Noeud deux = new Noeud("2");
		Noeud trois = new Noeud("3");
		Noeud quatre = new Noeud("4");
		Noeud cinq = new Noeud("5");
		Noeud six = new Noeud("6");
		Noeud sept = new Noeud("7");
		Noeud huit = new Noeud("8");
		Noeud neuf = new Noeud("9");
		Noeud dix = new Noeud("10");
		Noeud onze = new Noeud("11");
		Noeud douze = new Noeud("12");
		Noeud treize = new Noeud("13");

		// Assignation des noeuds en tant que fils les uns des autres
		un.setLeft(deux);
		un.setRight(trois);
		deux.setLeft(quatre);
		quatre.setRight(huit);
		deux.setRight(cinq);
		trois.setLeft(six);
		six.setLeft(neuf);
		six.setRight(dix);
		dix.setLeft(douze);
		dix.setRight(treize);
		trois.setRight(sept);
		sept.setLeft(onze);

		// Affichage des parcours en partant de la racine
		
		//Parcours Préfixe : tu donne la racine, puis récursivement les fils de gauche à droite 
		//(même chose pour chaque fils, d'abord la racine, puis les "sous-fils" de gauche à droite)
		System.out.println("\nParcours préfixe : ");
		un.prefixe();
		
		//Parcours Infixe : tu donne le sous-arbre de gauche, puis la racine,
		//et enfin le sous arbre de droite (récursivement aussi)
		System.out.println("\nParcours infixe : ");
		un.infixe();
		
		//Parcours Postfixe : Tu donne le sous-arbre de gauche, puis le sous arbre de droite, puis la racine. 
		//En gros, tu ne peux donner la racine que si tous ses fils ont déjà été donnés.
		System.out.println("\nParcours postfixe : ");
		un.postfixe();



	}
}