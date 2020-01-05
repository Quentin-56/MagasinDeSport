package main;

import controlleur.Connexion;
import controlleur.MagasinControlleur;
import controlleur.SetupEM;
import modele.Magasin;
import modele.Rayon;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        new SetupEM();
        Magasin magasin = new Magasin();
/*
        EntityManager em = SetupEM.getEm();
        em.getTransaction().begin();

        em.persist(magasin);

        em.getTransaction().commit();

        Rayon r = new Rayon();
        r.setNom("Football");
        r.setIdRayon(1);*/

        //MagasinControlleur.supprimerRayon(2);
        MagasinControlleur.recupererRayon();
        System.out.println(Magasin.getListeRayons());
        //MagasinControlleur.creerRayon("Tennis");
    }
}
