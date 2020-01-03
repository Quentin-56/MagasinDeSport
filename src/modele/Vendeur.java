package modele;

public class Vendeur extends Personne{

	private Rayon rayon;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motDePasse
	 * @param rayon
	 */
	public Vendeur( String nom, String prenom, String identifiant, String motDePasse, Rayon rayon) {
		super( nom, prenom, identifiant, motDePasse);
		this.rayon = rayon;
	}

	/**
	 * Constructeur par defaut
	 */
	public Vendeur() {
		super();
		this.rayon =null;
	}

	//GETTERS ET SETTERS
	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}
	
	
	
}
