package controlleur;

import modele.ChefMagasin;
import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class ChefMagasinDAO {
    /**
     *  Remplace lancienne objet chef
     * @param chefmagasin nouveau ChefMagasin
     */
    public static void modifierChefMagasin(ChefMagasin chefmagasin, String nom, String prenom, String motDePasse)
    {
        EntityManager em =SetupEM.getEm();

        chefmagasin.setNom(nom);
        chefmagasin.setPrenom(prenom);
        chefmagasin.setMotDePasse(motDePasse);

        em.getTransaction().begin();

        //em.merge(chefmagasin);
        em.persist(chefmagasin);
        em.getTransaction().commit();
    }

    /**
     * Rempli le chef magasin du magasin
     */
    public static /*void*/ChefMagasin recupererChefMagasin()
    {
        EntityManager em =SetupEM.getEm();

        em.getTransaction().begin();
        Query query = em.createQuery("from ChefMagasin");

        ChefMagasin chef= (ChefMagasin) query.getSingleResult();
        em.getTransaction().commit();

        Magasin.setChefMagasin(chef);

        return chef;

    }
}
