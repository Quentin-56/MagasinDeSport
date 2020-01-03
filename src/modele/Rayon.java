package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rayon {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  int idRayon;

	private String nom;

	@OneToMany(
			mappedBy = "rayonA",
			cascade = CascadeType.ALL
	)
	private List<Article>listeArticles;

	@OneToMany(
			mappedBy = "rayonA",
			cascade = CascadeType.ALL
	)
	private List<Article>listeReservationArticle;

	@OneToMany(
			mappedBy = "rayonV",
			cascade = CascadeType.ALL
	)
	private List<Vendeur> listeVendeurs;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param listeArticles
	 * @param listeReservationArticle
	 * @param listeVendeurs
	 */
	public Rayon(String nom, List<Article> listeArticles, List<Article> listeReservationArticle,
			List<Vendeur> listeVendeurs) {
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
		this.listeArticles = new ArrayList<>();
		this.listeReservationArticle = new ArrayList<>();
		this.listeVendeurs = new ArrayList<>();
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

	public List<Article> getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(List<Article> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public List<Article> getListeReservationArticle() {
		return listeReservationArticle;
	}

	public void setListeReservationArticle(List<Article> listeReservationArticle) {
		this.listeReservationArticle = listeReservationArticle;
	}

	public List<Vendeur> getListeVendeurs() {
		return listeVendeurs;
	}

	public void setListeVendeurs(List<Vendeur> listeVendeurs) {
		this.listeVendeurs = listeVendeurs;
	}
}
