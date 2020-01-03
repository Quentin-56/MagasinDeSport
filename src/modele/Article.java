package modele;

public class Article {

	private int idArticle;
	private String nom;
	private int quantite;
	private String details;
	private int quantiteReserve;
	private Rayon rayon;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param quantite
	 * @param details
	 * @param quantiteReserve
	 * @param rayon
	 */
	public Article(String nom, int quantite, String details, int quantiteReserve, Rayon rayon) {
		this.idArticle = 0;
		this.nom = nom;
		this.quantite = quantite;
		this.details = details;
		this.quantiteReserve = quantiteReserve;
		this.rayon = rayon;
	}

	/**
	 * Constructeur par defaut
	 */
	public Article() {
		this.nom = "";
		this.quantite = 0;
		this.details = "";
		this.quantiteReserve = 0;
		this.rayon = null;
		this.idArticle = 0;
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

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
}
