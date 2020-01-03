package modele;

import java.util.ArrayList;

public class Magasin {

	private String nom;
	private ArrayList<Rayon>listeRayons;
	private ChefMagasin chefMagasin;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param listeRayons
	 * @param chefMagasin
	 */
	public Magasin(String nom, ArrayList<Rayon> listeRayons, ChefMagasin chefMagasin) {
		this.nom = nom;
		this.listeRayons = listeRayons;
		this.chefMagasin = chefMagasin;
	}

	/**
	 * Constructeur par defaut
	 */
	public Magasin() {
		this.nom = "";
		this.listeRayons = new ArrayList<Rayon>();
		this.chefMagasin = new ChefMagasin();
	}

	//GETTERS ET SETTERS
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Rayon> getListeRayons() {
		return listeRayons;
	}

	public void setListeRayons(ArrayList<Rayon> listeRayons) {
		this.listeRayons = listeRayons;
	}

	public ChefMagasin getChefMagasin() {
		return chefMagasin;
	}

	public void setChefMagasin(ChefMagasin chefMagasin) {
		this.chefMagasin = chefMagasin;
	}
	
	
	
	
}
