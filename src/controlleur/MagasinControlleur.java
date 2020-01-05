package controlleur;

import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class MagasinControlleur {
    /**
     * appelle constructeur bien
     * ajout le rayon dans la bdd
     *	apelle ajouterRayonDansLaListeRayon
     * @param nom
     */
    public static void creerRayon(String nom)
    {
        Rayon rayon = new Rayon();
        rayon.setNom(nom);

        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();
        //Ajout du rayon dans la bdd
        em.persist(rayon);
        em.getTransaction().commit();

        ajouterRayonDansLaListeRayon(rayon);
    }

    /**
     * Ajoute le rayon dans la liste des rayons du magasin
     * @param rayon
     */
    public static void ajouterRayonDansLaListeRayon(Rayon rayon)
    {
        Magasin.getListeRayons().add(rayon);
    }

    /**
     * Supprime le rayon dans la BDD et actualise la liste des rayons
     * @param idRayonASupprimer
     */
    public static void supprimerRayon(int idRayonASupprimer)
    {
        EntityManager em =SetupEM.getEm();
        em.getTransaction().begin();

        Rayon rayon = em.find(Rayon.class, idRayonASupprimer);
        em.remove(rayon);

        em.getTransaction().commit();

        supprimerRayonDansLaListeRayon(rayon);
    }

    /**
     * Supprime le rayon dans la liste des rayons du magasin
     * @param rayon
     */
    public static void supprimerRayonDansLaListeRayon(Rayon rayon)
    {
        Magasin.getListeRayons().remove(rayon);
    }


    /**
     * Rempli le tableau des rayons du magasin
     */
    public static void recupererRayon()
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();
        Query query = em.createQuery("from rayon");

        ArrayList<Rayon> listeRayons = (ArrayList<Rayon>) query.getResultList();
        em.getTransaction().commit();

        Magasin.setListeRayons(listeRayons);
    }

    public static void modifierRayon(Rayon rayon)
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();

        em.merge(rayon);

        em.getTransaction().commit();
    }
}
