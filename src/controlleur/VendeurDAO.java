package controlleur;

import modele.Article;
import modele.Rayon;
import modele.Vendeur;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VendeurDAO {

    private EntityManager entityManager;

    //Getter et setter
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * appelle constructeur
     * 	ajout le vendeur dans la bdd
     * 	appelle ajouterVendeurDansListeVendeur
     * @param nom
     * @param prenom
     * @param identifiant
     * @param motDePasse
     * @param rayon
     */
    public void creerVendeur(String nom, String prenom, String identifiant, String motDePasse, Rayon rayon)
    {
        Vendeur vendeur =  new Vendeur();
        vendeur.setNom(nom);
        vendeur.setPrenom(prenom);
        vendeur.setIdentifiant(identifiant);
        vendeur.setMotDePasse(motDePasse);
        vendeur.setRayonV(rayon);

        entityManager.getTransaction().begin();
        //Ajout du rayon dans la bdd
        entityManager.persist(vendeur);
        entityManager.getTransaction().commit();

        ajouterVendeurDansListeVendeur(vendeur,rayon);
    }

    /**
     * ajoute vendeur dans la liste des vendeurs du magasin
     * @param vendeur
     * @param rayon
     */
    public void ajouterVendeurDansListeVendeur(Vendeur vendeur, Rayon rayon)
    {
        rayon.getListeVendeurs().add(vendeur);
    }

    /**
     * supprime vendeur dans la liste des vendeurs du magasin
     * @param vendeur
     * @param rayon
     */
    public void supprimerVendeurDansListeVendeur(Vendeur vendeur, Rayon rayon)
    {
        rayon.getListeVendeurs().remove(vendeur);
    }

    /**
     *  supprime de la bdd
     *  appelle supprimerVendeurDansListeVendeur
     * @param idVendeurASupprimer
     * @param rayon
     */
    public void supprimerVendeur(int idVendeurASupprimer, Rayon rayon)
    {

        entityManager.getTransaction().begin();

        Vendeur vendeur = entityManager.find(Vendeur.class, idVendeurASupprimer);
        entityManager.remove(vendeur);

        entityManager.getTransaction().commit();

        supprimerVendeurDansListeVendeur(vendeur, rayon);
    }

    /**
     *
     * @param vendeur
     */
    public void modifierVendeur( Vendeur vendeur)
    {
        entityManager.getTransaction().begin();

        entityManager.merge(vendeur);

        entityManager.getTransaction().commit();
    }

    /**
     * APPELE par barre de recherche, renvoie tableau des vendeurs correspond au prenom,nom, id
     * @param expressionReguliere
     * @return
     */
    /*public ArrayList<Vendeur> rechercherVendeur(String expressionReguliere)
    {

    }*/

    /**
     *
     * @param id
     * @return
     */
    public Vendeur trouverVendeurAvecId(int id)
    {
        entityManager.getTransaction().begin();

        Vendeur vendeur = entityManager.find(Vendeur.class, id);

        entityManager.getTransaction().commit();

        return vendeur;
    }

    /**
     *
     * @param identifiant
     * @return
     */
    public Vendeur trouverVendeurAvecIdentifiant(String identifiant)
    {
        entityManager.getTransaction().begin();

        Query query = SetupEM.getEm().createQuery("from Vendeur vendeur where vendeur.identifiant = ?1");
        Vendeur vendeur  = (Vendeur) query.setParameter(1, identifiant).getSingleResult();

        entityManager.getTransaction().commit();

        return vendeur;
    }

}
