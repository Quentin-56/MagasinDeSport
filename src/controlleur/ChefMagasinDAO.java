package controlleur;

import modele.ChefMagasin;
import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class ChefMagasinDAO {

    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *  Remplace lancienne objet chef
     * @param chefmagasin nouveau ChefMagasin
     */
    public static void modifierChefMagasin(ChefMagasin chefmagasin, String nom, String prenom, String motDePasse)
    {
        entityManager.getTransaction().begin();

        entityManager.merge(chefmagasin);

        entityManager.getTransaction().commit();
    }

    /**
     * Rempli le chef magasin du magasin
     */
    public ChefMagasin  recupererChefMagasin()
    {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from ChefMagasin");

        ChefMagasin chef= (ChefMagasin) query.getSingleResult();
        entityManager.getTransaction().commit();

        Magasin.setChefMagasin(chef);

        return chef;

    }
}
