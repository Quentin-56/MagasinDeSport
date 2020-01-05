package main;

import controlleur.Connexion;
import controlleur.MagasinControlleur;
import controlleur.SetupEM;
import modele.Magasin;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        new SetupEM();
        Magasin magasin = new Magasin();

        EntityManager em = SetupEM.getEm();
        em.getTransaction().begin();

        em.persist(magasin);

        em.getTransaction().commit();




        MagasinControlleur.creerRayon("Tennis");
    }
}
