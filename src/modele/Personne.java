package modele;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersonne;

	private String nom;
	private String prenom;
	private String identifiant;
	private String motDePasse;

	/**
	 * Constructeur de confort
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motDePasse
	 */
	public Personne(String nom, String prenom, String identifiant, String motDePasse) {
		this.idPersonne = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur par defaut
	 */
	public Personne() {
		super();
		this.idPersonne = 0;
		this.nom = "";
		this.prenom = "";
		this.identifiant = "";
		this.motDePasse = "";
	}

	//GETTERS ET SETTERS
	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
