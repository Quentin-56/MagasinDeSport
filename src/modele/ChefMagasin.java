package modele;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersonne")
public class ChefMagasin extends Personne{

	@OneToOne(fetch = FetchType.LAZY)
	private Magasin magasin;

	/**
	 * Constructeur par defaut
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motDePasse
	 * @param magasin
	 */
	public ChefMagasin( String nom, String prenom, String identifiant, String motDePasse,
			Magasin magasin) {
		super( nom, prenom, identifiant, motDePasse);
		this.magasin = magasin;
	}

	/**
	 * Constructeur par defaut
	 */
	public ChefMagasin() {
		super();
		this.magasin = null;
	}

	//GETTERS ET SETTERS
	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

}
