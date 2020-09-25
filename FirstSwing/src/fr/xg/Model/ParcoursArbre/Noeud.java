package fr.xg.Model.ParcoursArbre;

public class Noeud {

	private String name;
	private Noeud left, right;

	// Constructeur
	public Noeud(String name){
		this.name = name;
		this.left = null;
		this.right = null;
	}

	// Méthodes qui permettront d'attribuer les fils
	public void setLeft(Noeud left){
		this.left = left;
	}

	public void setRight(Noeud right){
		this.right = right;
	}


	// Méthode récursives de parcours
	public void prefixe(){
		System.out.print(name+" - ");
		if(left != null) left.prefixe();
		if(right != null) right.prefixe();
	}

	public void infixe(){
		if(left != null) left.infixe();
		System.out.print(name+" - ");
		if(right != null) right.infixe();
	}

	public void postfixe(){
		if(left != null) left.postfixe();
		if(right != null) right.postfixe();
		System.out.print(name+" - ");
	}

}