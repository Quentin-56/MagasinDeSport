package modele;

import java.util.ArrayList;

public class Rayon {

	private  int idRayon;
	private String nom;
	private ArrayList<Article>listeArticles;
	private ArrayList<Article>listeReservationArticle;
	private ArrayList<Vendeur>listeVendeurs;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param listeArticles
	 * @param listeReservationArticle
	 * @param listeVendeurs
	 */
	public Rayon(String nom, ArrayList<Article> listeArticles, ArrayList<Article> listeReservationArticle,
			ArrayList<Vendeur> listeVendeurs) {
		this.idRayon = 0;
		this.nom = nom;
		this.listeArticles = listeArticles;
		this.listeReservationArticle = listeReservationArticle;
		this.listeVendeurs = listeVendeurs;
	}

	/**
	 * Constructeur par defaut
	 */
	public Rayon() {
		super();
		this.idRayon = 0;
		this.nom = "";
		this.listeArticles = new ArrayList<Article>();
		this.listeReservationArticle = new ArrayList<Article>();
		this.listeVendeurs = new ArrayList<Vendeur>();
	}

	//GETTERS ET SETTERS
	public int getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(int idRayon) {
		this.idRayon = idRayon;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Article> getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(ArrayList<Article> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public ArrayList<Article> getListeReservationArticle() {
		return listeReservationArticle;
	}

	public void setListeReservationArticle(ArrayList<Article> listeReservationArticle) {
		this.listeReservationArticle = listeReservationArticle;
	}

	public ArrayList<Vendeur> getListeVendeurs() {
		return listeVendeurs;
	}

	public void setListeVendeurs(ArrayList<Vendeur> listeVendeurs) {
		this.listeVendeurs = listeVendeurs;
	}
}
