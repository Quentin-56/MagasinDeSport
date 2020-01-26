package controlleur;

import modele.Magasin;
import modele.Rayon;
import modele.Vendeur;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;



public class MagasinDAO {

    private EntityManager entityManager;

    //Getter et setter
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * appelle constructeur bien
     * ajout le rayon dans la bdd
     *	apelle ajouterRayonDansLaListeRayon
     * @param nom
     */
    public Rayon creerRayon(String nom)
    {
        Rayon rayon = new Rayon();
        rayon.setNom(nom);

        entityManager.getTransaction().begin();
        //Ajout du rayon dans la bdd
        entityManager.persist(rayon);
        entityManager.getTransaction().commit();

        ajouterRayonDansLaListeRayon(rayon);
        return rayon;
    }

    /**
     * Ajoute le rayon dans la liste des rayons du magasin
     * @param rayon
     */
    public void ajouterRayonDansLaListeRayon(Rayon rayon)
    {
        Magasin.getListeRayons().add(rayon);
    }

    /**
     * Supprime le rayon dans la BDD et actualise la liste des rayons
     * @param idRayonASupprimer
     */
    public void supprimerRayon(int idRayonASupprimer)
    {

        entityManager.getTransaction().begin();

        Rayon rayon = entityManager.find(Rayon.class, idRayonASupprimer);
        entityManager.remove(rayon);

        entityManager.getTransaction().commit();

        supprimerRayonDansLaListeRayon(rayon);
    }

    /**
     * Supprime le rayon dans la liste des rayons du magasin
     * @param rayon
     */
    public void supprimerRayonDansLaListeRayon(Rayon rayon)
    {
        Magasin.getListeRayons().remove(rayon);
    }


    /**
     * Rempli le tableau des rayons du magasin
     */
    public ArrayList<Rayon> recupererRayon()
    {

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Rayon");

        ArrayList<Rayon> listeRayons = (ArrayList<Rayon>) query.getResultList();
        entityManager.getTransaction().commit();

        Magasin.setListeRayons(listeRayons);

        return  listeRayons;
    }

    public void modifierRayon(Rayon rayon)
    {
        entityManager.getTransaction().begin();

        entityManager.merge(rayon);

        entityManager.getTransaction().commit();
    }

    /**
     *
     * @param nomRayon le nom du rayon
     * @return le rayon correspondant au nomRayon
     */
    public Rayon trouverRayonAvecNom(String nomRayon)
    {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from Rayon rayon where rayon.nom = ?1");
        query.setParameter(1, nomRayon);
        Rayon rayon  = (Rayon) query.getSingleResult();

        entityManager.getTransaction().commit();

        return rayon;
    }
}
