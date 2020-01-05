package modele;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Magasin {

	@Id
	private String nom;

	@Transient
	private static ArrayList<Rayon>listeRayons;

	@OneToOne(mappedBy = "magasin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
		this.nom = "Magasin";
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

	public static ArrayList<Rayon> getListeRayons() {
		return listeRayons;
	}

	public static void setListeRayons(ArrayList<Rayon> listeRayons) {
		Magasin.listeRayons = listeRayons;
	}

	public ChefMagasin getChefMagasin() {
		return chefMagasin;
	}

	public static void setChefMagasin(ChefMagasin chefMagasin) {
		this.chefMagasin = chefMagasin;
	}
	
	
	
	
}
