package controlleur;

import modele.ChefMagasin;
import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Set;

public class ChefMagasinDAO {

    private EntityManager entityManager;

    //Getter et setter
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public  ChefMagasinDAO()
    {
        entityManager = SetupEM.getEm();
    }

    /**
     *  Remplace lancienne objet chef
     * @param chefmagasin nouveau ChefMagasin
     */
    public void modifierChefMagasin(ChefMagasin chefmagasin)
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
