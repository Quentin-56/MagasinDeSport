package controlleur;

import modele.Rayon;
import modele.Vendeur;

import javax.persistence.EntityManager;

public class VendeurDAO {
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

        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();
        //Ajout du rayon dans la bdd
        em.persist(vendeur);
        em.getTransaction().commit();

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
        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();

        Vendeur vendeur = em.find(Vendeur.class, idVendeurASupprimer);
        em.remove(vendeur);

        em.getTransaction().commit();

        supprimerVendeurDansListeVendeur(vendeur, rayon);
    }

    /**
     *
     * @param vendeur
     */
    public void modifierVendeur( Vendeur vendeur)
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();

        em.merge(vendeur);

        em.getTransaction().commit();
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
        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();

        Vendeur vendeur = em.find(Vendeur.class, id);

        em.getTransaction().commit();

        return vendeur;
    }

}
