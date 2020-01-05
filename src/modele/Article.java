package modele;

import javax.persistence.*;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idArticle;

	private String nom;
	private int quantite;
	private String details;
	private int quantiteReserve;

	@ManyToOne(fetch = FetchType.LAZY)
	private Rayon rayonA;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param quantite
	 * @param details
	 * @param rayonA
	 */
	public Article(String nom, int quantite, String details, Rayon rayonA) {
		this.idArticle = 0;
		this.nom = nom;
		this.quantite = quantite;
		this.details = details;
		this.quantiteReserve = 0;
		this.rayonA = rayonA;
	}

	/**
	 * Constructeur par defaut
	 */
	public Article() {
		this.nom = "";
		this.quantite = 0;
		this.details = "";
		this.quantiteReserve = 0;
		this.rayonA = null;
		this.idArticle = 0;
	}

	@Override
	public String toString() {
		return "Article{" +
				"idArticle=" + idArticle +
				", nom='" + nom + '\'' +
				", quantite=" + quantite +
				'}';
	}

	//GETTERS ET SETTERS
	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getQuantiteReserve() {
		return quantiteReserve;
	}

	public void setQuantiteReserve(int quantiteReserve) {
		this.quantiteReserve = quantiteReserve;
	}

	public Rayon getRayonA() {
		return rayonA;
	}

	public void setRayonA(Rayon rayonA) {
		this.rayonA = rayonA;
	}
}
