package modele;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersonne")
public class Vendeur extends Personne{

	@ManyToOne(fetch = FetchType.LAZY)
	private Rayon rayonV;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motDePasse
	 * @param rayonV
	 */
	public Vendeur( String nom, String prenom, String identifiant, String motDePasse, Rayon rayonV) {
		super( nom, prenom, identifiant, motDePasse);
		this.rayonV = rayonV;
	}

	/**
	 * Constructeur par defaut
	 */
	public Vendeur() {
		super();
		this.rayonV =null;
	}


	/**
	 * Constructeur de recopie
	 * @param vendeur
	 */
	public Vendeur(Vendeur vendeur) {
		this.rayonV = vendeur.rayonV;
		/*this.nom = rayon.nom;
		this.listeArticles = rayon.listeArticles;
		this.listeReservationArticle = rayon.listeReservationArticle;
		this.listeVendeurs = rayon.listeVendeurs;
		this.magasin = rayon.magasin;*/
	}

	@Override
	public String toString() {
		return super.toString()+ "Vendeur{" +
				"rayonV=" + rayonV +
				'}';
	}

	//GETTERS ET SETTERS
	public Rayon getRayonV() {
		return rayonV;
	}

	public void setRayonV(Rayon rayonV) {
		this.rayonV = rayonV;
	}
}
