package controlleur;

import modele.Rayon;

import javax.persistence.EntityManager;

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
        em.persist(rayon);
        em.getTransaction().commit();

    }
}
